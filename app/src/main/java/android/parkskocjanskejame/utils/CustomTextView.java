package android.parkskocjanskejame.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.AttributedCharacterIterator;

/**
 * Created by Borut on 10. 06. 2017.
 */

public class CustomTextView extends AppCompatTextView {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Arvo-Regular.ttf");
        this.setTypeface(face);
    }

}
