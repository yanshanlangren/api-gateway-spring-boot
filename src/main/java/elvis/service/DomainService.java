package elvis.service;

import elvis.bo.Domain;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class DomainService {

    private final Map<String, Domain> domainMap;

    public DomainService(Map<String, Domain> domainMap) {
        this.domainMap = domainMap;
    }

    public void store(Domain domain) {
        domainMap.put(domain.getDomain_id(), domain);
    }

    public Optional<Domain> query(String domain_id) {
        return Optional.ofNullable(domainMap.get(domain_id));
    }

//    public Domain query(String domain_id) {
//        return domainMap.get(domain_id);
//    }
}
