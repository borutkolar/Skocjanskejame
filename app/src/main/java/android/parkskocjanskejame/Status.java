package android.parkskocjanskejame;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.GPSTracker;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.widget.TextView;

public class Status extends AppCompatActivity {
    GPSTracker gpsTracker;
    double latitude, longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);

        TextView statusText2 = (TextView) findViewById(R.id.statusText2);
        statusText2.setText(Integer.toString(Constants.status));

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

        gpsTracker = new GPSTracker(Status.this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Status.this, NFCScan.class);
                Status.this.startActivity(intent);
                Status.this.finish();
            }
        }, 3000);

    }
}
