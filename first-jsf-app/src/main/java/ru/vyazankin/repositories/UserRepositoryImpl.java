package ru.vyazankin.repositories;

import jdk.jfr.Name;
import ru.vyazankin.persists.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    private final AtomicLong identity = new AtomicLong(0);
    private final Map<Long, User> userMap = new ConcurrentHashMap<>();


    @PostConstruct
    public void init(){
        this.saveOrUpdate(new User(null, "bob", "100600"));
        this.saveOrUpdate(new User(null, "bil", "100500"));
    }

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
