package ru.vyazankin.services;

import ru.vyazankin.common.dto.OrderItemDto;
import ru.vyazankin.persists.OrderItem;
import ru.vyazankin.repositories.OrderItemRepository;
import ru.vyazankin.repositories.OrderRepository;
import ru.vyazankin.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OrderItemServiceImpl implements OrderItemService{

    @EJB
    private OrderItemRepository orderItemRepository;

    @EJB
    private OrderRepository orderRepository;

    @EJB
    private ProductRepository productRepository;

    @Override
    public OrderItemDto findById(Long id) {
        return new OrderItemDto(orderItemRepository.findById(id));
    }

    @Override
    public List<OrderItemDto> findAll() {
        return orderItemRepository.findAll().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

    @TransactionAttribute
    @Override
    public OrderItemDto saveOrUpdate(OrderItemDto t) {
        OrderItem orderItem = new OrderItem(
                t.getId(),
                orderRepository.getReference(t.getOrderId()),
                productRepository.getReference(t.getProductId()),
                t.getCount(),
                t.getPrice());
        orderItemRepository.saveOrUpdate(orderItem);

        return new OrderItemDto(orderItem);

    }

    @TransactionAttribute
    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }

    @TransactionAttribute
    @Override
    public void delete(OrderItemDto orderItemDto) {
        orderItemRepository.deleteById(orderItemDto.getId());
    }

    @Override
    public boolean isEmpty() {
        return orderItemRepository.isEmpty();
    }

    @Override
    public Long countAll() {
        return orderItemRepository.countAll();
    }
}
