package com.example.demo.entities;
import lombok.*; //i do love boiletplate lol 
import java.io.Serializable;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name="users")
public class User implements Serializable {
  private static final long serialVersionUID = 2025L; //needed to safely serialize 
  
  @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="email", nullable=false, unique=true)
  private String email;
  @Column(name="name", nullable=false, unique=false)
  private String name;
  @Column(name="password", nullable=false)
  private String password;
  //for roles I don't know
  //@Column(name="role", nullable=false)
  //private String role;

  @Enumerated(EnumType.STRING)
  @Column(name="role", nullable=false)
  private UserRole role;
}
