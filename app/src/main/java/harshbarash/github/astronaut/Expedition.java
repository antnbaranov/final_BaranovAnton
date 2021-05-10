package harshbarash.github.astronaut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Expedition extends Activity {
    Button btn;
    ImageButton rus, rus2, usa, china;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_expedition);

        btn = (Button)findViewById( R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        rus = (ImageButton)findViewById( R.id.rus);
        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        rus2 = (ImageButton)findViewById( R.id.rus2);
        rus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        usa = (ImageButton)findViewById( R.id.usa);
        usa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        china = (ImageButton)findViewById( R.id.china);
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Expedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
