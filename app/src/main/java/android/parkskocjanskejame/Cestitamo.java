package android.parkskocjanskejame;

import android.content.Intent;
import android.parkskocjanskejame.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cestitamo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cestitamo);

        Constants.status++;

        Button cestitamoButton = (Button) findViewById(R.id.cestitamoButton);
        cestitamoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Status.class);
                startActivity(intent);
            }
        });
    }
}
