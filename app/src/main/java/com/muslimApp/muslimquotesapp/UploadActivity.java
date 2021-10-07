package com.muslimApp.muslimquotesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jaredrummler.materialspinner.MaterialSpinner;


public class UploadActivity extends AppCompatActivity{

    MaterialSpinner spinner;
    EditText editText;
    Button button;

    private DatabaseReference mDatabase;

    private static final String[] ANDROID_VERSIONS = {
            "Business",
            "Family",
            "Friendship",
            "Leadership",
            "Motivational",
            "positive",
            "Success",
            "Life",
            "Student",
            "Wisdom"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        editText = findViewById(R.id.edit_view);
        button = findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString().trim();

                mDatabase.child("title").setValue(name);
                editText.setText("");

                finish();
            }
        });

        spinner = findViewById(R.id.spinner);
        spinner.setItems(ANDROID_VERSIONS);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                mDatabase = FirebaseDatabase.getInstance().getReference("images").child(item).push();
            }
        });
        spinner.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(MaterialSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });
    }

}
