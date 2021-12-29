package com.yu.bt.mybt.controllers;


import com.yu.bt.mybt.models.ERole;
import com.yu.bt.mybt.models.Role;
import com.yu.bt.mybt.models.User;
import com.yu.bt.mybt.repository.RoleRepository;
import com.yu.bt.mybt.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/roles")
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Role> getRoles(){
        return roleRepo.findAll();
    }


    @PostMapping("/user/{userId}/role/{roleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignRole(@PathVariable(value = "userId") Long userId,
                                        @PathVariable(value = "roleName") String roleName) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        Set<Role> roles = new HashSet<>();
switch (roleName){
    case "admin":
        Role roleAdmin = roleRepo.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(roleAdmin);
    case "approver":
        Role roleApprover = roleRepo.findByName(ERole.ROLE_APPROVER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(roleApprover);
    case "agent":
        Role roleAgent = roleRepo.findByName(ERole.ROLE_AGENT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(roleAgent);
}
        user.setRoles(roles);
        userRepo.save(user);

        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/user/{userId}/role/{roleName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRole(@PathVariable(value = "userId") Long userId,
                                        @PathVariable(value = "roleName") String roleName) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));
        Set<Role> userRoles = user.getRoles();
        switch (roleName){
            case "admin":
                Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                userRoles.remove(adminRole);
                user.setRoles(userRoles);
            case "approver":
                Role approverRole = roleRepo.findByName(ERole.ROLE_APPROVER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                userRoles.remove(approverRole);
                user.setRoles(userRoles);
            case "agent":
                Role agentRole = roleRepo.findByName(ERole.ROLE_AGENT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                userRoles.remove(agentRole);
                user.setRoles(userRoles);
        }
        userRepo.save(user);
        return ResponseEntity.ok().build();

    }
}
