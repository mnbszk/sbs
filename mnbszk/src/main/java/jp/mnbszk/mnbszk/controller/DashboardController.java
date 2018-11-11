package jp.mnbszk.mnbszk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.mnbszk.mnbszk.domain.entity.User;
import jp.mnbszk.mnbszk.domain.mapper.UserMapper;

@Controller
public class DashboardController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav)
    {
        mav.setViewName("dashboard");
        
        List<User> users = userMapper.getAll();
        System.out.println("users" + users);
        User user = userMapper.findById(1);
        System.out.println(user);
        
        mav.addObject("users", users);

        return mav;
    }

}
