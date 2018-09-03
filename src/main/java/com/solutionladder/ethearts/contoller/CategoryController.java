package com.solutionladder.ethearts.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutionladder.ethearts.model.response.GenericResponse;
import com.solutionladder.ethearts.persistence.entity.Category;
import com.solutionladder.ethearts.service.CategoryService;

/**
 * Controller class for handling Category.
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * 
 * @todo Move the CORS implementation to its own config
 *
 */
@RestController
@RequestMapping(path="/api/category")
@CrossOrigin(origins = {"http://localhost:8080", "*"})
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path= {"", "/"})
    public Iterable<Category> list() {
        return this.categoryService.getAll();
    }

    /**
     * Update help
     * @param help
     * @return
     */
    @PutMapping(path= {"", "/"})
    public ResponseEntity<Category> edit(
            @Valid @RequestBody Category category) {
        this.categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Create help.
     * @see 
     */
    @PostMapping(
            path= {"/", ""}, 
            consumes="application/json", 
            produces="application/json")
    public ResponseEntity<GenericResponse> create(
            @Valid @RequestBody Category category, BindingResult bindingResult) {
        //validate input from API first
        if (bindingResult.hasErrors()) {
            return this.checkValidationErrors(bindingResult);
        }

        this.categoryService.save(category);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}