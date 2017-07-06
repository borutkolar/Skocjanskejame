package android.parkskocjanskejame;

import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tabla4 extends AppCompatActivity {
    public static boolean[] tabla4CheckboxSelection = new boolean[9];
    public static Integer[] tabla4Images =
            {R.drawable.tabla4image0, R.drawable.tabla4image1,
                    R.drawable.tabla4image2, R.drawable.tabla4image3,
                    R.drawable.tabla4image4, R.drawable.tabla4image5,
                    R.drawable.tabla4image6, R.drawable.tabla4image7,
                    R.drawable.tabla4image8};
    public static Integer[] tabla26Sounds = {};
    public Integer[] popupTexts =
            {R.string.tabla4popup0, null, R.string.tabla4popup2, null, null, R.string.tabla4popup5, null, null, null};
    public Boolean[] answers =
            {false, true, false, true, true, false, true, true, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla4);

        Constants.status = 3;
        Constants.counter = 4;

        ExpandableHeightGridView tabla4GridView = (ExpandableHeightGridView) findViewById(R.id.tabla4Grid);
        ImageAdapter tabla4ImageAdapter = new ImageAdapter(Tabla4.this, tabla4Images, tabla26Sounds, tabla4CheckboxSelection, answers, popupTexts);
        tabla4GridView.setAdapter(tabla4ImageAdapter);
        tabla4GridView.setExpanded(true);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean equal = true;
                for (int i = 0; i < tabla4CheckboxSelection.length; i++) {
                    if (tabla4CheckboxSelection[i] != answers[i]) {
                        equal = false;
                    }
                }
                if (equal == true) {
                    Intent intent = new Intent(getApplicationContext(), Cestitamo.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Napačen odgovor!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
