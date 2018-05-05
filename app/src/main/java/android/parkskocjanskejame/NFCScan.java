package android.parkskocjanskejame;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.Functions;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.UnsupportedEncodingException;

public class NFCScan extends AppCompatActivity {
    /*
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    */

    long[] vibrations = {0, 500, 500, 500, 500, 500, 500};

    NfcAdapter nfcAdapter;

    int[] images = {R.drawable.nfcboard1, R.drawable.nfcboard2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfcscan);

        /*
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(vibrations, -1);
        */

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

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!nfcAdapter.isEnabled()) {
            showSettingsAlert();
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
        /*Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage[] messages = new NdefMessage[rawMessages.length];
        for (int i = 0; i < rawMessages.length; i++) {
            messages[i] = (NdefMessage) rawMessages[i];
        }*/

        byte[] tagBytes = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String tagID = ByteArrayToHexString(tagBytes);

        switch (Constants.status) {
            case 0:
                Intent intent0 = new Intent(this, Cestitamo.class);
                startActivity(intent0);
                break;
            case 1:
                Intent intent1 = new Intent(this, Tabla3a.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, Tabla4.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, Tabla7.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(this, Tabla10.class);
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(this, Tabla16.class);
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(this, Tabla19.class);
                startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent(this, Tabla26.class);
                startActivity(intent7);
                break;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        IntentFilter[] writeTagFilters = new IntentFilter[] { tagDetected };
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out = "";

        for(j = 0 ; j < inarray.length ; j++) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }

        return out;
    }

    /*
    private String buildTagViews(NdefMessage[] messages) {
        String text = "";
        byte[] payload = messages[0].getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
        int languageCodeLength = payload[0] & 0063;

        try {
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        return text;
    }
    */

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.nfcTitle);
        alertDialog.setMessage(R.string.nfcMessage);

        alertDialog.setPositiveButton(R.string.gpsSettings, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton(R.string.gpsCancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
    }
}