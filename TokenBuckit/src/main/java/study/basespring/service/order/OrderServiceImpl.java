package study.basespring.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basespring.entity.OrderItem;
import study.basespring.entity.Orders;
import study.basespring.entity.Product;
import study.basespring.entity.User;
import study.basespring.global.OrderStatus;
import study.basespring.repo.OrderItemRepository;
import study.basespring.repo.OrdersRepository;
import study.basespring.repo.ProductRepository;
import study.basespring.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Orders createOrder(Long userId, List<OrderItemRequest> orderItemRequests) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. ID: " + userId));
        
        // 주문 생성
        Orders order = Orders.createOrder(user);
        
        // 주문 항목 생성
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest request : orderItemRequests) {
            Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + request.productId()));
            
            OrderItem orderItem = OrderItem.createOrderItem(product, request.quantity());
            orderItem.setOrders(order);
            orderItems.add(orderItem);
        }
        
        // 주문에 주문 항목 추가
        orderItems.forEach(order::addOrderItem);
        
        // 저장
        Orders savedOrder = ordersRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        
        return savedOrder;
    }

    @Override
    public Orders findById(Long orderId) {
        return ordersRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다. ID: " + orderId));
    }

    @Override
    public List<Orders> findByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    @Transactional
    public Orders updateOrderStatus(Long orderId, OrderStatus status) {
        Orders order = findById(orderId);
        order.changeOrderStatus(status);
        return order;
    }

    @Override
    @Transactional
    public Orders cancelOrder(Long orderId) {
        Orders order = findById(orderId);
        order.cancelOrder();
        
        // 주문 항목의 재고 복구
        order.getOrderItems().forEach(OrderItem::cancel);
        
        return order;
    }
}