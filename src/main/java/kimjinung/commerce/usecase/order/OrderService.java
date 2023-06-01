package kimjinung.commerce.usecase.order;

import kimjinung.commerce.dto.order.*;

import java.util.List;

public interface OrderService {
    OrderCreateResultDTO order(OrderCreateDTO orderCreateDTO);
    List<OrderSearchResultDTO> search(OrderSearchDTO orderSearchDTO);
    void cancel(OrderCancelDTO orderCancelDTO);

    /*
    각 서비스마다 적절한 DTO 클래스로 넘겨주어야한다.
    결제 기능은 order 메서드 내에서 결제 API에 붙일 데이터를 가공하고, 호출한다.
    API 콜 치고, 결과에 따라 결제 응답을 해야한다.

    1. order
    2. payment

     */
}
