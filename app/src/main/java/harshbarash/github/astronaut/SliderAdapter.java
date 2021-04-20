package harshbarash.github.astronaut;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter<val> extends PagerAdapter {

    Context context;
    Button button;
    LayoutInflater layoutInflater;
    public SliderAdapter(Context context) {
        this.context = context;

    }

    public int[] slide_images = {

            R.drawable.mars,
            R.drawable.earth,
            R.drawable.moon
    };

    public int[] bottom_images = {

            R.drawable.rover,
            R.drawable.flag,
            R.drawable.lunokhod2
    };

    public String[] bottom_title = {

            "Россия, ПрОП-М V2",
            "Россия, Восточный",
            "Россия, Луноход-2"

    };

    @Override
    public int getCount() {
        return slide_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o){
        return view == (RelativeLayout) o;
    }


    @NonNull
    @Override
    public Object instantiateItem (@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        Button button = view.findViewById(R.id.change);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openExpedition();
            }

            private void openExpedition() {
                if ( position == 1) {
                    Intent inent = new Intent(context, Expedition.class);
                    context.startActivity(inent);
                } else if ( position == 2) {
                    Intent inent = new Intent(context, MoonExpedition.class);
                    context.startActivity(inent);
                } else {
                    Intent inent = new Intent(context, MarsExpedition.class);
                    context.startActivity(inent);
                }
            }
        });



        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        @SuppressLint("CutPasteId") ImageView slideBottomView = (ImageView) view.findViewById(R.id.bottom_image);

        @SuppressLint("CutPasteId") TextView slideBottomTitle = (TextView) view.findViewById(R.id.subtitle);

        slideBottomView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openExpedition();
            }

            private void openExpedition() {
                if ( position == 1) {
                    Intent inent = new Intent(context, Expedition.class);
                    context.startActivity(inent);
                } else if ( position == 2) {
                    Intent inent = new Intent(context, Lunokhod.class);
                    context.startActivity(inent);
                } else {
                    Intent inent = new Intent(context, Rover.class);
                    context.startActivity(inent);
                }
            }
        });


        slideImageView.setImageResource(slide_images[position]);
        slideBottomView.setImageResource(bottom_images[position]);
        slideBottomTitle.setText(bottom_title[position]);




        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object){
        container.removeView((RelativeLayout)object);
    }


}
