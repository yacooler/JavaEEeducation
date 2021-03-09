package ru.vyazankin.controllers;

import ru.vyazankin.common.dto.OrderItemDto;
import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.services.CartService;
import ru.vyazankin.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    static final long serialVersionUID = -5202128868518620640L;

    @EJB
    private CartService cartService;

    @EJB
    private ProductService productService;

    public List<ProductDto> getAllProducts(){
        return productService.findAll();
    }

    public List<OrderItemDto> getCartItemDtoList(){
        return cartService.getCartItems();
    }

    public void addProductToCart(Long productId){
        cartService.addProduct(productId);
    }

    public void removeProductFromCart(Long productId){
        cartService.removeProduct(productId);
    }


}
