package com.basic.themePark.parks.core;

import com.basic.themePark.cities.core.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "parks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Park {
    @Id
    @GeneratedValue
    private UUID id_park;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_city")
    private City city;
    private String description;
}
