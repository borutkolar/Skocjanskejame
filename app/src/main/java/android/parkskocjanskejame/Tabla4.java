package android.parkskocjanskejame;

import android.content.Intent;
import android.os.Bundle;
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
    public int[] wrongAnswers = new int[3];
    public Integer[] popupTexts =
            {R.string.tabla4popup0, R.string.tabla4popup2, R.string.tabla4popup5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla4);

        wrongAnswers[0] = 0;
        wrongAnswers[1] = 2;
        wrongAnswers[2] = 5;

        ExpandableHeightGridView tabla4GridView = (ExpandableHeightGridView) findViewById(R.id.tabla4Grid);
        ImageAdapter tabla4ImageAdapter = new ImageAdapter(Tabla4.this, tabla4Images, tabla4CheckboxSelection, wrongAnswers, popupTexts);
        tabla4GridView.setAdapter(tabla4ImageAdapter);
        tabla4GridView.setExpanded(true);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabla4CheckboxSelection[1] == true &&
                        tabla4CheckboxSelection[3] == true &&
                        tabla4CheckboxSelection[4] == true &&
                        tabla4CheckboxSelection[6] == true &&
                        tabla4CheckboxSelection[7] == true &&
                        tabla4CheckboxSelection[8] == true &&
                        tabla4CheckboxSelection[0] == false &&
                        tabla4CheckboxSelection[2] == false &&
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
