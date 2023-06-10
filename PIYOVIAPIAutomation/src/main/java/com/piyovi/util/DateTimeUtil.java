package com.piyovi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
    SimpleDateFormat formatter;
    Date date;

    public String getDateTimeInFormat(String expFormat) {
        this.formatter = new SimpleDateFormat(expFormat);
        this.date = getNextWorkingDate();
        return formatter.format(date);
    }

    public Date getNextWorkingDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        while(isBankHoliday(cal.getTime())){
            cal.add(Calendar.DATE,1);
        }
        return cal.getTime();
    }

    public boolean isBankHoliday(Date d) {
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        if((Calendar.SATURDAY == c.get(c.DAY_OF_WEEK)) || (Calendar.SUNDAY == c.get(c.DAY_OF_WEEK))) {
            return true;
        } else {
            return false;
        }
    }
}
