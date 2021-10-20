package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Currency;
import uz.azizbek.model.Measurement;
import uz.azizbek.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public Page<Currency> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return currencyService.getAll(pageable);
    }

    @PostMapping
    public ResponseData add(@RequestBody Currency currency) {
        return currencyService.save(currency);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return currencyService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return currencyService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody Currency currency) {
        return currencyService.edit(id, currency);
    }
}
