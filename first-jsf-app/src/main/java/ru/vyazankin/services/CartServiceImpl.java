package ru.vyazankin.services;

import lombok.Getter;
import ru.vyazankin.dto.OrderItemDto;
import ru.vyazankin.dto.ProductDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.*;


@Stateful
public class CartServiceImpl implements CartService {

    @Getter
    private Map<Long, OrderItemDto> orderItemsMapByProductId;

    @PostConstruct
    private void init(){
        orderItemsMapByProductId = new HashMap<>();
    }

    @EJB
    ProductService productService;

    @Override
    public void addProduct(Long productId){

        //Ищем, есть ли в корзине продукт с выбранным ID
        OrderItemDto orderItemDto = orderItemsMapByProductId.get(productId);

        if (orderItemDto == null) {
            //Если нет - добавляем с количеством 1
            orderItemDto = new OrderItemDto(productService.findById(productId), 1L);
            orderItemsMapByProductId.put(productId, orderItemDto);
        } else {
            //Иначе увеличиваем количество
            orderItemDto.setCount(orderItemDto.getCount() + 1L);
        }
    }

    @Override
    public void removeProduct(Long productId){
        //Ищем, есть ли в корзине продукт с выбранным ID
        OrderItemDto orderItemDto = orderItemsMapByProductId.get(productId);

        //Если есть - уменьшаем количество
        if (orderItemDto != null){
            orderItemDto.setCount(orderItemDto.getCount() - 1);
            //Если уменьшили до нуля - удаляем из мапы
            if (orderItemDto.getCount() <= 0L) orderItemsMapByProductId.remove(productId);
        }

    }

    @Override
    public List<OrderItemDto> getCartItems(){
        return new ArrayList<>(orderItemsMapByProductId.values());
    }


}
