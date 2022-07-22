package sg.edu.rp.c346.id20029699.l10_ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    Button showStarBtn;
    ListView lv;
    ArrayAdapter<Song> adapter;
    ArrayList<Song> al;
    CustomArrayAdapter CASong;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        showStarBtn = findViewById(R.id.starbutton);
        lv = findViewById(R.id.list);
        spinner = findViewById(R.id.spinner);

        DBHelper db = new DBHelper(ShowActivity.this);
        al = db.getAllSongs();
        adapter = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        CASong = new CustomArrayAdapter(this, R.layout.row,al);
        lv.setAdapter(CASong);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Song data = al.get(position); //user can select song to edit
                Intent i = new Intent(ShowActivity.this, EditActivity.class);
                i.putExtra("edit", data);
                startActivity(i);
            }
        });

        showStarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                DBHelper db = new DBHelper(ShowActivity.this);
                al.addAll(db.get5stars(5));
                CASong.notifyDataSetChanged();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                DBHelper db = new DBHelper(ShowActivity.this);
                switch (i){
                    case 0:
                        al.clear();
                        al.addAll(db.getYears(1998));
                        CASong.notifyDataSetChanged();
                        break;
                    case 1:
                        al.clear();
                        al.addAll(db.getYears(2002));
                        CASong.notifyDataSetChanged();
                        break;
                    case 2:
                        al.clear();
                        al.addAll(db.getYears(2015));
                        CASong.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    };
}