package ru.vyazankin.common.dto;

import lombok.Data;
import ru.vyazankin.persists.Role;
import ru.vyazankin.persists.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String roles;
    private List<Role> rolesList;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", "));
        this.rolesList = user.getRoles();
    }
}
