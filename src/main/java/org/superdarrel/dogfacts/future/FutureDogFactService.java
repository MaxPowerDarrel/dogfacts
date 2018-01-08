package org.superdarrel.dogfacts.future;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.superdarrel.dogfacts.DogFact;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class FutureDogFactService {
    private final String dogFactUrl;
    private final RestTemplate restTemplate;

    public FutureDogFactService(@Value("${dogFactUrl}") String dogFactUrl, RestTemplate template) {
        this.dogFactUrl = dogFactUrl;
        this.restTemplate = template;
    }

    public CompletableFuture<List<String>> getDogFacts(int quantity) {
        return CompletableFuture.supplyAsync(() -> {
            List<CompletableFuture<String>> facts = new ArrayList<>(quantity);
            for(int i=0; i<quantity; i++) {
                facts.add(CompletableFuture.supplyAsync(() -> restTemplate.getForObject(dogFactUrl, DogFact.class).getFacts()[0]));
            }
            return facts.parallelStream().map(CompletableFuture::join).collect(Collectors.toList());
        });
    }
}
