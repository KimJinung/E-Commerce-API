package kimjinung.commerce.repository.shipment;

import kimjinung.commerce.domain.Shipment;

import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository {

    UUID save(Shipment shipment);
    boolean remove(Shipment shipment);
    Optional<Shipment> findById(UUID uuid);
}
