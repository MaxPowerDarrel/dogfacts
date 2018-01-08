package org.superdarrel.dogfacts.flux;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.superdarrel.dogfacts.DogFact;
import reactor.core.publisher.Flux;


@Service
public class FluxDogFactService {

    private WebClient webClient;

    public FluxDogFactService(@Value("${dogFactUrl}") String dogFactUrl) {
        this.webClient = WebClient.create(dogFactUrl);
    }

    public Flux<String> getDogFacts(int quantity) {
        Flux<String> dogFacts = Flux.empty();
        for (int i = 0; i < quantity; i++) {
            dogFacts = dogFacts.mergeWith(webClient.
                    get().
                    retrieve().
                    bodyToMono(DogFact.class).
                    map(fact -> fact.getFacts()[0]));
        }
        return dogFacts;
    }
}
