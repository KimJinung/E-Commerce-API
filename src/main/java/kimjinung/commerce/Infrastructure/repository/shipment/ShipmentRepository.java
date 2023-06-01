package kimjinung.commerce.Infrastructure.repository.shipment;

import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.Shipment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository {

    Optional<Shipment> save(Shipment shipment);
    Optional<Shipment> findById(UUID uuid);

}
