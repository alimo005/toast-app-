
package com.example.hellotoast;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.hellotoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    private ActivityMainBinding mainBinding;

    private static final String COUNT_KEY = "saved_count";

    // Button colors
    private final int grayColor = Color.GRAY;
    private final int orangeColor = Color.rgb(255, 152, 0);
    private final int greenColor = Color.GREEN;
    private final int blueColor = Color.BLUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        mainBinding = ActivityMainBinding.inflate(
                getLayoutInflater()
        );

        setContentView(mainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        // Restore count after screen rotation
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(COUNT_KEY, 0);
        }

        updateCountDisplay();
        updateButtonColors();
    }

    public void show_toast(View view) {
        Toast.makeText(
                this,
                "Hello Toast",
                Toast.LENGTH_SHORT
        ).show();
    }

    public void increase_count(View view) {

        count++;

        updateCountDisplay();
        updateButtonColors();
    }

    public void showZero(View view) {

        count = 0;

        updateCountDisplay();
        updateButtonColors();
    }

    private void updateCountDisplay() {
        mainBinding.textDisplayCount.setText(
                String.valueOf(count)
        );
    }

    private void updateButtonColors() {

        if (count == 0) {

            mainBinding.buttonZero.setBackgroundTintList(
                    ColorStateList.valueOf(grayColor)
            );

            mainBinding.buttonCount.setBackgroundTintList(
                    ColorStateList.valueOf(blueColor)
            );

        } else {

            mainBinding.buttonZero.setBackgroundTintList(
                    ColorStateList.valueOf(orangeColor)
            );

            if (count % 2 == 1) {

                mainBinding.buttonCount.setBackgroundTintList(
                        ColorStateList.valueOf(greenColor)
                );

            } else {

                mainBinding.buttonCount.setBackgroundTintList(
                        ColorStateList.valueOf(blueColor)
                );
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(COUNT_KEY, count);
        super.onSaveInstanceState(outState);
    }
}
