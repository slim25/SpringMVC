package mentorship.program.controller;

import mentorship.program.model.MentorshipProgramm;
import mentorship.program.model.SearchCriteria;
import mentorship.program.model.User;
import mentorship.program.model.persistance.Level;
import mentorship.program.service.MentorshipProgrammService;
import mentorship.program.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List<MentorshipProgramm> getAllMentorshipPrograms(){
        List<MentorshipProgramm> allMentorshipProgram = mentorshipProgramService.findAll();
        return allMentorshipProgram;
    }

    @RequestMapping(value = "/getMentorshipProgramById", method = RequestMethod.GET)
    @ResponseBody
    public MentorshipProgramm getMentorshipProgramById(@RequestParam("id") Long id){
        MentorshipProgramm mentorshipProgramm = mentorshipProgramService.getMentorshipProgramm(id);
        return mentorshipProgramm;
    }

    @RequestMapping(value = "/searchMentorshipProgram", method = RequestMethod.GET)
    @ResponseBody
    public List<MentorshipProgramm> searchMentorshipProgram(@RequestParam("criteria") String criteria){
        List<MentorshipProgramm> matchedMentorshipProgram = new ArrayList<>();
        matchedMentorshipProgram.addAll(mentorshipProgramService.findByName(criteria));

        return matchedMentorshipProgram;
    }

}
