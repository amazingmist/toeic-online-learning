package vn.myclass.controller.admin;

import org.apache.log4j.Logger;
import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.common.util.FileUploadUtil;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.util.FormUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

//@WebServlet("/admin-guideline-listen-list.html")
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

        /*command.setMaxPageItems(2);
        RequestUtil.initSearchBean(req, command);

        Object[] finded = listenGuidelineService.findListenGuidelineByProperty(null, null, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());

        command.setListResult((List<ListenGuidelineDTO>) finded[1]);
        command.setTotalItems(Integer.parseInt(finded[0].toString()));*/

        req.setAttribute(WebConstant.LIST_ITEMS, command);
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)) {
            req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
        } else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)) {
            req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
        }
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
        for (Map.Entry<String, String> entry : returnValueMap.entrySet()) {
            if (entry.getKey().equals("pojo.title")) {
                command.getPojo().setTitle(entry.getValue());
            } else if (entry.getKey().equals("pojo.content")) {
                command.getPojo().setContent(entry.getValue());
            }
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
