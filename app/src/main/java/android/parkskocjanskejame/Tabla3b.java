package android.parkskocjanskejame;

import android.content.Context;
import android.media.Image;
import android.parkskocjanskejame.utils.Constants;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.parkskocjanskejame.utils.ImageAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import static android.parkskocjanskejame.R.layout.gridview;
import static android.parkskocjanskejame.utils.ImageAdapter.checkboxSelection;

public class Tabla3b extends AppCompatActivity {
    public static boolean[] tabla3bCheckboxSelection = new boolean[6];
    public static Integer[] tabla3bImages =
            {R.drawable.tabla3bimage1, R.drawable.tabla3bimage2,
                    R.drawable.tabla3bimage3, R.drawable.tabla3bimage4,
                    R.drawable.tabla3bimage5, R.drawable.tabla3bimage6};
    public static Integer[] tabla3bSounds =
            {R.raw.beep};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla3b);

        Constants.status++;

        ExpandableHeightGridView tabla3bGridView = (ExpandableHeightGridView) findViewById(R.id.tabla3bGrid);
        tabla3bGridView.setExpanded(true);
        tabla3bGridView.setAdapter(new ImageAdapter(Tabla3b.this, R.layout.gridview, tabla3bImages, tabla3bSounds, tabla3bCheckboxSelection));

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
                    Toast.makeText(getApplicationContext(), "Pravilen odgovor!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Napačen odgovor!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
