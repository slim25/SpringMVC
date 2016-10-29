package mentorship.program.controller;

import mentorship.program.model.*;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.Skill;
import mentorship.program.model.persistance.UserSuccessCompletions;
import mentorship.program.service.MentorshipProgrammService;
import mentorship.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Oleksandr_Tertyshnyi on 9/24/2016.
 */

@Controller
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private MentorshipProgrammService mentorshipProgramService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getAllUsers(){
        List<User> allUsers = userService.findAll();
        return allUsers;
    }

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public User getAllUsers(@RequestParam("id") Long id){
        User user = userService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public List<User> searchUser(@RequestParam("criteria") String criteria){
        List<User> matchedUsers = new ArrayList<>();
        if(Stream.of(Level.values()).anyMatch(level -> level.name().toString().equalsIgnoreCase(criteria))) {
            matchedUsers.addAll(userService.findByLevel(Level.valueOf(criteria)));
        }
        System.out.println(Level.valueOf(criteria));
        matchedUsers.addAll(userService.findByName(criteria));

        return matchedUsers;
    }

    @RequestMapping(value = "/getAllMentorshipPrograms", method = RequestMethod.GET)
    @ResponseBody
    public List<MentorshipProgram> getAllMentorshipPrograms(){
        List<MentorshipProgram> allMentorshipProgram = mentorshipProgramService.findAll();
        return allMentorshipProgram;
    }

    @RequestMapping(value = "/getMentorshipProgramById", method = RequestMethod.GET)
    @ResponseBody
    public MentorshipProgram getMentorshipProgramById(@RequestParam("id") Long id){
        MentorshipProgram mentorshipProgramm = mentorshipProgramService.getMentorshipProgramm(id);
        return mentorshipProgramm;
    }

    @RequestMapping(value = "/searchMentorshipProgram", method = RequestMethod.GET)
    @ResponseBody
    public List<MentorshipProgram> searchMentorshipProgram(@RequestParam("criteria") String criteria){
        List<MentorshipProgram> matchedMentorshipProgram = new ArrayList<>();
        matchedMentorshipProgram.addAll(mentorshipProgramService.findByName(criteria));

        return matchedMentorshipProgram;
    }

    @RequestMapping(value = "/getMenteesWithoutMentorsInLocation", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMentee> getMenteesWithoutMentorsInLocation(@RequestParam("city")String city){
        return userService.getMenteesWithoutMentorsInLocation(city);
    }

    @RequestMapping(value = "/getMenteesWithMentorshipDurationDESCOrdered", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(@RequestParam("pageIndex")Integer pageIndex,
                                                                        @RequestParam("noOfRecords")Integer noOfRecords){
        return userService.getMenteesWithMentorshipDurationDESCOrdered(pageIndex,noOfRecords);
    }

    @RequestMapping(value = "/getCitiesStatistic", method = RequestMethod.GET)
    @ResponseBody
    public List<CityStatistic> getCitiesStatistic(){
        return mentorshipProgramService.getStatistics();
    }

    @RequestMapping(value = "/getStatisticOfSuccessCompletions", method = RequestMethod.GET)
    @ResponseBody
    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions(){
        return mentorshipProgramService.getStatisticOfSuccessCompletions();
    }

    @RequestMapping(value = "/getMentorsWhoMentorsMoreThanTwoMentee", method = RequestMethod.GET)
    @ResponseBody
    public List<UserMentor> getMentorsWhoMentorsMoreThanTwoMentee(){
        return userService.getMentorsManagingMoreThanTwoMentees();
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public void init(){
        Map<String, Date> endOfMentorshipProgram = new HashMap<>();
        endOfMentorshipProgram.put("program1",new GregorianCalendar(2016, Calendar.JULY, 11).getTime());
        endOfMentorshipProgram.put("program2",new GregorianCalendar(2016, Calendar.JUNE, 11).getTime());
        Date dateOfNotEndedProgram = new GregorianCalendar(2016, Calendar.DECEMBER, 11).getTime();
        endOfMentorshipProgram.put("program3", dateOfNotEndedProgram);

        UserMentor mentor = new UserMentor("mentor1","mentor1LastName","mentor1@Email",Level.L3,Skill.JavaScript,"123");

        UserMentee mentee1 = new UserMentee("mentee1","mentee1LastName","email@1",Level.L1, Skill.Java, mentor, endOfMentorshipProgram,"123");
        UserMentee mentee2 = new UserMentee("mentee2","mentee2LastName","email@3",Level.L1, Skill.Java, mentor, endOfMentorshipProgram,"123");
        Map<String, Date> endOfOneMentorshipProgram = new HashMap<>();
        endOfOneMentorshipProgram.put("program4", new GregorianCalendar(2016, Calendar.JULY, 1).getTime());
        UserMentee mentee3 = new UserMentee("mentee3","mentee3LastName","email@3",Level.L1, Skill.Java, null, endOfOneMentorshipProgram,"123");
        UserMentee mentee4 = new UserMentee("mentee4","mentee4LastName","email@4",Level.L2, Skill.Net, mentor, endOfMentorshipProgram,"123");

        MentorshipGroup mentorshipGroup = new MentorshipGroup("group1",Arrays.asList(mentee1,mentee2));

        Date dateOfEnd = new GregorianCalendar(2016, Calendar.DECEMBER, 11).getTime();
        MentorshipProgram m1 = new MentorshipProgram("mentorshipProgram1", new Date(), dateOfEnd, "Lviv",
                Arrays.asList(mentorshipGroup));
        mentorshipGroup.setMentorshipProgram(m1);
        mentee1.setMentorshipGroup(mentorshipGroup);
//        mentee2.setMentorshipGroup(mentorshipGroup);
        mentee3.setMentorshipGroup(mentorshipGroup);

        mentorshipProgramService.addMentorshipProgramm(m1);

        mentor.addMentee(mentee1);
        mentor.addMentee(mentee2);
//        mentor.addMentee(mentee3);
        mentor.addMentee(mentee4);

        userService.addUser(mentor);
        userService.addUser(mentee3);
    }


    @Autowired
    DataSource dataSource;

    @RequestMapping(value = "/testDataSource", method = RequestMethod.GET)
    @ResponseBody
    public void testDataSource() throws Exception{
        ResultSet result = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM user");
        System.out.println(result.next());
        System.out.println(result.next());

    }
}
