package edu.miu.cs545.spring.controllers;

import edu.miu.cs545.spring.dto.RoleDto;
import edu.miu.cs545.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/admin")
@Controller
public class AdminController {
    @Autowired
    RoleService roleService;
    @GetMapping
    @PreAuthorize("hasRole(ADMIN)")
    public ResponseEntity<Collection<RoleDto>> findAll(){
        return ResponseEntity.ok(roleService.findAllRoles());
    }
}
