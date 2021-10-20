package uz.azizbek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Measurement;
import uz.azizbek.model.Warehouse;
import uz.azizbek.repository.MeasurementRepository;
import uz.azizbek.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Page<Measurement> getAll(Pageable pageable) {
        return measurementRepository.findAllByActiveTrue(pageable);
    }

    public ResponseData save(Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByName(measurement.getName());
        if (optionalMeasurement.isEmpty()){
            measurementRepository.save(measurement);
            return new ResponseData("Successfully saved", true);
        }

        if (optionalMeasurement.get().isActive())
            return new ResponseData("Warehouse already exist", false);

        measurement.setId(optionalMeasurement.get().getId());
        measurement.setActive(true);
        measurementRepository.save(measurement);
        return new ResponseData("Successfully saved", true);
    }

    public ResponseData findOne(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByIdAndActiveTrue(id);
        return optionalMeasurement.map(warehouse -> new ResponseData("Success", true, optionalMeasurement.get()))
                .orElseGet(() -> new ResponseData("Warehouse does not exist", false));

    }

    public ResponseData delete(Long id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByIdAndActiveTrue(id);

        if (optionalMeasurement.isEmpty())
            return new ResponseData("Not found", false);

        Measurement measurement = optionalMeasurement.get();
        measurement.setActive(false);
        measurementRepository.save(measurement);

        return new ResponseData("Successfully deleted", true);
    }

    public ResponseData edit(Long id, Measurement measurement) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findByIdAndActiveTrue(id);
        if (optionalMeasurement.isEmpty()){
            return new ResponseData("Warehouse does not exist", false);
        }
        Optional<Measurement> byName = measurementRepository.findByNameAndActiveFalse(measurement.getName());
        byName.ifPresent(value -> measurementRepository.delete(value));

        measurement.setId(id);
        measurementRepository.save(measurement);
        return new ResponseData("Successfully saved", true);
    }
}
