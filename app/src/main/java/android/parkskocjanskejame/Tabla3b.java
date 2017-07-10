package android.parkskocjanskejame;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.defaultValue;

public class Tabla3b extends AppCompatActivity {
    public static boolean[] tabla3bCheckboxSelection = new boolean[10];
    public static Integer[] tabla3bImages =
            {R.drawable.tabla3bimage0, R.drawable.tabla3bimage1,
                    R.drawable.tabla3bimage2, R.drawable.tabla3bimage3,
                    R.drawable.tabla3bimage4, R.drawable.tabla3bimage5,
                    R.drawable.tabla3bimage6, R.drawable.tabla3bimage7,
                    R.drawable.tabla3bimage8, R.drawable.tabla3bimage9,};
    public static Integer[] tabla3bSounds =
            {R.raw.tabla3bsound0, R.raw.tabla3bsound1, null,
                    R.raw.tabla3bsound3, null, R.raw.tabla3bsound5,
                    R.raw.tabla3bsound6, null, R.raw.tabla3bsound8, null};
    public Integer[] popupTexts =
            {null, null, R.string.tabla3bpopup2,
                    null, R.string.tabla3bpopup4, null,
                    null, R.string.tabla3bpopup7, null,
                    R.string.tabla3bpopup9};
    public Boolean[] answers =
            {true, true, false, true, false, true, true, false, true, false};

    int defaultValue = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla3b);

        ExpandableHeightGridView tabla3bGridView = (ExpandableHeightGridView) findViewById(R.id.tabla3bGrid);
        ImageAdapter tabla3bImageAdapter = new ImageAdapter(Tabla3b.this, tabla3bImages, tabla3bSounds, tabla3bCheckboxSelection, answers, popupTexts);
        tabla3bGridView.setAdapter(tabla3bImageAdapter);
        tabla3bGridView.setExpanded(true);

        TextView tabla3bText2 = (TextView) findViewById(R.id.tabla3bText2);
        int value = getIntent().getIntExtra("counter", defaultValue);
        tabla3bText2.setText(Integer.toString(value));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean equal = true;
                for (int i = 0; i < tabla3bCheckboxSelection.length; i++) {
                    if (tabla3bCheckboxSelection[i] != answers[i]) {
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
