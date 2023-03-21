package jp.moskau.test.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jp.moskau.test.JSONs.inputJson;
import jp.moskau.test.JSONs.outJson;

import java.io.IOException;
import java.net.URLEncoder;

public class serviceClass {
    private static String indexReturn = "Hello world";
    public static String index() {
        return indexReturn;
    }


    public static boolean isEven(int num, String auth) {
        if (num % 2 == 0 && auth.equals("Slava")) {
            return true;
        } else if (auth.equals("Slava")) {
            return false;
        } else {
            int a = Integer.parseInt(auth);
            return false;

        }
    }

        public static outJson shori(inputJson inJson){
        try {
            outJson out = new outJson();
            String reqEki = inJson.getStation();
            String reqInEki = reqEki.replaceAll("駅", "");
            String encStation = URLEncoder.encode(reqInEki, "UTF-8");
            Document html = Jsoup.connect(String.format("https://www.jorudan.co.jp/time/eki_%s.html", encStation)).get();
            Elements elems = html.select("._rosen_list > tbody:nth-child(1) > tr > td:nth-child(1)");
            for (Element elem : elems) {
                String txt = elem.text();
                if (txt == null || txt.isEmpty()) {
                    out.getLinesList().add("Not found or overlap station.");
                    return out;
                }
                boolean contain = txt.contains("線") || txt.contains("鉄道") || txt.contains("電鉄") || txt.contains("急行") || txt.contains("ライン") || txt.contains("モノレール") || txt.contains("京成成田スカイアクセス");
                boolean inContain = txt.contains("新幹線のぞみ") || txt.contains("新幹線はやぶさ") || txt.contains("新幹線みずほ");
                if (contain && !(inContain)) {
                    out.getLinesList().add(txt);
                } else if (!(contain) && !(inContain)) {
                    System.out.println(txt);
                    continue;
                } else {
                    System.out.println(txt);
                    continue;
                }

            }
            System.out.println(" ");


            return out;


        } catch (IOException e) {
            outJson out_err = new outJson();
            out_err.getLinesList().add("Not found or overlap station.");
            return out_err;
            }
        }




    }

