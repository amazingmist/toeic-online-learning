package vn.myclass.controller.admin;

import org.apache.log4j.Logger;
import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.util.FormUtil;
import vn.myclass.core.web.util.SingletonServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

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
        try {
            UserDTO userDTO = SingletonServiceUtil.getUserServiceInstance().findByNameAndPassword(pojo.getName(), pojo.getPassword());
            if (userDTO.getRoleDTO().getName().equals(WebConstant.ROLE_ADMIN)) {
                resp.sendRedirect("/admin-home.html");
            } else if (userDTO.getRoleDTO().getName().equals(WebConstant.ROLE_USER)) {
                resp.sendRedirect("/home.html");
            }
        } catch (NullPointerException ex) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources");
            logger.error(ex.getMessage(), ex);
            req.setAttribute(WebConstant.ALERT, WebConstant.TYPE_ERROR);
            req.setAttribute(WebConstant.MESSAGE_RESPONSE, resourceBundle.getString("label.login.wrong"));
            req.getRequestDispatcher("/views/web/login.jsp").forward(req, resp);
        }
    }
}
