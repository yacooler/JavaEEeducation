package ru.vyazankin.services;

import ru.vyazankin.common.dto.UserDto;
import ru.vyazankin.persists.User;
import ru.vyazankin.repositories.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class UserServiceImpl implements UserService{

    @EJB
    UserRepository userRepository;

    @Override
    public UserDto findById(Long id) {
        return new UserDto(userRepository.findById(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public UserDto saveOrUpdate(UserDto t) {
        User user;
        if (t.getId() == null){
            user = new User(t.getId(), t.getName(), t.getPassword(), t.getRolesList());
        } else {
            user = userRepository.findById(t.getId());
            user.setName(t.getName());
            user.setPassword(t.getPassword());
            user.setRoles(t.getRolesList());
        }
        return new UserDto(userRepository.saveOrUpdate(user));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete(UserDto userDto) {
        userRepository.deleteById(userDto.getId());
    }

    @Override
    public boolean isEmpty() {
        return userRepository.isEmpty();
    }

    @Override
    public Long countAll() {
        return userRepository.countAll();
    }
}
