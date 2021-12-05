package com.bvtech.toolslibrary.widget.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.bvtech.toolslibrary.R;
import com.bvtech.toolslibrary.utility.Utilities;
import com.bvtech.toolslibrary.widget.waveview.WaveView;

import java.lang.ref.WeakReference;

public class BatteryProgressbar extends View {

    private Context context;
    private Rect rectFull;
    private Rect rectCharge;
    private Bitmap fullBatteryBitmap;
    private Bitmap chargingBatteryBitmap;
    private Paint backgroundPaint = new Paint();
    private Paint foregroundPaint = new Paint();
    private Paint shaderPaint = new Paint();
    private Point screenSize = new Point(0, 0);
    private int backColor;
    private int frontColor;
    private int maxProgress;
    private int progress;
    private boolean isCharging;
    private Handler uiHandler;

    public BatteryProgressbar(Context context) {
        super(context);
        init(context, null);
    }

    public BatteryProgressbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BatteryProgressbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a;
        if(attrs != null){
            a = context.obtainStyledAttributes(attrs, R.styleable.WidgetAttributes);
        }else {
            a = context.obtainStyledAttributes(R.styleable.WidgetAttributes);
        }

        this.context = context;
        backColor = a.getColor(R.styleable.WidgetAttributes_pb_backColor, context.getResources().getColor(R.color.colorWhite));
        frontColor = a.getColor(R.styleable.WidgetAttributes_pb_frontColor, context.getResources().getColor(R.color.colorWhite));
        maxProgress = a.getInt(R.styleable.WidgetAttributes_pb_max, 100);
        progress = a.getInt(R.styleable.WidgetAttributes_pb_progress, 0);
        a.recycle();

        screenSize = new Point(getWidth(), getHeight());
        uiHandler = new UIHandler(new WeakReference<View>(this));

        backgroundPaint = new Paint();
        //backgroundPaint.setColor(backColor);
        ColorFilter filter = new PorterDuffColorFilter(backColor, PorterDuff.Mode.SRC_IN);
        backgroundPaint.setColorFilter(filter);
        backgroundPaint.setAlpha(55);
        backgroundPaint.setAntiAlias(true);

        foregroundPaint = new Paint();
        //foregroundPaint.setColor(frontColor);
        filter = new PorterDuffColorFilter(frontColor, PorterDuff.Mode.SRC_IN);
        foregroundPaint.setColorFilter(filter);
        foregroundPaint.setAntiAlias(true);
    }

    private void changeBitmap(){
        boolean isHorizontal = screenSize.x > screenSize.y ? true : false;
        fullBatteryBitmap = Utilities.drawableToBitmap(context, isHorizontal ? R.drawable.ic_battery_full_h : R.drawable.ic_battery_full);
        chargingBatteryBitmap = Utilities.drawableToBitmap(context, isHorizontal ? R.drawable.ic_battery_charging_h : R.drawable.ic_battery_charging);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenSize = new Point(w, h);
        changeBitmap();
        resetShape();
    }

    private void resetShape(){
        rectFull = new Rect(0 , 0 , screenSize.x, screenSize.y);
        //createRectCharge();
        createShader();

        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    private Bitmap getCroppedBitmap(Bitmap bmp) {
        int m = Math.min(screenSize.x, screenSize.y);
        int y = Math.max(screenSize.x, screenSize.y);

        int l =(int) (y * ((float) progress / (float) maxProgress));
        Rect rect;
        if(screenSize.x > screenSize.y) {
            rect = new Rect(0,0,l,m);
        }else{
            rect = new Rect(0,l,m,y);
        }

        Bitmap bitmap = Bitmap.createBitmap(screenSize.x, screenSize.y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        final String color = "#BAB399";
        final Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor(color));
        canvas.drawRect(rect, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bmp, null, rect, paint);

        return bitmap;
    }

    private void createShader(){
        if (screenSize.x <= 0 || screenSize.y <= 0) {
            return;
        }

        Bitmap bitmap = Bitmap.createScaledBitmap(isCharging ? chargingBatteryBitmap : fullBatteryBitmap, screenSize.x, screenSize.y, false);;
        Canvas canvas = new Canvas(bitmap);

        int m = Math.min(screenSize.x, screenSize.y);
        int y = Math.max(screenSize.x, screenSize.y);

        int l =(int) (y * ((float) progress / (float) maxProgress));
        if(screenSize.x > screenSize.y) {
            rectCharge = new Rect(0,0, l, m);
        }else{
            rectCharge = new Rect(0, y, m,y - l);
        }

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(frontColor);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRect(rectCharge, paint);
        shaderPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(fullBatteryBitmap, null, rectFull,  backgroundPaint);
        canvas.drawRect(rectCharge,  shaderPaint);
    }

    public void setCharging(boolean charging) {
        isCharging = charging;

        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    public void setProgress(int progress) {
        if(progress >= 0 || progress <= maxProgress){
            this.progress = progress;
            resetShape();
        }
    }

    private static class UIHandler extends Handler {
        private final View mView;

        UIHandler(WeakReference<View> view) {
            super(Looper.getMainLooper());
            mView = view.get();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mView != null) {
                mView.invalidate();
            }
        }
    }

    public int getMaxProgress() {
        return maxProgress;
    }
}
