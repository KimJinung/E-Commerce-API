package kimjinung.commerce.repository.shipment;

import kimjinung.commerce.Infrastructure.repository.shipment.ShipmentRepository;
import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.Shipment;
import kimjinung.commerce.domain.ShipmentStatus;
import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.Infrastructure.repository.order.OrderRepository;
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





}