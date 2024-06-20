package main_func;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Datefunction {
    public static String get_curdate(){
        Date cdate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String cur_date = format.format(cdate);
        return cur_date;
    }
}
