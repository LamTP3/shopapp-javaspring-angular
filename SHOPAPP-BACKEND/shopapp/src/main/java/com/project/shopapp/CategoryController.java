package com.project.shopapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/categories")
public class CategoryController {
    // Hiển thị tất cả các categories
    @GetMapping("")
    public ResponseEntity<String> getAllCategories(){
        return ResponseEntity.ok( "Test");
    }

    @PostMapping("")
    public ResponseEntity<String> insertCategory(){
        return ResponseEntity.ok( "Test create");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok( "Test update with" + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok( "Test delete with" +id);
    }

}
