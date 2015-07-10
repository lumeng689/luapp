package org.luapp.practise.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lum on 2015/6/18.
 */
public class SplitText {

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\tmp\\catalina.out";
        int q = 10;

        File f = new File(filePath);

        long perSize = f.length() / q;

        LineIterator li = FileUtils.lineIterator(f);

        int i = 0;
        File curFile = new File(f.getParent(), "part_" + i++);
        FileOutputStream curOs = new FileOutputStream(curFile);
        List<String> lines = new ArrayList<String>();
        while (li.hasNext()) {
            String line = li.nextLine();

            lines.add(line);
            if (lines.size() > 100) {
                IOUtils.writeLines(lines, null, curOs, "UTF-8");
                lines.clear();
            }

            if (curFile.length() > perSize) {
                IOUtils.closeQuietly(curOs);
                curFile = new File(f.getParent(), "part_" + i++);
                curOs = new FileOutputStream(curFile);
            }
        }

        IOUtils.closeQuietly(curOs);
    }
}
