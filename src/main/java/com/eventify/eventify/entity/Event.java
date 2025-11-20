package com.eventify.eventify.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title's field can't be empty")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "description's field can't be empty")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "locations's field can't be empty")
    @Column(nullable = false)
    private String location;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    private Integer capacity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;


}
