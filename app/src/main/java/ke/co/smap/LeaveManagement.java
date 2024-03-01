package ke.co.smap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LeaveManagement extends AppCompatActivity {
    private final String[] shift_views = {" ", "personalInfo", "leaveDetails", "leaveStatus"};

    private Button mNextButton, mpreviosuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_management);

        mNextButton = findViewById(R.id.nextButton);
        mpreviosuButton = findViewById(R.id.previousButton);





        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will call the views
            }
        });
        mpreviosuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here we will call the views
            }
        });



    }
}