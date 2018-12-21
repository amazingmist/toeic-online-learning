package vn.myclass.controller.admin;

import vn.myclass.command.UserCommand;
import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.service.RoleService;
import vn.myclass.core.service.UserService;
import vn.myclass.core.service.impl.RoleServiceImpl;
import vn.myclass.core.service.impl.UserServiceImpl;
import vn.myclass.core.web.common.WebConstant;
import vn.myclass.core.web.utils.FormUtil;
import vn.myclass.core.web.utils.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/admin-user-list.html", "/ajax-admin-user-edit.html"})
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, req);
        RequestUtil.initSearchBean(req, command);

        UserDTO dto = command.getPojo();
        if (command.getUrlType().equals(WebConstant.URL_LIST)) {
            Map<String, Object> properties = new HashMap<String, Object>();
            Object[] finded = userService.findByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
            command.setListResult((List<UserDTO>) finded[1]);
            command.setTotalItems(Integer.parseInt(finded[0].toString()));

            req.setAttribute(WebConstant.LIST_ITEMS, command);

            req.getRequestDispatcher("/views/admin/user/list.jsp").forward(req, resp);
        }else if (command.getUrlType().equals(WebConstant.URL_EDIT)){
//            check if is existed user id and then we get this pojo from db
            if (command.getPojo().getUserId() != null){
                dto = userService.findById(dto.getUserId());
                command.setPojo(dto);
            }

//            set all role to user command
            command.setRoles(roleService.findAll());
            req.setAttribute(WebConstant.FORM_ITEM, command);
            req.getRequestDispatcher("/views/admin/user/edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
