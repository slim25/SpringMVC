package mentorship.program.controller;


import mentorship.program.model.MentorshipProgramm;
import mentorship.program.model.SearchCriteria;
import mentorship.program.model.User;
import mentorship.program.model.persistance.Level;
import mentorship.program.service.MentorshipProgrammService;
import mentorship.program.service.UserService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Oleksandr_Tertyshnyi on 9/21/2016.
 */

@Controller
public class MVCController {

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

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid User user, ModelMap model){
        userService.addUser(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") @Valid User user, ModelMap model){
        return createUser(user,model);
    }
    @RequestMapping(value = "/editPage", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("id") Long id){
        User user = userService.getUser(id);
        ModelAndView mav = new ModelAndView();
        List<MentorshipProgramm> mentorshipPrograms =  mentorshipProgramService.findAll();
        mav.addObject("mentorshipPrograms", mentorshipPrograms);
        mav.addObject("user", user);
        mav.setViewName("edit");
        return mav;
    }


    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@ModelAttribute("id") Long id){
        userService.removeUser(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        List<User> users = userService.findAll();
        List<MentorshipProgramm> mentorshipPrograms =  mentorshipProgramService.findAll();
        users.stream().forEach(System.out::println);
        mav.addObject("users", users);
        mav.addObject("mentorshipPrograms", mentorshipPrograms);
        return mav;
    }


    @RequestMapping(value = "/createMentorshipProgram", method = RequestMethod.POST)
    public String createMentorshipProgram(@ModelAttribute("mentorshipProgram") @Valid MentorshipProgramm mentorshipProgram, ModelMap model){
        mentorshipProgramService.addMentorshipProgramm(mentorshipProgram);
        return "redirect:/index";
    }

    @RequestMapping(value = "/editMentorshipProgram", method = RequestMethod.POST)
    public String editMentorshipProgram(@ModelAttribute("mentorshipProgram") @Valid MentorshipProgramm mentorshipProgram, ModelMap model){
        return createMentorshipProgram(mentorshipProgram,model);
    }
    @RequestMapping(value = "/editMentorshipPage", method = RequestMethod.POST)
    public ModelAndView editMentorshipProgramm(@ModelAttribute("id") Long id){
        MentorshipProgramm mentorshipProgram = mentorshipProgramService.getMentorshipProgramm(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("mentorshipProgram", mentorshipProgram);
        mav.setViewName("edit");
        return mav;
    }

    @RequestMapping(value = "/searchPage", method = RequestMethod.POST)
    public ModelAndView searchUser(@ModelAttribute(name = "criteria") @Valid SearchCriteria criteria){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("search");
        List<User> matchedUsers = new ArrayList<>();
        List<MentorshipProgramm> matchedMentorshipProgramms = new ArrayList<>();
        if(Stream.of(Level.values()).anyMatch(level -> level.name().toString().equalsIgnoreCase(criteria.getCriteria()))) {
            matchedUsers.addAll(userService.findByLevel(Level.valueOf(criteria.getCriteria())));
        }
        matchedUsers.addAll(userService.findByName(criteria.getCriteria()));

        matchedMentorshipProgramms.addAll(mentorshipProgramService.findByName(criteria.getCriteria()));

        mav.addObject("matchedUsers", matchedUsers);
        mav.addObject("matchedMentorshipProgramms", matchedMentorshipProgramms);
        return mav;
    }

    @RequestMapping(value = "/deleteMentorshipProgram", method = RequestMethod.POST)
    public String deleteMentorshipProgramm(@ModelAttribute("id") Long id){
        mentorshipProgramService.removeMentorshipProgramm(id);
        return "redirect:/index";
    }

}
