package kimjinung.commerce.usecase.shipment;

import kimjinung.commerce.dto.shipment.ShipmentDTO;
import kimjinung.commerce.dto.shipment.ShipmentSearchDTO;

import java.util.List;

public interface ShipmentService {
    List<ShipmentDTO> track(ShipmentSearchDTO shipmentSearchDTO);
}
