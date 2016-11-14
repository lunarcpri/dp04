package utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtility {

    private String dateString;
    private Date date;


    public static Date stringToDate(String date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
