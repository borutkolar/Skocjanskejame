package android.parkskocjanskejame;

import android.content.Intent;
import android.parkskocjanskejame.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tabla1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla1);

        Constants.status++;

        Button tabla1Button = (Button) findViewById(R.id.tabla1Button);
        tabla1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Status.class);
                startActivity(intent);
            }
        });
    }
}
