package org.superdarrel.dogfacts.future;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/future/dogfact")
public class FutureDogFactResource {
    private final FutureDogFactService service;

    public FutureDogFactResource(FutureDogFactService service) {
        this.service = service;
    }

    @GetMapping
    public CompletableFuture<List<String>> getDogFacts(@RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        return service.getDogFacts(quantity);
    }
}
