package br.com.springboot.workshop.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfRemoteService {
   private final RestTemplate restTemplate = new RestTemplate();

   public Optional<Map<String, String>> findCpfByName(String name) {
       final String url = "http://localhost:9090/api/spring-boot-demo/cpf/{name}";
       final ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class, name);

       if (!response.getStatusCode().equals(HttpStatus.OK)) {
           return Optional.empty();
       }

       return Optional.of(response.getBody());
   }
}
