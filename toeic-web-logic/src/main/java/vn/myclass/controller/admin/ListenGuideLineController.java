package vn.myclass.controller.admin;

import vn.myclass.command.ListenGuideLineCommand;
import vn.myclass.core.dto.ListenGuideLineDTO;
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
public class ListenGuideLineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListenGuideLineDTO> listenGuideLineDTOList = new ArrayList<ListenGuideLineDTO>();
        ListenGuideLineDTO guideLineDTO1 = new ListenGuideLineDTO();
        guideLineDTO1.setTitle("Bai huong dan nghe 1");
        guideLineDTO1.setContent("Noi dung bai huong dan nghe 1");

        ListenGuideLineDTO guideLineDTO2 = new ListenGuideLineDTO();
        guideLineDTO2.setTitle("Bai huong dan nghe 2");
        guideLineDTO2.setContent("Noi dung bai huong dan nghe 2");

        ListenGuideLineDTO guideLineDTO3 = new ListenGuideLineDTO();
        guideLineDTO3.setTitle("Bai huong dan nghe 3");
        guideLineDTO3.setContent("Noi dung bai huong dan nghe 3");

        ListenGuideLineDTO guideLineDTO4 = new ListenGuideLineDTO();
        guideLineDTO4.setTitle("Bai huong dan nghe 4");
        guideLineDTO4.setContent("Noi dung bai huong dan nghe 4");

        listenGuideLineDTOList.add(guideLineDTO1);
        listenGuideLineDTOList.add(guideLineDTO2);
        listenGuideLineDTOList.add(guideLineDTO3);
        listenGuideLineDTOList.add(guideLineDTO4);

        ListenGuideLineCommand command = new ListenGuideLineCommand();
        command.setMaxPageItems(2);
        command.setTotalItems(listenGuideLineDTOList.size());
        command.setListResult(listenGuideLineDTOList);

        req.setAttribute(WebConstant.LIST_ITEMS, command);

        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/listenguideline/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
