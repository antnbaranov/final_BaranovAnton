package harshbarash.github.astronaut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private LinearLayout mDotLayout;
    private Button button;

    public int[] images = {

            R.drawable.mars,
            R.drawable.earth,
            R.drawable.moon
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); ///////отправил вьюху


        ViewPager mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        SliderAdapter sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.setCurrentItem(1);
    }


}