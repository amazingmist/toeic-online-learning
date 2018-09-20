package vn.myclass.command;

import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.web.command.AbstractCommand;


public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand() {
        this.pojo = new UserDTO();
    }
}
