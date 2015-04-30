package org.luapp.core.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文拼音
 */
public class PinyinUtils {

    /**
     * 获取汉字的拼音
     *
     * @param hanzi
     * @return
     */
    public static String getPinyin(String hanzi) {
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] hanziBytes = hanzi.trim().toCharArray();

        for (int i = 0; i < hanziBytes.length; i++) {
            Character c = hanziBytes[i];
            if (c.toString().matches("[\\u4E00-\\u9FA5]+")) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(c);
                pinyin.append(temp[0]);
            } else {
                pinyin.append(c);
            }
        }

        return pinyin.toString();
    }

    /**
     * 获取汉字的首字母
     *
     * @return
     */
    public static String getAcronym(String hanzi) {
        StringBuilder shouzimu = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] hanziBytes = hanzi.trim().toCharArray();

        for (int i = 0; i < hanziBytes.length; i++) {
            char c = hanziBytes[i];
            if (c > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(c,
                            format);
                    if (temp != null) {
                        shouzimu.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    throw new RuntimeException("获取汉字【" + c + "】首字母失败！");
                }
            } else {
                shouzimu.append(c);
            }
        }

        return shouzimu.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPinyin("张三b丰a"));
        System.out.println(getAcronym("张三丰a"));
        System.out.println(getAcronym("张"));
        System.out.println(getAcronym("高洪涛"));
    }

}