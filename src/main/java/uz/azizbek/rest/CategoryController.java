package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Category;
import uz.azizbek.model.Measurement;
import uz.azizbek.payload.CategoryDto;
import uz.azizbek.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public Page<Category> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @PostMapping
    public ResponseData add(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return categoryService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return categoryService.edit(id, categoryDto);
    }
}
