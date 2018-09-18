package vn.myclass.core.test;

import org.testng.annotations.Test;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {
//    @Test
    public void checkFindAll() {
        RoleDao roleDao = new RoleDaoImpl();
        List<RoleEntity> list = roleDao.findAll();
    }

//    @Test
    public void checkUpdate() {
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(2);
        roleEntity.setName("USER");
        roleDao.update(roleEntity);
    }

//    @Test
    public void checkSave(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(3);
        roleEntity.setName("MANAGER");
        roleDao.save(roleEntity);
    }

//    @Test
    public void checkFindById(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = roleDao.findById(5);
        System.out.println(roleEntity.getName());
    }

//    @Test
    public void checkFindByProperty(){
        RoleDao roleDao = new RoleDaoImpl();
        String property = null;
        String value = null;
        String sortExpression = null;
        String sortDirection = null;
        List<RoleEntity> list = roleDao.findByProperty(property, value, sortExpression, sortDirection);
    }

//    @Test
    public void checkDelete(){
        RoleDao roleDao = new RoleDaoImpl();
        List<Integer> list = new ArrayList<Integer>();
        list.add(3);
        roleDao.delete(list);
    }
}