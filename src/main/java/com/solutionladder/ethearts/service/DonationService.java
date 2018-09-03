package com.solutionladder.ethearts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionladder.ethearts.persistence.entity.Deposit;
import com.solutionladder.ethearts.persistence.entity.Member;
import com.solutionladder.ethearts.persistence.entity.MonetaryDonation;
import com.solutionladder.ethearts.persistence.repository.DepositRepository;
import com.solutionladder.ethearts.persistence.repository.DonationRepository;

/**
 * Service for Contribution related tasks
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date 11 - Aug - 2018
 */
@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DepositRepository depositRepository;

    /**
     * How much money does the member has for donation. This is the money after
     * all sync'd and pending donations.
     * 
     * @param member
     * @return amount.
     */
    public Double getDonatableAmount(Member member) {
        if (member == null) {
            return 0d;
        }

        return this.donationRepository.getDonatableMoney(member.getId());
    }

    /**
     * Check if member can donate the based on the balance.
     * 
     * @param member
     * @param donation
     * @return boolean
     */
    public boolean canDonate(Member member, Double donation) {
        if (member == null || donation == null) {
            return false;
        }

        Double donatableAmount = getDonatableAmount(member);
        if (donatableAmount != null && donatableAmount > 0) {
            return donatableAmount >= donation;
        }

        return false;
    }

    /**
     * Save deposit.
     * 
     * @param deposit
     * @return Deposit deposit
     */
    public Deposit saveDeposit(Deposit deposit) {
        if (deposit != null) {
            deposit = this.depositRepository.save(deposit);
        }

        return deposit;
    }

    /**
     * Save donation
     * 
     * @param donation
     * @return
     */
    public MonetaryDonation saveDonation(MonetaryDonation donation) {
        if (donation != null) {
            donation = this.donationRepository.save(donation);
        }

        return donation;
    }
}
