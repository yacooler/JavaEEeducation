package ru.vyazankin.rest;

import ru.vyazankin.common.dto.CategoryDto;
import ru.vyazankin.persists.Category;
import ru.vyazankin.services.BaseService;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/category")
public interface CategoryServiceRest extends BaseService<Category, CategoryDto> {

    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    default List<CategoryDto> findAll() {
        return null;
    }

    @Override
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    void delete(CategoryDto categoryDto);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto insert(CategoryDto categoryDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto update(CategoryDto categoryDto);
}
