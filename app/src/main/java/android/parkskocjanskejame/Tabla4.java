package android.parkskocjanskejame;


import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.parkskocjanskejame.Tabla3b.tabla3bSounds;

public class Tabla4 extends AppCompatActivity {
    public static boolean[] tabla3bCheckboxSelection = new boolean[9];
    public static Integer[] tabla3bImages =
            {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla3b);

        ExpandableHeightGridView tabla3bGridView = (ExpandableHeightGridView) findViewById(R.id.tabla3bGrid);
        tabla3bGridView.setExpanded(true);
        tabla3bGridView.setAdapter(new ImageAdapter(Tabla4.this, R.layout.gridview, tabla3bImages, tabla3bSounds, tabla3bCheckboxSelection));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabla3bCheckboxSelection[0] == true &&
                        tabla3bCheckboxSelection[3] == true &&
                        tabla3bCheckboxSelection[4] == true &&
                        tabla3bCheckboxSelection[1] == false &&
                        tabla3bCheckboxSelection[2] == false &&
                        tabla3bCheckboxSelection[5] == false) {
                    Intent intent = new Intent(getApplicationContext(), Cestitamo.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Napačen odgovor!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
