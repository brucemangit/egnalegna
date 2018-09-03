package com.solutionladder.ethearts.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Deposit;

/**
 * Repository for deposit(money)
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date 11 - Aug - 2018
 */
@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
