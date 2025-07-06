package study.basespring.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.basespring.entity.Orders;
import study.basespring.entity.User;
import study.basespring.global.OrderStatus;
import study.basespring.repo.OrdersRepository;
import study.basespring.repo.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    private final OrdersRepository ordersRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Orders createOrder(Long userId, Long totalAmount) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        Orders order = Orders.builder()
            .user(user)
            .totalAmount(totalAmount)
            .orderDate(LocalDate.now())
            .orderStatus(OrderStatus.PENDING)
            .build();
        
        return ordersRepository.save(order);
    }

    @Override
    public Orders findById(Long orderId) {
        return ordersRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public List<Orders> findByUserId(Long userId) {
        return ordersRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Orders updateOrderStatus(Long orderId, OrderStatus status) {
        Orders order = findById(orderId);
        order.setOrderStatus(status);
        return order;
    }
}