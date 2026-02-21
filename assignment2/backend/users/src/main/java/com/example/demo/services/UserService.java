package com.example.demo.services;
import java.util.List;
import java.util.stream.*;
import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import com.example.demo.security.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.user);
        return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      User user = userRepository.findByEmail(email);
      if (user == null) {
        throw new UsernameNotFoundException("User not found with email: " + email);
      }
      return org.springframework.security.core.userdetails.User
        .withUsername(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRole().name().toUpperCase())
        .build();
    }

    public List<User> getAllUsersWithRoleUser() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == UserRole.user)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        if (user != null) {
            user.setEmail(userDetails.getEmail());
            user.setName(userDetails.getName());
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                user.setPassword(userDetails.getPassword());
            }

            user.setRole(UserRole.user);
            return userRepository.save(user);
        }
        return null;
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User promoteToAdmin(Long id) {
      User user = getUserById(id);
      if (user != null) {
        user.setRole(UserRole.admin);
      }
      return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        System.out.println("User from DB: " + email);
        System.out.println("Provided password: " + password);
        //System.out.println(userRepository.findAll());
        //if (user != null && user.getPassword().equals(password)) {
        //    return user;
        //}
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
          return user;
        }
        return null;
    }
}
