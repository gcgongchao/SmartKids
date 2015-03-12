package com.cmbb.smartkids.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;

public class ImageTools {

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static Bitmap getRoundedCornerBitmapBig(Bitmap bitmap) {
        int min = 0;
        if (bitmap.getWidth() < bitmap.getHeight()) {
            min = bitmap.getWidth();
        } else {
            min = bitmap.getHeight();
        }
        Bitmap outBitmap = Bitmap.createBitmap(min,
                min, Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, min, min);
//		final RectF rectF = new RectF(rect);
        final float roundPX = min / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
//		canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        canvas.drawCircle(roundPX, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }

    /**
     * 图片的缩放方�?
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放�?
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * @param image 图片网络路径
     * @return
     */
    public static Bitmap getRemoteImage(String image) {
        try {
            URL imageUrl = new URL(image);
            URLConnection conn = imageUrl.openConnection();
            conn.connect();
            BufferedInputStream bis = new BufferedInputStream(
                    conn.getInputStream());
            Bitmap bm = BitmapFactory.decodeStream(bis);
            bis.close();
            return bm;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param path 保存图片路径
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String path) {
        File file = new File(path);
        FileOutputStream out;
        try {
            file.createNewFile();
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out))
                out.flush();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
