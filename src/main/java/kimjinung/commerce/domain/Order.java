package kimjinung.commerce.domain;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static kimjinung.commerce.domain.OrderStatus.*;

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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "uuid")
    private Member member;

    @OneToMany(mappedBy = "order")
    private final List<OrderLine> orders = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = PERSIST)
    private final List<Shipment> shipments = new ArrayList<>();

    protected Order() {
    }


    public Order(Member member) {
        this.member = member;
        this.status = PROCESSING;
    }

    public void addItem(Item item, int count) throws IllegalStateException{
        OrderLine orderLine = new OrderLine(this, item, count);
        this.orders.add(orderLine);
    }

    public void completeOrder() {
        this.status = COMPLETE;
        this.shipments.add(new Shipment(this));
    }

    public void cancelOrder() {
        Shipment lastShip = shipments.get(shipments.size() - 1);

        if (lastShip.getStatus() != ShipmentStatus.PENDING) {
            throw new IllegalStateException();
        }

        this.status = CANCEL;
        lastShip.cancelShip();
    }

/*
    TODO
    public void returnOrder() {
        this.status = RETURN;
        this.shipments.add(new Shipment(this, ));
    }

    public void exchangeOrder() {
        this.status = EXCHANGE;

    }
 */

}
