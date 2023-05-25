package kimjinung.commerce.Infrastructure.repository.order;

import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final EntityManager em;

    @Override
    public UUID save(Order order) {
        em.persist(order);
        return order.getId();
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        Order order = em.find(Order.class, uuid);
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<List<Order>> findByMember(Member member) {
        String jpql = "SELECT o FROM Order o where o.member = :member";
        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        query.setParameter("member", member);

        List<Order> orderList = query.getResultList();
        return Optional.ofNullable(orderList);
    }
}
