package android.parkskocjanskejame;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.Functions;
import android.parkskocjanskejame.utils.GPSTracker;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class LocationSearch extends AppCompatActivity {
    /*
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.locationsearch);

        /*
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission) != MockPackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
                // If any permission above not allowed by user, this condition will
                // execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        /*
        GPSTracker gpsTracker = new GPSTracker(LocationSearch.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            float distance = gpsTracker.getDistance(latitude, longitude, 45.6635, 13.98953);
            if (distance < 5.0) {
                Intent intent = new Intent(getApplicationContext(), NFCScan.class);
                startActivity(intent);
            }
        } else {
            gpsTracker.showSettingsAlert();
        }
        */

        ImageView help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.helpPopup(LocationSearch.this);
            }
        });

        TextView textView = (TextView) findViewById(R.id.locationsearchText2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NFCScan.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
