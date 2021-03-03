package ru.vyazankin.persists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "product")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "countAllProducts", query = "select count(*) from Product p")
})
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 1024)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, String description, Long price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
