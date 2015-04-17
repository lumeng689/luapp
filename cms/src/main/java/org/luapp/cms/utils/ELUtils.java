package org.luapp.cms.utils;

import org.owasp.encoder.Encode;

/**
 * Created by lumeng on 15-4-16.
 */
public class ELUtils {

    public static String escapeHtml(String input) {
        return Encode.forHtml(input);
    }
}
