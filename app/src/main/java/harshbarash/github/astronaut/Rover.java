package harshbarash.github.astronaut;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rover extends Activity {
    Button btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.marsokhod1);

        btn = (Button)findViewById( R.id.change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Rover.this, MarsExpedition.class);
                startActivity(i);
                finish();
            }
        });

    }
}
