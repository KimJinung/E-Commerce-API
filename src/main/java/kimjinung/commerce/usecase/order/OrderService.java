package kimjinung.commerce.usecase.order;

import kimjinung.commerce.dto.order.*;

import java.util.List;

public interface OrderService {
    OrderCreateResponseDto order(OrderCreateRequestDto orderCreateRequestDTO);
    List<OrderSearchResponseDto> search(OrderSearchRequestDto orderSearchRequestDto);
    OrderCancelResponseDto cancel(OrderCancelRequestDto orderCancelRequestDTO);

    // How to add Payment ?

}
