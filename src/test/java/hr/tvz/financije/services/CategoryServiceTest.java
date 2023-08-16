package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.CategoryCommand;
import hr.tvz.financije.repositories.CategoryRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CategoryRecord;
import hr.tvz.financije.security.services.UserDetailsServiceImpl;
import hr.tvz.financije.services.models.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserDetailsServiceImpl userDetailsService;
    @InjectMocks
    private CategoryService categoryService;

    private final int PROFILE_ID = 1;

    private final List<CategoryRecord> CATEGORY_RECORDS = List.of(
            new CategoryRecord(1, "name", "icon", "color", 1),
            new CategoryRecord(2, "name2", "icon2", "color2", null)
    );

    private final List<CategoryDto> CATEGORY_DTOS = List.of(
            new CategoryDto(1, "name", "icon", "color", false),
            new CategoryDto(2, "name2", "icon2", "color2", true)
    );

    private final CategoryCommand CATEGORY_COMMAND = new CategoryCommand(
            1, "name", "icon", "color"
    );

    @Test
    void getCategories() {
        when(categoryRepository.getCategories(PROFILE_ID)).thenReturn(CATEGORY_RECORDS);
        when(userDetailsService.getCurrentUserProfileId()).thenReturn(PROFILE_ID);
        List<CategoryDto> categoryDtos = categoryService.getCategories();
        assertEquals(categoryDtos, CATEGORY_DTOS);
    }

    @Test
    void saveCategory() {
        when(categoryRepository.saveCategory(CATEGORY_RECORDS.get(0))).thenReturn(CATEGORY_RECORDS.get(0));
        when(userDetailsService.getCurrentUserProfileId()).thenReturn(PROFILE_ID);
        CategoryDto categoryDto = categoryService.saveCategory(CATEGORY_COMMAND);
        assertEquals(categoryDto, CATEGORY_DTOS.get(0));
    }
}