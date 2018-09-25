package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuidelineCommand;
import vn.myclass.core.dto.ListenGuidelineDTO;
import vn.myclass.core.web.common.WebConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin-guideline-listen-list.html")
public class ListenGuidelineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListenGuidelineDTO> listenGuidelineDTOList = new ArrayList<ListenGuidelineDTO>();
        ListenGuidelineDTO guideLineDTO1 = new ListenGuidelineDTO();
        guideLineDTO1.setTitle("Bai huong dan nghe 1");
        guideLineDTO1.setContent("Noi dung bai huong dan nghe 1");

        ListenGuidelineDTO guideLineDTO2 = new ListenGuidelineDTO();
        guideLineDTO2.setTitle("Bai huong dan nghe 2");
        guideLineDTO2.setContent("Noi dung bai huong dan nghe 2");

        ListenGuidelineDTO guideLineDTO3 = new ListenGuidelineDTO();
        guideLineDTO3.setTitle("Bai huong dan nghe 3");
        guideLineDTO3.setContent("Noi dung bai huong dan nghe 3");

        ListenGuidelineDTO guideLineDTO4 = new ListenGuidelineDTO();
        guideLineDTO4.setTitle("Bai huong dan nghe 4");
        guideLineDTO4.setContent("Noi dung bai huong dan nghe 4");

        listenGuidelineDTOList.add(guideLineDTO1);
        listenGuidelineDTOList.add(guideLineDTO2);
        listenGuidelineDTOList.add(guideLineDTO3);
        listenGuidelineDTOList.add(guideLineDTO4);

        ListenGuidelineCommand command = new ListenGuidelineCommand();
        command.setMaxPageItems(2);
        command.setTotalItems(listenGuidelineDTOList.size());
        command.setListResult(listenGuidelineDTOList);

        req.setAttribute(WebConstant.LIST_ITEMS, command);

        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
