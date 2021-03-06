package uz.azizbek.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Output;
import uz.azizbek.payload.OutputDto;
import uz.azizbek.service.OutputService;

@RestController
@RequestMapping("/output")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public ResponseData post(@RequestBody OutputDto outputDto){
        return outputService.post(outputDto);
    }

    @GetMapping
    public Page<Output> getAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return outputService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id){
        return outputService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id){
        return outputService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody OutputDto outputDto){
        return outputService.edit(id, outputDto);
    }

}
