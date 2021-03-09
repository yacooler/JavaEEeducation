package ru.vyazankin.persists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "from Order o"),
        @NamedQuery(name = "countAllOrders", query = "select count(*) from Order o"),
        @NamedQuery(name = "findAllOrdersByUserId", query = "from Order o where o.user.id = :user_id"),
        @NamedQuery(name = "deleteOrderById", query = "delete from Order o where o.id = :id")
})
public class Order {

    public Order(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "order")
    private List<OrderItem> orderItemList;
}