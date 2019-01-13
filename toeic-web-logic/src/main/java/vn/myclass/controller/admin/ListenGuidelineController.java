package vn.myclass.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.common.util.FileUploadUtil;
import vn.myclass.core.dto.ListenGuidelineDTO;
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

@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("ResourceBundle");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
            if (command.getUrlType().equals(WebConstant.URL_LIST)) {
                executeSearch(req, command);

//            set crudAction response message
                if (command.getCrudAction() != null) {
                    Map<String, String> messageMap = WebCommonUtil.buildCrudMessageMap(resourceBundle);
                    WebCommonUtil.addRedirectMessage(req, command.getCrudAction(), messageMap);
                }

                req.setAttribute(WebConstant.LIST_ITEMS, command);
                req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);

            } else if (command.getUrlType().equals(WebConstant.URL_EDIT)) {
                if (command.getPojo().getListenGuideLineId() != null) {
                    Integer listenGuidelineId = command.getPojo().getListenGuideLineId();
                    ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance().findById(listenGuidelineId);

                    if (listenGuidelineDTO != null) {
                        command.setPojo(listenGuidelineDTO);
                    }
                }
                req.setAttribute(WebConstant.FORM_ITEM, command);
                req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
        }

    }

    private void executeSearch(HttpServletRequest req, ListenGuidelineCommand command) {
        RequestUtil.initSearchBean(req, command);
        Map<String, Object> propertiesMap = buildPropertiesMap(command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findApproximateByProperties(propertiesMap, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<ListenGuidelineDTO>) objects[1]);
        command.setTotalItems(Integer.parseInt(objects[0].toString()));
    }

    private Map<String, Object> buildPropertiesMap(ListenGuidelineCommand command) {
        Map<String, Object> propertiesMap = new HashMap<>();
        if (StringUtils.isNotBlank(command.getPojo().getTitle())) {
            propertiesMap.put("title", command.getPojo().getTitle());
        }
        return propertiesMap;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
            FileUploadUtil fileUploadUtil = new FileUploadUtil();

            ListenGuidelineDTO pojo = command.getPojo();

            if (command.getCrudAction().equals(WebConstant.CRUD_ACTION_UPDATE)) {
                Map<String, String> fileInfoMap = fileUploadUtil.writeOrUpdateFile(req, WebConstant.UPLOAD_PART_NAME, WebConstant.LISTEN_GUIDELINE_IMAGE_URL);
                if (StringUtils.isNotBlank(fileInfoMap.get("fileName"))) {
                    pojo.setImage(fileInfoMap.get("parentAndFileName"));
                }

                if (pojo.getListenGuideLineId() != null) {
//                update listenguideline
                    ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance().findById(command.getPojo().getListenGuideLineId());

                    if (pojo.getImage() == null) {
                        pojo.setImage(listenGuidelineDTO.getImage());
                    }

                    pojo.setCreatedDate(listenGuidelineDTO.getCreatedDate());

                    SingletonServiceUtil.getListenGuidelineServiceInstance().update(pojo);

                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_update");
                } else {
//                insert listenguideline
                    SingletonServiceUtil.getListenGuidelineServiceInstance().save(pojo);
                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_insert");
                }
            } else if (command.getCrudAction().equals(WebConstant.CRUD_ACTION_DELETE)) {
                List<Integer> idList = new ArrayList<>();
                for (String id : command.getCheckList()) {
                    idList.add(Integer.parseInt(id));
                }
                SingletonServiceUtil.getListenGuidelineServiceInstance().delete(idList);
                resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_delete");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
        }
    }
}
