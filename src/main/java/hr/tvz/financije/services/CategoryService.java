package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.CategoryCommand;
import hr.tvz.financije.repositories.CategoryRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CategoryRecord;
import hr.tvz.financije.security.services.UserDetailsServiceImpl;
import hr.tvz.financije.services.models.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;
    private final UserDetailsServiceImpl userDetailsService;

    public List<CategoryDto> getCategories() {
        int currentProfileId = userDetailsService.getCurrentUserProfileId();
        return repository.getCategories(currentProfileId).stream().map(this::mapToCategoryDto).toList();
    }

    public CategoryDto saveCategory(CategoryCommand command) {
        int currentProfileId = userDetailsService.getCurrentUserProfileId();
        return mapToCategoryDto(repository.saveCategory(mapToCategoryRecord(command, currentProfileId)));
    }

    public void deleteCategoryById(int id) {
        repository.deleteCategoryById(id);
    }

    private CategoryDto mapToCategoryDto(CategoryRecord record) {
        return new CategoryDto(record.getId(),
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
