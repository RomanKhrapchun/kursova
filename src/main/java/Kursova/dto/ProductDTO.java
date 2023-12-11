package Kursova.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Positive
    private BigDecimal price;
    private int productAmount;
    private String imageUrl;
}
