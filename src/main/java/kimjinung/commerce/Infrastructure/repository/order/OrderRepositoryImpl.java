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
    public Optional<Order> save(Order order) {
        em.persist(order);
        return findById(order.getId());
    }

    @Override
    public Optional<Order> findById(UUID id) {
        Order order = em.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<List<Order>> findByMember(Member member) {
        String jpql = "SELECT o FROM Order o where o.member = :member";
        List<Order> orders = em.createQuery(jpql, Order.class)
                .setParameter("member", member)
                .getResultList();

        return Optional.ofNullable(orders);
    }
}
