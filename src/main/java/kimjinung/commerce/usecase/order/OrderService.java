package kimjinung.commerce.usecase.order;

import kimjinung.commerce.dto.order.OrderCancelDTO;
import kimjinung.commerce.dto.order.OrderCreateDTO;
import kimjinung.commerce.dto.order.OrderDTO;
import kimjinung.commerce.dto.order.OrderSearchDTO;

import java.util.List;

public interface OrderService {
    OrderDTO order(OrderCreateDTO orderCreateDTO);
    List<OrderDTO> search(OrderSearchDTO orderSearchDTO);
    boolean cancel(OrderCancelDTO orderCancelDTO);
}
