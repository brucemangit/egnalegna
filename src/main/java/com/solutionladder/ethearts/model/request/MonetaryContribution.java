package com.solutionladder.ethearts.model.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import com.solutionladder.ethearts.persistence.entity.Help;

/**
 * Request mapper for the monetary contribution from member
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public class MonetaryContribution {
    @NotEmpty
    private Help help;
    
    @NotEmpty
    @DecimalMin(value="0.01")
    private double amount;

    public Help getHelp() {
        return help;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    } 
}
