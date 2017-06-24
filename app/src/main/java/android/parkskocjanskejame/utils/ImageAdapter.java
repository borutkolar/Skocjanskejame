package android.parkskocjanskejame.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.parkskocjanskejame.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    public static int layout;
    public static Integer[] images;
    public static Integer[] sounds;
    public static boolean[] checkboxSelection;
    public static Boolean[] answers;
    public static Integer[] popupTexts;

    AlertDialog alert;
    MediaPlayer mediaPlayer;

    public ImageAdapter(Context c, Integer[] images, Integer[] sounds, boolean[] checkboxSelection, Boolean[] answers, Integer[] popupTexts) {
        this.context = c;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.sounds = sounds;
        this.checkboxSelection = checkboxSelection;
        this.answers = answers;
        this.popupTexts = popupTexts;
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.gridviewImage);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.gridviewCheckbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setId(position);
        viewHolder.imageView.setId(position);

        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        viewHolder.imageView.setAdjustViewBounds(true);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                int idImage = viewHolder.imageView.getId();
                int idCheckbox = viewHolder.checkBox.getId();
                if (checkboxSelection[idCheckbox] == true) {
                    viewHolder.checkBox.setChecked(false);
                    checkboxSelection[idCheckbox] = false;
                } else {
                    createSound(idImage);
                    viewHolder.checkBox.setChecked(true);
                    checkboxSelection[idCheckbox] = true;
                    showPopup(idImage);
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

    private void showPopup(int imageID) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View v = inflater.inflate(R.layout.tablapopup, null);
        alertDialog.setView(v);
        alert = alertDialog.create();
        TextView popupText = (TextView) v.findViewById(R.id.tablapopupText);
        if (popupTexts[imageID] != null) {
            if (answers[imageID] == false) {
                popupText.setText(popupTexts[imageID]);
                alert.show();
            }
        }
        Button popupButton = (Button) v.findViewById(R.id.tablapopupButton);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }

    private void createSound(int imageID) {
        if (sounds.length > 0) {
            if (answers[imageID] == true) {
                mediaPlayer = MediaPlayer.create(context, sounds[imageID]);
                mediaPlayer.start();
            } else {
                mediaPlayer = MediaPlayer.create(context, R.raw.beep);
                mediaPlayer.start();
            }
        } else {
            if (answers[imageID] == false) {
                mediaPlayer = MediaPlayer.create(context, R.raw.beep);
                mediaPlayer.start();
            }
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}