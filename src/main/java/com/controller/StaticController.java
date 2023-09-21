package com.controller;

import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("staticAll")
public class StaticController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JobService jobService;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<Map<String,Integer>> getData(){
        Map<String,Integer> data = new HashMap<>();

        // 从数据库中获取最新的数据
        int userCount = userService.getUserCount();
        int adminCount = adminService.getAdminCount();
        int jobCount = jobService.getJobCount();
        int contactCount = contactService.getContactCount();
        int resumeCount = resumeService.getResumeCount();
        int blogCount = blogService.getBlogCount();

        // 将数据存入 Map
        data.put("博客量", blogCount);
        data.put("简历数", resumeCount);
        data.put("职位数", jobCount);
        data.put("用户", userCount);
        data.put("反馈", contactCount);
        data.put("管理员", adminCount);

        // 返回 JSON 响应
        return ResponseEntity.ok().body(data);
    }
}
