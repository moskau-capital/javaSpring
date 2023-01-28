package jp.moskau.test.route;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import jp.moskau.test.JSONs.inputJson;
import jp.moskau.test.JSONs.outJson;
import jp.moskau.test.JSONs.csv;
import jp.moskau.test.Service.serviceClass;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.FileReader;



@RestController
public class route {


    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest req) {
        System.out.println(req.getRemoteAddr());
        ipStorage.ip = req.getRemoteAddr();
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

    @RequestMapping(value="/getShitsudo", method=RequestMethod.POST)
    public String toCsv(@RequestParam double dry, double wet) {

        try {

            CSVReader reader = new CSVReader(new FileReader("./out.csv"));
            //乾球と湿球の差を整数化（10倍）
            double dry_wet_double = dry*10 - wet*10;
            int dry_wet = (int)dry_wet_double;
            //差が0の場合は100％を返却
            if (dry_wet == 0) {
                return "100%";
            }
            //以下差を10分の1として解釈
            if (dry_wet < 30)  {
                  //
                 if (dry_wet % 2 != 0) {
                        dry_wet = dry_wet + 1;
                 }

                 double betterRow = (double)dry_wet/10;

                 csv.setRow(csv.headers.indexOf(Double.toString(betterRow)));

            } else if (dry_wet < 150)  {

                    while (dry_wet % 5 != 0) {
                        dry_wet = dry_wet + 1;
                    }

                    double betterRow = (double)dry_wet/10;

                    csv.setRow(csv.headers.indexOf(Double.toString(betterRow)));


            } else if (dry_wet <= 250 )  {
                  double check = 250 / 10;
                  check = Math.floor(check);

                  double betterRow = dry_wet;

                  csv.setRow(csv.headers.indexOf(Double.toString(betterRow)));


            }  else {
                  return "the amounts are unacceptable";
            }

            int line = 50 - (int)dry;


            for (int i = 0; i < line; i++) {
                   reader.readNext();
            }

            String[] result = reader.readNext();



            reader.close();

            return result[csv.getRow()] + "%";







        } catch (IOException e) {
        e.printStackTrace();
        return "error";

        } catch (IndexOutOfBoundsException e) {
             return e.getMessage() + "\n" + "the amounts are unacceptable";
        }




    }





}

