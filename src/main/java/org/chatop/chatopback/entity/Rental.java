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
 * Represents a rental property in the system.
 */
@Getter
@Setter
@Entity
@Table(name = "rentals")
@DynamicInsert
@DynamicUpdate
public class Rental {

    /**
     * Unique identifier for the rental.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Name of the rental property.
     * Must not be empty.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String name;

    /**
     * Surface area of the rental property in square meters.
     * Must be a positive number.
     */
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 0)
    @Column(nullable = false)
    private Long surface;

    /**
     * Price of the rental property.
     * Must be a positive or zero number.
     */
    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 0)
    @Column(nullable = false)
    private Long price;

    /**
     * URL of the picture of the rental property.
     * Must not be empty.
     */
    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String picture;

    /**
     * Description of the rental property.
     * Must not be empty and can have a maximum length of 2000 characters.
     */
    @NotBlank
    @Size(max = 2000)
    @Column(nullable = false, length = 2000)
    private String description;

    /**
     * Creation date and time of the rental property.
     * Cannot be modified after creation.
     */
    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    /**
     * Last update date and time of the rental property.
     */
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    /**
     * Owner of the rental property.
     * Must not be null.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    /**
     * Set of messages associated with the rental property.
     */
    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> messages = new LinkedHashSet<>();

}