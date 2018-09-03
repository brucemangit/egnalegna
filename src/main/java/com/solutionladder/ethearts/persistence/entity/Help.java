package com.solutionladder.ethearts.persistence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model representing help database
 * 
 * Help is where someone will be looking for some help from a fellow. The member
 * will put as to why he/she needed the help. The help will be associated with
 * category.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Entity
public class Help extends DatedEntity {

    @ManyToMany
    @JoinTable(name = "help_category", joinColumns = @JoinColumn(name = "help_id", referencedColumnName = "id", unique = false), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id", unique = false))
    @NotNull(message = "Category cannot be blank")
    private List<Category> categories;

    @NotEmpty(message = "Message can not be blank")
    @Column(columnDefinition="TEXT")
    private String message;

    @NotEmpty(message = "Title can not be null")
    private String title;

    @ManyToOne
    private Member member;

    @NotNull(message = "help type not selected")
    @ManyToMany
    private List<HelpType> helpType;

    @OneToMany(mappedBy = "help", fetch = FetchType.LAZY)
    private List<MonetaryDonation> donations;

    @OneToMany(mappedBy = "help", fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    private List<HelpResource> resources;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<HelpType> getHelpType() {
        return helpType;
    }

    public void setHelpType(List<HelpType> helpType) {
        this.helpType = helpType;
    }

    public void addHelpType(HelpType helpType) {
        this.helpType = Optional.ofNullable(this.helpType).orElse(Collections.emptyList());
        this.helpType.add(helpType);
    }

    public void addCategory(Category category) {
        this.categories = Optional.ofNullable(this.categories).orElse(Collections.emptyList());
        this.categories.add(category);
    }

    public void setDonations(List<MonetaryDonation> donations) {
        this.donations = donations;
    }

    @JsonIgnore()
    public List<MonetaryDonation> getDonations() {
        return this.donations;
    }

    public void addDonations(MonetaryDonation donation) {
        if (this.donations == null) {
            this.donations = new ArrayList<MonetaryDonation>();
        }

        this.donations.add(donation);
    }

    /**
     * Add resources to help
     * 
     * @param resource
     */
    public void addHelpResource(HelpResource resource) {
        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }

        this.resources.add(resource);
    }

    /**
     * set resources
     * @param resources
     */
    public void setResources(List<HelpResource> resources) {
        this.resources = resources;
    }

    /**
     * get resources
     * @return
     */
    @JsonIgnore()
    public List<HelpResource> getResources() {
        return this.resources;
    }

    public AddObject<Category> add() {
        return (s, t) -> {
            if (s == null) {
                s = new ArrayList<Lookup>();
            }
        };
    }

}

@FunctionalInterface
interface AddObject<T> {
    public void addObject(List<? extends Lookup> s, T t);
}