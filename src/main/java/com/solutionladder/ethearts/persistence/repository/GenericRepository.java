package com.solutionladder.ethearts.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Lookup;

/**
 * Repository generic repository
 * For now only persisting objects extending from Lookup only.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Repository
public interface GenericRepository<T extends Lookup> extends CrudRepository<T, Long> {

}
