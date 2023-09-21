package com.controller;


import com.entity.Comment;
import com.entity.Job;
import com.entity.User;
import com.service.BlogService;
import com.service.CommentService;
import com.service.UserService;
import com.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @Autowired
    UserService userService;

    @RequestMapping("/addComment")
    public String addComment(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = user != null ? user.getUserId() : null;

        String observe = request.getParameter("observe");
        String blogIdString = request.getParameter("blogId");
        int blogId = 0;
        try {
            blogId = Integer.parseInt(blogIdString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:/404";
        }

        Comment comment = new Comment();
        // 设置comment对象的属性
        comment.setObserve(observe);
        comment.setUserId(userId);
        comment.setBlogId(blogId);

        int insertedCommentId = commentService.insert(comment);

        if (insertedCommentId > 0) {
            return "redirect:/blogdetails?blogId=" + blogId;
        }

        return "redirect:/404";
    }
}


