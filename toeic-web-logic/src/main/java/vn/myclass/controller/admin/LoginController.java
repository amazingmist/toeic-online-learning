package vn.myclass.controller.admin;

import org.apache.log4j.Logger;
import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.UserService;
import vn.myclass.core.service.impl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.html")
public class LoginController extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, req);
        UserDTO pojo = command.getPojo();
        UserService userService = new UserServiceImpl();

        try {
            RoleDTO roleDTO = userService.findRoleByUser(pojo).getRoleDTO();
            if (roleDTO.getName().equals(WebConstant.ROLE_ADMIN)) {
                resp.sendRedirect("/admin-home.html");
            } else if (roleDTO.getName().equals(WebConstant.ROLE_USER)) {
                resp.sendRedirect("/home.html");
            }
        } catch (NullPointerException e) {
            logger.error(e.getMessage(), e);
            req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            req.setAttribute(WebConstant.MESSAGE_RESPONSE, "Username or password is invalid");
            req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
        }
    }
}
