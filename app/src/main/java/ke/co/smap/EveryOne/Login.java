package ke.co.smap.EveryOne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ke.co.smap.R;
import ke.co.smap.supervisor.Asses;

public class Login extends AppCompatActivity {
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginBtn = findViewById(R.id.ButtonLogin);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Asses.class);
                startActivity(intent);
                finish();
            }
        });
    }
}