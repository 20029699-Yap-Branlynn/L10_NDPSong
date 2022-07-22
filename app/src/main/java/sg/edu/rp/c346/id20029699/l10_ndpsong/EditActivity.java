package sg.edu.rp.c346.id20029699.l10_ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    TextView tvSong, tvSinger, tvYear, tvStar, tvID;
    EditText etSong, etSinger, etYear, etID;
    RadioGroup radioGrp;
    RadioButton btn1, btn2, btn3, btn4, btn5;
    Button updateBtn, deleteBtn, cancelBtn;
    Song edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        tvSong = findViewById(R.id.TVSong);
        tvSinger = findViewById(R.id.tvSinger);
        tvYear = findViewById(R.id.tvYear);
        tvStar = findViewById(R.id.tvStar);
        etSong = findViewById(R.id.etSong);
        etSinger = findViewById(R.id.etSinger);
        etYear= findViewById(R.id.etYear);
        btn1 = findViewById(R.id.radioButton1);
        btn2 = findViewById(R.id.radioButton2);
        btn3 = findViewById(R.id.radioButton3);
        btn4 = findViewById(R.id.radioButton4);
        btn5 = findViewById(R.id.radioButton5);
        radioGrp = findViewById(R.id.radioGrp);
        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);
        cancelBtn = findViewById(R.id.cancelButton);

        tvID = findViewById(R.id.tvID);
        etID = findViewById(R.id.etID);

        Intent a = getIntent();
        edit = (Song) a.getSerializableExtra("edit");

        etID.setText(edit.getId()+"");
        etSong.setText(edit.getTitle());
        etSinger.setText(edit.getSinger());
        etYear.setText(edit.getYear()+"");
        radioGrp.check(edit.getStars());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                edit.setTitle(etSong.getText().toString());
                edit.setSinger(etSong.getText().toString());
                edit.setYear(Integer.parseInt(etYear.getText().toString()));
                edit.setStars(radioGrp.getCheckedRadioButtonId());
                dbh.updateSong(edit);
                dbh.close();
                Toast.makeText(EditActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                clear();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(edit.getId());
            }
        });

    }
    private void clear()
    {
        etSong.setText("");
        etSinger.setText("");
        etYear.setText("");
        radioGrp.clearCheck();
    }
}