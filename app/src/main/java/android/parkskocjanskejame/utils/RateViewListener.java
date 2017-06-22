package android.parkskocjanskejame.utils;

/**
 * Created by Nemanja on 22. 05. 2017.
 */

public interface RateViewListener {
    void onMove(int movedX, int maxHeight);
    void onSwipe(boolean liked);
}
