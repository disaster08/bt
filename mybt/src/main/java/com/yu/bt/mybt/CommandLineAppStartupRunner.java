package com.yu.bt.mybt;

import com.yu.bt.mybt.exception.ResourceNotFoundException;
import com.yu.bt.mybt.models.*;
import com.yu.bt.mybt.repository.CommentRepository;
import com.yu.bt.mybt.repository.IssueRepository;
import com.yu.bt.mybt.repository.RoleRepository;
import com.yu.bt.mybt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    IssueRepository issueRepo;
    @Autowired
    CommentRepository commentRepo;

    @Override
    public void run(String... args) throws Exception {
        Set<Role> roles = new HashSet<>();
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Role ModRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Role UserRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Role approverRole = roleRepository.findByName(ERole.ROLE_APPROVER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        Role agentRole = roleRepository.findByName(ERole.ROLE_AGENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        User admin = new User("admin", "admin@gmail.com", encoder.encode("123456"));
        roles.add(adminRole);
        roles.add(ModRole);
        roles.add(UserRole);
        admin.setRoles(roles);
        userRepository.save(admin);

        User john = new User("john", "john@gmail.com", encoder.encode("123456"));
        roles.remove(adminRole);
        john.setRoles(roles);
        userRepository.save(john);

        User mike = new User("mike", "mike@gmail.com", encoder.encode("123456"));
        roles.remove(ModRole);
        userRepository.save(mike);

        User steve = new User("steve", "steve@gmail.com", encoder.encode("123456"));
        steve.setRoles(roles);
        userRepository.save(steve);

        //approver
        User barry = new User("barry", "barry@gmail.com", encoder.encode("123456"));
        roles.add(approverRole);
        barry.setRoles(roles);
        userRepository.save(barry);

        //agent bug
        User rob = new User("rob", "rob@gmail.com", encoder.encode("123456"));
        roles.add(agentRole);
        roles.remove(approverRole);
        rob.setRoles(roles);
        userRepository.save(rob);

        //agent helpdesk
        User tom = new User("tom", "tom@gmail.com", encoder.encode("123456"));
        roles.add(agentRole);
        rob.setRoles(roles);
        userRepository.save(tom);

        //agent request
        User jim = new User("jim", "jim@gmail.com", encoder.encode("123456"));
        roles.add(agentRole);
        rob.setRoles(roles);
        userRepository.save(jim);

        //Create issues
        Issue issue1 = new Issue();
        issue1.setSummary("Monitor is dead");
        issue1.setIssueType("help desk");
        issue1.setPriority("medium");
        issue1.setDescription("monitor is broken, please help");
        issue1.setAssignee(userRepository.findByUsername("barry").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issue1.setReporter(userRepository.findByUsername("admin").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issueRepo.save(issue1);

        Comment commentForIssue4 = new Comment();
        commentForIssue4.setText("Go get your KB from HD");
        commentForIssue4.setIssue(issue1);
        commentRepo.save(commentForIssue4);

        Comment commentForIssue4Number2 = new Comment();
        commentForIssue4Number2.setText("test comment for issue #4");
        commentForIssue4Number2.setIssue(issue1);
        commentRepo.save(commentForIssue4Number2);

        Issue issue2 = new Issue();
        issue2.setSummary("Laptop problem");
        issue2.setIssueType("help desk");
        issue2.setPriority("high");
        issue2.setDescription("laptop is rebooting every 5 mins");
        issue2.setAssignee(userRepository.findByUsername("barry").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issue2.setReporter(userRepository.findByUsername("admin").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issueRepo.save(issue2);

        Issue issue3 = new Issue();
        issue3.setSummary("1C report is incorrect");
        issue3.setIssueType("bug");
        issue3.setPriority("high");
        issue3.setDescription("report data is incorrect");
        issue3.setAssignee(userRepository.findByUsername("barry").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issue3.setReporter(userRepository.findByUsername("admin").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issueRepo.save(issue3);

        Issue issue4 = new Issue();
        issue4.setSummary("keyboard is not working");
        issue4.setIssueType("help desk");
        issue4.setPriority("high");
        issue4.setDescription("keyboard is dead");
        issue4.setAssignee(userRepository.findByUsername("barry").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issue4.setReporter(userRepository.findByUsername("john").orElseThrow(() -> new ResourceNotFoundException("user not found")).getId());
        issueRepo.save(issue4);


    }
}
