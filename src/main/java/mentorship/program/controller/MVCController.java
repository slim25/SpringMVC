package mentorship.program.controller;


import mentorship.program.model.*;
import mentorship.program.model.persistance.CityStatistic;
import mentorship.program.model.persistance.Level;
import mentorship.program.model.persistance.UserSuccessCompletions;
import mentorship.program.service.MentorshipGroupService;
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

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private MentorshipGroupService mentorshipGroupService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ModelAttribute("user")
    public User getItem(final HttpServletRequest request){
        boolean isMentor = request.getParameterValues("isMentor") == null ? false : Boolean.valueOf(request.getParameter("isMentor"));
        Long selectedGroupId = request.getParameterValues("selectedMentorshipGroupId") == null ? null :Long.valueOf(request.getParameter("selectedMentorshipGroupId"));
        MentorshipGroup mentorshipGroup = selectedGroupId != null ? mentorshipGroupService.getById(selectedGroupId) : null;

        if(isMentor){
            UserMentor mentor = new UserMentor();
            mentor.setMentorshipGroup(mentorshipGroup);
            mentor.setMentor(true);
            String [] menteeIds = request.getParameterValues("menteeId");
            if(menteeIds != null) {
                for (String menteeId : menteeIds) {
                    mentor.addMentee((UserMentee) userService.getUser(Long.valueOf(menteeId)));
                }
            }
            return mentor;
        }else{
            UserMentee mentee = new UserMentee();
            mentee.setMentorshipGroup(mentorshipGroup);
            mentee.setMentor(false);
            String mentorId = request.getParameter("mentorId");
            if(mentorId != null)
                mentee.setUserMentor((UserMentor) userService.getUser(Long.valueOf(mentorId)));
            return mentee;
        }
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
        List<MentorshipProgram> mentorshipPrograms =  mentorshipProgramService.findAll();
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

    @RequestMapping(value = "/deleteMentorshipProgram", method = RequestMethod.POST)
    public String deleteMentorshipProgram(@ModelAttribute("id") Long id){
        mentorshipProgramService.removeMentorshipProgramm(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/deleteMentorshipGroup", method = RequestMethod.POST)
    public String deleteMentorshipGroup(@ModelAttribute("id") Long id){
        mentorshipGroupService.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        List<User> users = userService.findAll();
        List<MentorshipProgram> mentorshipPrograms =  mentorshipProgramService.findAll();
        List<MentorshipGroup> mentorshipGroups =  mentorshipProgramService.getAllMentorshipGroup();

        List<UserMentor> mentors = userService.getAllMentors();
        List<UserMentee> mentees = userService.getAllMentees();

        mav.addObject("users", users);
        mav.addObject("mentorshipPrograms", mentorshipPrograms);
        mav.addObject("mentors", mentors);
        mav.addObject("mentees", mentees);
        mav.addObject("mentorshipGroups", mentorshipGroups);

        return mav;
    }

    @RequestMapping(value = "/createMentorshipGroup", method = RequestMethod.POST)
    public String createMentorshipGroup(@ModelAttribute("mentorshipGroup") @Valid MentorshipGroup mentorshipGroup, ModelMap model, HttpServletRequest request){
        String choosedMentorshipProgramId = request.getParameter("choosedMentorshipProgramId");
        if(choosedMentorshipProgramId != null) {
            mentorshipGroup.setMentorshipProgram(mentorshipProgramService.getMentorshipProgramm(Long.valueOf(choosedMentorshipProgramId)));
        }
        mentorshipGroupService.create(mentorshipGroup);
        return "redirect:/index";
    }

    @RequestMapping(value = "/createMentorshipProgram", method = RequestMethod.POST)
    public String createMentorshipProgram(@ModelAttribute("mentorshipProgram") @Valid MentorshipProgram mentorshipProgram, ModelMap model){
        mentorshipProgramService.addMentorshipProgramm(mentorshipProgram);
        return "redirect:/index";
    }

    @RequestMapping(value = "/editMentorshipProgram", method = RequestMethod.POST)
    public String editMentorshipProgram(@ModelAttribute("mentorshipProgram") @Valid MentorshipProgram mentorshipProgram, ModelMap model){
        return createMentorshipProgram(mentorshipProgram,model);
    }
    @RequestMapping(value = "/editMentorshipPage", method = RequestMethod.POST)
    public ModelAndView editMentorshipProgramm(@ModelAttribute("id") Long id){
        MentorshipProgram mentorshipProgram = mentorshipProgramService.getMentorshipProgramm(id);
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
        List<MentorshipProgram> matchedMentorshipProgramms = new ArrayList<>();
        if(Stream.of(Level.values()).anyMatch(level -> level.name().toString().equalsIgnoreCase(criteria.getCriteria()))) {
            matchedUsers.addAll(userService.findByLevel(Level.valueOf(criteria.getCriteria())));
        }
        matchedUsers.addAll(userService.findByName(criteria.getCriteria()));

        matchedMentorshipProgramms.addAll(mentorshipProgramService.findByName(criteria.getCriteria()));

        mav.addObject("matchedUsers", matchedUsers);
        mav.addObject("matchedMentorshipProgramms", matchedMentorshipProgramms);
        return mav;
    }

    @RequestMapping(value = "/JPATaskPage", method = RequestMethod.POST)
    public ModelAndView JPATaskPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jpaTask");
        return mav;
    }
    @RequestMapping(value = "/getMenteesWithoutMentorsInLocationJPA", method = RequestMethod.POST)
    @ResponseBody
    public List<UserMentee> getMenteesWithoutMentorsInLocation(@RequestParam("city")String city){
        return userService.getMenteesWithoutMentorsInLocation(city);
    }

    @RequestMapping(value = "/getMenteesWithMentorshipDurationDESCOrderedJPA", method = RequestMethod.POST)
    @ResponseBody
    public List<Object[]> getMenteesWithMentorshipDurationDESCOrdered(@RequestParam("pageIndex")Integer pageIndex,
                                                                      @RequestParam("noOfRecords")Integer noOfRecords){
        return userService.getMenteesWithMentorshipDurationDESCOrdered(pageIndex,noOfRecords);
    }

    @RequestMapping(value = "/getCitiesStatisticJPA", method = RequestMethod.POST)
    @ResponseBody
    public List<CityStatistic> getCitiesStatistic(){
        return mentorshipProgramService.getStatistics();
    }

    @RequestMapping(value = "/getStatisticOfSuccessCompletionsJPA", method = RequestMethod.POST)
    @ResponseBody
    public List<UserSuccessCompletions> getStatisticOfSuccessCompletions(){
        return mentorshipProgramService.getStatisticOfSuccessCompletions();
    }

    @RequestMapping(value = "/getMentorsWhoMentorsMoreThanTwoMenteeJPA", method = RequestMethod.POST)
    @ResponseBody
    public List<UserMentor> getMentorsWhoMentorsMoreThanTwoMentee(){
        return userService.getMentorsManagingMoreThanTwoMentees();
    }

}
