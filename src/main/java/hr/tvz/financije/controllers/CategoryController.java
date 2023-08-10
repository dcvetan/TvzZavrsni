package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.commands.CategoryCommand;
import hr.tvz.financije.services.CategoryService;
import hr.tvz.financije.services.models.CategoryDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody @Valid CategoryCommand command) {
        return ResponseEntity.ok(service.saveCategory(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable int id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
