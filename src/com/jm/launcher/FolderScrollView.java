package com.jm.launcher;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class FolderScrollView extends ScrollView {

	private CellLayout mCellLayout;
	
	public FolderScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setOverScrollMode(OVER_SCROLL_NEVER);
	}
	
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		
		mCellLayout = (CellLayout) getChildAt(0);
	}
	
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();

		mCellLayout = null;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = mCellLayout.getDesiredWidth();
		int height = mCellLayout.getDesiredHeight();

		int widthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

		mCellLayout.measure(widthSpec, heightSpec);
		
		setMeasuredDimension(mCellLayout.getMeasuredWidth(),
				Math.min(mCellLayout.getMeasuredHeight(), getDesiredHeight()));
		
		boolean canScroll = mCellLayout.getMeasuredHeight() > getDesiredHeight();
		if (canScroll) {
			setVerticalScrollBarEnabled(true);
		} else {
			setVerticalScrollBarEnabled(false);
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (mCellLayout.getVisibility() != View.GONE) {
			mCellLayout.layout(10, 10, 10 + mCellLayout.getMeasuredWidth(),
					10 + mCellLayout.getMeasuredHeight());
		}
	}
	
	public int getDesiredWidth() {
		return mCellLayout == null ? -1 : mCellLayout.getDesiredWidth() + 20;
	}

	public int getDesiredHeight() {
		return mCellLayout == null ? -1 : mCellLayout.getDesiredMaxScrollHeight();
	}
}
