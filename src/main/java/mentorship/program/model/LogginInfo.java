package mentorship.program.model;

import lombok.*;
import mentorship.program.model.persistance.LogginAction;

import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import java.time.Duration;
import java.util.Date;

@Entity
@Table(name= "logginInfo")
@Data
@NoArgsConstructor
public class LogginInfo extends BaseEntity{

    private LogginAction action;
    private String info;
    private String userName;
    private Date logInDate;
    private Date logOutDate;
    private Long logInDurationTime;


    public LogginInfo(LogginAction action, String info, String userName, Date logInDate, Date logOutDate){
        this.action = action;
        this.userName = userName;
        this.info = info;
        this.logInDate = logInDate;
        this.logOutDate = logOutDate;
    }

    private Integer insuccessfullLoginCount = 0;

    public boolean incrInsuccessfullCount(){
        ++this.insuccessfullLoginCount;
        boolean shouldResetInsuccessfullCount = shouldResetInsuccessfullCount(this.insuccessfullLoginCount);
        return shouldResetInsuccessfullCount;
    }

    public boolean shouldResetInsuccessfullCount(int insuccessfullLoginCount) {
        boolean result = false;
        if (insuccessfullLoginCount % 3 == 0) {
            this.insuccessfullLoginCount = 0;
            result = true;
        }
        return result;
    }
}
