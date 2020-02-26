package com.hbisoft.fabb;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fabb extends RelativeLayout {
    FabbListener fabbListener;
    FloatingActionButton fab_main, fab1, fab2, fab3;
    Animation fab_open, fab_close;
    TextView tv_fab_one, tv_fab_two, tv_fab_three;
    Boolean isOpen = false;
    int mainFabOpenedColor;
    int fabMainBackgroundColor;
    int fabMainBackgroundColorOpened = 0;
    Drawable fabMainIconRef, fabActionOneIconRef, fabTwoIconRef, fabThreeIconRef;
    int numberOfActions = 0;
    boolean shouldKeepOpen = false;

    public void setEventListener(FabbListener fabbListener) {
        this.fabbListener = fabbListener;
    }

    public Fabb(Context context) {
        super(context);
        init(context, null, 0);

    }

    public Fabb(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public Fabb(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);

    }

    private void init(Context mContext, AttributeSet attrs, int defStyleAttr) {
        cancelOnOutsideClick(mContext);

        fab_main = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        tv_fab_one = findViewById(R.id.tv_fab_one);
        tv_fab_two = findViewById(R.id.tv_fab_two);
        tv_fab_three = findViewById(R.id.tv_fab_three);

        fabMainIconRef = getResources().getDrawable(R.drawable.fab_cam);

        fab_close = AnimationUtils.loadAnimation(mContext, R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(mContext, R.anim.fab_open);

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.Fabb, defStyleAttr, 0);
        String fabActionOneText = a.getString(R.styleable.Fabb_setActionOneText);
        String fabTwoText = a.getString(R.styleable.Fabb_setActionTwoText);
        String fabThreeText = a.getString(R.styleable.Fabb_setActionThreeText);
        fabMainBackgroundColor = a.getColor(R.styleable.Fabb_setMainFabBackgroundColor, defStyleAttr);
        fabMainBackgroundColorOpened = a.getColor(R.styleable.Fabb_setMainFabOpenedColor, defStyleAttr);
        int fabActionOneBackgroundColor = a.getColor(R.styleable.Fabb_setActionOneBackgroundColor, defStyleAttr);
        int fabActionTwoBackgroundColor = a.getColor(R.styleable.Fabb_setActionTwoBackgroundColor, defStyleAttr);
        int fabActionThreeBackgroundColor = a.getColor(R.styleable.Fabb_setActionThreeBackgroundColor, defStyleAttr);
        Drawable fabMainIcon = a.getDrawable(R.styleable.Fabb_setMainIcon);
        Drawable fabActionOneIcon = a.getDrawable(R.styleable.Fabb_setActionOneIcon);
        Drawable fabTwoIcon = a.getDrawable(R.styleable.Fabb_setActionTwoIcon);
        Drawable fabThreeIcon = a.getDrawable(R.styleable.Fabb_setActionThreeIcon);
        mainFabOpenedColor = a.getColor(R.styleable.Fabb_setMainFabOpenedColor, defStyleAttr);


        if (fabMainBackgroundColor != 0) {
            fab_main.setBackgroundTintList(ColorStateList.valueOf(fabMainBackgroundColor));
        }
        if (fabActionOneBackgroundColor != 0) {
            fab1.setBackgroundTintList(ColorStateList.valueOf(fabActionOneBackgroundColor));
        }
        if (fabActionTwoBackgroundColor != 0){
            fab2.setBackgroundTintList(ColorStateList.valueOf(fabActionTwoBackgroundColor));
        }
        if (fabActionThreeBackgroundColor != 0){
            fab3.setBackgroundTintList(ColorStateList.valueOf(fabActionThreeBackgroundColor));
        }


        if (fabMainIcon != null){
            fab_main.setImageDrawable(fabMainIcon);
            fabMainIconRef = fabMainIcon;
        }else{
            fabMainIconRef = getResources().getDrawable(R.drawable.fab_cam);
        }

        if (fabActionOneIcon != null){
            fab1.setImageDrawable(fabActionOneIcon);
            fabActionOneIconRef = fabActionOneIcon;
        }else{
            fabActionOneIconRef = getResources().getDrawable(R.drawable.fab_cam);
        }

        if (fabTwoIcon != null){
            fab2.setImageDrawable(fabTwoIcon);
            fabTwoIconRef = fabTwoIcon;
        }else {
            fabTwoIconRef = getResources().getDrawable(R.drawable.fab_cam);
        }

        if (fabThreeIcon != null){
            fab3.setImageDrawable(fabThreeIcon);
            fabThreeIconRef = fabTwoIcon;
        }else {
            fabThreeIconRef = getResources().getDrawable(R.drawable.fab_cam);
        }


        if (fabActionOneText != null)
            tv_fab_one.setText(fabActionOneText);
        if (fabTwoText != null)
            tv_fab_two.setText(fabTwoText);
        if (fabThreeText != null)
            tv_fab_three.setText(fabThreeText);

        a.recycle();

        onClickListeners();

    }

    private void onClickListeners() {
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbMainPressed();
            }
        });

        tv_fab_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbMainPressed();
            }

        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbActionOnePressed();
            }

        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbTwoPressed();
            }
        });

        tv_fab_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbTwoPressed();
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbThreePressed();
            }
        });

        tv_fab_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (isOpen) {
                    fabClosingAnimation();
                } else {
                    fabOpeningAnimation();
                }
                if (fabbListener != null)
                    fabbListener.onFabbThreePressed();
            }
        });
    }

    // Dismiss Fabb
    private void cancelOnOutsideClick(Context mContext) {
        View mView = inflate(mContext, R.layout.fab_layout, null);
        addView(mView);

        mView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (isOpen) {
                    if (!shouldKeepOpen) {
                        fabClosingAnimation();
                    }
                }
                return false;
            }
        });
    }

    // Open animation
    private void fabOpeningAnimation() {
        if (numberOfActions > 0) {
            if (fabbListener != null) {
                fabbListener.onFabbOpened();
            }
            tv_fab_one.setVisibility(View.VISIBLE);
            fab1.startAnimation(fab_open);
            if (numberOfActions > 1) {
                tv_fab_two.setVisibility(View.VISIBLE);
                fab2.startAnimation(fab_open);
                fab2.setClickable(true);
                if (numberOfActions > 2) {
                    tv_fab_three.setVisibility(View.VISIBLE);
                    fab3.startAnimation(fab_open);
                    fab3.setClickable(true);
                }
            }

            fab1.setClickable(true);
            fab_main.setImageResource(R.drawable.ic_close_black_24dp);
            if (fabMainBackgroundColorOpened != 0){
                fab_main.setBackgroundTintList(ColorStateList.valueOf(fabMainBackgroundColorOpened));
            }else {
                fab_main.setBackgroundTintList(ColorStateList.valueOf(mainFabOpenedColor));
            }
            isOpen = true;
        }
    }

    // Close animation
    private void fabClosingAnimation() {
        if (numberOfActions > 0) {
            if (fabbListener != null) {
                fabbListener.onFabbClosed();
            }
            tv_fab_one.setVisibility(View.INVISIBLE);
            fab1.startAnimation(fab_close);
            fab1.setClickable(false);
            if (numberOfActions > 1) {
                tv_fab_two.setVisibility(View.INVISIBLE);
                fab2.startAnimation(fab_close);
                fab2.setClickable(false);
                if (numberOfActions > 2) {
                    tv_fab_three.setVisibility(View.INVISIBLE);
                    fab3.startAnimation(fab_close);
                    fab3.setClickable(false);
                }
            }

            fab_main.setImageDrawable(fabMainIconRef);
            if (fabMainBackgroundColor != 0) {
                fab_main.setBackgroundTintList(ColorStateList.valueOf(fabMainBackgroundColor));
            } else {
                fab_main.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.redfab)));
            }
            isOpen = false;
        }
    }

    // Set the main fab background color
    public void setMainFabBackgroundColor(int color){
        fabMainBackgroundColor = color;
        fab_main.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    // Set the main fab background color when it is open
    public void setMainFabonOpenedColor(int color){
        fabMainBackgroundColorOpened = color;
        if (isOpen()) {
            fab_main.setBackgroundTintList(ColorStateList.valueOf(color));
        }
    }

    // Set the first action background color
    public void setActionOneBackgroundColor(int color){
        fab1.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    // Set the second action background color
    public void setActionTwoBackgroundColor(int color){
        fab2.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    // Set the third action background color
    public void setActionThreeBackgroundColor(int color){
        fab3.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    // Set the text for each action button
    // This is the text that is displayed left of the action button
    public void setActionOneText(String title){
        tv_fab_one.setText(title);
    }
    public void setActionTwoText(String title){
        tv_fab_two.setText(title);
    }
    public void setActionThreeText(String title){
        tv_fab_three.setText(title);
    }

    // Set the icon for each action button
    // Pass a drawable resource to each, for example -> R.drawable.some_image
    public void setMainFabIcon(Drawable drawableResource){
        fabMainIconRef = drawableResource;
        fab_main.setImageDrawable(drawableResource);
    }
    public void setActionOneIcon(int drawableResource){
        fabActionOneIconRef = getResources().getDrawable(drawableResource);
        fab1.setImageResource(drawableResource);
    }
    public void setActionTwoIcon(int drawableResource){
        fabTwoIconRef = getResources().getDrawable(drawableResource);
        fab2.setImageResource(drawableResource);
    }
    public void setActionThreeIcon(int drawableResource){
        fabThreeIconRef = getResources().getDrawable(drawableResource);
        fab3.setImageResource(drawableResource);
    }

    // Check if it is open
    public boolean isOpen(){
        return isOpen;
    }

    // Set the number of action button to be displayed
    // This can be 1-3
    public void setNumberOfActions(int amount){
        numberOfActions = amount;
    }

    // Set custom font
    // You should pass a Typface, this can be done like this:
    // First place your font in your asset folder, then call:
    // fabb.setCustomFont(Typeface.createFromAsset(getAssets(), "fonts/YourFontName.otf"));
    public void setCustomFont(Typeface font){
        tv_fab_one.setTypeface(font);
        tv_fab_two.setTypeface(font);
        tv_fab_three.setTypeface(font);
    }

    // Set the backgound color of the text that is being displayed left of the action buttons
    public void setTextBackgroundColor(int color){
        tv_fab_one.setBackgroundColor(color);
        tv_fab_two.setBackgroundColor(color);
        tv_fab_three.setBackgroundColor(color);
    }
    // Set the text color that is displayed left of the action buttons
    public void setTextColor(int color){
        tv_fab_one.setTextColor(color);
        tv_fab_two.setTextColor(color);
        tv_fab_three.setTextColor(color);
    }

    // Enable/disable the hiding of the action buttons
    // When enabled, the action buttons will be hidden as soon as the user pressed anywhere on the view
    // When disabled, fabb will remain open until the user pressed the main fab again
    public void dismissOnOutsideClicked(boolean bool){
        shouldKeepOpen = bool;
    }

    // Set all the action buttons background color
    public void setAllActionsBackground(int color){
        fab_main.setBackgroundTintList(ColorStateList.valueOf(color));
        fab1.setBackgroundTintList(ColorStateList.valueOf(color));
        fab2.setBackgroundTintList(ColorStateList.valueOf(color));
        fab3.setBackgroundTintList(ColorStateList.valueOf(color));
        fabMainBackgroundColor = color;
    }



}
