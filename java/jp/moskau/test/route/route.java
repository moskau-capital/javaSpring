package jp.moskau.test.route;

import jp.moskau.test.Service.serviceClass;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.ArrayList;

@RestController
public class route {


    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest req) {
        System.out.println(req.getRemoteAddr());
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public ModelAndView result(@RequestParam int num) {
        ModelAndView model = new ModelAndView();
        model.setViewName("result");
        if (serviceClass.isEven(num, "Slava")) {
            model.addObject("r", "偶数");
        } else {
            model.addObject("r", "奇数");
        }

        return model;
    }

    @RequestMapping(value="/getLines", method=RequestMethod.POST, produces = "application/json")
    public outJson getHow(@RequestBody inputJson json) {
        outJson out = serviceClass.shori(json);

        return out;


    }

}
