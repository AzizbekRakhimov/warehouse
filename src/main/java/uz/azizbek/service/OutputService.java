package uz.azizbek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Client;
import uz.azizbek.model.Currency;
import uz.azizbek.model.Output;
import uz.azizbek.model.Warehouse;
import uz.azizbek.payload.OutputDto;
import uz.azizbek.repository.ClientRepository;
import uz.azizbek.repository.CurrencyRepository;
import uz.azizbek.repository.OutputRepository;
import uz.azizbek.repository.WarehouseRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ClientRepository clientRepository;

    public ResponseData post(OutputDto outputDto) {
        Optional<Client> byClientId = clientRepository.findById(outputDto.getClientId());
        if (byClientId.isEmpty())
            return new ResponseData("client not found", false);

        Optional<Warehouse> byWarehouseId = warehouseRepository.findById(outputDto.getWarehouseId());
        if (byWarehouseId.isEmpty())
            return new ResponseData("warehouse not found", false);

        Optional<Currency> byCurrencyId = currencyRepository.findById(outputDto.getCurrencyId());
        if (byCurrencyId.isEmpty())
            return new ResponseData("currency not found", false);

        Output output = new Output();
        output.setDate(LocalDateTime.now());
        output.setCurrency(byCurrencyId.get());
        output.setClient(byClientId.get());
        output.setWarehouse(byWarehouseId.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);

        return new ResponseData("saved",true);

    }

    public Page<Output> getAll(Pageable pageable) {
        return outputRepository.findAll(pageable);
    }

    public ResponseData findOne(Long id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return optionalOutput.map(output -> new ResponseData("success", true, output)).orElseGet(() -> new ResponseData("not found", false));
    }

    public ResponseData delete(Long id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isPresent()){
            outputRepository.deleteById(id);
            return new ResponseData("successfully deleted", true);
        }
        return new ResponseData("not found", false);
    }

    public ResponseData edit(Long id, OutputDto outputDto) {

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()){
            return new ResponseData("output not found", false);
        }

        Optional<Client> byClientId = clientRepository.findById(outputDto.getClientId());
        if (byClientId.isEmpty())
            return new ResponseData("client not found", false);

        Optional<Warehouse> byWarehouseId = warehouseRepository.findById(outputDto.getWarehouseId());
        if (byWarehouseId.isEmpty())
            return new ResponseData("warehouse not found", false);

        Optional<Currency> byCurrencyId = currencyRepository.findById(outputDto.getCurrencyId());
        if (byCurrencyId.isEmpty())
            return new ResponseData("currency not found", false);

        Output output = new Output();
        output.setId(id);
        output.setCurrency(byCurrencyId.get());
        output.setClient(byClientId.get());
        output.setWarehouse(byWarehouseId.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        outputRepository.save(output);

        return new ResponseData("saved",true);
    }
}
