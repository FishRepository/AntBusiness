package com.backend.admin.service;

import com.backend.admin.entity.BackendUser;
import com.backend.admin.entity.Role;
import com.backend.admin.entity.RoleUser;
import com.backend.admin.mapper.BackendUserMapper;
import com.backend.admin.mapper.RoleMapper;
import com.backend.admin.mapper.RoleUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendUserService {

    @Autowired
    private BackendUserMapper backendUserMapper;
    
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 后台用户登录校验
     * @param backendUser
     * @return
     */
    public boolean validate(BackendUser backendUser){
        if(backendUser==null || StringUtils.isBlank(backendUser.getUserName()) || StringUtils.isBlank(backendUser.getPassword())){
            return false;
        }
        BackendUser user = backendUserMapper.queryByUserName(backendUser.getUserName());
        if(user == null){
            return false;
        }
        return StringUtils.equals(backendUser.getPassword(), user.getPassword());
    }

    /**
     * 根据用户名查询后台用户
     * @param name
     * @return
     */
    public BackendUser getUserByName(String name){
        return backendUserMapper.queryByUserName(name);
    }

    /**
     * 修改后台用户密码
     * @param user
     * @return
     */
    public Boolean changePwd(BackendUser user){
        int update = backendUserMapper.update(user);
        return update > 0;
    }

    /**
     * 查询所有有效后台用户
     * @return
     */
    public List<BackendUser> getAllUser() {
        BackendUser user = new BackendUser();
        user.setState(1);
        return backendUserMapper.queryAll(user);
    }

    /**
     * 查询单个后台用户
     * @param id
     * @return
     */
    public BackendUser getUserById(Integer id) {
        return backendUserMapper.getUserById(id);
    }

    /**
     * 新增或编辑用户
     * @param user
     * @return
     */
    public boolean saveUser(BackendUser user) {
        try {
            if(user==null || StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
                return false;
            }
            if(user.getId()==null){
                return addUser(user);
            }
            return edite(user);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean edite(BackendUser user) {
        int update = backendUserMapper.update(user);
        if(update <= 0){
            return false;
        }
        Integer userId = user.getId();
        List<RoleUser> roleUsers = user.getRoleUserList();
        RoleUser queryRoleUser = new RoleUser();
        queryRoleUser.setUserId(userId);
        List<RoleUser> roleUsersOld = roleUserMapper.queryAll(queryRoleUser);
        RoleUser roleUser;
        RoleUser oledRoleUser;
        for (int i = 0; i < roleUsersOld.size(); i++) {
            roleUser = roleUsers.get(i);
            oledRoleUser = roleUsersOld.get(i);
            oledRoleUser.setState(roleUser.getState()==null?0:1);
        }
        int i = roleUserMapper.updateRoleUserBatch(roleUsersOld);
        return i>0;
    }

    private boolean addUser(BackendUser user) {
        int check = backendUserMapper.checkUser(user.getUserName());
        if(check>0){
            return false;
        }
        int insert = backendUserMapper.insert(user);
        if(insert <= 0){
            return false;
        }
        Integer userId = user.getId();
        Role queryRole = new Role();
        List<Role> roleList = roleMapper.queryAll(queryRole);
        List<RoleUser> roleUsers = user.getRoleUserList();
        Role role;
        RoleUser roleUser;
        for (int i = 0; i < roleList.size(); i++) {
            role = roleList.get(i);
            roleUser = roleUsers.get(i);
            roleUser.setRoleId(role.getId());
            roleUser.setUserId(userId);
            roleUser.setState(roleUser.getState()==null?0:1);
        }
        int i = roleUserMapper.addRoleUserBatch(roleUsers);
        return i>0;
    }

    public boolean deleteUserById(Integer id) {
        return backendUserMapper.deleteById(id) > 0;
    }
}
