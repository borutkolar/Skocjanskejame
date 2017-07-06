package android.parkskocjanskejame;

import android.parkskocjanskejame.utils.Constants;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tabla19 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla19);

        Constants.status = 7;
        Constants.counter = 19;
    }
}
