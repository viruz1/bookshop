package zw.co.zss.bookshop.util;

import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        try {
            if(date == null) {
                return null;
            }

            XMLGregorianCalendar xmlgc = DatatypeFactoryImpl.newInstance()
                    .newXMLGregorianCalendar();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            xmlgc.setYear(cal.get(Calendar.YEAR));
            xmlgc.setMonth(cal.get(Calendar.MONTH) + 1);
            xmlgc.setDay(cal.get(Calendar.DAY_OF_MONTH));
            xmlgc.setHour(cal.get(Calendar.HOUR_OF_DAY));
            xmlgc.setMinute(cal.get(Calendar.MINUTE));
            xmlgc.setSecond(cal.get(Calendar.SECOND));
            return xmlgc;

        } catch (Exception e) {

            e.printStackTrace();


        }
        return null;
    }
}
