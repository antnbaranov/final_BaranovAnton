package harshbarash.github.astronaut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Lunokhod extends Activity {

protected void onCreate(Bundle savedInstanceState) {
        Button btn;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunokhod2);

        btn = (Button)findViewById( R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent i = new Intent(Lunokhod.this, MoonExpedition.class);
                        startActivity(i);
                        finish();
                }
        });
        }
        }