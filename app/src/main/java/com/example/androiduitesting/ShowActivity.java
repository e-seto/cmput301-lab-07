package com.example.androiduitesting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        /*
        The following code is adapted from...
        Author: user914425 https://stackoverflow.com/users/914425/user914425
        Editor: SpiritCrusher https://stackoverflow.com/users/4168607/spiritcrusher
        Title: "How do I pass data between Activities in Android application?"
        Answer: https://stackoverflow.com/a/7325248
        Date: 2011-09-06 (edited: 2018-03-25)
        Retrieved: 2026-02-24
        License: CC-BY-SA 3.0
        */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String cityName = extras.getString("cityName");

            // set text in TextView
            TextView textView = findViewById(R.id.city_name_text);
            textView.setText(cityName);
        }

        final Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*
                The following code is adapted from...
                Author: Osama Ibrahim https://stackoverflow.com/users/1632634/osama-ibrahim
                Editor: General Grievance https://stackoverflow.com/users/4294399/general-grievance
                Title: "How to close activity and go back to previous activity in android"
                Answer: https://stackoverflow.com/a/27255971
                Date: 2011-09-06 (edited: 2018-03-25)
                Retrieved: 2026-02-25
                License: CC-BY-SA 4.0
                */
                finish();


            }
        });

    }
}
    // https://developer.android.com/guide/components/activities/intro-activities#java
    // https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
    // https://stackoverflow.com/questions/15393899/how-to-close-activity-and-go-back-to-previous-activity-in-android
    // https://stackoverflow.com/questions/39021133/espresso-click-first-item-in-listview-inside-a-viewpager
    // https://stackoverflow.com/questions/39727106/whats-the-difference-between-intending-vs-intended-in-espresso

