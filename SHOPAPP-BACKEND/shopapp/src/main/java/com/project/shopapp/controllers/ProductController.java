package com.project.shopapp.controllers;

import com.project.shopapp.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("app/v1/products")
public class ProductController {

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // Nếu tham số truyền vào là 1 object thì sao ? => Data Transfer Object = Request Object
    public ResponseEntity<?> createProduct(@Valid @ModelAttribute ProductDTO productDTO,
//                                           @RequestPart("file") MultipartFile file,
                                           BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            List<MultipartFile> files = productDTO.getFiles();
            files = files == null ? new ArrayList<MultipartFile>() : files;
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue; // nếu getSize = 0 nhảy sang vòng lặp sau vì file không có ảnh
                }

                // Kiểm tra kích thước và định dạng file
                if (file.getSize() > 10 * 1024 * 1024) { // Kích thức > 10MB
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File is to large: Maximum file is 10MB");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("File must be an image");
                }
                // Lưu file và cập nhập thumbnail trong DTO
                String filename = storeFile(file);
                // lưu vào đối tượng product trong DB => sẽ làm sau
                //Lưu vào bảng product_images
            }
            return ResponseEntity.ok("Test create" + productDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    private String storeFile(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        // Thêm UUID vào trước tên file để đảm bảo tên file duy nhất
        String uniqueFileName = UUID.randomUUID().toString() + "_" + filename;
        // Đường dẫn file đến thư mục bạn muốn lưu file
        java.nio.file.Path uploadDir = Paths.get("uploads");
        // Kiểm tra và tạo thư mục nếu nó không tồn tại
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        // Đường dẫn đầy đủ của file
        java.nio.file.Path destination = Paths.get(uploadDir.toString(), uniqueFileName);
        // Sao chép file vào thư mục đích
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFileName;
    }

    @GetMapping("")
    public ResponseEntity<String> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(String.format("getAllProducts, page = %d, limit = %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAllProductById(@PathVariable("id") String productId) {
        return ResponseEntity.ok("Product with id: " + productId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductB(@PathVariable("id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body("Delete successfully product with id: " + id);
    }
}
