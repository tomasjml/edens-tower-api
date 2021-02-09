package com.edenstower.api.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public enum Rol {
        Admin, Client
    }

    @Id
    @NotNull
    private String username;

    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "email_address", nullable = false)
    @NotNull
    private String email;

    @Column(name = "created_at", nullable = false)
    @NotNull
    private Date createdAt;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Admin', 'Client')")
    @NotNull
    private Rol rol;
}
