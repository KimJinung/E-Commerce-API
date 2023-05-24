package kimjinung.commerce.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kimjinung.commerce.domain.ShipmentStatus.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ShipmentTest {

    Shipment shipment;


    @BeforeEach
    void beforeEach() {
        Address address = new Address("City", "Street", "1234");
        Member member = new Member("Jinung", "0410", "010", "outlook", address);
        Order order = new Order(member);
        shipment = new Shipment(order);
    }

    @Test
    void testStartShip() {
        assertThat(shipment.getStatus()).isEqualTo(PENDING);

        shipment.startShip();
        assertThat(shipment.getStatus()).isEqualTo(SHIPPED);
    }

    @Test
    void testCompleteShip() {
        assertThat(shipment.getStatus()).isEqualTo(PENDING);

        shipment.completeShip();
        assertThat(shipment.getStatus()).isEqualTo(COMPLETE);
    }

    @Test
    void testCancelShip() {
        assertThat(shipment.getStatus()).isEqualTo(PENDING);

        shipment.cancelShip();
        assertThat(shipment.getStatus()).isEqualTo(CANCEL);
    }

}
