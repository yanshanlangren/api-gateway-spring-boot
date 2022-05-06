package elvis.bo;

public class Domain {
    private String domain_id;
    private String domain_name;
    private String description;

    public Domain(){}

    public Domain(String domain_id, String domain_name, String description) {
        this.domain_id = domain_id;
        this.domain_name = domain_name;
        this.description = description;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "[domain_name:" + domain_name + ", description:" + description + "]";
    }
}
