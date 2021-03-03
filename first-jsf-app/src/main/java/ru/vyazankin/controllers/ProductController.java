package ru.vyazankin.controllers;

import lombok.Getter;
import lombok.Setter;
import ru.vyazankin.persists.Product;
import ru.vyazankin.repositories.ProductRepository;


import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    static final long serialVersionUID = -5455871471998158070L;

    @Inject
    private ProductRepository productRepository;

    @Getter
    @Setter
    private Product product;

    private List<Product> productList;

    public String createProduct(){
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void refreshData(){
        productList = productRepository.findAll();
    }

    public List<Product> getAllProducts(){
        return productList;
    }

    public String editProduct(Product product){
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product){
        productRepository.deleteById(product.getId());
        //return "/product.xhtml";
    }

    public String saveProduct(){
        productRepository.saveOrUpdate(this.product);
        return "/product.xhtml?faces-redirect=true";
    }

}
