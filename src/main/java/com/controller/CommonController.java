package com.controller;

import com.entity.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"jobs", "jobs1","users", "resumes", "blogs","blogs1","title"})
public class CommonController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private BlogService blogService;

    @Autowired
    private TitleService titleService;

    @RequestMapping("/")
    public String index(Model model){
        List<Job> jobs = jobService.selectAll();
        List<Job> jobs1 = jobService.selectSome();
        List<User> users = userService.selectAll();
        List<Resume> resumes = resumeService.selectAll();
        List<Blog> blogs = blogService.selectAll();
        List<Blog> blogs1 = blogService.selectSome();

        List<Title> title = titleService.selectTitle();



        model.addAttribute("jobs",jobs);
        model.addAttribute("users",users);
        model.addAttribute("resumes",resumes);
        model.addAttribute("blogs",blogs);
        model.addAttribute("blogs1",blogs1);
        model.addAttribute("jobs1",jobs1);

        model.addAttribute("title",title);

//        System.out.println(title);
        return "index";
    }






}
