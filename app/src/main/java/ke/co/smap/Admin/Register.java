package ke.co.smap.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import ke.co.smap.EveryOne.Login;
import ke.co.smap.R;
import ke.co.smap.supervisor.Asses;

public class Register extends AppCompatActivity {
    private String[] station_string = {" ","Mlolongo", "Syokimau",
            "SGR","JKIA", "Eastern Bypass", "Southern Bypass", "Capital Center", "Haile Selassie",
            "The Mall", "Westlands"};
    private Spinner select_StationSpinner;
    private TextView displayStation;
    private long pressedTime;

    private Button mRegButton, mRegLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        select_StationSpinner = findViewById(R.id.select_register_station);
        displayStation = findViewById(R.id.DisplayStation);
        mRegButton = findViewById(R.id.ButtonRegister);
        mRegLoginBtn = findViewById(R.id.ButtonLogin);


        mRegLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();



            }
        });



        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Asses.class);
                startActivity(intent);
                finish();

            }
        });




        ArrayAdapter<String> adapter_stations = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, station_string);

        adapter_stations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_StationSpinner.setAdapter(adapter_stations);
        select_StationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem_station = station_string[position];
               displayStation.setText(selectedItem_station);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    @Override

    public void onBackPressed() {
        if (pressedTime + 5000 > System.currentTimeMillis()) {


            super.onBackPressed();
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast
                    .LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}