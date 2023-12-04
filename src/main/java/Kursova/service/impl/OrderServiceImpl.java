package Kursova.service.impl;

import Kursova.dto.OrderDTO;
import Kursova.entity.Order;
import Kursova.mapper.MapperOrder;
import Kursova.repository.OrderRepository;
import Kursova.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MapperOrder mapperOrder = new MapperOrder();

    @Override
    public OrderDTO create(OrderDTO dto) {
        Order order = mapperOrder.toEntity(dto);
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
}