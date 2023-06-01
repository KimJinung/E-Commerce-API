package kimjinung.commerce.usecase.order;

import kimjinung.commerce.Infrastructure.repository.item.ItemRepository;
import kimjinung.commerce.Infrastructure.repository.member.MemberRepository;
import kimjinung.commerce.Infrastructure.repository.order.OrderRepository;
import kimjinung.commerce.domain.Item;
import kimjinung.commerce.domain.Member;
import kimjinung.commerce.domain.Order;
import kimjinung.commerce.domain.OrderLine;
import kimjinung.commerce.dto.order.*;
import kimjinung.commerce.exception.MemberNotFoundException;
import kimjinung.commerce.exception.OrderNotExistException;
import kimjinung.commerce.exception.OrderReadFailException;
import kimjinung.commerce.exception.ProductNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Override
    public OrderCreateResultDTO order(OrderCreateDTO orderCreateDTO) throws RuntimeException{
        // Create order by member
        Optional<Member> optionalMember = memberRepository.findByUserId(orderCreateDTO.getUserId());

        if (optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }
        Member member = optionalMember.get();
        Order order = new Order(member);

        // Find item by id
        HashMap<String, Integer> itemCart = orderCreateDTO.getItemCart();

        List<UUID> itemUuids = itemCart.keySet().stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());

        Optional<List<Item>> optionalItems = itemRepository.findByIds(itemUuids);

        if (optionalItems.isEmpty()) {
            throw new ProductNotExistException("Product not exist");
        }

        // Order use item and count
        Long totalOrderPrice = 0L;
        Map<String, Integer> orderItemResult = new HashMap<>();

        List<Item> items = optionalItems.get();

        for (Item item : items) {
            Integer count = itemCart.get(item.getUuid().toString());

            order.addItem(item, count);
            totalOrderPrice += item.getTotalPriceByCount(count);
            orderItemResult.put(item.getName(), count);
        }

        order.completeOrder();
        orderRepository.save(order);

        return new OrderCreateResultDTO(
                order.getId().toString(),
                order.getStatus().toString(),
                orderItemResult,
                totalOrderPrice
        );

    }

    @Override
    public List<OrderSearchResultDTO> search(OrderSearchDTO orderSearchDTO) throws RuntimeException{
        // Find member
        Optional<Member> optionalMember = memberRepository.findByUserId(orderSearchDTO.getUserId());

        if (optionalMember.isEmpty()) {
            throw new MemberNotFoundException();
        }

        // Find order by member
        Member member = optionalMember.get();
        Optional<List<Order>> optionalOrders = orderRepository.findByMember(member);

        if (optionalOrders.isEmpty()) {
            throw new OrderReadFailException();
        }
        List<Order> orders = optionalOrders.get();


        // Processing: 각각 오더에 대해서 DTO 클래스로 발라내야함
        List<OrderSearchResultDTO> orderList = new ArrayList<>();
        for (Order order : orders) {
            OrderSearchResultDTO orderSearchResultDTO = extractOrder(order);
            orderList.add(orderSearchResultDTO);
        }

        return orderList;
    }

    private OrderSearchResultDTO extractOrder(Order order) {
        Long totalOrderPrice = 0L;

        Map<String, Integer> orderMap = new HashMap<>();
        List<OrderLine> orders = order.getOrders();

        for (OrderLine orderLine : orders) {
            orderMap.put(orderLine.getItem().getName(), orderLine.getCount());
            totalOrderPrice += orderLine.getTotalPrice();
        }

        return new OrderSearchResultDTO(
                order.getId().toString(),
                order.getUpdatedAt(),
                order.getStatus().toString(),
                orderMap,
                totalOrderPrice
        );
    }

    @Override
    public void cancel(OrderCancelDTO orderCancelDTO) {
        String id = orderCancelDTO.getId();
        UUID uuid = UUID.fromString(id);

        Optional<Order> optionalOrder = orderRepository.findById(uuid);

        if (optionalOrder.isEmpty()) {
            throw new OrderNotExistException();
        }

        Order order = optionalOrder.get();
        order.cancelOrder();
    }
}
