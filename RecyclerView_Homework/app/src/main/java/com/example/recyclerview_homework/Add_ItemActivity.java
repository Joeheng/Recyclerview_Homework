package com.example.recyclerview_homework;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recyclerview_homework.model.Movie;

public class Add_ItemActivity extends AppCompatActivity {
    EditText mTitle, mDate, mDuration, mSummary;
    Button btnDone, btnCancel, btnBrowse;
    final static int PICK_IMAGE = 100;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);
        mTitle = findViewById(R.id.edt_Title);
        mDuration = findViewById(R.id.edt_Duration);
        mSummary = findViewById(R.id.edt_Summary);
        mDate = findViewById(R.id.edt_Date);
        btnDone = findViewById(R.id.btnDone);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnCancel = findViewById(R.id.btnCancel);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movie = new Movie();
                Intent intent = new Intent();
                String title = mTitle.getText().toString();
                String date = mDate.getText().toString();
                String summary = mSummary.getText().toString();
                String duration = mDuration.getText().toString();
                //int gallery = Integer.parseInt(imageUri);
                movie.setMovieTitle(title);
                movie.setDate(date);
                movie.setDuration(duration);
                movie.setSummary(summary);
                //movie.setGallery(gallery);

                Bundle b = new Bundle();
                b.putParcelable("movie", movie);
                intent.putExtras(b);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData().toString();
    }
}
