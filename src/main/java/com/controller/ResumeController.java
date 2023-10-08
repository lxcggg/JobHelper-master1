package com.controller;

import com.entity.Resume;
import com.entity.ResumeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ResumeService;
import com.service.UserService;
import com.util.FileUpload;
import com.util.Msg;
import net.sf.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResumeController {

    @Value("${reggie.path}")
    private String absPath;

    @Autowired
    private FileUpload fileUpload;

    @Autowired
    private ResumeService resumeService;
    @Autowired
    private UserService userService;

    @RequestMapping("/resume")
    public ModelAndView resume(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        ModelAndView mv = new ModelAndView();
        if (userId!=null){
            Integer userId1 = Integer.parseInt(request.getParameter("userId"));
            Resume resume = resumeService.selectByUserId(userId1);
            if (resume!=null) {
                mv.addObject("resume",resume);
                mv.setViewName("resume");
                //System.out.println("更新前"+resume);
                return mv;
            }
        }
        mv.setViewName("resume");
        return mv;
    }

    @RequestMapping(value = "/resumeSave", method = RequestMethod.POST)
    public String resumeSave(ResumeVO resumeVO, Model model) {
        System.out.println("data:::::" + resumeVO.toString());

        Resume resume = new Resume();

        BeanUtils.copyProperties(resumeVO, resume);

        if (!resumeVO.getResumeImage().isEmpty()) {
            // 上传到本地
            String uploadImageName = fileUpload.upload(resumeVO.getResumeImage());
            resume.setResumePic(uploadImageName);
        }

        System.out.println("页面传值"+resume.toString());
        if (resumeService.selectByPrimaryKey(resume.getResumeId())!=null){
            resumeService.updateByPrimaryKey(resume);
            Resume resume1 = resumeService.selectByPrimaryKey(resume.getResumeId());
            //System.out.println("更新后："+resume1);
            model.addAttribute("resume",resume1);
            return "redirect:/resume?userId=" + resume.getUserId();
        }
        resumeService.insert(resume);
        return "redirect:/resume?userId=" + resume.getUserId();
    }

    @ResponseBody
    @RequestMapping("/showImg/{fileName:.+}")
    public void showImg(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {

        System.out.println(fileName + "++++++++++++++++++++++");

        String RealPath = absPath +  "/" + fileName;

        // 新建文件 路径就是 D:/user photo/xxxxx.jpg
        File imgFile = new File(RealPath);

        // 转换为输入流
        FileInputStream inputStream = new FileInputStream(imgFile);

        ServletOutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];

        while (inputStream.read(buffer) != -1) {
            outputStream.write(buffer);
        }
        outputStream.flush();
    }




    //    *******************************************************88



    @RequestMapping("/manageResume")
    @ResponseBody
    public Msg getResumes(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        // 引入PageHelper分页插件
        // 在查询之前只需要调用，传入页码，以及每页的大小
        PageHelper.startPage(pn, 5);
        // startPage后面紧跟的这个查询就是一个分页查询
        List<Resume> resumes = resumeService.selectAll();
        // 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
        // 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
        PageInfo page = new PageInfo(resumes, 3);
        return Msg.success().add("pageInfo", page);
    }


    @RequestMapping("/addResume")
    @ResponseBody
    public Msg addResume(Resume resume) {
        if (resumeService.insert(resume) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }
    @RequestMapping("/deleteResume")
    @ResponseBody
    public Msg deleteResume(Integer id) {
        if (resumeService.deleteByPrimaryKey(id) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/editResume")
    @ResponseBody
    public Msg editResume(@RequestParam("id") Integer id) {
        Resume resume = resumeService.selectByPrimaryKey(id);
        if (resume != null) {
            return Msg.success().add("resume", resume);
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/updateResume")
    @ResponseBody
    public Msg updateResume(Resume resume) {
        if (resumeService.updateByPrimaryKey(resume) > 0) {
            return Msg.success().add("resume", resume);
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping("/batchDeleteResume")
    @ResponseBody
    public Msg batchDeleteResume(@RequestBody String ids) {
        JSONArray json = JSONArray.fromObject(ids);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < json.size(); i++) {
            list.add(Integer.parseInt((String) json.get(i)));
        }
        try {
            if (list != null && list.size() > 0) {
                resumeService.batchDelete(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Msg.success();
    }


}
