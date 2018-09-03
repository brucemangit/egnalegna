package com.solutionladder.ethearts.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Category;

/**
 * Repository interface for Category
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
