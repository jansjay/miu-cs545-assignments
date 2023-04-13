package edu.miu.cs545.spring.config.sysinit;

import edu.miu.cs545.spring.models.Role;
import edu.miu.cs545.spring.models.User;
import edu.miu.cs545.spring.repositories.RoleRepository;
import edu.miu.cs545.spring.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthEntryCreator {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        List<Role> roles = new ArrayList<>();
        Role role = roleRepository.findById("Admin").orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole("ADMIN");
            role = roleRepository.save(role);
        }
        roles.add(role);

        role = roleRepository.findById("CLIENT").orElse(null);
        if (role == null) {
            role = new Role();
            role.setRole("CLIENT");
            role = roleRepository.save(role);
        }
        roles.add(role);

        User existingUser = userRepository.findByName("admin").orElse(null);
        if (existingUser != null) {
            existingUser.setPassword(bCryptPasswordEncoder.encode("admin"));
            userRepository.save(existingUser);
        }
        else {
            existingUser = new User();
            existingUser.setName("admin");
            existingUser.setPassword(bCryptPasswordEncoder.encode("admin"));
            userRepository.save(existingUser);
        }
        List<User> users = new ArrayList<>();
        users.add(existingUser);
        for(Role savedRole : roles){
            savedRole.setUsers(users);
            roleRepository.save(savedRole);
        }
    }

}