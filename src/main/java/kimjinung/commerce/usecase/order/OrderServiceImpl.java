package kimjinung.commerce.usecase.order;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.Infrastructure.repository.order.OrderRepository;
import kimjinung.commerce.domain.*;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.exception.MemberNotFoundException;
import kimjinung.commerce.exception.OrderNotExistException;
import kimjinung.commerce.exception.OrderFindFailException;
import kimjinung.commerce.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Override
    public OrderCreateResponseDto order(OrderCreateRequestDto orderCreateRequestDTO) throws RuntimeException{

        String userId = orderCreateRequestDTO.getUserId();
        Member member = memberRepository.findByUserId(userId).orElseThrow(MemberNotFoundException::new);
        Order order = new Order(member);

        HashMap<String, Integer> itemCart = orderCreateRequestDTO.getItemCart();

        List<Item> items = itemCart.keySet().stream()
                .map(key -> itemRepository.findById(UUID.fromString(key))
                        .orElseThrow(ItemNotFoundException::new))
                .collect(Collectors.toList());

        HashMap<String, Integer> orderItem = new HashMap<>();
        Long totalOrderPrice = 0L;
        for (Item item : items) {
            Integer count = itemCart.get(item.getId().toString());
            order.addItem(item, count);
            orderItem.put(item.getName(), count);
            totalOrderPrice += item.getTotalPriceByCount(count);
        }

        order.complete();
        orderRepository.save(order);

        return new OrderCreateResponseDto(
                order.getId().toString(),
                order.getStatus().toString(),
                orderItem,
                totalOrderPrice
        );

    }

    @Override
    public List<OrderSearchResponseDto> search(OrderSearchRequestDto orderSearchRequestDto) throws RuntimeException{
        String userId = orderSearchRequestDto.getUserId();

        Member member = memberRepository.findByUserId(userId).orElseThrow(MemberNotFoundException::new);
        List<Order> orders = orderRepository.findByMember(member).orElseThrow(OrderFindFailException::new);

        List<OrderSearchResponseDto> orderList = new ArrayList<>();
        for (Order order : orders) {
            OrderSearchResponseDto orderSearchResponseDto = extractOrder(order);
            orderList.add(orderSearchResponseDto);
        }
        return orderList;
    }

    @Override
    public OrderCancelResponseDto cancel(OrderCancelRequestDto orderCancelRequestDTO) {
        UUID id = UUID.fromString(orderCancelRequestDTO.getId());
        Order order = orderRepository.findById(id).orElseThrow(OrderNotExistException::new);
        order.cancel();

        if (order.getStatus().equals(OrderStatus.CANCEL)) {
            return new OrderCancelResponseDto("ok");
        }
        return new OrderCancelResponseDto("fail");
    }

    private OrderSearchResponseDto extractOrder(Order order) {
        Long totalOrderPrice = 0L;

        Map<String, Integer> orderItem = new HashMap<>();
        List<OrderLine> orders = order.getOrders();

        for (OrderLine orderLine : orders) {
            orderItem.put(orderLine.getItem().getName(), orderLine.getCount());
            totalOrderPrice += orderLine.getTotalPrice();
        }

        return new OrderSearchResponseDto(
                order.getId().toString(),
                order.getCreatedAt(),
                order.getStatus().toString(),
                orderItem,
                totalOrderPrice
        );
    }
}
