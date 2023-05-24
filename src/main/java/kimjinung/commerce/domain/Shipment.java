package kimjinung.commerce.domain;


import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

import static kimjinung.commerce.domain.ShipmentStatus.*;

@Getter
@Entity
public class Shipment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "item_id", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Address address;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    protected Shipment() {
    }

    public Shipment(Order order) {
        this.order = order;
        this.address = order.getMember().getAddress();
        this.status = PENDING;

    }

    public void startShip() {
        this.status = SHIPPED;
    }

    public void cancelShip() {
        this.status = CANCEL;
    }

    public void completeShip() {
        this.status = COMPLETE;
    }
}
