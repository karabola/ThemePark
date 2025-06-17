package com.basic.themePark.cities.core;

import com.basic.themePark.parks.core.Park;
import com.basic.themePark.provinces.core.Province;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue
    private UUID id_city;
    private String name;
    @ManyToOne
    @JoinColumn(name = "id_province")
    private Province province;
    
    @Override
    public String toString() {
        return "City{name='" + name + '\'' +"}";
    }
}
