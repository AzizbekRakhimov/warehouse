package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Supplier;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.WorkerDto;
import uz.azizbek.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @GetMapping
    public Page<Supplier> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return supplierService.getAll(pageable);
    }

    @PostMapping
    public ResponseData add(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return supplierService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return supplierService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody Supplier supplier) {
        return supplierService.edit(id, supplier);
    }
}
