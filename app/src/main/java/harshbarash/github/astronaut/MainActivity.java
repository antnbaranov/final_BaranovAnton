package harshbarash.github.astronaut;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private LinearLayout mDotLayout;

    public int[] images = {

            R.drawable.earth,
            R.drawable.moon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);

        SliderAdapter sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        mSlideViewPager.setCurrentItem(1);


    }
}