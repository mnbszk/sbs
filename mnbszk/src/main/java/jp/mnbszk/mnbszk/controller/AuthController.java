package jp.mnbszk.mnbszk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {

    @RequestMapping(value = "/auth/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("auth/index");

        return mav;
    }
    
    @PostMapping("/login")
    public ModelAndView login(ModelAndView mav)
    {
        mav.setViewName("login");
        return mav;
    }
    
    @GetMapping("/login-error")
    public ModelAndView loginError(ModelAndView mav)
    {
        mav.setViewName("login");
        mav.addObject("loginError", true);
        return mav;
    }
}
