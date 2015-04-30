package org.luapp.core.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

public class BadWord {

    private final static File wordFilter = new File("");
    private static long lastModified = 0L;
    private static Set<String> words = new HashSet<String>();

    private static void checkReload() {
        if (wordFilter.lastModified() > lastModified) {
            synchronized (BadWord.class) {
                lastModified = wordFilter.lastModified();
                try {
                    LineIterator lineIterator = FileUtils.lineIterator(wordFilter, "utf-8");

                    while (lineIterator.hasNext()) {
                        String line = lineIterator.next();
                        if (StringUtils.isNotBlank(line)) {
                            words.add(StringUtils.trim(line).toLowerCase());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查敏感字内容
     * 
     * 如有敏感词，则返回包含的词汇，若没有则返回null
     * @param contents
     */
    public static String check(String... contents) {
        if (!wordFilter.exists()) {
            return null;
        }
        // 检查更新
        checkReload();

        for (String word : words) {
            for (String content : contents) {
                if (StringUtils.isNotEmpty(content) && content.indexOf(word) >= 0) {
                    return word;
                }
            }
        }

        return null;
    }

    public static Set<String> List() {
        checkReload();
        return words;
    }

    public static void Add(String word) throws IOException {
        word = word.toLowerCase();
        if (!words.contains(word)) {
            words.add(word);
            FileUtils.writeLines(wordFilter, "UTF-8", words);
            lastModified = wordFilter.lastModified();
        }
    }

    public static void Delete(String word) throws IOException {
        word = word.toLowerCase();
        words.remove(word);
        FileUtils.writeLines(wordFilter, "UTF-8", words);
        lastModified = wordFilter.lastModified();
    }
}
