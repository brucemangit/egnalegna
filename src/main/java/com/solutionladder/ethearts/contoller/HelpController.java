package com.solutionladder.ethearts.contoller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solutionladder.ethearts.model.errorhandler.InvalidArgumentException;
import com.solutionladder.ethearts.model.response.GenericResponse;
import com.solutionladder.ethearts.persistence.entity.Contribution;
import com.solutionladder.ethearts.persistence.entity.Help;
import com.solutionladder.ethearts.persistence.entity.HelpResource;
import com.solutionladder.ethearts.persistence.entity.HelpType;
import com.solutionladder.ethearts.service.HelpService;

/**
 * Controller class for handling Help.
 * 
 * This class mainly focuses on facilitating initial help to be posted.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * 
 * @todo Move the CORS implementation to its own config
 *
 */
@RestController
@RequestMapping(path = "/api/help")
@CrossOrigin(origins = { "*" })
public class HelpController extends BaseController {

    @Autowired
    private HelpService helpService;

    @GetMapping(path = { "", "/" })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public Iterable<Help> list() {
        return this.helpService.getAll();
    }

    /**
     * Update help
     * 
     * @param help
     * @return
     */
    @PutMapping(path = { "", "/" })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public ResponseEntity<Help> edit(@Valid @RequestBody Help help) {
        this.helpService.save(help);
        return new ResponseEntity<>(help, HttpStatus.OK);
    }

    /**
     * Save help
     * 
     * @param help
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @PostMapping(path = { "", "/" }, consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public ResponseEntity<Help> save(@Valid @RequestBody Help help)
            throws JsonParseException, JsonMappingException, IOException {

        help.setMember(this.getCurrentMember());
        this.helpService.save(help);
        return new ResponseEntity<>(help, HttpStatus.OK);
    }

    @GetMapping(path = { "{id}", "/{id}" })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public Help getById(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new InvalidArgumentException("Invalid Id", null, null);
        }

        return this.helpService.get(id);
    }

    @PostMapping(path = { "/helptype", "/helptype/" }, consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<GenericResponse> addHelpType(@Valid @RequestBody HelpType helpType,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return this.checkValidationErrors(bindingResult);
        }

        this.helpService.save(helpType);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * Adding contribution per help. Anyone can help to the posted help as
     * contribution. For this to work 1. First use member/authenticate to get
     * the token 2. pass the token through header with key x-auth-token along
     * with the contribute body Sample: {"help":{"id":1}, "message":"I want to
     * help in resource", "member":{"id":1}, "helpType":{"id":1}}
     * 
     * @param contribution
     * @param bindingResult
     * @return
     */
    @PostMapping(path = { "/support", "/support/" }, consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public ResponseEntity<GenericResponse> addHelpContribution(@Valid @RequestBody Contribution contribution,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return this.checkValidationErrors(bindingResult);
        }

        this.helpService.saveContribution(contribution);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * Save the comment associated with the help. This HelpResource will be used
     * when associating with files as well, but if no resource is provided,
     * comment will be handled.
     * 
     * @param HelpResoure
     *            helpResource
     */
    @PostMapping(path = { "/comments", "/comments/" }, consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public ResponseEntity<GenericResponse> addHelpComment(@Valid @RequestBody HelpResource helpResource,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.checkValidationErrors(bindingResult);
        }

        GenericResponse response = new GenericResponse();
        helpResource = this.helpService.saveHelpResource(helpResource);
        
        if (helpResource == null) {
            response.setMessage(Arrays.asList("Please check input values"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        response.setSuccess(true);
        response.setObject(helpResource);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * As of now, comments are basically help resources with blank resource
     * 
     * @todo - get comment by itself, it need moderation..
     * @param helpId
     * @return list of comments
     */
    @GetMapping(path = { "/comments/{helpId}", "/comments/{helpId}/" })
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR') or hasRole('ROLE_USER')")
    public ResponseEntity<GenericResponse> getHelpComments(@PathVariable Long helpId) {
        // get the help first
        List<HelpResource> comments = this.helpService.getComments(helpId);
        if (comments == null) // invalid help
            return null;

        GenericResponse response = new GenericResponse();
        response.setObject(comments);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}