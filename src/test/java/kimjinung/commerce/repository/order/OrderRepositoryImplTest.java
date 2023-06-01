package kimjinung.commerce.repository.order;

import kimjinung.commerce.Infrastructure.repository.order.OrderRepository;
import kimjinung.commerce.domain.*;
import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class OrderRepositoryImplTest {

    Order order;
    Member member;
    @Autowired
    OrderRepository repository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        member = new Member("Jinung", "0410", "010", "outlook", null);
        memberRepository.save(member);

        order = new Order(member);
        order.complete();
        repository.save(order);
    }

    @Test
    void testSave() {
        Optional<Order> optionalOrder = repository.save(order);
        assertThat(optionalOrder).isPresent();

        Order myOrder = optionalOrder.get();
        assertThat(myOrder.getMember()).isEqualTo(order.getMember());
        assertThat(myOrder.getOrders()).isEqualTo(order.getOrders());
    }

    @Test
    void testFindById() {
        UUID id = order.getId();
        Optional<Order> optionalOrder = repository.findById(id);
        assertThat(optionalOrder).isPresent();

        Order foundOrder = optionalOrder.get();
        assertThat(foundOrder.getMember()).isEqualTo(member);
        assertThat(foundOrder.getOrders().size()).isEqualTo(0);
        assertThat(foundOrder.getStatus()).isEqualTo(OrderStatus.COMPLETE);
        assertThat(foundOrder.getShipments().size()).isEqualTo(1);

        Optional<Shipment> optionalShipment = foundOrder.getShipments().stream().findFirst();
        assertThat(optionalShipment).isPresent();

        Shipment shipment = optionalShipment.get();
        assertThat(shipment.getOrder()).isEqualTo(order);
        assertThat(shipment.getStatus()).isEqualTo(ShipmentStatus.PENDING);
    }

    @Test
    void testFindByMember() {
        Optional<List<Order>> optionalOrders = repository.findByMember(member);
        assertThat(optionalOrders).isPresent();

        List<Order> foundOrderList = optionalOrders.get();
        assertThat(foundOrderList).isNotEmpty();
        assertThat(foundOrderList.size()).isEqualTo(1);
    }

}