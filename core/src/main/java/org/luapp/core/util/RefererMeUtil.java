package org.luapp.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefererMeUtil {

    private static Pattern refererPattern = Pattern
            .compile("@([^@^\\s^:]{1,})([\\s\\:\\,\\;]{0,1})");//@.+?[\\s:]

    /**
     * 处理提到某人 @xxxx
     * @param msg  传入的文本内容
     * @param referers 传出被引用到的会员名单
     * @return 返回带有链接的文本内容
     */
    public static String generateLinks(String msg, List<Long> referers) {

        StringBuilder html = new StringBuilder();

        int lastIndex = 0;
        Matcher match = refererPattern.matcher(msg);

        while (match.find()) {
            String originStr = match.group();
            String str = originStr.substring(1).trim();
            html.append(msg.substring(lastIndex, match.start()));

            // 获取user访问链接
            // 判断用户是否活跃，需添加逻辑
            boolean isUserActive = true;
            if (isUserActive) {
                //  String url = LinkTool.user(u);
                String url = "";
                html.append("<a href='" + url + "' class='referer' target='_blank'>@");
                html.append(str.trim());
                html.append("</a> ");
                long uid = -1;
                if (referers != null && !referers.contains(uid))
                    referers.add(uid);
            } else {
                html.append(originStr);
            }

            lastIndex = match.end();
        }

        html.append(msg.substring(lastIndex));

        return html.toString();
    }

    public static void main(String[] args) {
        System.out.println(RefererMeUtil.generateLinks("你们@张三 @李四  怎么看", new ArrayList<Long>()));
    }
}
