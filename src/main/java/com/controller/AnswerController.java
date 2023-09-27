package com.controller;

import com.entity.Answer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.AnswerService;

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
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @RequestMapping("/allanswers")
    public String allJobs() {
        return "answer-list";
    }

    @RequestMapping("/answerCategory")
    public String getByCategory(HttpServletRequest request) {
        String answerCategory = request.getParameter("answerCategory");
        List<Answer> answers = answerService.getByCategory(answerCategory);
        request.getSession().setAttribute("answers", answers);
        return "forward:/allanswers";
    }

    @RequestMapping("/answerdetails")
    private String answerDetails(HttpServletRequest request, Model model) {
        Integer answerId = Integer.parseInt(request.getParameter("answerId"));
        Answer answer = answerService.selectByPrimaryKey(answerId);
        model.addAttribute("answer", answer);
        return "answer-details";
    }

    @RequestMapping("/answerAdd")
    public String answerAdd() {
        return "single-answer-post";
    }

    @RequestMapping("/manageAnswer")
    @ResponseBody
    public Msg getStudents(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Answer> answers = answerService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(answers, 3);
        return Msg.success().add("pageInfo", page);
    }

    @RequestMapping("/answerInsert")
    public String answerInsert(Answer answer, HttpServletResponse response) throws IOException {
        answer.setAnswerId(answerService.selectAll().size() + 1);
        answerService.insert(answer);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script>alert('添加成功！');</script>");
        return "forward:/answerAdd";
    }



    @RequestMapping("/deleteAnswer")
    @ResponseBody
    public Msg deleteAnswer(Integer id) {
        if (answerService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/editAnswer")
    @ResponseBody
    public Msg edit(@RequestParam("id") Integer id) {
        Answer answer = answerService.selectByPrimaryKey(id);
        if (answer != null) {
            return Msg.success().add("answer", answer);
        } else {
            return Msg.fail();
        }
    }

//    @PostMapping("/updateTitle")
//    @ResponseBody
//    public Msg updateTitle(
//            @RequestParam("titleId") Integer titleId,
//            @RequestParam("titleName") String titleName,
//            @RequestParam("titleState") String titleState,
//            @RequestParam("titleQuestionone") String titleQuestionone,
//            @RequestParam("titleQuestiontwo") String titleQuestiontwo,
//            @RequestParam("titleQuestionthree") String titleQuestionthree,
//            @RequestParam("titleQuestionfour") String titleQuestionfour
//    )
//    {
//        Title title = new Title();
//        title.setTitleId(titleId);
//        title.setTitleName(titleName);
//        title.setTitleQuestionone(titleQuestionone);
//        title.setTitleQuestiontwo(titleQuestiontwo);
//        title.setTitleQuestionthree(titleQuestionthree);
//        title.setTitleQuestionfour(titleQuestionfour);
//        title.setTitleState(titleState);
//
//        // 设置其他参数
//        int rowsAffected = titleService.updateByPrimaryKey(title);
//
//        if (rowsAffected > 0) {
//            return Msg.success().add("title", title);
//        } else {
//            return Msg.fail();
//        }
//    }

    @RequestMapping("/batchDeleteAnswer")
    @ResponseBody
    public Msg batchDelete(@RequestBody String ids) {
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                answerService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();
    }
}
