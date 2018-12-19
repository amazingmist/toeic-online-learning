package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.service.ListenGuidelineService;
import vn.myclass.core.service.impl.ListenGuidelineServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.RequestUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {
    ListenGuidelineService listenGuidelineService = new ListenGuidelineServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ListenGuidelineCommand command = new ListenGuidelineCommand();

        command.setMaxPageItems(2);
        RequestUtil.initSearchBean(req, command);

        Object[] finded = listenGuidelineService.findListenGuidelineByProperty(null, null, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());

        command.setListResult((List<ListenGuidelineDTO>) finded[1]);
        command.setTotalItems(Integer.parseInt(finded[0].toString()));

        req.setAttribute(WebConstant.LIST_ITEMS, command);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
