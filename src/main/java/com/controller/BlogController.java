package com.controller;

import com.entity.Blog;
import com.entity.Comment;
import com.entity.Job;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.BlogService;
import com.service.CommentService;
import com.util.Msg;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/blog")
    public String blog() {
        return "blog";
    }

    @RequestMapping("/blogdetails")
    public ModelAndView blogdetails(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Blog blog = blogService.selectByPrimaryKey(Integer.parseInt(request.getParameter("blogId")));
        //System.out.println(blog);
        mv.addObject("blog", blog);
        List<Comment> commentList = commentService.getComments(Integer.parseInt(request.getParameter("blogId")));
//        model.addAttribute("commentList", commentList);
        mv.addObject("commentList", commentList);
        mv.setViewName("blog-details");
        return mv;
    }

    @RequestMapping("/searchBlog")
    public String getByLike(HttpServletRequest request) {
        String like = request.getParameter("blogLike");
        String blogLike = "%" + like + "%";
        List<Blog> blogs = blogService.getByLike(blogLike);
        request.getSession().setAttribute("blogs", blogs);
        return "forward:/blog";
    }

    @RequestMapping("/blogCategory")
    public String getBlogCategory(HttpServletRequest request) {
        String blogCategory = request.getParameter("blogCategory");
        List<Blog> blogs = blogService.getByBlogCategory(blogCategory);
        request.getSession().setAttribute("blogs", blogs);
        return "forward:/blog";
    }

    @RequestMapping("/blogCategoryAll")
    public String getBlogCategoryAll(HttpServletRequest request) {
        String blogCategory = request.getParameter("blogCategory");
        List<Blog> blogs = blogService.getByBlogCategoryAll(blogCategory);
        request.getSession().setAttribute("blogs", blogs);
        return "forward:/blog";
    }

    @RequestMapping("/manageBlog")
    @ResponseBody
    public Msg getBlogs(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Blog> blogs = blogService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(blogs, 3);
        return Msg.success().add("pageInfo", page);
    }





//   ================================= 分隔 =============================================
//    新增
    @PostMapping("/addBlog")
    @ResponseBody
    public Msg addBlog(HttpServletRequest request) throws ParseException {
        String dateString = request.getParameter("blogDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date blogDate = dateFormat.parse(dateString);

        Blog blog = new Blog();
        blog.setBlogUser(request.getParameter("blogUser"));
        blog.setBlogCategory(request.getParameter("blogCategory"));
        blog.setBlogTitle(request.getParameter("blogTitle"));
        blog.setBlogContext(request.getParameter("blogContext"));
        blog.setBlogDate(blogDate);

        int i = blogService.insert(blog);

        if (i>0){
            return Msg.success();
        }
        return Msg.fail();
    }

//删除
    @RequestMapping("/delete")
    @ResponseBody
    public Msg deleteBlog(Integer id) {
        if (blogService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }
//编辑
    @RequestMapping("/edit")
    @ResponseBody
    public Msg editBlog(@RequestParam("id") Integer id) {
        Blog blog = blogService.selectByPrimaryKey(id);
        if (blog != null) {
            return Msg.success().add("blog", blog);
        } else {
            return Msg.fail();
        }
    }
//修改
    @PostMapping("/update")
    @ResponseBody
    public Msg update(
            @RequestParam("blogId") Integer blogid,
            @RequestParam("blogUser") String blogUser,
            @RequestParam("blogCategory") String blogCategory,
            @RequestParam("blogTitle") String blogTitle,
            @RequestParam("blogContext") String blogContext
              )
    {
        Blog blog = new Blog();
        blog.setBlogId(blogid);
        blog.setBlogUser(blogUser);
        blog.setBlogCategory(blogCategory);
        blog.setBlogTitle(blogTitle);
        blog.setBlogContext(blogContext);
        // 设置其他参数
        int rowsAffected = blogService.updateByPrimaryKey(blog);

        if (rowsAffected > 0) {
            return Msg.success().add("blog", blog);
        } else {
            return Msg.fail();
        }
    }
//批量删除
    @RequestMapping("/batchDelete")
    @ResponseBody
    public Msg batchDeleteBlog(@RequestBody String ids) {
        System.out.println(ids);
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                blogService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();



        // 标题表    id      title     ......

        // 问题表   id      标题id    问题     问题类型（单选，多选，输入框）   [ {"a": "1"} , {"b": "2"} ]

        // 回答表   id     标题id    问题表id   回答内容    回答的用户   ......





    }

}
