package uz.azizbek.rest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.azizbek.common.ResponseData;
import uz.azizbek.model.Attachment;
import uz.azizbek.model.Currency;
import uz.azizbek.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/info")
    public Page<Attachment> getAllImagesInfo(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return attachmentService.getAllImagesInfo(pageable);
    }

    @SneakyThrows
    @PostMapping
    public ResponseData add(MultipartHttpServletRequest httpServletRequest) {
        return attachmentService.save(httpServletRequest);
    }

    @GetMapping("/{id}")
    public ResponseData findOne(@PathVariable Long id, HttpServletResponse response) {
        return attachmentService.findOne(id, response);
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable Long id) {
        return attachmentService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseData edit(@PathVariable Long id, @RequestBody MultipartFile multipartFile) {
        return attachmentService.edit(id, multipartFile);
    }
}
