package com.solutionladder.ethearts.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Contribution;

/**
 * Repository inteface for Help
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Repository
public interface ContributionRepository extends CrudRepository<Contribution, Long> {
}
