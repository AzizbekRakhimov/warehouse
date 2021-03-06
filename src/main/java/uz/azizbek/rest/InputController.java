package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Input;
import uz.azizbek.payload.InputDto;
import uz.azizbek.service.InputService;

@RestController
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputService inputService;

    @PostMapping
    public ResponseData post(@RequestBody InputDto inputDto){
        return inputService.post(inputDto);
    }

    @GetMapping
    public Page<Input> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return inputService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id){
        return inputService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id){
        return inputService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody InputDto inputDto){
        return inputService.edit(id, inputDto);
    }


}
