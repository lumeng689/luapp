package org.luapp.core.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 条形码和二维码生成、识别工具类 
 */
public class BarCodeUtils {

    private final static Logger logger = LoggerFactory.getLogger(BarCodeUtils.class);

    private static int QR_WIDTH = 200;
    private static int QR_HEIGHT = 200;

    /**
     * 数据写成二维码
     * 
     * @param url
     * @param imgPath
     */
    public static void encodeQR(String url, String imgPath) {

        if (StringUtils.isBlank(url)) {
            logger.warn("empty url need not to generate qrcode");
            return;
        }

        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);

            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            for (int x = 0; x < QR_WIDTH; x++) {
                for (int y = 0; y < QR_HEIGHT; y++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }

            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
        } catch (WriterException e) {
            logger.error("生成二维码失败！", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取二维码中的信息
     * 
     * @param imgFile
     * @return
     */
    public static String decodeQR(String imgFile) {
        File file = new File(imgFile);
        BufferedImage bi = null;

        try {
            bi = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(bi);
            BinaryBitmap bm = new BinaryBitmap(new HybridBinarizer(source));

            Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bm, hints);
            return result.getText();
        } catch (IOException e) {
            logger.error("读取文件失败", e);
        } catch (NotFoundException e) {
            logger.error("识别时发生错误", e);
        }
        return "";
    }

    public static void encodeBar(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard  
                (7 * 6) + // left bars  
                5 + // middle guard  
                (7 * 6) + // right bars  
                3; // end guard  
        codeWidth = Math.max(codeWidth, width);

        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.EAN_13, codeWidth, height,hints);
            MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
        } catch (WriterException e) {
            logger.error("条形码编码失败", e);
        } catch (IOException e) {
            logger.error("写入条形码失败", e);
        }
    }

    public static String decodeBar(String imgFile) {
        BufferedImage img = null;
        Result result = null;

        try {
            img = ImageIO.read(new File(imgFile));
            LuminanceSource source = new BufferedImageLuminanceSource(img);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (IOException e) {
            logger.error("读取条形码文件失败", e);
        } catch (NotFoundException e) {
            logger.error("读取条形码文件失败", e);
        }

        return "";
    }

    public static void main(String[] args) {
        encodeQR("test", "d:/test/aa.png");

        System.out.println(decodeQR("d:/test/aa.png"));
        
        encodeBar("6923450657713",105,50,"d:/test/bb.png");
        System.out.println(decodeBar("d:/test/bb.png"));
    }
}
