package org.superdarrel.dogfacts.simple;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/simple/dogfact")
public class SimpleDogFactResource {

    private final SimpleDogFactService service;

    public SimpleDogFactResource(SimpleDogFactService service) {
        this.service = service;
    }

    @GetMapping
    public List<String> getDogFact(@RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        return service.getDogFact(quantity);
    }
}
