package com.example.server.component;

import com.example.server.repository.PermissionRepository;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PermissionRepository permissionRepository;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
//        if (mode.equals("always")) {
//            List<Permission> permissions = new ArrayList<>();
//            for (PermissionName permissionName : PermissionName.values()) {
//                permissions.add(new Permission(permissionName));
//            }
//            permissionRepository.saveAll(permissions);
//            Role superAdmin = roleRepository.save(new Role("ROLE_ADMIN", new HashSet<>(permissionRepository.findAll())));
//            Role manager = roleRepository.save(new Role("ROLE_MANAGER", new HashSet<>(permissionRepository.findAllByIdIn(Collections.singleton(9L)))));
//            Role customer = roleRepository.save(new Role("ROLE_CLIENT", new HashSet<>(permissionRepository.findAllByIdIn(Collections.singleton(2L)))));
//            userRepository.save(
//                    new User(
//                            "admin",
//                            "admin",
//                            "+998991234567",
//                            "admin",
//
//                            passwordEncoder.encode("admin"),
//                            Collections.singletonList(superAdmin)
//
//                    )
//            );
//            userRepository.save(
//                    new User(
//                            "manager",
//                            "manager",
//                            "+9989912388987",
//                            "manager",
//                            passwordEncoder.encode("manager"),
//                            Collections.singletonList(manager)
//
//                    )
//            );
//            userRepository.save(
//                    new User(
//                            "customer",
//                            "customer",
//                            "+998991289897",
//                            "customer",
//                            passwordEncoder.encode("customer"),
//                            Collections.singletonList(customer)
//
//                    )
//            );
//        }
    }
}