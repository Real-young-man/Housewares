package com.ssj.housewares.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class ValidateImageView extends View {
    private Paint paint = new Paint();
    /**
     * 验证码图片
     */
    private Bitmap bitmap = null;
    /**
     * 验证码 用于验证验证码重复时的变量
     */
    private String m_sIdentifyingCode = "";

    /**
     * 图片的宽度
     */
    private int width;
    /**
     * 图片的高度
     */
    private int height;
    private ValidateListener validateListener;

    public void setValidateListener(ValidateListener validateListener) {
        this.validateListener = validateListener;
    }

    public ValidateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateListener != null) {
                    validateListener.onValidate(getValidateAndSetImage());
                }
            }
        });
    }

    public ValidateImageView(Context context) {
        super(context);
        this.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (validateListener != null) {
                    validateListener.onValidate(getValidateAndSetImage());
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, paint);
        } else {
            paint.setColor(Color.GRAY);
            paint.setTextSize(30);
            canvas.drawText("获取验证码", 10, 30, paint);
        }
        super.draw(canvas);
    }

    /**
     * 为数组赋值0~9的随机数
     *
     * @return
     */
    private String[] getRandomInteger() {

        String[] resultArray = new String[4];
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            resultArray[i] = String.valueOf((random.nextInt(10) + i) % 10);
        }
        return resultArray;
    }

    /**
     * 得到验证码；绘制图片
     * <p>
     * 验证码的字符串数组
     *
     * @return
     */
    private String getValidateAndSetImage() {
        // 产生四个随机数
        String[] strContent = getRandomInteger();
        // 查重验证，并返回
        strContent = generateRandom(strContent);
        // 传随机串和随机数
        bitmap = generateValidateBitmap(strContent);
        invalidate();
        return strContent[0] + strContent[1] + strContent[2] + strContent[3];
    }

    private Bitmap generateValidateBitmap(String[] strContent) {
        if (isBlack(strContent)) {
            return null;
        }

        Bitmap sourceBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(sourceBitmap);
        Paint p = new Paint();
        // 设置字体的大小为我们bitmap的1/2
        p.setTextSize(height / 2f);
        // 在xml布局文件中使用android:textStyle=”bold”可以将英文设置成粗体，但是不能将中文设置成粗体，
        // 将中文设置成粗体的方法是：使用TextPaint的仿“粗体”设置setFakeBoldText为true。
        p.setFakeBoldText(true);
        p.setColor(0xFFD0CCC7);
        canvas.drawRect(0, 0, width, height, p);

        // (从0-150随机rgb，150一下处于深色状态，防止出现颜色看不清楚)
        p.setColor(getRandColor(150, 150, 150));
        // x:默认是这个字符串的左边在屏幕的位置，如果设置了paint.setTextAlign(Paint.Align.CENTER),那就是字符的中心
        // y:是指定这个字符baseline在屏幕上的位置(就是字符串的底部) 左上角为原点（0,0）;
        // canvas.drawText(text, x, y, p)
        float tY = height / 2f;
        canvas.drawText(strContent[0], width / 10, tY, p);
        Matrix m1 = new Matrix();
        m1.setRotate(15);
        canvas.setMatrix(m1);

        p.setColor(getRandColor(150, 150, 150));
        canvas.drawText(strContent[1], width * 2 / 5, tY, p);
        m1.setRotate(10);
        canvas.setMatrix(m1);

        p.setColor(getRandColor(150, 150, 150));
        canvas.drawText(strContent[2], width * 3 / 5, tY - 10, p);
        m1.setRotate(15);
        canvas.setMatrix(m1);

        p.setColor(getRandColor(150, 150, 150));
        canvas.drawText(strContent[3], width * 4 / 5, tY - 15, p);
        m1.setRotate(20);
        canvas.setMatrix(m1);

        // 障碍设置
        int startX = 0, startY = 0, stopX = 0, stopY = 0;
        for (int i = 0; i < 55; i++) {
            startX = pointRandom(width);
            startY = pointRandom(height);
            stopX = pointRandom(15);
            stopY = pointRandom(15);
            p.setColor(getRandColor(200, 230, 220));
            canvas.drawLine(startX, startY - 20, startX + stopX, startY + stopY
                    - 20, p);
        }

        canvas.save();
        return sourceBitmap;
    }

    /**
     * 检测产生的验证码是否为空
     *
     * @param strContent
     * @return
     */
    private boolean isBlack(String[] strContent) {
        if (strContent == null || strContent.length <= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 从指定数组中随机取出4个字符(数组)
     *
     * @param strContent
     * @return
     */
    private String[] generateRandom(String[] strContent) {
        m_sIdentifyingCode = "";// 每次验证前重置改验证码
        // 生成4个随机数
        String[] str = new String[4];
        int count = 9;
        Random random = new Random();
        int randomResFirst = checkRandom(random.nextInt(count), count);
        int randomResSecond = checkRandom(random.nextInt(count), count);
        int randomResThird = checkRandom(random.nextInt(count), count);
        int randomResFourth = checkRandom(random.nextInt(count), count);

        str[0] = randomResFirst + "";
        str[1] = randomResSecond + "";
        str[2] = randomResThird + "";
        str[3] = randomResFourth + "";
        return str;
    }

    /**
     * 检测验证码是否重复 如果一样的话重新生成新的 直到不重复为止
     *
     * @param nValue 新的验证码
     * @param nCount
     * @return
     */
    private int checkRandom(int nValue, int nCount) {
        Random random = new Random();
        boolean bValue = true;
        while (bValue) {
            if (m_sIdentifyingCode.contains(nValue + ""))
                nValue = random.nextInt(nCount);
            else
                bValue = false;
        }
        m_sIdentifyingCode += nValue;
        return nValue;
    }

    private int pointRandom(int n) {
        Random r = new Random();
        return r.nextInt(n);
    }

    /**
     * 给定范围获得随机颜色
     *
     * @param rc 0-255
     * @param gc 0-255
     * @param bc 0-255
     * @return colorValue 颜色值，使用setColor(colorValue)
     */
    private int getRandColor(int rc, int gc, int bc) {
        Random random = new Random();
        if (rc > 255)
            rc = 255;
        if (gc > 255)
            gc = 255;
        if (bc > 255)
            bc = 255;
        int r = random.nextInt(rc);
        int g = random.nextInt(gc);
        int b = random.nextInt(bc);
        return Color.rgb(r, g, b);
    }

    public interface ValidateListener {
        /**
         * 点击产生随机数的时候调用
         *
         * @param validate
         */
        void onValidate(String validate);
    }

}