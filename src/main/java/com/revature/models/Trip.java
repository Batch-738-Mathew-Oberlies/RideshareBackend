package com.revature.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Entity
@Table(name = "trips")
@NoArgsConstructor @Data
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private int tripId;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    // Riders array can be empty because we don't prepopulate them
    @ManyToMany
    @JoinTable(name = "riders", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private List<User> riders;

    @NotNull
    private int availableSeats;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "departure_id")
    private Address departure;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Address destination;
    
    @NotNull
    private LocalDateTime tripDate;
}
