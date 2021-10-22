package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Client;
import uz.azizbek.model.Product;
import uz.azizbek.payload.ProductDto;
import uz.azizbek.service.ClientService;
import uz.azizbek.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public Page<Product> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return productService.getAll(pageable);
    }

    @PostMapping
    public ResponseData add(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return productService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.edit(id, productDto);
    }
}
