package com.solutionladder.ethearts.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.MonetaryDonation;

/**
 * Repository for donation(money)
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date 11 - Aug - 2018
 */
@Repository
public interface DonationRepository extends CrudRepository<MonetaryDonation, Long> {
    
    @Query(value="select ( select sum(deposit) from deposit where member_id = :memberId) - (select coalesce(sum(contribution),0) from monetary_donation where member_id = :memberId) as current_value", 
            nativeQuery=true)
    public Double getDonatableMoney(Long memberId);
}
