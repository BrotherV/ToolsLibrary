package com.bvtech.toolslibrary.widget.waveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.bvtech.toolslibrary.R;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class WaveView extends View {

    public enum Shape {
        CIRCLE(1), SQUARE(2), HEART(3), STAR(4);
        int value;

        Shape(int value) {
            this.value = value;
        }

        static Shape fromValue(int value) {
            for (Shape shape : values()) {
                if (shape.value == value) return shape;
            }
            return CIRCLE;
        }
    }

    private float shiftX1 = 0;
    private float waveVector = -0.25f;
    private int waveOffset = 25;
    private int speed = 25;
    private HandlerThread thread = new HandlerThread("YPWaveView_" + hashCode());
    private Handler animHandler, uiHandler;

    private Paint mBorderPaint = new Paint();
    private Paint mViewPaint = new Paint();
    private Paint mWavePaint1 = new Paint();
    private Paint mWavePaint2 = new Paint();

    private Path mPathContent;
    private Path mPathBorder;

    private static final int DEFAULT_PROGRESS = 405;
    private static final int DEFAULT_MAX = 1000;
    private static final int DEFAULT_STRONG = 50;
    public static final int DEFAULT_BEHIND_WAVE_COLOR = Color.parseColor("#443030d5");
    public static final int DEFAULT_FRONT_WAVE_COLOR = Color.parseColor("#FF3030d5");
    public static final int DEFAULT_BORDER_COLOR = Color.parseColor("#000000");
    private static final float DEFAULT_BORDER_WIDTH = 5f;
    public static final int DEFAULT_TEXT_COLOR = Color.parseColor("#000000");
    private static final boolean DEFAULT_ENABLE_ANIMATION = false;
    private static final boolean DEFAULT_HIDE_TEXT = false;
    private static final int DEFAULT_SPIKE_COUNT = 5;
    private static final float DEFAULT_PADDING = 0f;

    private float mShapePadding = DEFAULT_PADDING;
    private int mProgress = DEFAULT_PROGRESS;
    private int mMax = DEFAULT_MAX;
    private int mFrontWaveColor = DEFAULT_FRONT_WAVE_COLOR;
    private int mBehindWaveColor = DEFAULT_BEHIND_WAVE_COLOR;
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private float mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mTextColor = DEFAULT_TEXT_COLOR;
    private boolean isAnimation = DEFAULT_ENABLE_ANIMATION;
    private boolean isHideText = DEFAULT_HIDE_TEXT;
    private int mStrong = DEFAULT_STRONG;
    private int mSpikes = DEFAULT_SPIKE_COUNT;
    private Shape mShape = Shape.CIRCLE;
    private OnWaveStuffListener mListener;
    private Point screenSize = new Point(0, 0);

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WidgetAttributes, defStyleAttr, 0);
        mFrontWaveColor = attributes.getColor(R.styleable.WidgetAttributes_wv_frontColor, DEFAULT_FRONT_WAVE_COLOR);
        mBehindWaveColor = attributes.getColor(R.styleable.WidgetAttributes_wv_backColor, DEFAULT_BEHIND_WAVE_COLOR);
        mBorderColor = attributes.getColor(R.styleable.WidgetAttributes_wv_borderColor, DEFAULT_BORDER_COLOR);
        mTextColor = attributes.getColor(R.styleable.WidgetAttributes_wv_textColor, DEFAULT_TEXT_COLOR);
        mProgress = attributes.getInt(R.styleable.WidgetAttributes_wv_progress, DEFAULT_PROGRESS);
        mMax = attributes.getInt(R.styleable.WidgetAttributes_wv_max, DEFAULT_MAX);
        mBorderWidth = attributes.getDimension(R.styleable.WidgetAttributes_wv_borderWidthSize, DEFAULT_BORDER_WIDTH);
        mStrong = attributes.getInt(R.styleable.WidgetAttributes_wv_strong, DEFAULT_STRONG);
        mShape = Shape.fromValue(attributes.getInt(R.styleable.WidgetAttributes_wv_shapeType, 1));
        mShapePadding = attributes.getDimension(R.styleable.WidgetAttributes_wv_shapePadding, DEFAULT_PADDING);
        isAnimation = attributes.getBoolean(R.styleable.WidgetAttributes_wv_animatorEnable, DEFAULT_ENABLE_ANIMATION);
        isHideText = attributes.getBoolean(R.styleable.WidgetAttributes_wv_textHidden, DEFAULT_HIDE_TEXT);

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(mBorderColor);

        mWavePaint1 = new Paint();
        mWavePaint1.setStrokeWidth(2f);
        mWavePaint1.setAntiAlias(true);
        mWavePaint1.setColor(mBehindWaveColor);
        mWavePaint2 = new Paint();
        mWavePaint2.setStrokeWidth(2f);
        mWavePaint2.setAntiAlias(true);
        mWavePaint2.setColor(mFrontWaveColor);

        thread.start();
        animHandler = new Handler(thread.getLooper());
        uiHandler = new UIHandler(new WeakReference<View>(this));

        screenSize = new Point(getWidth(), getHeight());
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     * 0-MAX
     */
    public void setProgress(int progress) {
        if (progress <= mMax) {
            if (mListener != null) {
                mListener.onStuffing(progress, mMax);
            }
            mProgress = progress;
            createShader();
            Message message = Message.obtain(uiHandler);
            message.sendToTarget();
        }
    }

    public int getProgress() {
        return mProgress;
    }

    public void startAnimation() {
        isAnimation = true;
        if (getWidth() > 0 && getHeight() > 0) {
            animHandler.removeCallbacksAndMessages(null);
            animHandler.post(new Runnable() {
                @Override
                public void run() {
                    shiftX1 += waveVector;
                    createShader();
                    Message message = Message.obtain(uiHandler);
                    message.sendToTarget();
                    if (isAnimation) {
                        animHandler.postDelayed(this, speed);
                    }
                }
            });
        }
    }

    public void stopAnimation() {
        isAnimation = false;
    }

    public OnWaveStuffListener getListener() {
        return mListener;
    }

    public void setListener(OnWaveStuffListener mListener) {
        this.mListener = mListener;
    }

    /**
     */
    public void setMax(int max) {
        if (mMax != max) {
            if (max >= mProgress) {
                mMax = max;
                createShader();
                Message message = Message.obtain(uiHandler);
                message.sendToTarget();
            }
        }
    }

    public int getMax() {
        return mMax;
    }

    /**
     */
    public void setBorderColor(int color) {
        mBorderColor = color;
        mBorderPaint.setColor(mBorderColor);
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     */
    public void setFrontWaveColor(int color) {
        mFrontWaveColor = color;
        mWavePaint2.setColor(mFrontWaveColor);
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     */
    public void setBehindWaveColor(int color) {
        mBehindWaveColor = color;
        mWavePaint1.setColor(mBehindWaveColor);
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     */
    public void setTextColor(int color) {
        mTextColor = color;
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     */
    public void setBorderWidth(float width) {
        mBorderWidth = width;
        mBorderPaint.setStrokeWidth(mBorderWidth);
        resetShapes();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     */
    public void setShapePadding(float padding) {
        this.mShapePadding = padding;
        resetShapes();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     * Fast -> Slow
     * 0...∞
     */
    public void setAnimationSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("The speed must be greater than 0.");
        }
        this.speed = speed;
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     * 0-100
     */
    public void setWaveVector(float offset) {
        if (offset < 0 || offset > 100) {
            throw new IllegalArgumentException("The vector of wave must be between 0 and 100.");
        }
        this.waveVector = (offset - 50f) / 50f;
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     *
     * @param hidden
     */
    public void setHideText(boolean hidden) {
        this.isHideText = hidden;
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     * 3...∞
     *
     * @param count
     */
    public void setStarSpikes(int count) {
        if (count < 3) {
            throw new IllegalArgumentException("The number of spikes must be greater than 3.");
        }
        this.mSpikes = count;
        if (Math.min(screenSize.x, screenSize.y) != 0) {
            resetShapes();
        }
    }


    /**
     * 1-100
     */
    public void setWaveOffset(int offset) {
        this.waveOffset = offset;
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    /**
     * 0-100
     */
    public void setWaveStrong(int strong) {
        this.mStrong = strong;
        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    public void setShape(Shape shape) {
        mShape = shape;
        resetShapes();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenSize = new Point(w, h);
        resetShapes();
        if (isAnimation) {
            startAnimation();
        }
    }

    private void resetShapes() {
        int radius = Math.min(screenSize.x, screenSize.y);
        int cx = (screenSize.x - radius) / 2;
        int cy = (screenSize.y - radius) / 2;
        switch (mShape) {
            case STAR:
                mPathBorder = drawStart(radius / 2 + cx, radius / 2 + cy + (int) mBorderWidth, mSpikes, radius / 2 - (int) mBorderWidth, radius / 4);
                mPathContent = drawStart(radius / 2 + cx, radius / 2 + cy + (int) mBorderWidth, mSpikes, radius / 2 - (int) mBorderWidth - (int) mShapePadding, radius / 4 - (int) mShapePadding);
                break;
            case HEART:
                mPathBorder = drawHeart(cx, cy, radius);
                mPathContent = drawHeart(cx + ((int) mShapePadding / 2), cy + ((int) mShapePadding / 2), radius - (int) mShapePadding);
                break;
            case CIRCLE:
                mPathBorder = drawCircle(cx, cy, radius);
                mPathContent = drawCircle(cx + ((int) mShapePadding / 2), cy + ((int) mShapePadding / 2), radius - (int) mShapePadding);
                break;
            case SQUARE:
                mPathBorder = drawSquare(cx, cy, radius);
                mPathContent = drawSquare(cx + ((int) mShapePadding / 2), cy + ((int) mShapePadding / 2), radius - (int) mShapePadding);
                break;
        }


        createShader();
        Message message = Message.obtain(uiHandler);
        message.sendToTarget();
    }

    private Path drawSquare(int cx, int cy, int radius) {
        Path path = new Path();
        path.moveTo(cx, cy + (mBorderWidth / 2));
        path.lineTo(cx, radius + cy - mBorderWidth);
        path.lineTo(radius + cx, radius + cy - mBorderWidth);
        path.lineTo(radius + cx, cy + mBorderWidth);
        path.lineTo(cx, cy + mBorderWidth);
        path.close();
        return path;
    }

    private Path drawCircle(int cx, int cy, int radius) {
        Path path = new Path();
        path.addCircle((radius / 2) + cx, (radius / 2) + cy, (radius / 2) - mBorderWidth, Path.Direction.CCW);
        path.close();
        return path;
    }

    private Path drawHeart(int cx, int cy, int radius) {
        Path path = new Path();
        path.moveTo(radius / 2 + cx, radius / 5 + cy);
        path.cubicTo(5 * radius / 14 + cx, cy, cx, radius / 15 + cy, radius / 28 + cx, 2 * radius / 5 + cy);
        path.cubicTo(radius / 14 + cx, 2 * radius / 3 + cy, 3 * radius / 7 + cx, 5 * radius / 6 + cy, radius / 2 + cx, 9 * radius / 10 + cy);
        path.cubicTo(4 * radius / 7 + cx, 5 * radius / 6 + cy, 13 * radius / 14 + cx, 2 * radius / 3 + cy, 27 * radius / 28 + cx, 2 * radius / 5 + cy);
        path.cubicTo(radius + cx, radius / 15 + cy, 9 * radius / 14 + cx, cy, radius / 2 + cx, radius / 5 + cy);
        path.close();
        return path;
    }

    /**
     *
     * @param cx          X
     * @param cy          Y
     * @param spikes
     * @param outerRadius
     * @param innerRadius
     * @return
     */
    private Path drawStart(int cx, int cy, int spikes, int outerRadius, int innerRadius) {
        Path path = new Path();
        double rot = Math.PI / 2d * 3d;
        double step = Math.PI / spikes;

        path.moveTo(cx, cy - outerRadius);
        for (int i = 0; i < spikes; i++) {
            path.lineTo(cx + (float) Math.cos(rot) * outerRadius, cy + (float) Math.sin(rot) * outerRadius);
            rot += step;

            path.lineTo(cx + (float) Math.cos(rot) * innerRadius, cy + (float) Math.sin(rot) * innerRadius);
            rot += step;
        }
        path.lineTo(cx, cy - outerRadius);
        path.close();
        return path;
    }


    /**
     * B(t) = X(1-t)^2 + 2t(1-t)Y + Zt^2 , 0 <= t <= n
     */
    private void createShader() {
        if (screenSize.x <= 0 || screenSize.y <= 0) {
            return;
        }
        int viewSize = Math.min(screenSize.x, screenSize.y);
        double w = (2.0f * Math.PI) / viewSize;

        Bitmap bitmap = Bitmap.createBitmap(viewSize, viewSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        float level = ((((float) (mMax - mProgress)) / (float) mMax) * viewSize) + ((screenSize.y / 2) - (viewSize / 2));
        int x2 = viewSize + 1;
        int y2 = viewSize + 1;
        float zzz = (((float) viewSize * ((waveOffset - 50) / 100f)) / ((float) viewSize / 6.25f));
        float shiftX2 = shiftX1 + zzz;
        int waveLevel = mStrong * (viewSize / 20) / 100;  // viewSize / 20

        for (int x1 = 0; x1 < x2; x1++) {
            float y1 = (float) (waveLevel * Math.sin(w * x1 + shiftX1) + level);
            canvas.drawLine((float) x1, y1, (float) x1, y2, mWavePaint1);
            y1 = (float) (waveLevel * Math.sin(w * x1 + shiftX2) + level);
            canvas.drawLine((float) x1, y1, (float) x1, y2, mWavePaint2);
        }

        mViewPaint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP));
    }

    @Override
    protected void onDetachedFromWindow() {
        if (animHandler != null) {
            animHandler.removeCallbacksAndMessages(null);
        }
        if (thread != null) {
            thread.quit();
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPathContent, mViewPaint);
        if (mBorderWidth > 0) {
            canvas.drawPath(mPathBorder, mBorderPaint);
        }


        if (!isHideText) {
            float percent = (mProgress * 100) / (float) mMax;
            String text = String.format(Locale.TAIWAN, "%.1f", percent) + "%";
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(mTextColor);
            if (mShape == Shape.STAR) {
                textPaint.setTextSize((Math.min(screenSize.x, screenSize.y) / 2f) / 3);
            } else {
                textPaint.setTextSize((Math.min(screenSize.x, screenSize.y) / 2f) / 2);
            }

            textPaint.setAntiAlias(true);
            float textHeight = textPaint.descent() + textPaint.ascent();
            canvas.drawText(text, (screenSize.x - textPaint.measureText(text)) / 2.0f, (screenSize.y - textHeight) / 2.0f, textPaint);

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
}
