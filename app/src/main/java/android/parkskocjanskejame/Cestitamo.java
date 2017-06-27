package android.parkskocjanskejame;

import android.content.Intent;
import android.parkskocjanskejame.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.parkskocjanskejame.utils.Constants.counter;

public class Cestitamo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cestitamo);

        TextView cestitamoText1 = (TextView) findViewById(R.id.cestitamoText1);
        ImageView cestitamoImage = (ImageView) findViewById(R.id.cestitamoImage);
        TextView cestitamoText2 = (TextView) findViewById(R.id.cestitamoText2);

        Constants.status++;

        switch (Constants.status) {
            case 1:
                cestitamoText1.setText(R.string.cestitamo1a);
                cestitamoImage.setImageResource(R.drawable.znacka1);
                cestitamoText2.setText(R.string.cestitamo1b);
                counter = 1;
                break;
            case 2:
                cestitamoText1.setText(R.string.cestitamo3a);
                cestitamoImage.setImageResource(R.drawable.znacka2);
                cestitamoText2.setText(R.string.cestitamo3b);
                counter = 3;
                break;
            case 3:
                cestitamoText1.setText(R.string.cestitamo4a);
                cestitamoImage.setImageResource(R.drawable.znacka3);
                cestitamoText2.setText(R.string.cestitamo4b);
                counter = 4;
                break;
            case 4:
                cestitamoText1.setText(R.string.cestitamo7a);
                cestitamoImage.setImageResource(R.drawable.znacka4);
                cestitamoText2.setText(R.string.cestitamo7b);
                counter = 7;
                break;
            case 5:
                cestitamoText1.setText(R.string.cestitamo10a);
                cestitamoImage.setImageResource(R.drawable.znacka5);
                cestitamoText2.setText(R.string.cestitamo10b);
                counter = 10;
                break;
            case 6:
                cestitamoText1.setText(R.string.cestitamo16a);
                cestitamoImage.setImageResource(R.drawable.znacka6);
                cestitamoText2.setText(R.string.cestitamo16b);
                counter = 16;
                break;
            case 7:
                cestitamoText1.setText(R.string.cestitamo19a);
                cestitamoImage.setImageResource(R.drawable.znacka7);
                cestitamoText2.setText(R.string.cestitamo19b);
                counter = 19;
                break;
            case 8:
                cestitamoText1.setText(R.string.cestitamo26a);
                cestitamoImage.setImageResource(R.drawable.znacka8);
                cestitamoText2.setText(R.string.cestitamo26b);
                counter = 26;
                break;
        }

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
