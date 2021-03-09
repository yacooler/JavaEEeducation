package ru.vyazankin.persists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "from Category c"),
        @NamedQuery(name = "countAllCategories", query = "select count(*) from Category c"),
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id")
})
public class Category {

    public Category(String name) {
        Name = name;
    }

    public Category(Long id, String name) {
        this.id = id;
        Name = name;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String Name;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            mappedBy = "category")
    List<Product> productList;
}
