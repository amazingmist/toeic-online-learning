package vn.myclass.command;

import vn.myclass.core.dto.RoleDTO;
import vn.myclass.core.web.command.AbstractCommand;

public class RoleCommand extends AbstractCommand<RoleDTO> {
    public RoleCommand() {
        this.pojo = new RoleDTO();
    }
}
