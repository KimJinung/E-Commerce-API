package kimjinung.commerce.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_line_id", columnDefinition = "BINARY(16")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Integer count;
    private Integer totalPrice;

    protected OrderLine() {
    }

    public OrderLine(Order order, Item item, Integer count) throws IllegalStateException{
        this.order = order;
        this.item = item;
        this.item.reduceStock(count);
        this.count = count;
        this.totalPrice = item.getTotalPriceByCount(count);
        
    }

}
