package uz.azizbek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Supplier;
import uz.azizbek.model.Warehouse;
import uz.azizbek.model.Worker;
import uz.azizbek.payload.WorkerDto;
import uz.azizbek.repository.SupplierRepository;
import uz.azizbek.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Page<Supplier> getAll(Pageable pageable) {
        return supplierRepository.findAllByActiveTrue(pageable);
    }

    public ResponseData save(Supplier supplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findByPhone(supplier.getPhone());

        if (supplierOptional.isPresent()) {
            if (supplierOptional.get().isActive()) {
                return new ResponseData("Supplier already exist", false);
            }
            supplier.setId(supplierOptional.get().getId());
            supplier.setActive(true);
        }

        supplierRepository.save(supplier);

        return new ResponseData("Successfully saved", true, supplier);
    }

    public ResponseData findOne(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveTrue(id);
        return optionalSupplier.map(supplier -> new ResponseData("Success", true, supplier))
                .orElseGet(() -> new ResponseData("Supplier does not exist", false));
    }

    public ResponseData delete(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findByIdAndActiveTrue(id);

        if (optionalSupplier.isEmpty())
            return new ResponseData("Not found", false);

        Supplier supplier = optionalSupplier.get();
        supplier.setActive(false);
        supplierRepository.save(supplier);

        return new ResponseData("Successfully deleted", true);
    }

    public ResponseData edit(Long id, Supplier supplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findByIdAndActiveTrue(id);
        if (supplierOptional.isEmpty()) {
            return new ResponseData("Supplier does not exist", false);
        }
        Optional<Supplier> byPhoneNumber = supplierRepository.findByPhoneAndActiveFalse(supplier.getPhone());
        byPhoneNumber.ifPresent(value -> supplierRepository.delete(value));

        supplier.setId(id);
        supplierRepository.save(supplier);

        return new ResponseData("Successfully saved", true, supplier);
    }
}
