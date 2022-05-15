package elvis.service;

import elvis.bo.Domain;
import elvis.dao.mapper.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DomainService {

    @Autowired
    private DomainMapper domainMapper;

    public void store(Domain domain) {
        domainMapper.insert(domain);
    }

    public Optional<Domain> query(String domain_id) {
        return Optional.ofNullable(domainMapper.get(domain_id));
    }

    public List<Domain> list() {
        List<Domain> list = domainMapper.getAll();
        list.sort(Comparator.comparing(Domain::getCreate_time));
        return list;
    }
}
