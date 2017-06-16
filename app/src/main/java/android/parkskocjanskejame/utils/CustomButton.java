package android.parkskocjanskejame.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Borut on 10. 06. 2017.
 */

public class CustomButton extends AppCompatButton {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Arvo-Regular.ttf");
        this.setTypeface(face);
    }

}