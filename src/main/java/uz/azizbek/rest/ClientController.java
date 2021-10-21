package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Client;
import uz.azizbek.model.Supplier;
import uz.azizbek.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public Page<Client> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return clientService.getAll(pageable);
    }

    @PostMapping
    public ResponseData add(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id) {
        return clientService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody Client client) {
        return clientService.edit(id, client);
    }
}
