package vn.myclass.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.common.util.FileUploadUtil;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.util.FormUtil;
import vn.myclass.core.web.util.RequestUtil;
import vn.myclass.core.web.util.SingletonServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html", "/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
//            get session from post method and then set them to request
            req.setAttribute(WebConstant.ALERT, session.getAttribute(WebConstant.ALERT));
            req.setAttribute(WebConstant.MESSAGE_RESPONSE, session.getAttribute(WebConstant.MESSAGE_RESPONSE));

//            remove used session
            session.removeAttribute(WebConstant.ALERT);
            session.removeAttribute(WebConstant.MESSAGE_RESPONSE);
        }

        ListenGuidelineCommand command = FormUtil.populate(ListenGuidelineCommand.class, req);

        command.setMaxPageItems(4);
        executeSearch(req, command);

        req.setAttribute(WebConstant.LIST_ITEMS, command);
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
        }
    }

    private void executeSearch(HttpServletRequest req, ListenGuidelineCommand command) {
        RequestUtil.initSearchBean(req, command);
        Map<String, Object> propertiesMap = buildPropertiesMap(command);
        Object[] objects = SingletonServiceUtil.getListenGuidelineServiceInstance().findListenGuidelineByProperty(propertiesMap, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
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
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        Set<String> titleValueSet = buildTitleValueSet();

        try {
            Object[] returnedObjects = fileUploadUtil.writeOrUpdateFile(req, titleValueSet, WebConstant.LISTEN_GUIDELINE_IMAGE_URL);
            Map<String, String> returnValueMap = (Map<String, String>) returnedObjects[3];
            mappingReturnValueMapToCommand(returnValueMap, command);

            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.fail"));
        }

        resp.sendRedirect("/admin-guideline-listen-list.html?urlType=url_list");
    }

    private ListenGuidelineCommand mappingReturnValueMapToCommand(Map<String, String> returnValueMap, ListenGuidelineCommand command) {
//        mapping all returned value in map to command
        if (returnValueMap.containsKey("pojo.title")) {
            command.getPojo().setTitle(returnValueMap.get("pojo.title"));
        } else if (returnValueMap.containsKey("pojo.content")) {
            command.getPojo().setContent(returnValueMap.get("pojo.content"));
        }
        return command;
    }

    private Set<String> buildTitleValueSet() {
        Set<String> titleValueSet = new HashSet<>();
        titleValueSet.add("pojo.title");
        titleValueSet.add("pojo.content");
        return titleValueSet;
    }
}
