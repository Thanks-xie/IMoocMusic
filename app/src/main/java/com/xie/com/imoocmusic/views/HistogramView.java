package com.xie.com.imoocmusic.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.nfc.FormatException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.xie.com.imoocmusic.R;

public class HistogramView extends View {
    private String mHistogramTitle;
    private String mXAxisName;
    private String mYAxisName;
    private float mAxisTextSize;
    private int mAxisTextColor;

    private Paint paint;
    private Context mContext;

    private int width;  //视图宽
    private int height; //视图高
    private int originalX = 100; //起点的X轴坐标值
    private int originalY = 800; //起点的Y轴坐标值

    private int axisDividedSizeX; //X轴等份
    private int axisDividedSizeY; //Y轴等份
    private float maxAxisValueY; //Y轴最大值
    private float maxAxisValueX; //X轴最大值

    public int[][] columnInfo; //二位数组，一维为值，二维为颜色

    public HistogramView(Context context) {
        super(context,null);
        init(context,null);
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs,-1);
        init(context,attrs);
    }

    public HistogramView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        //获取直方图自定义样式
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HistogramStyle);

        mHistogramTitle = typedArray.getString(R.styleable.HistogramStyle_histogramTitle);
        mXAxisName = typedArray.getString(R.styleable.HistogramStyle_xAxisName);
        mYAxisName = typedArray.getString(R.styleable.HistogramStyle_yAxisName);
        mAxisTextSize = typedArray.getDimension(R.styleable.HistogramStyle_axisTextSize,12);
        mAxisTextColor = typedArray.getColor(R.styleable.HistogramStyle_axisTextColor,context.getResources().getColor(R.color.infoColor));


        if (typedArray != null){
            typedArray.recycle();
        }

        initPaint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth() - originalX;
        height = (originalY > getHeight() ? getHeight():originalY)-300;

        drawXAxis(canvas,paint);
        drawYAxis(canvas,paint);
        drawHistogramTitle(canvas,paint);
        drawXAxisScale(canvas,paint);
        drawXAxisScaleValue(canvas,paint);
        drawYAxisScale(canvas,paint);
        drawYAxisScaleValue(canvas,paint);
        drawXAxisArrow(canvas,paint);
        drawYAxisArrow(canvas,paint);
        drawColumn(canvas,paint);

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        if (paint ==null ){
            paint = new Paint();
            paint.setDither(true);
            paint.setAntiAlias(true);
        }
    }

    /**
     * 画中间直方图,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
    private void drawColumn(Canvas canvas, Paint paint){
        if (columnInfo!=null){
            float cellWidth = width/maxAxisValueX;
            for (int i=0;i<columnInfo.length;i++){
                paint.setColor(columnInfo[i][1]);
                float leftTopY = originalY-height*(columnInfo[i][0])/axisDividedSizeY;
                canvas.drawRect(originalX+cellWidth*(i+1),leftTopY,originalX+cellWidth*(i+2),originalY,paint);
            }
        }
    }

    /**
     * 画Y轴箭头，两条直线相交
     * @param canvas
     * @param paint
     */
    private void drawYAxisArrow(Canvas canvas, Paint paint) {
        Path mPathY = new Path();
        mPathY.moveTo(originalX,originalY-height-30);
        mPathY.lineTo(originalX-10,originalY-height);
        mPathY.lineTo(originalX+10,originalY-height);
        mPathY.close();
        canvas.drawPath(mPathY,paint);
        canvas.drawText(mYAxisName,originalX-50,originalY-height-30,paint);
    }

    /**
     * 画X轴箭头，两条直线相交
     * @param canvas
     * @param paint
     */
    private void drawXAxisArrow(Canvas canvas, Paint paint) {
        Path mPathX = new Path();
        mPathX.moveTo(originalX+width+10,originalY);
        mPathX.lineTo(originalX+width-20,originalY+10);
        mPathX.lineTo(originalX+width-20,originalY-10);
        mPathX.close();

        canvas.drawPath(mPathX,paint);
        canvas.drawText(mXAxisName,originalX+width-20,originalY+50,paint);
    }

    /**
     * 画直方图title
     * @param canvas
     * @param paint
     */
    private void drawHistogramTitle(Canvas canvas, Paint paint) {
        if (!TextUtils.isEmpty(mHistogramTitle)){
            paint.setTextSize(mAxisTextSize);
            paint.setColor(mAxisTextColor);
            paint.setFakeBoldText(true);

            canvas.drawText(mHistogramTitle,(getWidth()/2)-(paint.measureText(mHistogramTitle))/2,originalY+100,paint);  //设置并固定显示位置
        }
    }

    /**
     * 画Y轴刻度值,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
    private void drawYAxisScaleValue(Canvas canvas, Paint paint) {
        float cellHeight = height/axisDividedSizeY;
        float cellValueY = maxAxisValueY/axisDividedSizeY;
        for (int i=0;i<axisDividedSizeY;i++){
            canvas.drawText(cellValueY*i+"",originalX-30,originalY-cellHeight*i+10,paint);
        }
    }
    /**
     * 画Y轴刻度,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
   private void drawYAxisScale(Canvas canvas, Paint paint) {
        float cellHeight = height/axisDividedSizeY;
        for (int i=0;i<axisDividedSizeY;i++){
            canvas.drawLine(originalX,(originalY-cellHeight*(i+1)),originalX+10,(originalY-cellHeight*(i+1)),paint);
        }
    }

    /**
     * 画X轴刻度值,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
    private void drawXAxisScaleValue(Canvas canvas, Paint paint) {
        paint.setColor(Color.GRAY);
        paint.setTextSize(16);
        paint.setFakeBoldText(true);
        float cellWidth = width/axisDividedSizeX;
        for (int i=0;i<axisDividedSizeX;i++){
            if (i>7){
                break;
            }
            canvas.drawText(i+"",cellWidth*i+originalX-10,originalY+30,paint);
        }
    }
    /**
     * 画X轴刻度,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
    private void drawXAxisScale(Canvas canvas, Paint paint) {

        float cellWidth = width/axisDividedSizeX;

        for (int i=0;i<axisDividedSizeX-1;i++){
            if (i>6){
                break;
            }
            canvas.drawLine(cellWidth*(i+1)+originalX,originalY,cellWidth*(i+1)+originalX,originalY-10,paint);
        }
    }


    /**
     * 画Y轴,抽象方法，由其子类实现
     * @param canvas
     * @param paint
     */
    private void drawYAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        canvas.drawLine(originalX,originalY,originalX,originalY-height,paint);
    }
    /**
     * 画X轴
     * @param canvas
     * @param paint
     */
    private void drawXAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        canvas.drawLine(originalX,originalY,originalX+width,originalY,paint);
    }

    public void setColumnInfo(int[][] columnInfo) {
        this.columnInfo = columnInfo;
    }

    /**
     *
     * @param maxAxisValuex X轴最大值
     * @param scale X轴几等份
     */
    public void setXAxisScaleValue(float maxAxisValuex,int scale){
        maxAxisValueX = maxAxisValuex;
        axisDividedSizeX = scale;
    }

    /**
     *
     * @param maxAxisValuey Y轴最大值
     * @param scale Y轴几等份
     */
    public void setYAxisScaleValue(float maxAxisValuey,int scale){
        maxAxisValueY = maxAxisValuey;
        axisDividedSizeY = scale;
    }

}
