package ru.vyazankin.rest;


import ru.vyazankin.common.dto.ProductDto;
import ru.vyazankin.persists.Product;
import ru.vyazankin.services.BaseService;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductServiceRest extends BaseService<Product, ProductDto> {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{categoryId}")
    List<ProductDto> findAllByCategoryId(@PathParam("categoryId") Long categoryId);

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    ProductDto findById(@PathParam("id") Long id);

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    default List<ProductDto> findAll() {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto insert(ProductDto t);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto update(ProductDto t);

    @Override
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(ProductDto productDto);

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/empty")
    boolean isEmpty();

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    Long countAll();
}
