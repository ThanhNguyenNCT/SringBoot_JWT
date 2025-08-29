package Cybersoft.ExJPA_Security.services.Imp;

import Cybersoft.ExJPA_Security.entity.Role;
import Cybersoft.ExJPA_Security.entity.User;
import Cybersoft.ExJPA_Security.exception.CentralExceptions;
import Cybersoft.ExJPA_Security.exception.InvalidException;
import Cybersoft.ExJPA_Security.repo.UserRepository;
import Cybersoft.ExJPA_Security.services.AuthenticationService;
import Cybersoft.ExJPA_Security.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private CentralExceptions centralExceptions;

    @Override
    public String checkLogin(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        if(passwordEncoder.matches(password, user.getPassword())) {
            return jwtHelper.generateToken(user.getUsername(), user.getRole().getName());
        }else {
            throw new InvalidException("Invalid password for user with username: " + username);
        }
    }

    @Override
    public void register(String username, String password) {
        System.out.println("Registering user: " + username);
        System.out.println("Password: " + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        Role role = new Role();
        role.setId(2);
        user.setRole(role);

        userRepository.save(user);
    }
}
