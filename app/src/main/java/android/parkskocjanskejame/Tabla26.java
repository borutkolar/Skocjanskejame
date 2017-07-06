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

public class Tabla26 extends AppCompatActivity {
    public static boolean[] tabla26CheckboxSelection = new boolean[7];
    public static Integer[] tabla26Images =
            {R.drawable.tabla26image0, R.drawable.tabla26image1,
                    R.drawable.tabla26image2, R.drawable.tabla26image3,
                    R.drawable.tabla26image4, R.drawable.tabla26image5,
                    R.drawable.tabla26image6};
    public static Integer[] tabla26Sounds = {};
    public Integer[] popupTexts =
            {null, R.string.tabla26popup1, null, null, R.string.tabla26popup4, R.string.tabla26popup5, null};
    public Boolean[] answers = {true, false, true, true, false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla26);

        Constants.status = 8;
        Constants.counter = 26;

        ExpandableHeightGridView tabla26GridView = (ExpandableHeightGridView) findViewById(R.id.tabla26Grid);
        ImageAdapter tabla26ImageAdapter = new ImageAdapter(Tabla26.this, tabla26Images, tabla26Sounds, tabla26CheckboxSelection, answers, popupTexts);
        tabla26GridView.setAdapter(tabla26ImageAdapter);
        tabla26GridView.setExpanded(true);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean equal = true;
                for (int i = 0; i < tabla26CheckboxSelection.length; i++) {
                    if (tabla26CheckboxSelection[i] != answers[i]) {
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
