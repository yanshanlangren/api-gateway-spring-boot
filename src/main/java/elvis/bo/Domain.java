package elvis.bo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Domain {
    @Getter
    @Setter
    private String domain_id;
    @Getter
    @Setter
    private String domain_name;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Date create_time;
    @Getter
    @Setter
    private Date update_time;
    @Getter
    @Setter
    private Long id;

    public Domain() {
    }

    public Domain(String domain_id, String domain_name, String description) {
        this.domain_id = domain_id;
        this.domain_name = domain_name;
        this.description = description;
    }

    public String toString() {
        return "[domain_name:" + domain_id + ",domain_name:" + domain_name + ", description:" + description + "]";
    }
}
