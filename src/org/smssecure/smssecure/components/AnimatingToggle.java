package org.smssecure.smssecure.components;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import org.smssecure.smssecure.R;
import org.smssecure.smssecure.util.ViewUtil;

public class AnimatingToggle extends FrameLayout {

  private View current;

  private final Animation inAnimation;
  private final Animation outAnimation;

  public AnimatingToggle(Context context) {
    this(context, null);
  }

  public AnimatingToggle(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public AnimatingToggle(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.outAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_out);
    this.inAnimation  = AnimationUtils.loadAnimation(getContext(), R.anim.animation_toggle_in);
  }

  @Override
  public void addView(@NonNull View child, int index, ViewGroup.LayoutParams params) {
    super.addView(child, index, params);

    if (getChildCount() == 1) {
      current = child;
      child.setVisibility(View.VISIBLE);
    } else {
      child.setVisibility(View.GONE);
    }
    child.setClickable(false);
  }

  public void display(@Nullable View view) {
    if (view == current) return;
    if (current != null) ViewUtil.animateOut(current, outAnimation);
    if (view    != null) ViewUtil.animateIn(view, inAnimation);

    current = view;
  }
}
