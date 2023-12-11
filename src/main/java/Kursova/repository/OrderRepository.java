package Kursova.repository;

import Kursova.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o.* FROM order_details o WHERE o.creator = :userId", nativeQuery=true)
    List<Order> findForUser(Long userId);
}
