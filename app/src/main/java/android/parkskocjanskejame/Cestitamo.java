package android.parkskocjanskejame;

import android.content.Intent;
import android.parkskocjanskejame.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cestitamo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cestitamo);

        TextView cestitamoText = (TextView) findViewById(R.id.cestitamoText);

        Constants.status++;

        switch (Constants.status) {
            case 1:
                cestitamoText.setText(R.string.cestitamo1);
                break;
            case 2:
                cestitamoText.setText(R.string.cestitamo3);
                break;
            case 3:
                cestitamoText.setText(R.string.cestitamo4);
                break;
            case 4:
                cestitamoText.setText(R.string.cestitamo7);
                break;
            case 5:
                cestitamoText.setText(R.string.cestitamo10);
                break;
            case 6:
                cestitamoText.setText(R.string.cestitamo16);
                break;
            case 7:
                cestitamoText.setText(R.string.cestitamo19);
                break;
            default:
                cestitamoText.setText(R.string.cestitamo26);
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
