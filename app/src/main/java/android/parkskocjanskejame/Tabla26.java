package android.parkskocjanskejame;


import android.content.Intent;
import android.os.Bundle;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.parkskocjanskejame.Tabla4.tabla4CheckboxSelection;

public class Tabla26 extends AppCompatActivity {
    public static boolean[] tabla26CheckboxSelection = new boolean[7];
    public static Integer[] tabla26Images =
            {R.drawable.tabla26image0, R.drawable.tabla26image1,
                    R.drawable.tabla26image2, R.drawable.tabla26image3,
                    R.drawable.tabla26image4, R.drawable.tabla26image5,
                    R.drawable.tabla26image6};
    public int[] wrongAnswers = new int[3];
    public Integer[] popupTexts =
            {R.string.tabla26popup1, R.string.tabla26popup4, R.string.tabla26popup5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla26);

        wrongAnswers[0] = 1;
        wrongAnswers[1] = 4;
        wrongAnswers[2] = 5;

        ExpandableHeightGridView tabla26GridView = (ExpandableHeightGridView) findViewById(R.id.tabla26Grid);
        ImageAdapter tabla26ImageAdapter = new ImageAdapter(Tabla26.this, tabla26Images, tabla26CheckboxSelection, wrongAnswers, popupTexts);
        tabla26GridView.setAdapter(tabla26ImageAdapter);
        tabla26GridView.setExpanded(true);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabla4CheckboxSelection[0] == true &&
                        tabla4CheckboxSelection[2] == true &&
                        tabla4CheckboxSelection[3] == true &&
                        tabla4CheckboxSelection[6] == true &&
                        tabla4CheckboxSelection[1] == false &&
                        tabla4CheckboxSelection[4] == false &&
                        tabla4CheckboxSelection[5] == false) {
                    Intent intent = new Intent(getApplicationContext(), Cestitamo.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Napačen odgovor!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
