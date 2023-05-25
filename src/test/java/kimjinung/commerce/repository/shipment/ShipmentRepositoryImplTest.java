package kimjinung.commerce.repository.shipment;

import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.Shipment;
import kimjinung.commerce.domain.ShipmentStatus;
import kimjinung.commerce.repository.member.MemberRepository;
import kimjinung.commerce.repository.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class ShipmentRepositoryImplTest {

    Shipment shipment;
    Member member;
    Order order;

    @Autowired
    ShipmentRepository repository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        member = new Member("dummy", "1234", "1234", "1234", null);
        memberRepository.save(member);

        order = new Order(member);
        orderRepository.save(order);

        shipment = new Shipment(order);
        repository.save(shipment);
    }

    @Test
    void testFindById() {
        Optional<Shipment> optionalShipment = repository.findById(shipment.getUuid());
        assertThat(optionalShipment).isPresent();

        Shipment foundShipment = optionalShipment.get();
        assertThat(foundShipment.getStatus()).isEqualTo(ShipmentStatus.PENDING);
        assertThat(foundShipment.getOrder().getMember()).isEqualTo(member);

    }

    @Test
    void testFindById_NotExistShipment() {
        UUID uuid = UUID.randomUUID();
        Optional<Shipment> optionalShipment = repository.findById(uuid);
        assertThat(optionalShipment).isEmpty();
    }

    @Test
    void testFindByOrder() {
        Optional<List<Shipment>> optionalShipments = repository.findByOrder(order);
        assertThat(optionalShipments).isPresent();

        List<Shipment> shipments = optionalShipments.get();
        assertThat(shipments.size()).isEqualTo(1);

        Optional<Shipment> optionalFirstShipment = shipments.stream().findFirst();
        assertThat(optionalFirstShipment).isPresent();

        Shipment firstShipment = optionalFirstShipment.get();
        assertThat(firstShipment).isEqualTo(shipment);
    }

    @Test
    void testFindByOrder_InvalidOrder() {
        Order dummyOrder = new Order(member);
        org.junit.jupiter.api.Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class,
                () -> repository.findByOrder(dummyOrder)
        );
    }

}