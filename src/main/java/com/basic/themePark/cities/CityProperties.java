package com.basic.themePark.cities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "city")
@Data
public class CityProperties {
    private String greeting;

}
