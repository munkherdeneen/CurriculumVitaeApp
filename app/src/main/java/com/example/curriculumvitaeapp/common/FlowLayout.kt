package com.example.curriculumvitaeapp.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
import androidx.core.view.children

class FlowLayout(context: Context, attributeSet: AttributeSet) : ViewGroup(context, attributeSet) {

    private var horizontalSpacing = 0F
    private var verticalSpacing = 0F
    private var lineHeight: Int = 0

    init {
        context.withStyledAttributes(attributeSet, R.styleable.FlowLayout) {
            horizontalSpacing = getDimension(R.styleable.FlowLayout_horizontalSpacing, 0F)
            verticalSpacing = getDimension(R.styleable.FlowLayout_verticalSpacing, 0F)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        var height = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom
        var xPosition = paddingLeft
        var yPosition = paddingTop
        val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
            height,
            if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) MeasureSpec.AT_MOST else MeasureSpec.UNSPECIFIED
        )

        children.forEach { child ->
            if (child.visibility != GONE) {
                val layoutParams = child.layoutParams as LayoutParamsWithSpacing
                child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), childHeightMeasureSpec)
                val childWidth = child.measuredWidth
                lineHeight =
                    Math.max(lineHeight, child.measuredHeight + layoutParams.verticalSpacing)

                if (xPosition + childWidth > width) {
                    xPosition = paddingLeft
                    yPosition += lineHeight
                }

                xPosition += childWidth + layoutParams.horizontalSpacing
            }
        }

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED ||
            MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST && yPosition + lineHeight < height
        ) {
            height = yPosition + lineHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left
        var xPosition = paddingLeft
        var yPosition = paddingTop

        children.forEach { child ->
            if (child.visibility != View.GONE) {
                val layoutParams = child.layoutParams as LayoutParamsWithSpacing
                val childWidth = child.measuredWidth
                if (xPosition + childWidth > width) {
                    xPosition = paddingLeft
                    yPosition += lineHeight
                }
                child.layout(
                    xPosition, yPosition, xPosition + childWidth,
                    yPosition + child.measuredHeight
                )
                xPosition += layoutParams.horizontalSpacing
                xPosition += childWidth
            }
        }
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams =
        LayoutParamsWithSpacing(1, 1)

    override fun generateLayoutParams(layoutParams: LayoutParams) =
        LayoutParamsWithSpacing(horizontalSpacing.toInt(), verticalSpacing.toInt())

    override fun checkLayoutParams(layoutParams: LayoutParams) =
        layoutParams is LayoutParamsWithSpacing

    class LayoutParamsWithSpacing(val horizontalSpacing: Int, val verticalSpacing: Int) :
        ViewGroup.LayoutParams(0, 0)
}