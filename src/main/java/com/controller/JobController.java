package com.controller;

import com.entity.Blog;
import com.entity.Company;
import com.entity.Job;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.JobService;
import com.service.impl.CompanyService;
import com.util.Msg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/alljobs")
    public String allJobs() {
        return "job-board";
    }

    @RequestMapping("/jobCategory")
    public String getByCategory(HttpServletRequest request) {
        String jobCategory = request.getParameter("jobCategory");
        List<Job> jobs = jobService.getByCategory(jobCategory);
        request.getSession().setAttribute("jobs", jobs);
        return "forward:/alljobs";
    }

    @RequestMapping("/jobCategoryAll")
    public String getJobCategoryAll(HttpServletRequest request) {
        String jobCategory = request.getParameter("jobCategory");
        List<Job> jobs  = jobService.getByJobCategoryAll(jobCategory);
        request.getSession().setAttribute("jobs", jobs);
        return "forward:/alljobs";
    }




    @RequestMapping("/jobdetails")
    private String jobDetails(HttpServletRequest request, Model model) {
        Integer jobId = Integer.parseInt(request.getParameter("jobId"));
        Job job = jobService.selectByPrimaryKey(jobId);
        model.addAttribute("job", job);
        return "job-details";
    }

    @RequestMapping("/jobAdd")
    public String jobAdd() {
        return "single-job-post";
    }

    @RequestMapping("/manageJob")
    @ResponseBody
    public Msg getStudents(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Job> jobs = jobService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(jobs, 3);
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping("/jobInsert")
    public String jobInsert(Job job, HttpServletResponse response) throws IOException {
        job.setJobId(jobService.selectAll().size() + 1);
        jobService.insert(job);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('添加成功！');</script>");
        return "forward:/jobAdd";
    }


    @PostMapping("/addJob")
    @ResponseBody
    public Msg addJob(HttpServletRequest request) throws ParseException {
        String dateString = request.getParameter("jobDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date jobDate = dateFormat.parse(dateString);

        Job job = new Job();
        job.setJobName(request.getParameter("jobName"));
        job.setJobAddress(request.getParameter("jobAddress"));
        job.setJobCategory(request.getParameter("jobCategory"));
        job.setJobDescribe(request.getParameter("jobDescribe"));
        job.setJobCompany(request.getParameter("jobCompany"));
        job.setJobNeed(request.getParameter("jobNeed"));
        job.setJobQuality(request.getParameter("jobQuality"));
        job.setJobDate(jobDate);
        job.setJobSalary(request.getParameter("jobSalary"));

        int i = jobService.insert(job);

        if (i>0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping("/deleteJob")
    @ResponseBody
    public Msg deleteBlog(Integer id) {
        if (jobService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/editJob")
    @ResponseBody
    public Msg edit(@RequestParam("id") Integer id) {
        Job job = jobService.selectByPrimaryKey(id);
        if (job != null) {
            return Msg.success().add("job", job);
        } else {
            return Msg.fail();
        }
    }

    @PostMapping("/updateJob")
    @ResponseBody
    public Msg updateJob(
            @RequestParam("jobId") Integer jobId,
            @RequestParam("jobName") String jobName,
            @RequestParam("jobAddress") String jobAddress,
            @RequestParam("jobCategory") String jobCategory,
            @RequestParam("jobDescribe") String jobDescribe,
            @RequestParam("jobCompany") String jobCompany,
            @RequestParam("jobNeed") String jobNeed,
            @RequestParam("jobQuality") String jobQuality,
            @RequestParam("jobSalary") String jobSalary   )
    {
        Job job = new Job();
        job.setJobId(jobId);
        job.setJobName(jobName);
        job.setJobAddress(jobAddress);
        job.setJobCategory(jobCategory);
        job.setJobDescribe(jobDescribe);
        job.setJobCompany(jobCompany);
        job.setJobNeed(jobNeed);
        job.setJobQuality(jobQuality);
        job.setJobSalary(jobSalary);
        // 设置其他参数
        int rowsAffected = jobService.updateByPrimaryKey(job);

        if (rowsAffected > 0) {
            return Msg.success().add("job", job);
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/batchDeleteJob")
    @ResponseBody
    public Msg batchDelete(@RequestBody String ids) {
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                jobService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();
    }

    @RequestMapping("/getCompanies")
    @ResponseBody
    public List<Company> getCompanies() {
        return companyService.selectAll();
    }
}
