package com.solutionladder.ethearts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionladder.ethearts.persistence.entity.Category;
import com.solutionladder.ethearts.persistence.repository.CategoryRepository;

/**
 * Service class for Category
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;
    
    public Category save(Category category) {
        this.categoryRepository.save(category);
        return category;
    }

    public Iterable<Category> getAll() {
        return this.categoryRepository.findAll();
    }
    
    public Category get(Long id) {
        Optional<Category> Category = this.categoryRepository.findById(id);
        if (Category.isPresent()) return Category.get(); 
        return null;
    }
}
