package com.solutionladder.ethearts.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Member;
import com.solutionladder.ethearts.persistence.entity.Role;

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
    
    @Query(value="SELECT r FROM Role r WHERE r.name = :name")
    Role getRole(String name);
}
