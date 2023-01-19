package jp.moskau.test.route;

import jp.moskau.test.JSONs.inputJson;
import jp.moskau.test.JSONs.outJson;
import jp.moskau.test.Service.serviceClass;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class route {


    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest req) {
        System.out.println(req.getRemoteAddr());
        ipStrage.ip = req.getRemoteAddr();
        sendLog logging = new sendLog();
        logging.start();
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

    @RequestMapping(value="/ua", method=RequestMethod.GET)
    public String test(HttpServletRequest reqHead) {

            return reqHead.getHeader("User-Agent");
    }


}

