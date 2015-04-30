package org.luapp.core.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

import com.google.common.collect.Lists;

public class ThumbnailGenerator {

    public static void gen160(String origFileName, String thumbnailFileName) throws IOException {
        Thumbnails.of(new File(origFileName))
                  .size(160, 160)
                  .outputFormat("png")
                  .toFile(new File(thumbnailFileName));
    }

    public static void gen(String origFileName, String thumbnailFileName, int size) throws IOException {
        Thumbnails.of(new File(origFileName))
                  .size(size, size)
                  .outputFormat("png")
                  .toFile(new File(thumbnailFileName));
    }

    public static void gen160WithWaterMark(String origFileName, String thumbnailFileName, String waterMarkFileName)
            throws IOException {
        Thumbnails.of(new File(origFileName))
                  .size(160, 160)
                  .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(waterMarkFileName)), 0.5f)
                  .outputFormat("png")
                  .toFile(new File(thumbnailFileName));
    }
    
    public static void genToDir(List<String> origFileNames,String destinationDir) throws IOException {
        Thumbnails.fromFilenames(origFileNames)
                  .size(160, 160)
                  .outputFormat("png")
                  .toFiles(new File(destinationDir), Rename.SUFFIX_HYPHEN_THUMBNAIL);
    }

    public static void main(String[] args) throws IOException {

        List<String> fileNames = Lists.newArrayList();
        fileNames.add("D:/test/img/src/mapPos.png");
        fileNames.add("D:/test/img/src/earth_road.png");
        fileNames.add("D:/test/img/src/test1.gif");
        fileNames.add("D:/test/img/src/launch.png");

        genToDir(fileNames, "d:/test2");

    }
}
