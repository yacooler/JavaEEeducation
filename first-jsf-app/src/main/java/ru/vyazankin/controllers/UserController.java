package ru.vyazankin.controllers;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.common.dto.UserDto;
import ru.vyazankin.common.dto.UserRoleDto;
import ru.vyazankin.persists.Role;
import ru.vyazankin.persists.User;
import ru.vyazankin.repositories.RoleRepository;
import ru.vyazankin.repositories.UserRepository;
import ru.vyazankin.services.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @EJB
    private UserService userService;

    @EJB
    private RoleRepository roleRepository;

    private List<UserDto> userDtoList;
    private List<Role> possibleRoles;


    //Для редактирования выбранного пользователя
    @Getter
    @Setter
    private UserDto userDto;

    //Для редактирования ролей выбранного пользователя
    @Getter
    @Setter
    private UserRoleDto userRoleDto;

    @PostConstruct
    private void init(){
        userDtoList = new ArrayList<>();
        possibleRoles = new ArrayList<>();
    }

    public void refreshData(){
        userDtoList = userService.findAll();
        possibleRoles = roleRepository.findAll();
    }


    public List<UserDto> getUserList() {
        return userDtoList;
    }

    public void deleteUser(UserDto userDto){
        userService.delete(userDto);
    }

    public String editUser(UserDto userDto){
        this.userDto = userDto;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public String editUserRoles(UserDto userDto){
        this.userDto = userDto;
        this.userRoleDto = new UserRoleDto(
                userService.findById(userDto.getId()),
                possibleRoles);
        logger.info(userRoleDto.getRoleEntities().toString());
        return "/admin/role_form.xhtml?faces-redirect=true";
    }


    //Записываем пользователя userDto
    public String saveUser(){
        logger.info(userDto.toString());
        userService.saveOrUpdate(userDto);
        return "/admin/manage_users.xhtml?faces-redirect=true";
    }

    //Сохраняем измененные пользовательские роли
    public String saveUserRoles(){

        //Пробегаем по каждой роли и убираем или добавляем её в список ролей пользователя
        for (UserRoleDto.RoleEntity roleEntity: this.userRoleDto.getRoleEntities()) {
            //Если роли не было, но её установили
            if (roleEntity.getChecked() && !ckeckRole(roleEntity.getName())){
                userDto.getRolesList().add(roleRepository.getReference(roleEntity.getId()));
                userService.saveOrUpdate(userDto);
            //Если роли не было, но её установили
            } else if(!roleEntity.getChecked() && ckeckRole(roleEntity.getName())){
                userDto.getRolesList().removeIf(role -> role.getName().equals(roleEntity.getName()));
                userService.saveOrUpdate(userDto);
            }
        }

        return "/admin/manage_users.xhtml?faces-redirect=true";
    }

    //Проверка, есть ли у пользователя userDto роль name
    private boolean ckeckRole(String roleName){
        return userDto.getRolesList().stream().anyMatch(role -> role.getName().equals(roleName));
    }

}
