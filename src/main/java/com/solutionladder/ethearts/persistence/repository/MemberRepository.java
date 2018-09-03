package com.solutionladder.ethearts.persistence.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Member;

/**
 * Repository inteface for member
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPasswordHash(String email, String passwordHash);
}
