package vn.myclass.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vn.myclass.command.UserCommand;
import vn.myclass.core.common.util.ExcelPoiUtil;
import vn.myclass.core.common.util.FileUploadUtil;
import vn.myclass.core.common.util.SessionUtil;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.dto.UserImportDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.util.FormUtil;
import vn.myclass.core.web.util.RequestUtil;
import vn.myclass.core.web.util.SingletonServiceUtil;
import vn.myclass.core.web.util.WebCommonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html", "/admin-user-import.html",
        "/admin-user-import-validate.html"})
public class UserController extends HttpServlet {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundle");
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String SESSION_USER_IMPORT_LIST = "user_import_list";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            UserCommand command = FormUtil.populate(UserCommand.class, req);
            RequestUtil.initSearchBean(req, command);

            UserDTO pojo = command.getPojo();
            if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                Map<String, Object> properties = new HashMap<>();
                Object[] objects = SingletonServiceUtil.getUserServiceInstance().findByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
                command.setListResult((List<UserDTO>) objects[1]);
                command.setTotalItems(Integer.parseInt(objects[0].toString()));

                req.setAttribute(WebConstant.LIST_ITEMS, command);

//            set crudAction response message
                if (command.getCrudAction() != null) {
                    Map<String, String> messageMap = WebCommonUtil.buildCrudMessageMap(resourceBundle);
                    WebCommonUtil.addRedirectMessage(req, command.getCrudAction(), messageMap);
                }

                req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
            } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getPojo() != null && command.getPojo().getUserId() != null) {
//            check if is existed user id and then we get this pojo from db
                    pojo = SingletonServiceUtil.getUserServiceInstance().findById(pojo.getUserId());
                    command.setPojo(pojo);
                }

//            set all role to user command
                command.setRoles(SingletonServiceUtil.getRoleServiceInstance().findAll());
                req.setAttribute(WebConstant.FORM_ITEM, command);
                req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);

            } else if (command.getUrlType().equals(WebConstant.URL_IMPORT)) {
                req.getRequestDispatcher("/views/admin/user/importUser.jsp").forward(req, resp);

            } else if (command.getUrlType().equals(WebConstant.URL_SHOW_VALIDATE_IMPORT)) {
                List<UserImportDTO> excelValueList = (List<UserImportDTO>) SessionUtil.getInstance().getAttribute(req, this.SESSION_USER_IMPORT_LIST);
                command.setMaxPageItems(3);
                command.setUserImportDTOS(excelValueList);
                command.setTotalItems(excelValueList.size());
                req.setAttribute(WebConstant.LIST_ITEMS, command);
                req.getRequestDispatcher("/views/admin/user/importUser.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            resp.sendRedirect("/admin-user-list.html?urlType=url_list&crudAction=redirect_error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            UserCommand command = FormUtil.populate(UserCommand.class, req);
            UserDTO pojo = command.getPojo();

            if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getCrudAction().equals(WebConstant.CRUD_ACTION_UPDATE)) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());

                    pojo.setRoleDTO(roleDTO);

                    if (pojo.getUserId() != null) {
//                    update user
                        SingletonServiceUtil.getUserServiceInstance().update(pojo);
                        req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);

                    } else {
//                    insert user
                        SingletonServiceUtil.getUserServiceInstance().save(pojo);
                        req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }
                req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);

            } else if (command.getUrlType().equals(WebConstant.URL_VALIDATE_IMPORT)) {
                FileUploadUtil fileUploadUtil = new FileUploadUtil();
                fileUploadUtil.setUploadFileIsExcel();

                Map<String, String> fileInfo = fileUploadUtil.writeOrUpdateFile(req, WebConstant.UPLOAD_PART_NAME, WebConstant.UPLOAD_EXCEL_PATH);
                String fileLocation = fileInfo.get("fileLocation");

                List<UserImportDTO> excelDataList = getExcelValueList(fileLocation);
                validateExcelData(excelDataList);
                SessionUtil.getInstance().putAttribute(req, this.SESSION_USER_IMPORT_LIST, excelDataList);
                resp.sendRedirect("/admin-user-import-validate.html?urlType=" + WebConstant.URL_SHOW_VALIDATE_IMPORT);

            } else if (command.getUrlType().equals(WebConstant.URL_IMPORT)) {
                List<UserImportDTO> excelValueList = (List<UserImportDTO>) SessionUtil.getInstance().getAttribute(req, this.SESSION_USER_IMPORT_LIST);
                SingletonServiceUtil.getUserServiceInstance().saveImportUsers(excelValueList);
                SessionUtil.getInstance().removeAttribute(req, this.SESSION_USER_IMPORT_LIST);
                resp.sendRedirect("/admin-user-list.html?urlType=url_list&crudAction=redirect_insert");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
        }
    }

    private void validateExcelData(List<UserImportDTO> excelDataList) {
        Set<String> existedValueSet = new HashSet<>();

        excelDataList.forEach(item -> {
            checkRequiredField(item);

            if (item.isValid()) {
                checkDuplicateField(item, existedValueSet);
            }
        });

        SingletonServiceUtil.getUserServiceInstance().validateImportUsers(excelDataList);
    }

    private void checkDuplicateField(UserImportDTO item, Set<String> existedValueSet) {
        String msg = item.getError();

        if (existedValueSet.contains(item.getName())) {
            if (!msg.equals("")) {
                msg += "<br/>";
            }
            msg += resourceBundle.getString("label.username.duplicate");
        } else {
            existedValueSet.add(item.getName());
        }

        if (!msg.equals("")) {
            item.setValid(false);
            item.setError(msg);
        }
    }

    private void checkRequiredField(UserImportDTO item) {
        String msg = item.getError();
        if (StringUtils.isBlank(item.getName())) {
            msg += resourceBundle.getString("label.username.notempty");
        }

        if (StringUtils.isBlank(item.getPassword())) {
            if (!msg.equals("")) {
                msg += "<br/>";
            }
            msg += resourceBundle.getString("label.password.notempty");
        }

        if (StringUtils.isBlank(item.getRoleName())) {
            if (!msg.equals("")) {
                msg += "<br/>";
            }
            msg += resourceBundle.getString("label.rolename.notempty");
        }

        if (!msg.equals("")) {
            item.setValid(false);
            item.setError(msg);
        }
    }

    private List<UserImportDTO> getExcelValueList(String fileLocation) throws IOException {
        List<UserImportDTO> excelDataList = new ArrayList<>();

        Workbook workbook = ExcelPoiUtil.getWorkBook(fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            UserImportDTO userImportDTO = convertExcelRowToUserImportDTO(sheet.getRow(i));
            excelDataList.add(userImportDTO);
        }
        workbook.close();

        return excelDataList;
    }

    private UserImportDTO convertExcelRowToUserImportDTO(Row row) {
        UserImportDTO userImportDTO = new UserImportDTO();
        userImportDTO.setName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(1)));
        userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(2)));
        userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));
        return userImportDTO;
    }
}
