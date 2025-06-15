package com.basic.themePark.cities.core;

import com.basic.themePark.provinces.core.Province;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class City {
    @Id
    @GeneratedValue
    private UUID id_city;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
}
