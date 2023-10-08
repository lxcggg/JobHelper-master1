package com.controller;


import com.entity.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.TitleService;

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
import java.util.ArrayList;
import java.util.List;

@Controller
public class TitleController {
    @Autowired
    private TitleService titleService;

    @RequestMapping("/alltitles")
    public String allJobs() {
        return "title-list";
    }

    @RequestMapping("/titleCategory")
    public String getByCategory(HttpServletRequest request) {
        String titleCategory = request.getParameter("titleCategory");
        List<Title> titles = titleService.getByCategory(titleCategory);
        request.getSession().setAttribute("titles", titles);
        return "forward:/alltitles";
    }

    @RequestMapping("/titledetails")
    private String titleDetails(HttpServletRequest request, Model model) {
        Integer titleId = Integer.parseInt(request.getParameter("titleId"));
        Title title = titleService.selectByPrimaryKey(titleId);
        model.addAttribute("title", title);
        return "title-details";
    }

    @RequestMapping("/titleAdd")
    public String titleAdd() {
        return "single-title-post";
    }

    @RequestMapping("/manageTitle")
    @ResponseBody
    public Msg getStudents(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Title> titles = titleService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(titles, 3);
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping("/titleInsert")
    public String titleInsert(Title title, HttpServletResponse response) throws IOException {
        title.setTitleId(titleService.selectAll().size() + 1);
        titleService.insert(title);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('添加成功！');</script>");
        return "forward:/titleAdd";
    }

    @PostMapping("/addTitle")
    @ResponseBody
    public Msg addTitle(HttpServletRequest request) throws ParseException {

        Title title = new Title();
        title.setTitleName(request.getParameter("titleName"));
        title.setTitleQuestionone(request.getParameter("titleQuestionone"));
        title.setTitleQuestiontwo(request.getParameter("titleQuestiontwo"));
        title.setTitleQuestionthree(request.getParameter("titleQuestionthree"));
        title.setTitleQuestionfour(request.getParameter("titleQuestionfour"));
        title.setTitleState("未发布");

        int i = titleService.insert(title);

        if (i>0){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping("/deleteTitle")
    @ResponseBody
    public Msg deleteBlog(Integer id) {
        if (titleService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/editTitle")
    @ResponseBody
    public Msg edit(@RequestParam("id") Integer id) {
        Title title = titleService.selectByPrimaryKey(id);
        if (title != null) {
            return Msg.success().add("title", title);
        } else {
            return Msg.fail();
        }
    }

    @PostMapping("/updateTitle")
    @ResponseBody
    public Msg updateTitle(
            @RequestParam("titleId") Integer titleId,
            @RequestParam("titleName") String titleName,
            @RequestParam("titleState") String titleState,
            @RequestParam("titleQuestionone") String titleQuestionone,
            @RequestParam("titleQuestiontwo") String titleQuestiontwo,
            @RequestParam("titleQuestionthree") String titleQuestionthree,
            @RequestParam("titleQuestionfour") String titleQuestionfour
    )
    {
        Title title = new Title();
        title.setTitleId(titleId);
        title.setTitleName(titleName);
        title.setTitleQuestionone(titleQuestionone);
        title.setTitleQuestiontwo(titleQuestiontwo);
        title.setTitleQuestionthree(titleQuestionthree);
        title.setTitleQuestionfour(titleQuestionfour);
        title.setTitleState(titleState);

        // 设置其他参数
        int rowsAffected = titleService.updateByPrimaryKey(title);

        if (rowsAffected > 0) {
            return Msg.success().add("title", title);
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/batchDeleteTitle")
    @ResponseBody
    public Msg batchDelete(@RequestBody String ids) {
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                titleService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();
    }



    }
