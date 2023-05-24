package kimjinung.commerce.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Table(name = "orders")
@Entity
public class Order extends BaseEntity{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uuid")
    private Member member;

    @OneToMany(mappedBy = "order")
    private final List<OrderLine> orders = new ArrayList<>();

    protected Order() {
    }

    public Order(Member member) {
        this.member = member;
    }

    public void addOrder(Item item, int count) throws IllegalStateException{
        OrderLine orderLine = new OrderLine(this, item, count);
        this.orders.add(orderLine);
    }

}
