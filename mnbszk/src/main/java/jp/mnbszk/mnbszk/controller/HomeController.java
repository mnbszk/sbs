package jp.mnbszk.mnbszk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");

        return mav;
    }
    
    @GetMapping("/ex")
    public ModelAndView ex(ModelAndView mav) throws Exception {
        mav.setViewName("ex");
        
        throw new Exception();
        
        //return mav;
    }
}
