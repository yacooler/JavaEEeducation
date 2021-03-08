package ru.vyazankin.persists;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table

@NamedQueries({
        @NamedQuery(name = "deleteOrderItemById", query = "delete from OrderItem o where o.id = :id")
})
public class OrderItem {

    public OrderItem(Product product, Long count) {
        this.product = product;
        this.count = count;
        this.price = product.getPrice() * count;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "cnt")
    private Long count;

    @Column(name = "price")
    private Long price;
}
