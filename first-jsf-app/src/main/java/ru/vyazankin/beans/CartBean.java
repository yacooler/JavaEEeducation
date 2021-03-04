package ru.vyazankin.beans;

import lombok.Getter;
import ru.vyazankin.persists.OrderItem;
import ru.vyazankin.persists.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class CartBean implements Serializable {
    static final long serialVersionUID = 991969064634901748L;

    @Getter
    private Map<Product, Long> productsMap;

    @PostConstruct
    private void init(){
        productsMap = new HashMap<>();
    }

    public void addProduct(Product product){
        Long count = productsMap.get(product);
        if (count == null) count = 0L;
        productsMap.put(product, ++count);
    }

    public void removeProduct(Product product){
        Long count = productsMap.get(product);
        if ((count == null) || (count == 1L)){
            productsMap.remove(product);
            return;
        }
        productsMap.put(product, --count);
    }

    public List<OrderItem> getCartItems(){
        return productsMap.entrySet().stream().map(
                entry -> new OrderItem(
                        entry.getKey(),
                        entry.getValue())
                ).collect(Collectors.toList());
    }



}
