package Kursova.mapper;

import Kursova.dto.ProductDTO;
import Kursova.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class MapperProduct {

    public ProductDTO toDto(final Product product) {
        final ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setProductAmount(product.getProductAmount());
        return dto;
    }

    public Product toEntity(final ProductDTO dto) {
        final Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setProductAmount(dto.getProductAmount());
        return product;
    }
}
