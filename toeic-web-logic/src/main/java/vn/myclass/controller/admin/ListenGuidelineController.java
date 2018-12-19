package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.common.utils.UploadUtil;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.service.ListenGuidelineService;
import vn.myclass.core.service.impl.ListenGuidelineServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    ListenGuidelineService listenGuidelineService = new ListenGuidelineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null){
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
        if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_LIST)){
            req.getRequestDispatcher("/views/admin/listenguideline/list.jsp").forward(req, resp);
        }else if (command.getUrlType() != null && command.getUrlType().equals(WebConstant.URL_EDIT)){
            req.getRequestDispatcher("/views/admin/listenguideline/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationRescources");
        UploadUtil uploadUtil = new UploadUtil();
        try{
            uploadUtil.writeOrUpdateFile(req);
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_SUCCESS);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.success"));
        }catch (Exception ex){
            session.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            session.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.guideline.listen.add.fail"));
        }

        resp.sendRedirect("/admin-guideline-listen-edit.html?urlType=url_list");
    }
}
