package org.chatop.chatopback.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a message in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "messages")
@DynamicInsert
@DynamicUpdate
public class Message {

    /**
     * Unique identifier for the message.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Content of the message.
     * Must not be empty and can have a maximum length of 2000 characters.
     */
    @NotBlank
    @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    private String message;

    /**
     * Creation date and time of the message.
     * Cannot be modified after creation.
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Last update date and time of the message.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Rental property associated with the message.
     * Must not be null.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;

    /**
     * User who created the message.
     * Must not be null.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}