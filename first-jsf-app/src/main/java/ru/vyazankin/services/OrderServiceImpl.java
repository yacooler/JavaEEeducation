package ru.vyazankin.services;

import ru.vyazankin.common.dto.OrderDto;
import ru.vyazankin.persists.Order;
import ru.vyazankin.repositories.OrderRepository;
import ru.vyazankin.repositories.UserRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceImpl implements OrderService{

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private UserRepository userRepository;

    @Override
    public OrderDto findById(Long id) {
        return new OrderDto(orderRepository.findById(id));
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @TransactionAttribute
    @Override
    public OrderDto saveOrUpdate(OrderDto t) {
        Order order = new Order(t.getId(), userRepository.findById(t.getUserId()));
        orderRepository.saveOrUpdate(order);
        return new OrderDto(order);
    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @TransactionAttribute
    @Override
    public void delete(OrderDto orderDto) {
        orderRepository.deleteById(orderDto.getId());
    }

    @Override
    public boolean isEmpty() {
        return orderRepository.isEmpty();
    }

    @Override
    public Long countAll() {
        return orderRepository.countAll();
    }

    @Override
    public List<Order> findAllByUserId(Long id) {
        return orderRepository.findAllByUserId(id);
    }
}
