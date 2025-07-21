package com.arslan.E_voting.Backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "elections")
public class Election {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private Integer eligibilityMinAge;
    private String eligibilityRegion;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createdBy;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private List<Vote> votes;

    // Getters, Setters, Constructors
}

