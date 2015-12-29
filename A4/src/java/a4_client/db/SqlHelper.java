/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4_client.db;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 *
 * @author Doru
 */
public class SqlHelper {

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();

        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month);
        cal.set(cal.DATE, day);

        return new Date(cal.getTime().getTime());
    }

    public static Timestamp getTime(int hour, int minute) {
        Calendar cal = Calendar.getInstance();

        cal.set(cal.HOUR, hour);
        cal.set(cal.MINUTE, minute);

        return new Timestamp(cal.getTime().getTime());
    }
}
