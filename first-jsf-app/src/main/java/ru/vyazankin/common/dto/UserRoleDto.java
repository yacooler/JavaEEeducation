package ru.vyazankin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vyazankin.persists.Role;
import ru.vyazankin.persists.User;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserRoleDto {
    Long userId;
    String userName;
    List<RoleEntity> roleEntities = new ArrayList<>();

    public UserRoleDto(UserDto userDto, List<Role> possibleRoles) {
        for (Role r : possibleRoles) {
            roleEntities.add(new RoleEntity(
                    r.getId(),
                    r.getName(),
                    userDto.getRolesList().contains(r))
            );
        }
    }

    @Data
    @AllArgsConstructor
    public class RoleEntity {
        private Long id;
        private String name;
        private Boolean checked;


    }

}
