package sg.edu.rp.c346.id20029699.l10_ndpsong;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;

public class CustomArrayAdapter extends ArrayAdapter{

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList1;

    public CustomArrayAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList1 = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
// Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle1);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger1);
        TextView tvYear = rowView.findViewById(R.id.textViewYear1);
        TextView tvStar = rowView.findViewById(R.id.textViewStar1);

        // Obtain the Android Version information based on the position
        Song cVersion = songList1.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(cVersion.getTitle());
        tvSinger.setText(cVersion.getSinger());
        tvYear.setText(cVersion.getYear()+"");
        tvStar.setText(cVersion.displayStars(cVersion.getStars()));

        return rowView;
    }
}

