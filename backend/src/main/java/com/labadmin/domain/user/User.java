package com.labadmin.domain.user;
import com.labadmin.domain.common.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
