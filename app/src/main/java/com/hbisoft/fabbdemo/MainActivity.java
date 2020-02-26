package com.hbisoft.fabbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hbisoft.fabb.Fabb;
import com.hbisoft.fabb.FabbListener;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Declare Fabb
    Fabb fabb;
    TextView tv_current_selected, tv_current_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all views
        initViews();
        // Set custom Fabb setting
        fabbSettings();
        // Fabb Listeners
        fabbEventListeners();

    }

    private void initViews() {
        fabb = findViewById(R.id.fabb);
        tv_current_selected = findViewById(R.id.tv_current_selected);
        tv_current_state = findViewById(R.id.tv_current_state);
    }

    // If settings are set here and in xml, then this will override the xml settings
    private void fabbSettings() {
        fabb.setMainFabIcon(getResources().getDrawable(R.drawable.ic_mail_white_24dp));
        fabb.setActionOneText("Email");
        fabb.setActionTwoText("Call");
        fabb.setActionThreeText("Video Call");
        fabb.setAllActionsBackground(getResources().getColor(R.color.colorPrimary));
        fabb.setActionOneIcon(R.drawable.ic_mail_white_24dp);
        fabb.setActionTwoIcon(R.drawable.ic_call_white_24dp);
        fabb.setActionThreeIcon(R.drawable.fab_cam);
        fabb.setNumberOfActions(3);
    }

    @SuppressLint("SetTextI18n")
    private void fabbEventListeners() {
        fabb.setEventListener(new FabbListener() {
            @Override
            public void onFabbOpened() {
                tv_current_state.setText("Opened");
            }

            @Override
            public void onFabbClosed() {
                tv_current_state.setText("Closed");
            }

            @Override
            public void onFabbMainPressed() {
                tv_current_selected.setText("Main Fab Pressed");
            }

            @Override
            public void onFabbActionOnePressed() {
                tv_current_selected.setText("Action One Pressed");
            }

            @Override
            public void onFabbTwoPressed() {
                tv_current_selected.setText("Action Two Pressed");
            }

            @Override
            public void onFabbThreePressed() {
                tv_current_selected.setText("Action Three Pressed");
            }
        });
    }


    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_keep_open:
                fabb.dismissOnOutsideClicked(true);
                return true;
            case R.id.action_fabb_color:
                int color = getRandomColor();
                if (!fabb.isOpen()) {
                    fabb.setMainFabBackgroundColor(color);
                }
                fabb.setActionOneBackgroundColor(color);
                fabb.setActionTwoBackgroundColor(color);
                fabb.setActionThreeBackgroundColor(color);
                return true;
            case R.id.action_text_color:
                fabb.setTextColor(getRandomColor());
                return true;
            case R.id.action_text_font:
                fabb.setCustomFont(Typeface.createFromAsset(getAssets(), "fonts/Level Two SC.otf"));
                return true;
            case R.id.action_text_background:
                fabb.setTextBackgroundColor(getRandomColor());
                return true;
            case R.id.action_main_opened_color:
                fabb.setMainFabonOpenedColor(getRandomColor());
                return false;
        }

        return false;
    }


}
