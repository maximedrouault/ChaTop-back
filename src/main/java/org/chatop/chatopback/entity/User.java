package org.chatop.chatopback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Represents a user in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    @Max(Integer.MAX_VALUE)
    private Integer id;

    /**
     * Email address of the user.
     * Must be unique and not empty.
     */
    @NotBlank
    @Email
    @Size(max = 255)
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Name of the user.
     * Must not be empty.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    /**
     * Password of the user.
     * Must not be empty.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    /**
     * Creation date and time of the user.
     * Cannot be modified after creation.
     */
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    /**
     * Last update date and time of the user.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Set of rentals associated with the user.
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rental> rentals = new LinkedHashSet<>();

    /**
     * Set of messages associated with the user.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages = new LinkedHashSet<>();

}