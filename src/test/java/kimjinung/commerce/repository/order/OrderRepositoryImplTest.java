package kimjinung.commerce.repository.order;

import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;
import kimjinung.commerce.repository.member.MemberRepository;
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
import static org.junit.jupiter.api.Assertions.*;


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
        order.completeOrder();
        repository.save(order);
    }

    @Test
    @Rollback(value = false)
    void testFindById() {
        UUID id = order.getId();
        Optional<Order> optionalOrder = repository.findById(id);
        assertTrue(optionalOrder.isPresent());
    }

    @Test
    void testFindByMember() {
        Optional<List<Order>> optionalOrders = repository.findByMember(member);
        assertTrue(optionalOrders.isPresent());
        List<Order> foundOrderList = optionalOrders.get();

        assertFalse(foundOrderList.isEmpty());
        assertThat(foundOrderList.size()).isEqualTo(1);
    }

    @Test
    void testFindByMember_NotFound() {
        Member dummyMember = new Member(null, null, null, null, null);
        memberRepository.save(dummyMember);

        Optional<List<Order>> optionalOrders = repository.findByMember(dummyMember);
        assertTrue(optionalOrders.isPresent());

        List<Order> orders = optionalOrders.get();
        assertTrue(orders.isEmpty());
    }

}