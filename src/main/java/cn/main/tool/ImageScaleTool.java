package cn.main.tool;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;

/**
 * @Author: yy
 * @Date: 18-11-11 16:43
 * @Description:
 **/
public class ImageScaleTool {

    public ImageScaleTool() {
    }

    /**
     * @param file
     * @return A Integer Array which includes the width and height of image.
     */
    public static int[] getImageSize(File file) {
        InputStream inputStream = null;
        BufferedImage bufferedImage = null;
        int[] result = {0, 0};
        try {
            // Get InputStream from file
            inputStream = new FileInputStream(file);
            // Get imageBuffered Obj from inputStream
            bufferedImage = ImageIO.read(inputStream);
            // Get image size
            result[0] = bufferedImage.getWidth();
            result[1] = bufferedImage.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param imgOriginSrc
     * @param imgAimSrc
     * @param param        this param is a map
     *                     {
     *                     "type": 1(default)
     *                     # If type is 1, the program will scale according to proportion which you given below.
     *                     # If type is 2, the program will scale according to the width which you given below
     *                     "rate" # proportion
     *                     "targetWidth": target image width
     *                     "targetHeight": target image height
     *                     }
     */
    public static void scaleImage(String imgOriginSrc, String imgAimSrc, Map<String, Object> param) {
        // Judge operation type which will do
        int type = FilterTool.changeToInteger(param.get("type"));
        File file = new File(imgOriginSrc);
        // Check file whether exists
        if (!file.exists()) {
            try {
                throw new Exception("Image is not exists!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int imgSize[] = getImageSize(file);
        float tmpWidth = (float) imgSize[0];
        float tmpHeight = (float) imgSize[1];
        float proportion = tmpHeight / tmpWidth;
        if (type == 2) {
            imgSize[0] = FilterTool.changeToInteger(param.get("targetWidth"));
        } else {
            imgSize[0] = (int) (imgSize[0] * FilterTool.changeToFloat(param.get("rate")));
        }
        imgSize[1] = (int) (imgSize[0] * proportion);

        try {
            Image image = ImageIO.read(file);
            // Create canvas
            BufferedImage bufferedImage = new BufferedImage(imgSize[0], imgSize[1], BufferedImage.TYPE_INT_RGB);
            // Get imageType
            String imageType = imgAimSrc.indexOf(".") != -1 ? imgAimSrc.substring(imgAimSrc.lastIndexOf(".") + 1, imgAimSrc.length()) : null;
            switch (imageType){
                case "jpg":
                case "jpeg":
                case "JPEG":
                    // Draw origin image to new canvas according to size which a calculated value
                    bufferedImage.getGraphics().drawImage(image.getScaledInstance(imgSize[0], imgSize[1], Image.SCALE_SMOOTH), 0, 0, null);
                    // Input new image canvas in a file stream
                    FileOutputStream fileOutputStream = new FileOutputStream(imgAimSrc);
                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
                    encoder.encode(bufferedImage);
                    break;
                case "png":
                    Graphics2D g2d = bufferedImage.createGraphics();
                    bufferedImage = g2d.getDeviceConfiguration().createCompatibleImage(imgSize[0], imgSize[1], Transparency.TRANSLUCENT);
                    g2d.dispose();
                    g2d = bufferedImage.createGraphics();
                    Image from = image.getScaledInstance(imgSize[0], imgSize[1], image.SCALE_AREA_AVERAGING);
                    g2d.drawImage(from, 0, 0, null);
                    g2d.dispose();
                    ImageIO.write(bufferedImage, "png", new File(imgAimSrc));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
