package android.parkskocjanskejame.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Vibrator;
import android.parkskocjanskejame.R;
import android.parkskocjanskejame.utils.RateViewListener;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Nemanja on 22. 05. 2017.
 */

public class RateView extends View{

    DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
    int fullWidth = metrics.widthPixels;

    //Finger tracking variables
    float touchY = 0;
    float touchX = 0;
    float startY = 0;
    float startX = 0;
    boolean isPressed = false;
    boolean inFalseArea = false;
    boolean inTrueArea = false;
    boolean vibrated = true;
    int maxHeight = 0;
    int maxWidth = 0;
    int leftRegion = (int) (fullWidth * (0.3));
    int rightRegion = (int) (maxWidth * (0.6));

    //Drawing variables
    double circleStartPosition = 0;
    Paint circlePaint = new Paint();
    int radius = 100;
    Paint holdNDragPaint = new Paint();
    Paint falsePaint = new Paint();
    Paint truePaint = new Paint();
    Paint confidencePaint = new Paint();
    Paint leftArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint rightArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint upArrowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Vibrator v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
    Bitmap arrows;
    int arrowBitMapSize = 120;
    //Costum Listener
    private RateViewListener mListener;
    String napacno = getContext().getString(R.string.napacno);
    String pravilno = getContext().getString(R.string.pravilno);
    String drzivleci = getContext().getString(R.string.drzivleci);

    public RateView(Context context) {
        super(context);
        init(context, null);
    }

    public RateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public RateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public RateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setCustomEventListener(RateViewListener listener) {
        this.mListener = listener;
    }

