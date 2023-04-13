package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.AuthDto;
import edu.miu.cs545.spring.dto.JwtResponseDto;
import edu.miu.cs545.spring.dto.RefreshDto;
import edu.miu.cs545.spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authenticate")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

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
        return ResponseEntity.ok(authService.getJwtTokens(request.getUsername()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtResponseDto> refresh(@RequestBody RefreshDto refreshDto){
        return ResponseEntity.ok(authService.refresh(refreshDto));
    }

    @RequestMapping("/logout")
    public ResponseEntity<Void> logout(){
        String username = "";
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null &&
                SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        }
        authService.logout(username);
        return ResponseEntity.ok().build();
    }
}
