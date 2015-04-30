package org.luapp.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public final static String dateFileName() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sf.format(date);
    }

    public static void main(String[] args) {

        System.out.println(dateFileName());

    }
}
