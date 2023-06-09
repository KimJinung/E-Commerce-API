package kimjinung.commerce.controller.api;


import kimjinung.commerce.dto.common.ResponseDto;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.usecase.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/order")
@RestController
public class OrderApiController extends BaseApiController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseDto<OrderCreateResponseDto> order(
            @RequestBody @Validated OrderCreateRequestDto orderCreateRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        OrderCreateResponseDto order = orderService.order(orderCreateRequestDto);
        return new ResponseDto<>(200, order);
    }

    @GetMapping("/search")
    public ResponseDto<List<OrderSearchResponseDto>> search(
            @RequestBody @Validated OrderSearchRequestDto orderSearchRequestDto,
            BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        List<OrderSearchResponseDto> search = orderService.search(orderSearchRequestDto);
        return new ResponseDto<>(200, search);
    }

    @PatchMapping("/cancel")
    public ResponseDto<OrderCancelResponseDto> cancel(
        @RequestBody @Validated OrderCancelRequestDto orderCancelRequestDto,
        BindingResult bindingResult
    ) {
        validateRequest(bindingResult);
        OrderCancelResponseDto cancel = orderService.cancel(orderCancelRequestDto);
        return new ResponseDto<>(200, cancel);
    }
}
