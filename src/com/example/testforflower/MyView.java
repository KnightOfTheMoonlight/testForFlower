package com.example.testforflower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyView extends ImageView{
    private static final int STATE_SUB_CROP = 0;
    public Bitmap mBitmapDisplayed = null;
    public Canvas cacheCanvas =null;
    final int VIEW_WIDTH = 480;
    final int VIEW_HEIGHT = 800;
    public Paint paint = null;
    float preX;
    float preY;
    private Path path;
    Bitmap bm;
    protected int mState = STATE_SUB_CROP;
    public MyView(Context context) {
        super(context);
        path = new Path();
        paint = new Paint();
    }
    
    public MyView(Context context,AttributeSet attr) {
        super(context,attr);
        path = new Path();
        paint = new Paint();
        paint = new Paint(Paint.DITHER_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        //反锯齿
        paint.setAntiAlias(true);
        paint.setDither(true);  
    }
    @Override
    public void setImageBitmap(Bitmap bitmap) {
        Drawable d = getDrawable();
        if (d != null) {
            d.setDither(true);
        }
        mBitmapDisplayed = Bitmap.createScaledBitmap(bitmap, VIEW_WIDTH, VIEW_HEIGHT, false);
      bm = Bitmap.createBitmap(VIEW_WIDTH,VIEW_HEIGHT,Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas(bm);

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取拖动事件的发生位置
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN: 
            cacheCanvas.clipPath(path);
            path.moveTo(x, y);
            preX = x;
            preY = y;     
            break;
        case MotionEvent.ACTION_UP:
            path.close();
  
     //   paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            cacheCanvas.save();
            cacheCanvas.clipPath(path,Region.Op.UNION);
            cacheCanvas.clipRect(0,0,VIEW_WIDTH,VIEW_HEIGHT);
            cacheCanvas.drawBitmap(mBitmapDisplayed, 0 , 0 , null);  
            cacheCanvas.restore();
            path.reset();
            break;
        case MotionEvent.ACTION_MOVE:
            path.quadTo(preX, preY, x, y);
            preX = x;
            preY = y;
            break;
        }
            invalidate();
            return true;
    }
    @Override 
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint bmpPaint = new Paint();
        bmpPaint.setColor(Color.RED);
        canvas.drawBitmap(bm, 0 , 0 , bmpPaint);  
        canvas.drawPath(path,bmpPaint);
       
    }

}
