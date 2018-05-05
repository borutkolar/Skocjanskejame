package android.parkskocjanskejame;

import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tabla26 extends AppCompatActivity {
    public static boolean[] tabla26CheckboxSelection = new boolean[7];
    public static Integer[] tabla26Images =
            {R.drawable.tabla26image0, R.drawable.tabla26image1,
                    R.drawable.tabla26image2, R.drawable.tabla26image3,
                    R.drawable.tabla26image4, R.drawable.tabla26image5,
                    R.drawable.tabla26image6};
    public static Integer[] tabla26Sounds = {
            R.raw.tabla26sound0, null,
            R.raw.tabla26sound2, R.raw.tabla26sound3,
            null, null,
            R.raw.tabla26sound6
    };
    public Integer[] popupTexts =
            {null, R.string.tabla26popup1, null, null, R.string.tabla26popup4, R.string.tabla26popup5, null};
    public Boolean[] answers = {true, false, true, true, false, false, true};

    public Integer[] imageTexts = {
            R.string.tabla26image0Text, R.string.tabla26image1Text,
            R.string.tabla26image2Text, R.string.tabla26image3Text,
            R.string.tabla26image4Text, R.string.tabla26image5Text,
            R.string.tabla26image6Text
    };

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla26);

        Constants.status++;

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.tabla26Text2);

        ExpandableHeightGridView tabla26GridView = (ExpandableHeightGridView) findViewById(R.id.tabla26Grid);
        ImageAdapter tabla26ImageAdapter = new ImageAdapter
                (Tabla26.this, tabla26Images, tabla26Sounds, tabla26CheckboxSelection, answers, popupTexts, imageTexts, button, textView, counter);
        tabla26GridView.setAdapter(tabla26ImageAdapter);
        tabla26GridView.setExpanded(true);
    }

    @Override
    public void onBackPressed() {
    }
}
