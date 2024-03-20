package ke.co.smap.supervisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ke.co.smap.EveryOne.AssessHistory;
import ke.co.smap.R;

public class Profile extends AppCompatActivity {
    private ImageButton homeBtn, HistoryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        homeBtn = findViewById(R.id.ImageButtonHome);
        HistoryBtn = findViewById(R.id.imageButtonAssessHistory);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Asses.class);
                startActivity(intent);
            }
        });
        HistoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, AssessHistory.class);
                startActivity(intent);

            }
        });
    }
}