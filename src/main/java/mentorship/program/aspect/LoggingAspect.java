package mentorship.program.aspect;

import mentorship.program.model.BaseEntity;
import mentorship.program.model.MentorshipProgramm;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Oleksandr_Tertyshnyi on 9/26/2016.
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);


    @Before("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
    public void logDaoCall(JoinPoint joinPoint) {
        String logString = "Action name : " + joinPoint.getSignature().getName() + ";\n Args : "
                + Stream.of(joinPoint.getArgs()).reduce((first, second) -> first.toString() + " ," + second.toString())
                        .orElse(" empty")
                + ";\n Kind : " + joinPoint.getKind() + ";\n Current pointcut : " + joinPoint.toLongString()
                + ";\n Source location : " + joinPoint.getSourceLocation() + ";\n Target class : "
                + joinPoint.getTarget();

        logger.info("Signature name : " + logString);
    }

    @AfterThrowing(pointcut = "execution( public * mentorship.program..*(..))", throwing = "excep")
    public void afterThrowing(JoinPoint joinPoint, Throwable excep) throws Throwable {
        stringWriter.getBuffer().setLength(0);
        excep.printStackTrace(printWriter);
        logger.error( stringWriter.toString());
    }

    @Before("beforeEditingMentorshipProgramm(mentorshipProgram, request, model) || beforeSavingMentorshipProgramm(mentorshipProgram, request, model) ")
    public void modifyMentorshipProgramm(JoinPoint joinPoint, BaseEntity mentorshipProgram, HttpServletRequest request, ModelMap model) {
        String modifiedBy = request.getRequestedSessionId();
        Date modifiedDate = new Date();

        if(mentorshipProgram.getCreatedByUser() == null){
            logger.info("createdBy : " + modifiedBy + "/n createdDate : " + modifiedDate);
            mentorshipProgram.setCreatedByUser(modifiedBy);
            mentorshipProgram.setDateCreated(dateFormat.format(modifiedDate));
        }else{
            logger.info("modifiedBy : " + modifiedBy + "/n modifiedDate : " + modifiedDate);
            mentorshipProgram.setLastModifiedByUser(modifiedBy);
            mentorshipProgram.setDateLastModified(modifiedDate);
        }
    }


    @Pointcut("execution( public java.lang.String mentorship.program.controller.MVCController.editMentorshipProgram(..)) && args(mentorshipProgram, request, model)")
    public void beforeEditingMentorshipProgramm( BaseEntity mentorshipProgram, HttpServletRequest request, ModelMap model){}
    @Pointcut("execution( public java.lang.String mentorship.program.controller.MVCController.createMentorshipProgram(..)) && args(mentorshipProgram, request, model)")
    public void beforeSavingMentorshipProgramm( BaseEntity mentorshipProgram, HttpServletRequest request, ModelMap model){}
}
