package com.example.recyclerview_homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.recyclerview_homework.model.Movie;

public class Edit_ItemActivity extends AppCompatActivity {
    private Movie movie;
    EditText etitle, eDate, eSummary, eDuration;
    Button btnBrowse, btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__item);
        etitle = findViewById(R.id.edit_Title);
        eDate = findViewById(R.id.edit_Date);
        eSummary = findViewById(R.id.edit_Summary);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        if (getIntent() != null) {
            movie = getIntent().getParcelableExtra("film");
            etitle.setText(movie.getMovieTitle());
            eDate.setText(movie.getDate());
            //eSummary.setText(movie.getSummary());
           // eDuration.setText(movie.getDuration());
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveChange();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SaveChange() {
        movie.setMovieTitle(etitle.getText().toString());
        movie.setDate(eDate.getText().toString());
     //   movie.setDuration(eDuration.getText().toString());
    //    movie.setSummary(eSummary.getText().toString());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("film", movie);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}
