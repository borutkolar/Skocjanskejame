package android.parkskocjanskejame;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.RateView;
import android.parkskocjanskejame.utils.RateViewListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import static android.parkskocjanskejame.R.string.gumbZnova;
import static android.parkskocjanskejame.R.string.naprej;


public class Tabla3a extends AppCompatActivity implements RateViewListener {

    //Client side variables
    RateView rateView;
    AlertDialog.Builder alertDialog;
    AlertDialog alert;
    LayoutInflater inflater;
    View view;
    int images3a[] = {R.drawable.kras1, R.drawable.kras2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla3a);

        Constants.status++;

       /* //Setup the number font
        font = Typeface.createFromAsset(getAssets(), "dinFont.ttf");
        confidence = (TextView)findViewById(R.id.confidence);
        confidence.setTypeface(font);*/

        //Setup event listener for drawing
        rateView = (RateView) findViewById(R.id.rateView);
        rateView.setCustomEventListener(this);
    }

    @Override
    public void onMove(int movedX, int maxHeight) {

    }

    @Override
    public void onSwipe(boolean prav) {
        AlertDialog.Builder aD = new AlertDialog.Builder(Tabla3a.this);
        LayoutInflater inf = getLayoutInflater();
        View v = inf.inflate(R.layout.tabla3apopup, null);
        aD.setView(v);
        alert = aD.create();
        alert.setCancelable(false);
        alert.show();

        TextView text = (TextView) v.findViewById(R.id.textView23);
        text.setText(R.string.tabla3arazlaga);

        final ImageView tabla = (ImageView) v.findViewById(R.id.imageTabla3a);
        tabla.setImageResource(R.drawable.kras1);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i = 1;
            @Override
            public void run() {
                tabla.setImageResource(images3a[i]);
                i--;
                if (i < (0)) {
                    i = 1;
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);



        if (prav) {
            //Continue button
            Button continueButton = (Button) v.findViewById(R.id.button2345);
            continueButton.setText(naprej);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Tabla3b.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            //Retry button
            Button continueButton = (Button) v.findViewById(R.id.button2345);
            continueButton.setText(gumbZnova);
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.cancel();
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
    }
}

