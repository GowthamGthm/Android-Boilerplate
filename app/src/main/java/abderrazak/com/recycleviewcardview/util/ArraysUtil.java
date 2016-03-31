package abderrazak.com.recycleviewcardview.util;

import java.util.ArrayList;

/**
 * Created by abderrazak on 31/03/2016.
 */
public class ArraysUtil {

    public static String strSeparator = "__,__";
    public static String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            // Do not append comma at the end of last element
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public static String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }

    public static String convertArrayListToString(ArrayList<String> stringArrayList) {
        String genreStr = "";
        for (String str: stringArrayList) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
        return genreStr;
    }
}
