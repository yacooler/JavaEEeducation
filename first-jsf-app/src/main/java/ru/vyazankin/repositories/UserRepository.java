package ru.vyazankin.repositories;

import ru.vyazankin.persists.User;

import java.util.List;


public interface UserRepository extends BaseRepository<User>{
    User findByName(String name);
}
