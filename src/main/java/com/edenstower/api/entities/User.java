package com.edenstower.api.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    public enum Rol {
        Admin, Cliente
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Admin', 'Cliente')")
    private Rol rol;
}
