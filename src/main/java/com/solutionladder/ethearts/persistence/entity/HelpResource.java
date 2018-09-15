package com.solutionladder.ethearts.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Anything related to the help that can be file, video, document..
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Entity
public class HelpResource extends DatedEntity {

    @NotNull
    @ManyToOne
    private Help help;

    /**
     * Initial assumption: if there is no any resource given, mark it as
     * comment.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinColumn(nullable = true, name = "resource_id")
    private Resource resource;

    /**
     * help resource owner.
     */
    @OneToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(nullable = true, name = "member_id")
    private Member member;

    @NotEmpty
    private String comment;

    public Help getHelp() {
        return help;
    }

    public void setHelp(Help help) {
        this.help = help;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return this.member;
    }
}
