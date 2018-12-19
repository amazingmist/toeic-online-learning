package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuidelineCommand;
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
import java.io.IOException;
import java.util.List;

//@WebServlet("/admin-guideline-listen-list.html")
@WebServlet(urlPatterns = {"/admin-guideline-listen-list.html","/admin-guideline-listen-edit.html"})
public class ListenGuidelineController extends HttpServlet {
    ListenGuidelineService listenGuidelineService = new ListenGuidelineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        super.doPost(req, resp);
    }
}
