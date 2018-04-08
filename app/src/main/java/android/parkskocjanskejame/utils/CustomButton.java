package android.parkskocjanskejame.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by Borut on 10. 06. 2017.
 */

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/HELR45W.ttf");
        this.setTypeface(face);
        //Helvetica
        //Arial narrow
        //Narrow
        //this.setTypeface(Typeface.DEFAULT);
    }

}