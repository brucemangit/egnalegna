package com.solutionladder.ethearts.contoller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;

import com.solutionladder.ethearts.model.errorhandler.InvalidArgumentException;
import com.solutionladder.ethearts.model.response.GenericResponse;
import com.solutionladder.ethearts.persistence.entity.Member;
import com.solutionladder.ethearts.security.CustomUser;

/**
 * Abstract base controller for controllers
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 *
 */
public abstract class BaseController {

    /**
     * Have access to the logger in each class
     */
    private Logger logger = LogManager.getLogger(this.getClass());
    
    /**
     * When there is error, response the error with common format
     * 
     * @param messages
     * @param status
     * @return
     */
    protected ResponseEntity<GenericResponse> responseError(List<String> messages, HttpStatus status) {
        GenericResponse response = new GenericResponse();
        response.setMessage(messages);
        return new ResponseEntity<>(response, status);
    }

    /**
     * For entities with validation, when the validation failed, this will kick
     * in
     * 
     * @param bindingResult
     * @return
     */
    protected ResponseEntity<GenericResponse> checkValidationErrors(BindingResult bindingResult) {
        /*
         * For the implementation of Exception handling:
         * 
         * @see InvalidArgumentException
         * 
         * @see RequestValidationException
         */
        throw new InvalidArgumentException("Invalid Parameter Detected", bindingResult, null);
    }

    /**
     * To be used by the controllers to get the curretnly logged in member. This
     * is specially vital for editing and saving member related entities
     * 
     * HANDLE WITH CARE: THIS IS FOR A MUST USE CASE ONLY. THAT IS WHEN MEMBER
     * WITH ID IS REQUIRED OF FAIL CASE.
     * 
     * @see ContributionController#deposit
     * @return
     */
    protected Member getCurrentMember() {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            Member member = new Member();
            member.setId(user.getMemberId());

            return member;
        }

        throw new InvalidArgumentException("Please login to proceed", null, null);
    }

    protected GenericResponse getInitalGenericResponse() {
        GenericResponse response = new GenericResponse();
        List<String> messages = new ArrayList<>();
        response.setSuccess(false);
        response.setMessage(messages);

        return response;
    }
    
    protected Logger getLogger() {
        return this.logger;
    }
}
