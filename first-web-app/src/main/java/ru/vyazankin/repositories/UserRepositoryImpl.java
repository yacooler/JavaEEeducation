package ru.vyazankin.repositories;

import ru.vyazankin.persists.User;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryImpl implements UserRepository{

    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public List<User> findAll(){
        return List.copyOf(userMap.values());
    }

    @Override
    public User saveOrUpdate(User user){
        if (user.getId() == null) user.setId(identity.incrementAndGet());
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userMap.remove(id);
    }

}
