package com.revature.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Component
@Entity
@Table(name = "trips")
@Getter @NoArgsConstructor @AllArgsConstructor @Setter @EqualsAndHashCode @ToString
public class Trip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trips_id")
    private int tripId;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
//    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User driver;

    // Riders array can be empty because we don't prepopulate them
    @ManyToMany
//    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "riders", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private List<User> riders;

    @NotNull
    private int availableSeats;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address destination;
}