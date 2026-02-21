package com.example.demo.controller;
import com.example.demo.dto.LoginRequest;
import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo.security.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost/") //frontend
public class UserController {
  
  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    User newUser = userService.register(user);
    return ResponseEntity.ok(newUser);
  }
  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User user){
    User newUser = userService.createUser(user);
    return ResponseEntity.ok(newUser);
  }
  @PostMapping("/login")
  public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest){
    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword(); // if this prints null → JSON mismatch
  
    System.out.println("Email: " + email);
    System.out.println("Password: " + password);
    User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
    Map<String, Object> response = new HashMap<>();
    System.out.println("Email: " + loginRequest.getEmail());
    System.out.println("Password: " + loginRequest.getPassword());
    if(user != null){
      response.put("message","Login ok");
      response.put("user", user);
      return ResponseEntity.ok(response);
    } else {
      response.put("message:", "Login failed, try again");
      //System.out.println("Response status:", response.status);
      //System.out.println("Response text:", await response.clone().text());
      return ResponseEntity.status(401).body(response);
    }
  }
  @GetMapping("/me")
  public ResponseEntity<?> getCurrentUser(Authentication authentication) {
    if (authentication == null) {
      return ResponseEntity.status(401).body("Not authenticated :c");
    }
    String email = authentication.getName();
    User user = userRepository.findByEmail(email);
    if (user == null) {
      return ResponseEntity.status(404).body("User not found or does not exist :c");
    }
    return ResponseEntity.ok(user);
  }
  @GetMapping("/roleUser")
  public List<User> getUsersWithRoleUser(){
    return userService.getAllUsersWithRoleUser();
  }
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id){
    User user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  //@PreAuthorize("hasRole('admin')")
  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    List<User> AllUsers = userService.getAllUsers();
    return ResponseEntity.ok(AllUsers);
  }
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    User updateUser = userService.updateUser(id, userDetails);
    return ResponseEntity.ok(updateUser);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
  @PutMapping("/{id}/promote")
  public ResponseEntity<User> promoteToAdmin(@PathVariable Long id) {
    User promotedUser = userService.promoteToAdmin(id);
    return ResponseEntity.ok(promotedUser);
  }
}
