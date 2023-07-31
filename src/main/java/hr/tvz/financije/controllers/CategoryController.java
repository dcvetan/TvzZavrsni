package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.CategoryCommand;
import hr.tvz.financije.services.CategoryService;
import hr.tvz.financije.services.models.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody @Valid CategoryCommand command) {
        return ResponseEntity.ok(service.saveCategory(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable int id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
