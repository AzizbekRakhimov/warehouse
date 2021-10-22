package uz.azizbek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Currency;
import uz.azizbek.model.Input;
import uz.azizbek.model.Supplier;
import uz.azizbek.model.Warehouse;
import uz.azizbek.payload.InputDto;
import uz.azizbek.repository.CurrencyRepository;
import uz.azizbek.repository.InputRepository;
import uz.azizbek.repository.SupplierRepository;
import uz.azizbek.repository.WarehouseRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    public ResponseData post(InputDto inputDto) {

        Optional<Supplier> bySupplierId = supplierRepository.findById(inputDto.getSupplierId());
        if (bySupplierId.isEmpty())
            return new ResponseData("supplier not found", false);

        Optional<Warehouse> byWarehouseId = warehouseRepository.findById(inputDto.getWarehouseId());
        if (byWarehouseId.isEmpty())
            return new ResponseData("warehouse not found", false);

        Optional<Currency> byCurrencyId = currencyRepository.findById(inputDto.getCurrencyId());
        if (byCurrencyId.isEmpty())
            return new ResponseData("currency not found", false);

        Input input = new Input();
        input.setDate(LocalDateTime.now());
        input.setCurrency(byCurrencyId.get());
        input.setSupplier(bySupplierId.get());
        input.setWarehouse(byWarehouseId.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);

        return new ResponseData("saved", true);
    }

    public Page<Input> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return inputRepository.findAll(pageable);
    }

    public ResponseData findOne(Long id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        return optionalInput.map(input -> new ResponseData("success", true, input)).orElseGet(() -> new ResponseData("not found", false));
    }

    public ResponseData delete(Long id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()) {
            inputRepository.deleteById(id);
            return new ResponseData("successfully deleted", true);
        }
        return new ResponseData("not found", false);
    }

    public ResponseData edit(Long id, InputDto inputDto) {

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return new ResponseData("input not found", false);
        }

        Optional<Supplier> bySupplierId = supplierRepository.findById(inputDto.getSupplierId());
        if (bySupplierId.isEmpty())
            return new ResponseData("supplier not found", false);

        Optional<Warehouse> byWarehouseId = warehouseRepository.findById(inputDto.getWarehouseId());
        if (byWarehouseId.isEmpty())
            return new ResponseData("warehouse not found", false);

        Optional<Currency> byCurrencyId = currencyRepository.findById(inputDto.getCurrencyId());
        if (byCurrencyId.isEmpty())
            return new ResponseData("currency not found", false);

        Input input = new Input();
        input.setId(id);
        input.setCurrency(byCurrencyId.get());
        input.setSupplier(bySupplierId.get());
        input.setWarehouse(byWarehouseId.get());
        input.setFactureNumber(inputDto.getFactureNumber());
        inputRepository.save(input);

        return new ResponseData("saved", true);
    }
}
