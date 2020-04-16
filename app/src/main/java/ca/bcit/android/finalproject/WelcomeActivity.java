package ca.bcit.android.finalproject;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    Drawable logo_raw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        // Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        // Set logo to app color
        logo_raw = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_movie, null);
        int appColor = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        logo_raw.setColorFilter(new PorterDuffColorFilter(appColor, PorterDuff.Mode.SRC_ATOP));

        // Hooks
        image = findViewById(R.id.app_image);
        logo = findViewById(R.id.app_logo);
        slogan = findViewById(R.id.app_slogan);

        //Assign animations to hooks

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

