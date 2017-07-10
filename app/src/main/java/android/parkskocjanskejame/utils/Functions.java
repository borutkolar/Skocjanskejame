package android.parkskocjanskejame.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.parkskocjanskejame.R;
import android.parkskocjanskejame.Status;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Borut on 28. 06. 2017.
 */

public class Functions {
    public static void helpPopup(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.helppopup, null);
        alertDialog.setView(v);
        final AlertDialog alert = alertDialog.create();
        alert.show();
        Button helpButton = (Button) v.findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }
}
