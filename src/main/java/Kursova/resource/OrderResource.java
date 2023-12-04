package Kursova.resource;

import Kursova.dto.OrderDTO;
import Kursova.repository.OrderRepository;
import Kursova.service.OrderService;
import Kursova.service.ProductService;
import Kursova.service.impl.OrderServiceImpl;
import Kursova.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
public class OrderResource {
    @Autowired
    private OrderService orderService = new OrderServiceImpl();
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    private ProductService productService = new ProductServiceImpl();

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody final OrderDTO orderDto) {
        Long productId;
        try {
            productId = Long.valueOf(orderDto.getProduct());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Product ID is not a valid number");
        }

        int productAmount = orderDto.getProductAmount();
        if (productAmount <= 0) {
            return ResponseEntity.badRequest().body("Product amount must be greater than zero.");
        }

        boolean stockUpdated = productService.decrementProductStock(productId, productAmount);
        if (!stockUpdated) {
            return ResponseEntity.badRequest().body("Insufficient stock for the product.");
        }

        OrderDTO createdOrderDto = orderService.create(orderDto);
        if (createdOrderDto != null) {
            return ResponseEntity.ok(createdOrderDto);
        } else {
            return ResponseEntity.badRequest().body("Failed to create the order.");
        }
    }

    @GetMapping("/{id}")
    public OrderDTO read(final @PathVariable Long id) {
        return orderService.read(id);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDTO> update(@RequestBody OrderDTO orderDTO) {
        orderService.update(orderDTO);
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(final @PathVariable("id") Long id) {
        OrderDTO orderDTO = orderService.read(id);
        orderService.delete(id);
        return "Order:" + orderDTO.toString();
    }

    @GetMapping(value = "/all")
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }
}

