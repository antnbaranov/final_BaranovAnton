package harshbarash.github.astronaut;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MarsExpedition extends AppCompatActivity {
    Button btn;
    ImageButton btnm1, btnm2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_researches);

        btn = (Button)findViewById( R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnm1 = (ImageButton)findViewById(R.id.btn);
        btnm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, Rover.class);
                startActivity(i);
                finish();
            }
        });

        btnm2 = (ImageButton)findViewById(R.id.btn2);
        btnm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MarsExpedition.this, Rover2.class);
                startActivity(i);
                finish();
            }
        });
    }
}