package elvis.bo;

import lombok.Data;

import java.util.Date;

@Data
public class Domain {
    private String domain_id;
    private String domain_name;
    private String description;
    private Date create_time;
    private Date update_time;
    private Long id;
}
