package com.solutionladder.ethearts.contoller;

import java.util.Collections;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.solutionladder.ethearts.model.request.AuthenticationRequest;
import com.solutionladder.ethearts.model.response.AuthenticationResponse;
import com.solutionladder.ethearts.model.response.GenericResponse;
import com.solutionladder.ethearts.persistence.entity.Member;
import com.solutionladder.ethearts.persistence.repository.MemberRepository;
import com.solutionladder.ethearts.security.JwtTokenProvider;
import com.solutionladder.ethearts.service.MemberService;

/**
 * Controller class for handling members.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * 
 * @todo Move the CORS implementation to its own config
 *
 */
@RestController
@RequestMapping(path = "/api/member")
@CrossOrigin(origins = { "*" })
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository repository; // this has to be moved never use this
                                         // in controller

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(path = { "", "/" }, consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
    public Iterable<Member> list() {
        return this.memberService.getAll();
    }

    /**
     * Update member
     * 
     * @param member
     * @return
     */
    @PutMapping(path = { "", "/" })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Member> edit(@Valid @RequestBody Member member) {
        this.memberService.save(member);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Create member.
     * 
     * @see
     */
    @PostMapping(path = { "/", "" }, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Member> create(@Valid @RequestBody Member member) {
        this.memberService.save(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    /**
     * 
     * @return
     */
    @PostMapping(path = { "/login", "/login/" }, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Member> login(@Valid @RequestBody AuthenticationRequest loginRequest) {
        Member member = this.memberService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (member == null) {
            return new ResponseEntity<>(member, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @DeleteMapping(path = { "/logout", "/logout/" })
    public void exit() {
        /*
         * The implementation for this will be having one more column "deleted"
         * and updating that.
         */
    }

    @RequestMapping(value = "/authenticate", method = { RequestMethod.POST })
    public ResponseEntity<GenericResponse> signin(@RequestBody AuthenticationRequest authReq) {
        GenericResponse response = new GenericResponse();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword()));
            Optional<Member> member = repository.findByEmail(authReq.getEmail());
            String token = jwtTokenProvider.createToken(member.get());

            AuthenticationResponse authResponse = new AuthenticationResponse(member.get().getFirstName(),
                    member.get().getLastName(), member.get().getEmail(), token);

            response.setObject(authResponse);
            response.setSuccess(true);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException e) {
            response.setMessage(Collections.singletonList("Username/Password incorrect"));
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}