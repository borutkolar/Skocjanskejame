package android.parkskocjanskejame;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.Functions;
import android.parkskocjanskejame.utils.GPSTracker;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class NFCScan extends AppCompatActivity {
    GPSTracker gpsTracker;
    double latitude, longitude;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    long[] vibrations = {0, 500, 500, 500, 500, 500, 500};

    NfcAdapter nfcAdapter;

    int[] images = {R.drawable.nfcboard1, R.drawable.nfcboard2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfcscan);

        /*Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(vibrations, -1);*/

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

        gpsTracker = new GPSTracker(NFCScan.this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();
        } else {
            gpsTracker.showSettingsAlert();
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
            Toast.makeText(this, R.string.NFC, Toast.LENGTH_LONG).show();
        }

        final ImageView board = (ImageView) findViewById(R.id.nfcscanImage);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 1;
            @Override
            public void run() {
                board.setImageResource(images[i]);
                i--;
                if (i < 0) {
                    i = 1;
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);

        ImageView leftArrow = (ImageView) findViewById(R.id.leftarrow);
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.status == 0) {
                    Intent i1 = new Intent(getApplicationContext(), LocationSearch.class);
                    startActivity(i1);
                } else {
                    Intent i1 = new Intent(getApplicationContext(), Status.class);
                    startActivity(i1);
                }
            }
        });

        ImageView help = (ImageView) findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Functions.helpPopup(NFCScan.this);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        switch (Constants.status) {
            case 0:
                Intent intent0 = new Intent(this, Cestitamo.class);
                intent0.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent0, 0);
                IntentFilter[] intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 1:
                Intent intent1 = new Intent(this, Tabla3a.class);
                intent1.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 2:
                Intent intent2 = new Intent(this, Tabla4.class);
                intent2.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent2, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 3:
                Intent intent3 = new Intent(this, Tabla7.class);
                intent3.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent3, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 4:
                Intent intent4 = new Intent(this, Tabla10.class);
                intent4.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent4, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 5:
                Intent intent5 = new Intent(this, Tabla16.class);
                intent5.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent5, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 6:
                Intent intent6 = new Intent(this, Tabla19.class);
                intent6.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent6, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
            case 7:
                Intent intent7 = new Intent(this, Tabla26.class);
                intent7.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
                pendingIntent = PendingIntent.getActivity(this, 0, intent7, 0);
                intentFilter = new IntentFilter[]{};
                nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
                super.onResume();
                break;
        }
    }

    @Override
    protected void onPause() {
        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }
}