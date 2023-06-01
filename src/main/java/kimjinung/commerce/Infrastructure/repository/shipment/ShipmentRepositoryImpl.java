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
    public Optional<Shipment> save(Shipment shipment) {
        em.persist(shipment);
        return findById(shipment.getId());
    }

    @Override
    public Optional<Shipment> findById(UUID id) {
        Shipment shipment = em.find(Shipment.class, id);
        return Optional.ofNullable(shipment);
    }

}
