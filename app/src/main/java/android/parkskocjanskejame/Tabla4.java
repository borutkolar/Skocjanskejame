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

public class Tabla4 extends AppCompatActivity {
    public static boolean[] tabla4CheckboxSelection = new boolean[9];
    public static Integer[] tabla4Images =
            {R.drawable.tabla4image0, R.drawable.tabla4image1,
                    R.drawable.tabla4image2, R.drawable.tabla4image3,
                    R.drawable.tabla4image4, R.drawable.tabla4image5,
                    R.drawable.tabla4image6, R.drawable.tabla4image7,
                    R.drawable.tabla4image8};
    public static Integer[] tabla4Sounds = {
            null, R.raw.tabla4sound1,
            null, R.raw.tabla4sound3,
            R.raw.tabla4sound4, null,
            R.raw.tabla4sound67, R.raw.tabla4sound67,
            R.raw.tabla4sound8
    };
    public Integer[] popupTexts =
            {R.string.tabla4popup0, null, R.string.tabla4popup2, null, null, R.string.tabla4popup5, null, null, null};
    public Boolean[] answers =
            {false, true, false, true, true, false, true, true, true};

    public Integer[] imageTexts = {
            R.string.tabla4image0Text, R.string.tabla4image1Text,
            R.string.tabla4image2Text, R.string.tabla4image3Text,
            R.string.tabla4image4Text, R.string.tabla4image5Text,
            R.string.tabla4image6Text, R.string.tabla4image7Text,
            R.string.tabla4image8Text
    };

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla4);

        Constants.status++;

        Button button = (Button) findViewById(R.id.button);
        TextView textView = (TextView) findViewById(R.id.tabla4Text2);

        ExpandableHeightGridView tabla4GridView = (ExpandableHeightGridView) findViewById(R.id.tabla4Grid);
        ImageAdapter tabla4ImageAdapter = new ImageAdapter
                (Tabla4.this, tabla4Images, tabla4Sounds, tabla4CheckboxSelection, answers, popupTexts, imageTexts, button, textView, counter);
        tabla4GridView.setAdapter(tabla4ImageAdapter);
        tabla4GridView.setExpanded(true);
    }

    @Override
    public void onBackPressed() {
    }
}
