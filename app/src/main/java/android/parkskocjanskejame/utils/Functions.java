package android.parkskocjanskejame.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.parkskocjanskejame.R;
import android.parkskocjanskejame.Status;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Borut on 28. 06. 2017.
 */

public class Functions {
    public static void helpPopup(Context context) {

        final Dialog alertDialog=new Dialog(context,R.style.AppTheme);
        alertDialog.setContentView(R.layout.helppopup);
        alertDialog.show();


        /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.helppopup, null);
        alertDialog.setView(v);

        final AlertDialog alert = alertDialog.create();
        alert.show();*/
        Button helpButton = (Button) alertDialog.findViewById(R.id.helpButton);

        TextView t2 = (TextView) alertDialog.findViewById(R.id.helppopupText2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
    }

    public static void putBooleanArray(boolean[] array, SharedPreferences.Editor editor) {
        for (int i = 0; i <  array.length; i++) {
            editor.putBoolean("Alpha" + "_" + i, array[i]);
        }

        editor.apply();
    }

    public static boolean[] getBooleanArray(SharedPreferences prefs) {
        boolean array[] = new boolean[12];
        for (int i = 0; i < 12; i++) {
            array[i] = prefs.getBoolean("Alpha" + "_" + i, false);
        }

        return array;
    }
}
