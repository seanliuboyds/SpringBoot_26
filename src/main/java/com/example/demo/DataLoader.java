package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.management.relation.Role;
import java.util.Arrays;
@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new AppRole("USER"));
        roleRepository.save(new AppRole("ADMIN"));

        AppRole adminRole = roleRepository.findByRole("ADMIN");
        AppRole userRole = roleRepository.findByRole("USER");

        AppUser appUser = new
                AppUser("bob@bob.com", "bob", "Bob", "Bobberson", true, "bob");
        appUser.setRoles(Arrays.asList(userRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

         appUser = new
                AppUser("jim@jim.com", "jim", "Jim", "Jimmerson", true, "jim");
        appUser.setRoles(Arrays.asList(userRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

        appUser = new
                AppUser("admin@adm.com", "pass", "Admin", "User", true, "admin");
        appUser.setRoles(Arrays.asList(adminRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

        appUser = new
                AppUser("sam@ev.com", "pass", "Sam", "Everyman", true, "sam");
        appUser.setRoles(Arrays.asList(userRole, adminRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);



    }
}
