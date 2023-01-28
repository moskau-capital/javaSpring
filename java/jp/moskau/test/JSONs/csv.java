package jp.moskau.test.JSONs;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class csv {
    public static List<String> headers = new ArrayList<String>(Arrays.asList("0.2","0.4","0.6","0.8","1.0","1.2","1.4","1.6","1.8","2.0","2.2","2.4","2.6","2.8","3.0","3.5","4.0","4.5","5.0","5.5","6.0","6.5","7.0","7.5","8.0","8.5","9.0","9.5","10.0","10.5","11.0","11.5","12.0","12.5","13.0","13.5","14.0","14.5","15.0","16.0","17.0","18.0","19.0","20.0","21.0","22.0","23.0","24.0","25.0"));

    private static int row;

    public static int getRow() {
        return row;
    }

    public static void setRow(int inRow) {
        row = inRow;
    }

}
