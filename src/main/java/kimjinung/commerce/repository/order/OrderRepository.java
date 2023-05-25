package kimjinung.commerce.repository.order;

import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    UUID save(Order order);
    Optional<Order> findById(UUID uuid);
    Optional<List<Order>> findByMember(Member member);

}
