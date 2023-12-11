package Kursova.service.impl;

import Kursova.dto.OrderDTO;
import Kursova.entity.Order;
import Kursova.entity.Product;
import Kursova.mapper.MapperOrder;
import Kursova.repository.OrderRepository;
import Kursova.repository.ProductRepository;
import Kursova.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MapperOrder mapperOrder = new MapperOrder();

    @Override
    public OrderDTO create(OrderDTO dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        Order order = mapperOrder.toEntity(dto);
        order.setUserId(userDetails.getId());
        orderRepository.save(order);
        return mapperOrder.toDto(order);
    }

    @Override
    public OrderDTO read(Long code) {
        return mapperOrder.toDto(orderRepository.findById(code).orElseThrow());
    }

    @Override
    public void update(OrderDTO dto) {
        Order order = mapperOrder.toEntity(dto);
        orderRepository.save(order);
    }

    @Override
    public void delete(Long code) {
        orderRepository.delete(orderRepository.findById(code).orElseThrow());
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.findAll().stream().map(mapperOrder::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getForUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<OrderDTO> orders = orderRepository.findForUser(userDetails.getId()).stream().map(mapperOrder::toDto).collect(Collectors.toList());

        if(!orders.isEmpty()) {
            orders.stream().forEach(order -> {
                Long productId = Long.valueOf(order.getProduct());
                Product product = productRepository.getById(productId);
                order.setTotal(product.getPrice().multiply(BigDecimal.valueOf(order.getProductAmount())));
            });
        }

        return orders;
    }
}