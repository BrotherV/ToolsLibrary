package com.bvtech.toolslibrary.widget.waveview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bvtech.toolslibrary.R;

/**
 * Created by John on 2014/10/15.
 */
public class WaveView extends LinearLayout {
    public static final int LARGE = 1;
    public static final int MIDDLE = 2;
    public static final int LITTLE = 3;

    private int mAboveWaveColor;
    private int mBlowWaveColor;
    private int mProgress;
    private float mProgressMax;
    private int mWaveHeight;
    private int mWaveMultiple;
    private int mWaveHz;

    private int mWaveToTop;

    private Wave mWave;
    private Solid mSolid;

    private final int DEFAULT_ABOVE_WAVE_COLOR = Color.WHITE;
    private final int DEFAULT_BLOW_WAVE_COLOR = Color.WHITE;
    private final int DEFAULT_PROGRESS = 0;
    private final int DEFAULT_MAX_PROGRESS = 100;

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        //load styled attributes.
        final TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WidgetAttributes, R.attr.tl_waveViewStyle, 0);
        mAboveWaveColor = attributes.getColor(R.styleable.WidgetAttributes_tl_above_wave_color, DEFAULT_ABOVE_WAVE_COLOR);
        mBlowWaveColor = attributes.getColor(R.styleable.WidgetAttributes_tl_blow_wave_color, DEFAULT_BLOW_WAVE_COLOR);
        mProgress = attributes.getInt(R.styleable.WidgetAttributes_tl_progress, DEFAULT_PROGRESS);
        mProgressMax = attributes.getInt(R.styleable.WidgetAttributes_tl_progress_max, DEFAULT_MAX_PROGRESS);
        mWaveHeight = attributes.getInt(R.styleable.WidgetAttributes_tl_wave_height, MIDDLE);
        mWaveMultiple = attributes.getInt(R.styleable.WidgetAttributes_tl_wave_length, LARGE);
        mWaveHz = attributes.getInt(R.styleable.WidgetAttributes_tl_wave_hz, MIDDLE);
        attributes.recycle();

        mWave = new Wave(context, null);
        mWave.initializeWaveSize(mWaveMultiple, mWaveHeight, mWaveHz);
        mWave.setAboveWaveColor(mAboveWaveColor);
        mWave.setBlowWaveColor(mBlowWaveColor);
        mWave.initializePainters();

        mSolid = new Solid(context, null);
        mSolid.setAboveWavePaint(mWave.getAboveWavePaint());
        mSolid.setBlowWavePaint(mWave.getBlowWavePaint());

        addView(mWave);
        addView(mSolid);

        setProgress(mProgress);
    }

    public void setWaveColor(int color){
        mWave.setAboveWaveColor(color);
        mWave.setBlowWaveColor(color);
        mWave.initializePainters();
    }

    public void setProgress(int progress) {
        this.mProgress = progress > mProgressMax ? (int) mProgressMax : progress;
        computeWaveToTop();
    }

    public void setProgressMax(float progressMax) {
        this.mProgressMax = progressMax;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            computeWaveToTop();
        }
    }

    private void computeWaveToTop() {
        mWaveToTop = (int) (getHeight() * (1f - mProgress / mProgressMax));
        ViewGroup.LayoutParams params = mWave.getLayoutParams();
        if (params != null) {
            ((LayoutParams) params).topMargin = mWaveToTop;
        }
        mWave.setLayoutParams(params);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        // Force our ancestor class to save its state
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.progress = mProgress;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setProgress(ss.progress);
    }

    private static class SavedState extends BaseSavedState {
        int progress;

        /**
         * Constructor called from {@link android.widget.ProgressBar#onSaveInstanceState()}
         */
        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            progress = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(progress);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
