package elvis.generator;

import elvis.bo.Domain;

import java.util.UUID;

public class DomainInfoGenerator {
    public Domain generate(){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return new Domain("ad-"+uuid, "domain_name", "this is domain of ad-"+ uuid);
    }
}
