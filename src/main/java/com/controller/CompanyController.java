package com.controller;


import com.entity.Company;
import com.entity.Job;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.impl.CompanyService;
import com.util.Msg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @RequestMapping("/allcompanys")
    public String allJobs() {
        return "company-board";
    }

    @RequestMapping("/companyCategory")
    public String getByCategory(HttpServletRequest request) {
        String companyCategory = request.getParameter("companyCategory");
        List<Company> companys = companyService.getByCategory(companyCategory);
        request.getSession().setAttribute("companys", companys);
        return "forward:/allcompanys";
    }

    @RequestMapping("/companydetails")
    private String companyDetails(HttpServletRequest request, Model model) {
        Integer companyId = Integer.parseInt(request.getParameter("companyId"));
        Company company = companyService.selectByPrimaryKey(companyId);
        model.addAttribute("coompany", company);
        return "company-details";
    }

    @RequestMapping("/companyAdd")
    public String companyAdd() {
        return "single-company-post";
    }

    @RequestMapping("/manageCompany")
    @ResponseBody
    public Msg getStudents(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Company> companies = companyService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(companies, 3);
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping("/companyInsert")
    public String companyInsert(Company company, HttpServletResponse response) throws IOException {
        company.setCompanyId(companyService.selectAll().size() + 1);
        companyService.insert(company);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('添加成功！');</script>");
        return "forward:/companyAdd";
    }

    @PostMapping("/addCompany")
    @ResponseBody
    public Msg addCompany(HttpServletRequest request) throws ParseException {

        Company company = new Company();
        company.setCompanyName(request.getParameter("companyName"));


        int i = companyService.insert(company);

        if (i>0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping("/deleteCompany")
    @ResponseBody
    public Msg deleteBlog(Integer id) {
        if (companyService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/editCompany")
    @ResponseBody
    public Msg edit(@RequestParam("id") Integer id) {
        Company company = companyService.selectByPrimaryKey(id);
        if (company != null) {
            return Msg.success().add("company", company);
        } else {
            return Msg.fail();
        }
    }

    @PostMapping("/updateCompany")
    @ResponseBody
    public Msg updateCompany(
            @RequestParam("companyId") Integer companyId,
            @RequestParam("companyName") String companyName
          )
    {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCompanyName(companyName);

        // 设置其他参数
        int rowsAffected = companyService.updateByPrimaryKey(company);

        if (rowsAffected > 0) {
            return Msg.success().add("company", company);
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/batchDeleteCompany")
    @ResponseBody
    public Msg batchDelete(@RequestBody String ids) {
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                companyService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();
    }
}
