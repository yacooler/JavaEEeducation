package ru.vyazankin.repositories;

import ru.vyazankin.persists.Role;

public interface RoleRepository extends BaseRepository<Role>{
    Role findByName(String name);
}
