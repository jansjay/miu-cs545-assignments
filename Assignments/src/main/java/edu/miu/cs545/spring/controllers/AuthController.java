package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.AuthDto;
import edu.miu.cs545.spring.dto.JwtResponseDto;
import edu.miu.cs545.spring.utils.JwtUtil;
import edu.miu.cs545.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("")
    public ResponseEntity<JwtResponseDto> createToken(@RequestBody AuthDto
                                                request) throws Exception {
        try {
            daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponseDto(jwtToken));
    }
}
