package ke.co.smap.EveryOne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ke.co.smap.supervisor.Asses;
import ke.co.smap.R;

public class WelcomeScreen extends AppCompatActivity {
    private TextView mproceed;
    private TextView msignUp;
    private TextView mreset;

    private Button mlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        mproceed = findViewById(R.id.proceedTextView);
        msignUp = findViewById(R.id.signUpPrompt);
        mlogin = findViewById(R.id.loginButton);
        mreset = findViewById(R.id.resetPassword);

        mproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, Asses.class);
                startActivity(intent);
            }
        });
        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeScreen.this, CreateAccount.class);
                startActivity(intent);
            }
        });
    }
}