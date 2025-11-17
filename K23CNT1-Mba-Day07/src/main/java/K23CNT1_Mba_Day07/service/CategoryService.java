package K23CNT1_Mba_Day07.service;

import K23CNT1_Mba_Day07.entity.Category;
import K23CNT1_Mba_Day07.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lấy danh sách
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Lấy category theo id
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Create / Update
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Xóa category theo id
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}