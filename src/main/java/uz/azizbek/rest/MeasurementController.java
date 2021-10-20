package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Measurement;
import uz.azizbek.model.Warehouse;
import uz.azizbek.service.MeasurementService;
import uz.azizbek.service.WarehouseService;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @GetMapping
    public Page<Measurement> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return measurementService.getAll(pageable);
    }

    @PostMapping
    public ResponseData addMeasurement(@RequestBody Measurement measurement) {
        return measurementService.save(measurement);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return measurementService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return measurementService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody Measurement measurement) {
        return measurementService.edit(id, measurement);
    }
}
