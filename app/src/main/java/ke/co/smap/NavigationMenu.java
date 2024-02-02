package ke.co.smap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NavigationMenu extends AppCompatActivity {
    private TextView AssessmentHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        AssessmentHistory  =  findViewById(R.id.assessmentHistoryMenuItem);

        AssessmentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationMenu.this, AssessHistory.class);
            startActivity( intent);}
        });
    }
}