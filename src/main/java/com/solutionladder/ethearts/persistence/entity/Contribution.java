package com.solutionladder.ethearts.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Model representing Contribution table
 * 
 * Contribution is a response for the help requested. Usually this is in message
 * format.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Entity
public class Contribution extends DatedEntity{
    
    @ManyToOne
    @NotNull(message = "Help cannot be blank")
    private Help help;

    @NotEmpty(message = "Message can not be blank")
    private String message;

    @ManyToOne
    @NotNull(message = "member can not be null")
    private Member member;

    @ManyToOne
    @NotNull(message = "help type cannot be null")
    private HelpType helpType;

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member contributor) {
        this.member = contributor;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public Help getHelp() {
        return this.help;
    }

    public HelpType getHelpType() {
        return helpType;
    }

    public void setHelpType(HelpType helpType) {
        this.helpType = helpType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}