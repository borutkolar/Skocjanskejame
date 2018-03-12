package android.parkskocjanskejame;

import android.content.Intent;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.Functions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Cestitamo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cestitamo);

        if (Constants.status == 0) {
            Constants.status = 1;
        }

        TextView cestitamoText1 = (TextView) findViewById(R.id.cestitamoText1);
        ImageView cestitamoImage = (ImageView) findViewById(R.id.cestitamoImage);
        TextView cestitamoText2 = (TextView) findViewById(R.id.cestitamoText2);
        TextView cestitamoText3 = (TextView) findViewById(R.id.cestitamoText3);

        switch (Constants.status) {
            case 1:
                cestitamoText1.setText(R.string.cestitamo1a);
                cestitamoImage.setImageResource(R.drawable.znacka1);
                cestitamoText2.setText(R.string.cestitamo1b);
                Constants.alpha[0] = true;
                break;
            case 2:
                cestitamoText1.setText(R.string.cestitamo3a);
                cestitamoImage.setImageResource(R.drawable.znacka2);
                cestitamoText2.setText(R.string.cestitamo3b);
                Constants.alpha[1] = true;
                break;
            case 3:
                cestitamoText1.setText(R.string.cestitamo4a);
                cestitamoImage.setImageResource(R.drawable.znacka3);
                cestitamoText2.setText(R.string.cestitamo4b);
                Constants.alpha[2] = true;
                break;
            case 4:
                cestitamoText1.setText(R.string.cestitamo7a);
                cestitamoImage.setImageResource(R.drawable.znacka4);
                cestitamoText2.setText(R.string.cestitamo7b);
                Constants.alpha[3] = true;
                break;
            case 5:
                cestitamoText1.setText(R.string.cestitamo10a);
                cestitamoImage.setImageResource(R.drawable.znacka5);
                cestitamoText2.setText(R.string.cestitamo10b);
                Constants.alpha[4] = true;
                break;
            case 6:
                cestitamoText1.setText(R.string.cestitamo16a);
                cestitamoImage.setImageResource(R.drawable.znacka6);
                cestitamoText2.setText(R.string.cestitamo16b);
                Constants.alpha[5] = true;
                break;
            case 7:
                cestitamoText1.setText(R.string.cestitamo19a);
                cestitamoImage.setImageResource(R.drawable.znacka7);
                cestitamoText2.setText(R.string.cestitamo19b);
                Constants.alpha[6] = true;
                break;
            case 8:
                cestitamoText1.setText(R.string.cestitamo26a);
                cestitamoImage.setImageResource(R.drawable.znacka8);
                cestitamoText2.setText(R.string.cestitamo26b);
                cestitamoText3.setText(R.string.cestitamo26c);
                Constants.alpha[7] = true;
                break;
        }

        Button cestitamoButton = (Button) findViewById(R.id.cestitamoButton);
        cestitamoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Status.class);
                startActivity(intent);
                finish();
            }
        });

        ImageView help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.helpPopup(Cestitamo.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
