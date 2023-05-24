package kimjinung.commerce.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    Member member;
    Address address;
    Order order;
    Item item;

    @BeforeEach
    void beforeEach() {
        new Address("City", "Street", "12345");
        member = new Member("Jinung Kim", "0410", "010", "outlook", address);
        order = new Order(member);
        item = new Item("MacBook", 1000000, 10);
    }

    @Test
    void testAddItem() {
        order.addItem(item, 1);
        order.addItem(item, 2);

        assertThat(order.getOrders().size()).isEqualTo(2);
        order.getOrders()
                .forEach(
                        i -> assertThat(i.getItem().getName()).isEqualTo("MacBook")
                );
    }

    @Test
    void testAddOrder_NotEnoughStockException() {
        assertThrows(
                IllegalStateException.class,
                () -> order.addItem(item, 100)
        );
    }

    @Test
    void testCompleteOrder() {
        order.completeOrder();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.COMPLETE);
        assertThat(order.getShipments().get(order.getShipments().size() - 1).getStatus())
                .isEqualTo(ShipmentStatus.PENDING);
    }

    @Test
    void testCancelOrder() {
        order.completeOrder();
        order.cancelOrder();

        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(order.getShipments().get(order.getShipments().size() - 1).getStatus())
                .isEqualTo(ShipmentStatus.CANCEL);
    }

    @Test
    void testCancelOrder_IllegalStatueException() {
        order.completeOrder();
        Shipment lastShip = order.getShipments().get(order.getShipments().size() - 1);
        lastShip.startShip();

        assertThrows(
                IllegalStateException.class,
                () -> order.cancelOrder());
    }

}