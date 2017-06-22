package android.parkskocjanskejame;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.parkskocjanskejame.utils.ExpandableHeightGridView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Tabla3b extends AppCompatActivity {
    public static boolean[] tabla3bCheckboxSelection = new boolean[10];
    public static Integer[] tabla3bImages =
            {R.drawable.tabla3bimage0, R.drawable.tabla3bimage1,
                    R.drawable.tabla3bimage2, R.drawable.tabla3bimage3,
                    R.drawable.tabla3bimage4, R.drawable.tabla3bimage5,
                    R.drawable.tabla3bimage6, R.drawable.tabla3bimage7,
                    R.drawable.tabla3bimage8, R.drawable.tabla3bimage9,};
    public static Integer[] tabla3bSounds =
            {R.raw.tabla3bsound0, R.raw.tabla3bsound1,
                    R.raw.tabla3bsound3, R.raw.tabla3bsound5,
                    R.raw.tabla3bsound6, R.raw.tabla3bsound8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla3b);

        ExpandableHeightGridView tabla3bGridView = (ExpandableHeightGridView) findViewById(R.id.tabla3bGrid);
        final Tabla3bImageAdapter tabla3bImageAdapter = new Tabla3bImageAdapter(Tabla3b.this);
        tabla3bGridView.setAdapter(tabla3bImageAdapter);
        tabla3bGridView.setExpanded(true);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabla3bImageAdapter.stopPlaying();
                if (tabla3bCheckboxSelection[0] == true &&
                        tabla3bCheckboxSelection[1] == true &&
                        tabla3bCheckboxSelection[3] == true &&
                        tabla3bCheckboxSelection[5] == true &&
                        tabla3bCheckboxSelection[6] == true &&
                        tabla3bCheckboxSelection[8] == true &&
                        tabla3bCheckboxSelection[2] == false &&
                        tabla3bCheckboxSelection[4] == false &&
                        tabla3bCheckboxSelection[7] == false &&
                        tabla3bCheckboxSelection[9] == false) {
                    Intent intent = new Intent(getApplicationContext(), Cestitamo.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Napačen odgovor!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public class Tabla3bImageAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        MediaPlayer mediaPlayer;
        AlertDialog alert;

        public Tabla3bImageAdapter(Context c) {
            context = c;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return tabla3bImages.length;
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

            viewHolder.imageView.setImageResource(tabla3bImages[position]);
            viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            viewHolder.imageView.setAdjustViewBounds(true);

            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopPlaying();
                    int idImage = viewHolder.imageView.getId();
                    createSound(idImage);
                    int idCheckbox = viewHolder.checkBox.getId();
                    if (tabla3bCheckboxSelection[idCheckbox] == true) {
                        viewHolder.checkBox.setChecked(false);
                        tabla3bCheckboxSelection[idCheckbox] = false;
                    } else {
                        mediaPlayer.start();
                        viewHolder.checkBox.setChecked(true);
                        tabla3bCheckboxSelection[idCheckbox] = true;
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

        private void createSound(int imageID) {
            switch(imageID) {
                case 0:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[0]);
                    break;
                case 1:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[1]);
                    break;
                case 3:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[2]);
                    break;
                case 5:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[3]);
                    break;
                case 6:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[4]);
                    break;
                case 8:
                    mediaPlayer = MediaPlayer.create(context, tabla3bSounds[5]);
                    break;
                default:
                    mediaPlayer = MediaPlayer.create(context, R.raw.beep);
                    break;
            }
        }

        private void stopPlaying() {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }

        private void showPopup(int imageID) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            View v = inflater.inflate(R.layout.tablapopup, null);
            alertDialog.setView(v);
            alert = alertDialog.create();
            TextView popupText = (TextView) v.findViewById(R.id.tablapopupText);
            switch(imageID) {
                case 2:
                    popupText.setText(R.string.tabla3bpopup2);
                    alert.show();
                    break;
                case 4:
                    popupText.setText(R.string.tabla3bpopup4);
                    alert.show();
                    break;
                case 7:
                    popupText.setText(R.string.tabla3bpopup7);
                    alert.show();
                    break;
                case 9:
                    popupText.setText(R.string.tabla3bpopup9);
                    alert.show();
                    break;
            }
            Button popupButton = (Button) v.findViewById(R.id.tablapopupButton);
            popupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.cancel();
                }
            });
        }
    }
}
