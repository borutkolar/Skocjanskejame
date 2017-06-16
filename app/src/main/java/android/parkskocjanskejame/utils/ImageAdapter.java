package android.parkskocjanskejame.utils;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.parkskocjanskejame.R;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class ImageAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;

    public static int layout;
    public static Integer[] images;
    public static Integer[] sounds;
    public static boolean[] checkboxSelection;

    private PopupWindow pwindo;
    AlertDialog alert;

    public ImageAdapter(Context c, int layout, Integer[] images, Integer[] sounds, boolean[] checkboxSelection) {
        this.context = c;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout = layout;
        this.images = images;
        this.sounds = sounds;
        this.checkboxSelection = checkboxSelection;
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
            convertView = inflater.inflate(layout, null);
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

        final MediaPlayer mediaPlayer = MediaPlayer.create(context, sounds[0]);

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idImage = viewHolder.imageView.getId();
                if (idImage == 1) {
                    popupWindow();
                }
                int idCheckbox = viewHolder.checkBox.getId();
                if (checkboxSelection[idCheckbox] == true) {
                    viewHolder.checkBox.setChecked(false);
                    checkboxSelection[idCheckbox] = false;
                } else {
                    mediaPlayer.start();
                    viewHolder.checkBox.setChecked(true);
                    checkboxSelection[idCheckbox] = true;
                }
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

    private void popupWindow() {
        /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.popup, null);
        pwindo = new PopupWindow(layout, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);*/

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        View v = inflater.inflate(R.layout.popup, null);
        alertDialog.setView(v);
        alert = alertDialog.create();
        alert.show();
    }
}