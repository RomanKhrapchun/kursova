package Kursova.resource;

import Kursova.dto.ProductDTO;
import Kursova.resource.service.ProductService;
import Kursova.resource.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductResource {
    @Autowired
    private ProductService productService = new ProductServiceImpl();

    @PostMapping("/create")
    public String create(@Valid @RequestBody final ProductDTO productDTO) {
        productService.create(productDTO);
        return "all good";
    }

    @GetMapping("/{id}")
    public ProductDTO read(final @PathVariable Long id) {
        return productService.read(id);
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        productService.update(productDTO);
        return ResponseEntity.ok(productDTO);

    }

    @DeleteMapping("/{id}")
    public String delete(final @PathVariable("id") Long id) {
        ProductDTO productDTO = productService.read(id);
        productService.delete(id);
        return "Product:" + productDTO.toString();
    }

    @GetMapping(value = "/all")
    public List<ProductDTO> getAll() {
        return productService.getAll();
    }

}