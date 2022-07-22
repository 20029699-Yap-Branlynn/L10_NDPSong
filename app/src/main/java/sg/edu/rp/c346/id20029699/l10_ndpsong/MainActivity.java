package sg.edu.rp.c346.id20029699.l10_ndpsong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvSong, tvSinger, tvYear, tvStar;
    EditText etSong;
    EditText etSinger;
    EditText etYear;
    RadioGroup radioGrp;
    RadioButton btn1, btn2, btn3, btn4, btn5;
    Button insertBtn, showBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        insertBtn = findViewById(R.id.insertbutton);
        showBtn = findViewById(R.id.showbutton);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleData = etSong.getText().toString();
                String singerData = etSinger.getText().toString();
                int yearData = Integer.parseInt(etYear.getText().toString());
                int starNum = radioGrp.getCheckedRadioButtonId();

                int starData = -1;
                if (starNum == R.id.radioButton1){
                    starData = 1;
                }else if (starNum == R.id.radioButton2){
                    starData = 2;
                }else if (starNum == R.id.radioButton3){
                    starData = 3;
                }else if (starNum == R.id.radioButton4){
                    starData = 4;
                }else if (starNum ==R.id.radioButton5){
                    starData = 5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(titleData,singerData, yearData, starData);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                    clear();
                }
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