package Kursova.resource.service.impl;

import Kursova.dto.ProductDTO;
import Kursova.entity.Product;
import Kursova.mapper.MapperProduct;
import Kursova.repository.ProductRepository;
import Kursova.resource.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MapperProduct mapperProduct = new MapperProduct();

    @Override
    public ProductDTO create(ProductDTO productDTO) {
        Product product = mapperProduct.toEntity(productDTO);
        productRepository.save(product);
        return mapperProduct.toDto(product);
    }

    @Override
    public ProductDTO read(Long id) {
        return mapperProduct.toDto(productRepository.findById(id).orElseThrow());
    }

    @Override
    public void update(ProductDTO dto) {
        productRepository.save(mapperProduct.toEntity(dto));
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(productRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProductDTO> getAll() {
        return productRepository.findAll().stream().map(mapperProduct::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean decrementProductStock(Long productId, int amountToDecrement) {
        Optional<Product> productOptional = productRepository.findById((long) productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            int currentStock = product.getProductAmount(); // Replace getStock with the actual method to get the stock

            if (currentStock >= amountToDecrement) {
                int newStock = currentStock - amountToDecrement;
                product.setProductAmount(newStock); // Replace setStock with the actual method to set the new stock
                productRepository.save(product);
                return true;
            } else {
                // Not enough stock to decrement
                return false;
            }
        } else {
            // Product not found
            return false;
        }
    }
}
