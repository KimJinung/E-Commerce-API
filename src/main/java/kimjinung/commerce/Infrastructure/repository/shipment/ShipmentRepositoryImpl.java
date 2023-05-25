package kimjinung.commerce.Infrastructure.repository.shipment;

import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.Shipment;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class ShipmentRepositoryImpl implements ShipmentRepository {

    private final EntityManager em;

    @Override
    public UUID save(Shipment shipment) {
        em.persist(shipment);
        return shipment.getUuid();
    }

    @Override
    public Optional<Shipment> findById(UUID uuid) {
        Shipment shipment = em.find(Shipment.class, uuid);
        return Optional.ofNullable(shipment);
    }

    @Override
    public Optional<List<Shipment>> findByOrder(Order order) throws InvalidDataAccessApiUsageException {
        String jpql = "SELECT s FROM Shipment s where s.order = :order";
        List<Shipment> shipments = em.createQuery(jpql, Shipment.class)
                .setParameter("order", order)
                .getResultList();

        return Optional.ofNullable(shipments);
    }
}
