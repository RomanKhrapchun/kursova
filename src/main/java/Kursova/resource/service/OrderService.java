package Kursova.resource.service;

import Kursova.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO create(OrderDTO dto);

    OrderDTO read(Long code);

    void update(OrderDTO dto);

    void delete(Long code);

    List<OrderDTO> getAll();
}