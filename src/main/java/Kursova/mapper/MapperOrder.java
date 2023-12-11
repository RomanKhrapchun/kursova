package Kursova.mapper;

import Kursova.dto.OrderDTO;
import Kursova.entity.Order;
import org.springframework.stereotype.Component;


@Component
public class MapperOrder {

    public OrderDTO toDto(final Order order) {
        final OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setProduct(order.getProduct());
        dto.setProductAmount(order.getProductAmount());
        dto.setDateCreated(order.getDateCreated());
        return dto;
    }

    public Order toEntity(final OrderDTO dto) {
        final Order order = new Order();
        order.setId(dto.getId());
        order.setProduct(dto.getProduct());
        order.setProductAmount(dto.getProductAmount());
        return order;
    }
}
