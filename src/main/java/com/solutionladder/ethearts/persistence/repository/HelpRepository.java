package com.solutionladder.ethearts.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solutionladder.ethearts.persistence.entity.Help;
import com.solutionladder.ethearts.persistence.entity.HelpResource;

/**
 * Repository inteface for Help
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
@Repository
public interface HelpRepository extends CrudRepository<Help, Long> {
    
    /**
     * As of this writing, the comment corresponding to the help is considered as resource except it has 
     * no associated resource with it, like file or link..
     *  
     * @param helpId
     * @return
     */
    @Query(value="SELECT r FROM HelpResource r WHERE r.resource IS NULL AND r.help.id =:helpId")
    public List<HelpResource> getCommentsByHelp(Long helpId);
}
