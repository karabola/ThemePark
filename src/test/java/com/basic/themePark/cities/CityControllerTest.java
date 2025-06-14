package com.basic.themePark.cities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerTest {
    private HttpHeaders headers;
    private String URL;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        URL = "http://localhost:8081/themePark/cities";
        headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("accept", MediaType.APPLICATION_JSON_VALUE);

    }

    //    void testAdd() {
//        City city = new City(0, "warszawa");
//        ResponseEntity<String> responseEntity = new RestTemplate().postForEntity(URL, new HttpEntity<City>(city, headers), String.class);
//        String locationUrl = responseEntity.getHeaders().get("location").get(0);
//        ResponseEntity<City> response = new RestTemplate().getForEntity(URL + locationUrl, City.class);
//        System.out.println(response.getBody());
//    }
    @Test
    public void testAddCity() throws Exception {
        String json = "{\"name\":\"Warszawa\"}";
//        URL = "http://localhost:8081/themePark/cities";
        mockMvc.perform(post("http://localhost:8081/themePark/cities/add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isAccepted());
    }
}
