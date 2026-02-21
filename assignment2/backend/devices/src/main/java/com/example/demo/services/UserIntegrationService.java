package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


@Service
public class UserIntegrationService {

    private static final String USER_SERVICE_BASE_URL = "http://traefik/user/api/users";

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_USERNAME="admin@app.com";
    private static final String SERVICE_PASSWORD="admin";

    public UserDTO getUserById(Long userId) {
        String url = USER_SERVICE_BASE_URL + "/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(SERVICE_USERNAME, SERVICE_PASSWORD);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
          ResponseEntity<UserDTO> response = restTemplate.exchange(
              url,
              HttpMethod.GET,
              entity,
              UserDTO.class
              );
          return response.getBody();
        } catch (Exception e) {
          System.out.println("User service auth error:" + e.getMessage());
          return null;
        }
        //return restTemplate.getForObject(url, UserDTO.class);
    }
}
