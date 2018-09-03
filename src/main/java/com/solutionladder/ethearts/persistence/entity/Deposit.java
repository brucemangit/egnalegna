package com.solutionladder.ethearts.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * Keeps track of member deposit
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date 11 - Aug - 2018
 */
@Entity
public class Deposit extends DatedEntity {

    @ManyToOne
    private Member member;

    @NotNull(message = "Donation can not be blank")
    @DecimalMin(value = "2.0", message = "Donation starts with 2.0 dollar")
    private Double deposit;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

}
