package vn.myclass.controller.admin;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import vn.myclass.command.UserCommand;
import vn.myclass.core.common.util.ExcelPoiUtil;
import vn.myclass.core.common.util.FileUploadUtil;
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
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String SHOW_LIST_ERROR = "show_list_error";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, req);
        RequestUtil.initSearchBean(req, command);

        UserDTO dto = command.getPojo();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> properties = new HashMap<>();
            Object[] finded = SingletonServiceUtil.getUserServiceInstance().findByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) finded[1]);
            command.setTotalItems(Integer.parseInt(finded[0].toString()));

            req.setAttribute(WebConstant.LIST_ITEMS, command);

//            set crudAction response message
            if (command.getCrudAction() != null) {
                Map<String, String> messageMap = buildMessageMap(resourceBundle);
                WebCommonUtil.addRedirectMessage(req, command.getCrudAction(), messageMap);
            }

            req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            if (command.getPojo() != null && command.getPojo().getUserId() != null) {
//            check if is existed user id and then we get this pojo from db
                dto = SingletonServiceUtil.getUserServiceInstance().findById(dto.getUserId());
                command.setPojo(dto);
            }

//            set all role to user command
            command.setRoles(SingletonServiceUtil.getRoleServiceInstance().findAll());
            req.setAttribute(WebConstant.FORM_ITEM, command);
            req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
        }else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_IMPORT)) {
            req.getRequestDispatcher("/views/admin/user/importUser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<String> titleValueSet = new HashSet<>();
        titleValueSet.add("urlType");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        Object[] objects = fileUploadUtil.writeOrUpdateFile(req, titleValueSet, "excel");
        Map<String, String> returnValueMap = (Map<String, String>) objects[3];
        String urlType = returnValueMap.get("urlType");
        try {
            UserCommand command = FormUtil.populate(UserCommand.class, req);
            UserDTO pojo = command.getPojo();

            if (urlType != null && urlType.equals(WebConstant.URL_EDIT)) {
                if (command.getCrudAction() != null && command.getCrudAction().equals(WebConstant.INSERT_UPDATE_ACTION)) {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleId(command.getRoleId());

                    pojo.setRoleDTO(roleDTO);

                    if (pojo.getUserId() != null) {
//                    update user
                        pojo = SingletonServiceUtil.getUserServiceInstance().updateUser(pojo);
                        req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_UPDATE);

                    } else {
//                    insert user
                        SingletonServiceUtil.getUserServiceInstance().saveUser(pojo);
                        req.setAttribute(WebConstant.MESSAGE_RESPONSE, WebConstant.REDIRECT_INSERT);
                    }
                }
                req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);

            }else if (urlType != null && urlType.equals(WebConstant.URL_IMPORT)) {
                String fileLocation = (String) objects[1];
                List<UserImportDTO> excelValueList = getExcelValueList(fileLocation);
                resp.sendRedirect("/admin-user-import-validate.html?urlType=show_list_error");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            req.setAttribute(WebConstant.MESSAGE_RESPONSE, ex.getMessage());
            req.getRequestDispatcher("/views/admin/error.jsp").forward(req, resp);
        }
    }

    private List<UserImportDTO> getExcelValueList(String fileLocation) throws IOException {
        List<UserImportDTO> excelValueList = new ArrayList<>();

        Workbook workbook = ExcelPoiUtil.getWorkBook(fileLocation);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            UserImportDTO userImportDTO = convertExcelRowToUserImportDTO(sheet.getRow(i));
            excelValueList.add(userImportDTO);
        }
        workbook.close();

        return excelValueList;
    }

    private UserImportDTO convertExcelRowToUserImportDTO(Row row) {
        UserImportDTO userImportDTO = new UserImportDTO();
        userImportDTO.setName(ExcelPoiUtil.getCellValue(row.getCell(0)));
        userImportDTO.setFullName(ExcelPoiUtil.getCellValue(row.getCell(1)));
        userImportDTO.setPassword(ExcelPoiUtil.getCellValue(row.getCell(2)));
        userImportDTO.setRoleName(ExcelPoiUtil.getCellValue(row.getCell(3)));
        return userImportDTO;
    }

    private Map<String, String> buildMessageMap(ResourceBundle resourceBundle) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.user.add.success"));
        messageMap.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.user.update.success"));
        messageMap.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.user.delete.success"));
        messageMap.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return messageMap;
    }

}
