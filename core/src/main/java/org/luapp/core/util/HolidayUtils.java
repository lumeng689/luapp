package org.luapp.core.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.util.Calendar;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

/**
 * 节假日处理
 * @author Winter Lau
 * @date 2010-6-21 下午12:23:24
 */
public class HolidayUtils {

    private static Ini Holidays = null;
    private static long lastModified = 0L;
    private final static NumberFormat NumFmt = NumberFormat.getInstance();
    static {
        NumFmt.setMaximumFractionDigits(0);
        NumFmt.setMinimumIntegerDigits(2);
    }

    public static String get(Calendar cal) {
        long[] ds = LunarCalendar.get(cal);
        String nongli = NumFmt.format(ds[1]) + NumFmt.format(ds[2]);
        String yangli = NumFmt.format(cal.get(Calendar.MONTH) + 1) + NumFmt.format(cal.get(Calendar.DATE));
        Ini iniFile = _ReloadIniFile();

        //周节日
        String week = NumFmt.format(cal.get(Calendar.MONTH) + 1) + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH)
                + (cal.get(Calendar.DAY_OF_WEEK) - 1);
        String holiday = iniFile.get("WEEK", week);

        if (holiday != null)
            return holiday;
        //阳历
        holiday = iniFile.get("SOLAR", yangli);
        if (holiday != null)
            return holiday;
        //阴历
        holiday = iniFile.get("LUNAR", nongli);
        if (holiday != null)
            return holiday;

        return holiday;
    }

    private static Ini _ReloadIniFile() {
        File iniFile = new File(HolidayUtils.class.getResource("/conf/holiday.dat").getFile());
        
        if (!iniFile.exists()) {
            throw new RuntimeException("holiday.dat does not exists");
        }

        if (Holidays == null || lastModified != iniFile.lastModified()) {
            synchronized (HolidayUtils.class) {
                if (Holidays == null || lastModified != iniFile.lastModified()) {
                    if (Holidays == null) {
                        Config cfg = new Config();
                        cfg.setMultiSection(true);
                        cfg.setFileEncoding(Charset.forName("UTF-8"));

                        Holidays = new Ini();
                        Holidays.setConfig(cfg);

                        try {
                            Holidays.load(iniFile);
                        } catch (InvalidFileFormatException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        lastModified = iniFile.lastModified();

                    }
                }
            }
        }
        return Holidays;
    }

    public static void main(String[] args) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        System.out.println(get(cal));
    }

}
