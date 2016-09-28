package mentorship.program.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Date dateCreated;
    private String createdByUser;
    private Date dateLastModified;
    private String lastModifiedByUser;

    public void setDateCreated(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateCreated = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
