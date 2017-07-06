package android.parkskocjanskejame;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.Functions;
import android.parkskocjanskejame.utils.GPSTracker;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SearchViewCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.resource;
import static android.parkskocjanskejame.R.id.helpButton;
import static android.parkskocjanskejame.R.id.statusText2;

public class Status extends AppCompatActivity {
    GPSTracker gpsTracker;
    double latitude, longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    AlertDialog alert;

    public Integer[] images =
            {R.drawable.znacka1, R.drawable.znacka2,
                    R.drawable.znacka3, R.drawable.znacka4,
                    R.drawable.znacka5, R.drawable.znacka6,
                    R.drawable.znacka7, R.drawable.znacka8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);

        final Context context = getApplicationContext();

        ImageView znacka1 = (ImageView) findViewById(R.id.znacka1);
        ImageView znacka2 = (ImageView) findViewById(R.id.znacka2);
        ImageView znacka3 = (ImageView) findViewById(R.id.znacka3);
        ImageView znacka4 = (ImageView) findViewById(R.id.znacka4);
        ImageView znacka5 = (ImageView) findViewById(R.id.znacka5);
        ImageView znacka6 = (ImageView) findViewById(R.id.znacka6);
        ImageView znacka7 = (ImageView) findViewById(R.id.znacka7);
        ImageView znacka8 = (ImageView) findViewById(R.id.znacka8);

        final ImageView[] imageViews = {znacka1, znacka2, znacka3, znacka4, znacka5, znacka6, znacka7, znacka8};

        for (int i = 0; i < Constants.alpha.length; i++) {
            if (Constants.alpha[i] == true) {
                imageViews[i].setAlpha(1f);
            }
        }

        TextView statusText2 = (TextView) findViewById(R.id.statusText2);
        statusText2.setText(Integer.toString(Constants.status));
        statusText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NFCScan.class);
                startActivity(intent);
            }
        });

        ImageView leftArrow = (ImageView) findViewById(R.id.leftarrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cestitamo.class);
                startActivity(i);
            }
        });

        ImageView help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.helpPopup(Status.this);
            }
        });

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
    }

    public void statusPopup(int resource) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Status.this);
        LayoutInflater layoutInflater = (LayoutInflater) Status.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.statuspopup, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.statuspopupImage);
        imageView.setImageResource(resource);
        alertDialog.setView(view);
        alert = alertDialog.create();
        alert.show();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }
}
