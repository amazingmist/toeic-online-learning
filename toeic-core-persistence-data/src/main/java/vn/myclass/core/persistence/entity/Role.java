package vn.myclass.core.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id     // mark as primary key
    @Column(name = "roleid")    // mapping with column in table
    private short roleId;

    @Column(name = "name")
    private String name;

    // one role to many user
    // mappedBy is the same with @JoinColumn in User.java
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> userList;

    public short getRoleId() {
        return roleId;
    }

    public void setRoleId(short roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
