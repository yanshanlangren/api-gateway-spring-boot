package elvis.controller;

import elvis.bo.Domain;
import elvis.service.DomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/domain")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/query/{domain_id}")
    public ResponseEntity get(@PathVariable String domain_id) {
        Optional<Domain> domain = domainService.query(domain_id);
        log.info(domain.toString());
        return domain.isPresent() ? ResponseEntity.ok(domain.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Domain domain) {
        domainService.store(domain);
        log.info(domain.toString());
        return ResponseEntity.ok(domain);
    }
}
