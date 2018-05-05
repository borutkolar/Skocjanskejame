package android.parkskocjanskejame.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.parkskocjanskejame.Cestitamo;
import android.parkskocjanskejame.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    public static int layout;
    public static Integer[] images;
    public static Integer[] sounds;
    public static boolean[] checkboxSelection;
    public static Boolean[] answers;
    public static Integer[] popupTexts;
    public static Integer[] imageTexts;

    public static int counter;

    Button button;
    TextView textView;

    AlertDialog alert;
    MediaPlayer mediaPlayer;

    public ImageAdapter (Context c, Integer[] images, Integer[] sounds, boolean[] checkboxSelection, Boolean[] answers, Integer[] popupTexts, Integer[] imageTexts, Button button, TextView textView, int counter) {
        this.context = c;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = images;
        this.sounds = sounds;
        this.checkboxSelection = checkboxSelection;
        this.answers = answers;
        this.popupTexts = popupTexts;
        this.imageTexts = imageTexts;
        this.button = button;
        this.textView = textView;
        this.counter = counter;
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
            viewHolder.textView = (TextView) convertView.findViewById(R.id.gridviewText);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.checkBox.setId(position);
        viewHolder.checkBox.setClickable(false);

        viewHolder.textView.setId(position);
        viewHolder.textView.setText(imageTexts[position]);

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
                if (answers[idImage] == true) {
                    if (checkboxSelection[idCheckbox] == true) {
                        counter--;
                        textView.setText(Integer.toString(counter));
                        viewHolder.checkBox.setChecked(false);
                        checkboxSelection[idCheckbox] = false;
                    } else {
                        counter++;
                        textView.setText(Integer.toString(counter));
                        viewHolder.checkBox.setChecked(true);
                        checkboxSelection[idCheckbox] = true;
                        createSound(idImage);
                    }
                } else {
                    createSound(idImage);
                    showPopup(idImage);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                boolean equal = true;
                for (int i = 0; i < checkboxSelection.length; i++) {
                    if (checkboxSelection[i] != answers[i]) {
                        equal = false;
                    }
                }
                if (equal == true) {
                    Intent intent = new Intent(context, Cestitamo.class);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.napacenOdgovor) + Integer.toString(counter) + "/6).", Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
        TextView textView;
    }

    private void showPopup(int imageID) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View v = inflater.inflate(R.layout.tablapopup, null);
        alertDialog.setView(v);
        alert = alertDialog.create();
        TextView popupText = (TextView) v.findViewById(R.id.tablapopupText);
        popupText.setText(popupTexts[imageID]);
        alert.show();

        Button popupButton = (Button) v.findViewById(R.id.tablapopupButton);
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.cancel();
            }
        });
    }

    private void createSound(int imageID) {
        if (answers[imageID] == true) {
            mediaPlayer = MediaPlayer.create(context, sounds[imageID]);
            mediaPlayer.start();
        } else {
            mediaPlayer = MediaPlayer.create(context, R.raw.beep);
            mediaPlayer.start();
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