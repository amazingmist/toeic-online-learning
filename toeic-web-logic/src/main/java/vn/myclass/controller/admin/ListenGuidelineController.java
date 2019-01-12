package vn.myclass.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);
        Map<String, String> messageMap = buildMessageMap(resourceBundle);

        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            if (command.getCrudAction() != null && command.getCrudAction().equals(WebConstant.REDIRECT_DELETE)){
                List<Integer> idList = new ArrayList<>();
                try {
                    for (String id : command.getCheckList()) {
                        idList.add(Integer.parseInt(id));
                    }
                    SingletonServiceUtil.getListenGuidelineServiceInstance().delete(idList);
                }catch (Exception ex){
                    logger.error(ex.getMessage(), ex);
                    command.setCrudAction(WebConstant.REDIRECT_ERROR);
                }
            }

            executeSearch(req, command);

//            set crudAction response message
            if (command.getCrudAction() != null) {
                WebCommonUtil.addRedirectMessage(req, command.getCrudAction(), messageMap);
            }

            req.setAttribute(WebConstant.LIST_ITEMS, command);
            req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);

        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
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
    }

    private Map<String, String> buildMessageMap(ResourceBundle resourceBundle) {
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put(WebConstant.REDIRECT_INSERT, resourceBundle.getString("label.add.success"));
        messageMap.put(WebConstant.REDIRECT_UPDATE, resourceBundle.getString("label.update.success"));
        messageMap.put(WebConstant.REDIRECT_DELETE, resourceBundle.getString("label.delete.success"));
        messageMap.put(WebConstant.REDIRECT_ERROR, resourceBundle.getString("label.message.error"));
        return messageMap;
    }

    private void executeSearch(HttpServletRequest req, ListenGuidelineCommand command) {
        RequestUtil.initSearchBean(req, command);
        Map<String, Object> propertiesMap = buildPropertiesMap(command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findByProperties(propertiesMap, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
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
        ListenGuidelineCommand command = new ListenGuidelineCommand();
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        Set<String> titleValueSet = buildTitleValueSet();

        Object[] objects = fileUploadUtil.writeOrUpdateFile(req, titleValueSet, WebConstant.LISTEN_GUIDELINE_IMAGE_URL);

        boolean isSuccess = (boolean) objects[0];
        if (!isSuccess) {
            resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
        } else {
            Map<String, String> returnedValueMap = (Map<String, String>) objects[3];
            mappingReturnedValueMapToCommand(returnedValueMap, command);

//            set image for dto
            String imageName = objects[2].toString();
            if (StringUtils.isNotBlank(imageName)) {
                command.getPojo().setImage(imageName);
            }

            if (command.getPojo().getListenGuideLineId() != null) {
//                update listenguideline
                ListenGuidelineDTO listenGuidelineDTO = SingletonServiceUtil.getListenGuidelineServiceInstance().findById(command.getPojo().getListenGuideLineId());

                if (command.getPojo().getImage() == null){
                    command.getPojo().setImage(listenGuidelineDTO.getImage());
                }

                command.getPojo().setCreatedDate(listenGuidelineDTO.getCreatedDate());

                ListenGuidelineDTO result = SingletonServiceUtil.getListenGuidelineServiceInstance().update(command.getPojo());

                if (result != null){
                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_update");
                }else{
                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
                }

            } else {
//                insert listenguideline
                try {
                    SingletonServiceUtil.getListenGuidelineServiceInstance().save(command.getPojo());
                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_insert");
                }catch (ConstraintViolationException ex){
                    logger.error(ex.getMessage(), ex);
                    resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list&crudAction=redirect_error");
                }
            }

        }
    }

    private ListenGuidelineCommand mappingReturnedValueMapToCommand(Map<String, String> returnValueMap, ListenGuidelineCommand command) {
//        mapping all returned value in map to command
        if (returnValueMap.containsKey("pojo.listenGuideLineId")) {
            command.getPojo().setListenGuideLineId(Integer.parseInt(returnValueMap.get("pojo.listenGuideLineId")));
        }

        if (returnValueMap.containsKey("pojo.title")) {
            command.getPojo().setTitle(returnValueMap.get("pojo.title"));
        }

        if (returnValueMap.containsKey("pojo.content")) {
            command.getPojo().setContent(returnValueMap.get("pojo.content"));
        }
        return command;
    }

    private Set<String> buildTitleValueSet() {
        Set<String> titleValueSet = new HashSet<>();
        titleValueSet.add("pojo.listenGuideLineId");
        titleValueSet.add("pojo.title");
        titleValueSet.add("pojo.content");
        return titleValueSet;
    }
}
