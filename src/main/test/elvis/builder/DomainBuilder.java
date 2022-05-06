package elvis.builder;

import elvis.bo.Domain;

public class DomainBuilder {
    String domain_id;
    String domain_name;
    String description;

    public DomainBuilder setDomain_id(String domain_id) {
        this.domain_id = domain_id;
        return this;
    }

    public DomainBuilder setDomain_name(String domain_name) {
        this.domain_name = domain_name;
        return this;
    }

    public DomainBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Domain build(){
        return new Domain(domain_id, domain_name, description);
    }
}