    private void init(Context context, AttributeSet attr) {



        //Circle color
        circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.circleColor));

        //Hold&Drag text color
        holdNDragPaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        holdNDragPaint.setTextSize(70);
        holdNDragPaint.setAntiAlias(true);
        holdNDragPaint.setTextAlign(Paint.Align.CENTER);

        //Confidence text color
        confidencePaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        confidencePaint.setTextSize(70);
        confidencePaint.setAntiAlias(true);
        confidencePaint.setTextAlign(Paint.Align.CENTER);

        //True text color
        truePaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        truePaint.setAlpha(80);
        truePaint.setTextSize(70);
        truePaint.setAntiAlias(true);
        truePaint.setTextAlign(Paint.Align.CENTER);

        //False text color
        falsePaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        falsePaint.setAlpha(80);
        falsePaint.setTextSize(70);
        falsePaint.setAntiAlias(true);
        falsePaint.setTextAlign(Paint.Align.CENTER);

        //Arrow color
        leftArrowPaint.setAlpha(80);
        rightArrowPaint.setAlpha(80);

        //Arrow bitmap
        arrows = BitmapFactory.decodeResource(getResources(), R.drawable.arrow1);
        arrows = Bitmap.createScaledBitmap(arrows, arrowBitMapSize, arrowBitMapSize, false);

    }

    @Override
    public void onDraw(Canvas canvas) {
        maxHeight = (int) (canvas.getHeight());
        maxWidth = canvas.getWidth();
        leftRegion = (int) (maxWidth * (0.3));
        rightRegion = (int) (maxWidth * (0.7));

        //Invert number
        circleStartPosition = maxHeight - (canvas.getHeight() * 0.5);
        if (circleStartPosition < 0) {
            circleStartPosition = 0;
        }
        super.onDraw(canvas);

        if (isPressed) {

            //Left side of screen
            if(inFalseArea){
                falsePaint.setTextSize(70);
                circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.falseAreaColor));
                truePaint.setColor(ContextCompat.getColor(getContext(), R.color.falseAreaColor));
                falsePaint.setColor(ContextCompat.getColor(getContext(), R.color.falseAreaColor));

                //output = output_start + ((output_end - output_start) / (input_end - input_start)) * (input - input_start)
                int alphaRange = (int)(0 + ((255 - 0) / ((maxWidth*0.18) - (maxWidth*0.3)) * (touchX - (maxWidth*0.3))));
                if(alphaRange>=255){
                    alphaRange = 255;
                }
                //dislikePaint.setAlpha(alphaRange);
                falsePaint.setAlpha(alphaRange);
                //MainActivity.imageViewDenied.setAlpha(alphaRange/255.0f);
                canvas.drawText(napacno, touchX,touchY-140, falsePaint);
                //canvas.drawText("Dislike", (float)(maxWidth*0.4), 150, dislikePaint);
            }
            //Right side of screen
            else if(inTrueArea){
                truePaint.setTextSize(70);
                truePaint.setColor(ContextCompat.getColor(getContext(), R.color.trueAreaColor));
                circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.trueAreaColor));
                falsePaint.setColor(ContextCompat.getColor(getContext(), R.color.trueAreaColor));


                //output = output_start + ((output_end - output_start) / (input_end - input_start)) * (input - input_start)
                int alphaRange = (int)(0 + ((255 - 0) / ((maxWidth*0.85) - (maxWidth*0.7)) * (touchX - (maxWidth*0.7))));
                if(alphaRange>=255){
                    alphaRange = 255;
                }
                //likePaint.setAlpha(alphaRange);
                truePaint.setAlpha(alphaRange);
                //MainActivity.imageViewApproved.setAlpha(alphaRange/255.0f);

                canvas.drawText(pravilno, touchX,touchY-140, truePaint);
                //canvas.drawText("Like", (float)(maxWidth*0.8), 150 , likePaint);
            }
            //Middle part of the screen
            else{

                resetColors();

                //output = output_start + ((output_end - output_start) / (input_end - input_start)) * (input - input_start)
                int alphaRange = (int)(0 + ((255 - 0) / ((maxWidth*0.3) - (maxWidth*0.5)) * (touchX - (maxWidth*0.5))));
                if(alphaRange>=255){
                    alphaRange = 255;
                }else if(alphaRange<100){
                    alphaRange = 100;
                }
                falsePaint.setAlpha(alphaRange);
                leftArrowPaint.setAlpha(alphaRange);

                //output = output_start + ((output_end - output_start) / (input_end - input_start)) * (input - input_start)
                alphaRange = (int)(0 + ((255 - 0) / ((maxWidth*0.7) - (maxWidth*0.5)) * (touchX - (maxWidth*0.5))));
                if(alphaRange>=255){
                    alphaRange = 255;
                }else if(alphaRange<100){
                    alphaRange = 100;
                }
                truePaint.setAlpha(alphaRange);
                rightArrowPaint.setAlpha(alphaRange);


                canvas.drawText(napacno, touchX-340,touchY+20, falsePaint);
                canvas.drawText(pravilno, touchX+330,touchY+20, truePaint);
            }

            //Paint circle and remove "Hold&Drag" text
            canvas.drawCircle(touchX,touchY, radius, circlePaint);
            canvas.drawText("", touchX,touchY+170, holdNDragPaint);
            //Paint left arrow
            canvas.drawBitmap(arrows, touchX-(radius+120), (int)touchY-(arrowBitMapSize/2), leftArrowPaint);
            //Paint right arrow
            canvas.drawBitmap(RotateBitmap(arrows, 180), touchX+radius, touchY-(arrowBitMapSize/2), rightArrowPaint);
        }else{
            //Reset colors
            resetColors();
            confidencePaint.setAlpha(255);
            upArrowPaint.setAlpha(255);

            //Paint the circle
            canvas.drawCircle((canvas.getWidth()/2),(int)circleStartPosition, radius, circlePaint);
            //canvas.drawText("Confidence", (canvas.getWidth()/2),(int)circleStartPosition-210, confidencePaint);
            canvas.drawText(drzivleci, (canvas.getWidth()/2),(int)circleStartPosition+170, holdNDragPaint);
            canvas.drawText(napacno, (canvas.getWidth()/2)-340,(int)circleStartPosition+20, falsePaint);
            canvas.drawText(pravilno, (canvas.getWidth()/2)+330,(int)circleStartPosition+20, truePaint);

            //Paint up arrow
            //canvas.drawBitmap(RotateBitmap(arrows, 90), (canvas.getWidth()/2)-(arrowBitMapSize/2), (int)circleStartPosition-(radius+arrowBitMapSize), null);

            //Paint left arrow
            canvas.drawBitmap(arrows, (canvas.getWidth()/2)-(radius+120), (int)circleStartPosition-(arrowBitMapSize/2), transparent(100));

            //Paint right arrow
            canvas.drawBitmap(RotateBitmap(arrows, 180), (canvas.getWidth()/2)+radius, (int)circleStartPosition-(arrowBitMapSize/2), transparent(100));
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {


        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isPressed = true;
                startX = motionEvent.getX();
                startY = motionEvent.getY();
                touchX = maxWidth/2;
                touchY = (float)circleStartPosition;
                //touchX = motionEvent.getX();
                //touchY = motionEvent.getY();
                if ((int) touchX < leftRegion) {
                    inFalseArea = true;
                } else if (rightRegion <(int) touchX) {
                    inTrueArea = true;
                }else {
                    inFalseArea = false;
                    inTrueArea = false;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                //Get Y and X coordinates
                //touchY = motionEvent.getY();
                //touchX = motionEvent.getX();
                touchX = (maxWidth/2)+(motionEvent.getX() - startX );
                //touchY = (float)circleStartPosition-(startY -motionEvent.getY());
                if ((int) touchX < leftRegion) {
                    inFalseArea = true;
                    //vibrate();
                } else if (rightRegion <(int) touchX) {
                    inTrueArea = true;
                    //vibrate();
                }else {
                    inFalseArea = false;
                    inTrueArea = false;
                    vibrated = false;
                }
                mListener.onMove((int)touchY, maxHeight);
                break;

            case MotionEvent.ACTION_UP:
                isPressed = false;
                inFalseArea = false;
                inTrueArea = false;
                startX = 0;
                startY = 0;
                //Check if true or false
                if (touchX < leftRegion) {
                    resetColors();
                    mListener.onSwipe(false);
                } else if (rightRegion < touchX) {
                    resetColors();
                    mListener.onSwipe(true);
                } else {
                    mListener.onMove(0, 0);
                }
                break;
        }
        invalidate();
        return true;
    }
    public void resetColors(){
        circlePaint.setColor(ContextCompat.getColor(getContext(), R.color.circleColor));
        confidencePaint.setTextSize(70);
        falsePaint.setTextSize(70);
        truePaint.setTextSize(70);

        //pricePaint.setColor(ContextCompat.getColor(getContext(), R.color.buttonText));
        falsePaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        truePaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        //upArrowPaint.setColor(ContextCompat.getColor(getContext(), R.color.buttonText));
        leftArrowPaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));
        rightArrowPaint.setColor(ContextCompat.getColor(getContext(), R.color.textColor));

        truePaint.setAlpha(80);
        falsePaint.setAlpha(80);
        leftArrowPaint.setAlpha(80);
        rightArrowPaint.setAlpha(80);
    }


    public void vibrate(){
        if(vibrated == false){
            v.vibrate(50);
            vibrated = true;
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public static Paint transparent(int alpha){
        Paint paint = new Paint();
        paint.setAlpha(alpha);
        return paint;
    }

}

