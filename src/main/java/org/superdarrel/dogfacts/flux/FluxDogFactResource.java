package org.superdarrel.dogfacts.flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/flux/dogfact")
public class FluxDogFactResource {
    private final FluxDogFactService service;

    public FluxDogFactResource(FluxDogFactService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<String> getDogFacts(@RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        return service.getDogFacts(quantity);
    }
}
