package com.solutionladder.ethearts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutionladder.ethearts.persistence.entity.Contribution;
import com.solutionladder.ethearts.persistence.entity.Help;
import com.solutionladder.ethearts.persistence.entity.HelpResource;
import com.solutionladder.ethearts.persistence.entity.HelpType;
import com.solutionladder.ethearts.persistence.repository.ContributionRepository;
import com.solutionladder.ethearts.persistence.repository.GenericRepository;
import com.solutionladder.ethearts.persistence.repository.HelpRepository;

/**
 * Service class for Help
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Service
public class HelpService {

    @Autowired
    public HelpRepository helpRepository;

    @Autowired
    public GenericRepository<HelpType> helpTypeRepository;

    @Autowired
    public ContributionRepository contributionRepository;

    public Help save(Help help) {
        this.helpRepository.save(help);
        return help;
    }

    public Iterable<Help> getAll() {
        return this.helpRepository.findAll();
    }

    public HelpType save(HelpType helpType) {
        this.helpTypeRepository.save(helpType);
        return helpType;
    }

    public Contribution saveContribution(Contribution contribution) {
        this.contributionRepository.save(contribution);
        return contribution;
    }

    public Help get(Long id) {
        Optional<Help> Help = this.helpRepository.findById(id);
        if (Help.isPresent())
            return Help.get();
        return null;
    }

    /**
     * Add resource to help.
     * 
     * @param resource
     * @return resource
     */
    public HelpResource saveHelpResource(HelpResource helpResource) {

        if (helpResource == null || helpResource.getHelp() == null || helpResource.getHelp().getId() == null) {
            return null;
        }

        Optional<Help> optionalHelp = this.helpRepository.findById(helpResource.getHelp().getId());

        if (!optionalHelp.isPresent())
            return null;

        Help help = optionalHelp.get();
        help.addHelpResource(helpResource);
        
        //save updated help
        this.helpRepository.save(help);
        return helpResource;
    }

    /**
     * Get list of comments given the id of the help
     * 
     * @param helpId
     * @return
     */
    public List<HelpResource> getComments(Long helpId) {

        if (helpId == null || helpId <= 0) {
            return null;
        }

        return this.helpRepository.getCommentsByHelp(helpId);
    }
}
