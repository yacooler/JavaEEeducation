package ru.vyazankin.controllers;

import ru.vyazankin.beans.CartBean;
import ru.vyazankin.dto.CartItemDto;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    static final long serialVersionUID = -5202128868518620640L;

    @Inject
    private CartBean cartBean;
    @Inject
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return Collections.unmodifiableList(productRepository.findAll());
    }

    public List<CartItemDto> getCartItemDtoList(){
        return cartBean.getCartItems();
    }

    public void addProductToCart(Product product){
        cartBean.addProduct(product);
    }

    public void removeProductFromCart(Product product){
        cartBean.removeProduct(product);
    }


}
