package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.CategoryCommand;
import hr.tvz.financije.repositories.CategoryRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CategoryRecord;
import hr.tvz.financije.services.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getCategories() {
        // todo real profileId
        return repository.getCategories(1).stream().map(this::mapToCategory).toList();
    }

    public Category saveCategory(CategoryCommand command) {
        // todo real profileId
        return mapToCategory(repository.saveCategory(mapToCategoryRecord(command, 1)));
    }

    public void deleteCategoryById(int id) {
        repository.deleteCategoryById(id);
    }

    private Category mapToCategory(CategoryRecord record) {
        return new Category(record.getId(),
                record.getName(),
                record.getIcon(),
                record.getColor(),
                record.getProfileId() == null);
    }

    private CategoryRecord mapToCategoryRecord(CategoryCommand command, int profileId) {
        return new CategoryRecord(command.id(),
                command.name(),
                command.icon(),
                command.color(),
                profileId);
    }
}
