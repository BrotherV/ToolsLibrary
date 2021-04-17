package com.bvtech.toolslibrary.widget;

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.bvtech.toolslibrary.R
import kotlin.math.max

class DrawerPathView : View {
    private var path: Path? = null
    private var paint: Paint? = null
    private var length = 0f
    private var pathDirection = Path.Direction.CCW
    private var pathDirectionType = 1
    private var roundCorner = 28f
    private var pathWidth = 8f
    private var paddingRectangleFromEdge = 5f
    private var animatorDuration = 6000
    private var colorPath = 0xff000000.toInt()

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.WidgetAttributes)
        arr.let {
            paddingRectangleFromEdge = it.getDimension(R.styleable.WidgetAttributes_tl_paddingRectangleFromEdge, 5f)
            animatorDuration = it.getInt(R.styleable.WidgetAttributes_tl_animatorDurationSec, 6)
            pathDirectionType = it.getInt(R.styleable.WidgetAttributes_tl_pathDirectionType, 1)
            roundCorner = it.getDimension(R.styleable.WidgetAttributes_tl_roundCornerInDp, 28f)
            pathWidth = it.getDimension(R.styleable.WidgetAttributes_tl_strokeWidthPath, 8f)
            colorPath = it.getColor(R.styleable.WidgetAttributes_tl_colorPath, 0xff000000.toInt())
        }
        arr.recycle()
    }

    fun init(widthRec: Int, heightRec: Int) {
        setPaint()
        setPathToDraw(widthRec, heightRec)
        setAnimator()
    }

    private fun setAnimator() {
        // Measure the path
        val measure = PathMeasure(path, false)
        length = measure.length
        val animator = ObjectAnimator.ofFloat(this@DrawerPathView, "phase", 1.0f, 0.0f)
        animator.duration = animatorDuration.times(1000).toLong()
        animator.start()
    }

    //is called by animator object "propertyName" parameter
    fun setPhase(phase: Float) {
        paint?.pathEffect = createPathEffect(length, phase, 0.0f)
        invalidate()
    }

    private fun createPathEffect(pathLength: Float, phase: Float, offset: Float) =
        DashPathEffect(
            floatArrayOf(pathLength, pathLength),
            max(phase * pathLength, offset)
        )


    private fun setPathToDraw(widthRec: Int, heightRec: Int) {
        path = Path()
        val rectF = RectF(
            paddingRectangleFromEdge,
            paddingRectangleFromEdge,
            (widthRec - paddingRectangleFromEdge),
            (heightRec - paddingRectangleFromEdge)
        )
        if(pathDirectionType == 1){
            pathDirection = Path.Direction.CCW
        }else if(pathDirectionType == 0){
            pathDirection = Path.Direction.CW
        }
        path?.addRoundRect(rectF, roundCorner, roundCorner, pathDirection)
    }

    private fun setPaint() {
        paint = Paint()
        paint?.color = colorPath
        paint?.strokeWidth = pathWidth
        paint?.style = Paint.Style.STROKE
        paint?.isAntiAlias = true
    }

    public override fun onDraw(c: Canvas) {
        super.onDraw(c)
        path?.let { pathT ->
            paint?.let { paintT -> c.drawPath(pathT, paintT) }
        }
    }
}