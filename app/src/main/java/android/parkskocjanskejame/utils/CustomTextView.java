package android.parkskocjanskejame.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Borut on 10. 06. 2017.
 */

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Arvo-Regular.ttf");
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/HELR45W.ttf");
        this.setTypeface(face);
        //this.setTypeface(Typeface.DEFAULT);
    }

}
