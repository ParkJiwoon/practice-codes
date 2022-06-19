package com.example.springthymeleaf.upload;


import com.example.springthymeleaf.form.domain.item.Item;
import com.example.springthymeleaf.upload.domain.FileStore;
import com.example.springthymeleaf.upload.domain.ItemForm;
import com.example.springthymeleaf.upload.domain.UploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form,
                           RedirectAttributes redirectAttributes) throws IOException {

        UploadFile uploadFile = fileStore.storeFile(form.getAttachFile());

        return "redirect:/items/{itemId}";
    }

    @ResponseBody
    @GetMapping("/test")
    public ResponseEntity<String> test(HttpServletRequest request) {
        String path = Objects.requireNonNull(this.getClass().getResource("")).getPath();
        String realPath = path.split("/build")[0];
        log.info("realPath={}", realPath);
        String path2 = realPath + "/src/main/resources/static";
        log.info("path2={}", path2);
        return ResponseEntity.ok("hello world");
    }
}
