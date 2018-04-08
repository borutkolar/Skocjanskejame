package android.parkskocjanskejame;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class ImageFS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagefs);

        ImageView imageFS = (ImageView) findViewById(R.id.imageFS);

        //enable application rotate:
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        Bundle extras = getIntent().getExtras();
        int image = extras.getInt("image");

        switch (image) {
            case 31:
                imageFS.setImageResource(R.drawable.kras1);
                break;
            case 32:
                imageFS.setImageResource(R.drawable.kras2);
                break;
            case 7:
                imageFS.setImageResource(R.drawable.tabla7slika1);
                break;
            case 10:
                imageFS.setImageResource(R.drawable.tabla10slika2);
                break;
            case 16:
                imageFS.setImageResource(R.drawable.tabla16);
                break;
            default:
                imageFS.setImageResource(R.drawable.tabla19image);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
