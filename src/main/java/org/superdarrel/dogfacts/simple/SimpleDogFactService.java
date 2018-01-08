package org.superdarrel.dogfacts.simple;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.superdarrel.dogfacts.DogFact;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleDogFactService {

    private final String dogFactUrl;
    private final RestTemplate restTemplate;

    public SimpleDogFactService(@Value("${dogFactUrl}") String dogFactUrl, RestTemplate template) {
        this.dogFactUrl = dogFactUrl;
        this.restTemplate = template;
    }

    public List<String> getDogFact(int quantity) {
        List<String> dogFacts = new ArrayList<>(quantity);
        for(int i = 0; i< quantity; i++) {
            DogFact dogfact = restTemplate.getForObject(dogFactUrl, DogFact.class);
            dogFacts.add(dogfact.getFacts()[0]);
        }
        return dogFacts;
    }
}
