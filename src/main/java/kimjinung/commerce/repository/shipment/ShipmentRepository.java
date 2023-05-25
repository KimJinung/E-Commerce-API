package kimjinung.commerce.repository.shipment;

import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.Shipment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository {

    UUID save(Shipment shipment);
    Optional<Shipment> findById(UUID uuid);

    Optional<List<Shipment>> findByOrder(Order order);
}
