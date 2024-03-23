package ke.co.smap.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ke.co.smap.EveryOne.Login;
import ke.co.smap.R;
import ke.co.smap.supervisor.Asses;

public class Register extends AppCompatActivity {
    private String[] station_string = {" ","Mlolongo", "Syokimau",
            "SGR","JKIA", "Eastern Bypass", "Southern Bypass", "Capital Center", "Haile Selassie",
            "The Mall", "Westlands"};
    private Spinner select_StationSpinner;
    private TextView displayStation;
    private EditText mfullName, mworkId, mcompId,
            mstation, mphone,mRank, mpassword;

    private long pressedTime;
    ProgressDialog progressDialog;

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
        mfullName = findViewById(R.id.editTextTextFN);
        mworkId = findViewById(R.id.editTextTextWorkId);
        mcompId = findViewById(R.id.editTextTextCompId);
        mstation = findViewById(R.id.editTextTextStation);
        mphone = findViewById(R.id.editTextPhone);
        mpassword = findViewById(R.id.editTextPassword);
        mRank = findViewById(R.id.editTextRank);




        mRegLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Asses.class);
                startActivity(intent);
                finish();



            }
        });



        mRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verifyInputs();
               // Intent intent = new Intent(Register.this, Asses.class);
                //startActivity(intent);
                //finish();

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

    public void verifyInputs() {
        String name = mfullName.getText().toString().trim();
        String workId = mworkId.getText().toString().trim();
        String companyId = mcompId.getText().toString().trim();
        String station = mstation.getText().toString().trim();
        String phone = mphone.getText().toString().trim();
        String pass = mpassword.getText().toString().trim();
        if (TextUtils.isEmpty(name)){
            mfullName.requestFocus();
            mfullName.setError("enter name");
            return;
        }
        if (TextUtils.isEmpty(workId)){
            mworkId.requestFocus();
            mworkId.setError("enter work id");
            return;

        }  if (TextUtils.isEmpty(companyId)){
            mcompId.requestFocus();
            mcompId.setError("enter company id");
            return;

        }  if (TextUtils.isEmpty(station)){
            mstation.requestFocus();
            mstation.setError("enter station");
            return;

        }  if (TextUtils.isEmpty(phone)){
            mphone.requestFocus();
            mphone.setError("enter phone");
            return;

        }  if (TextUtils.isEmpty(pass)){
            mpassword.requestFocus();
            mpassword.setError("enter password");
            return;

            }
        else {
            RegisterUser();
        }

        }

    private void RegisterUser() {
        ProgressDialog pd = new ProgressDialog(this);
        pd.show();
        pd.setMessage("registering..");
        pd.setTitle("registering user");
        pd.setCanceledOnTouchOutside(false);
        String name = mfullName.getText().toString().trim();
        String workId = mworkId.getText().toString().trim();
        String companyId = mcompId.getText().toString().trim();
        String station = mstation.getText().toString().trim();
        String phone = mphone.getText().toString().trim();
        String pass = mpassword.getText().toString().trim();

        Map<String, String> userMap = new HashMap<>();
        userMap.put("name", name);
        userMap.put("workId", workId);
        userMap.put("companyId", companyId);
        userMap.put("station", station);
        userMap.put("phone", phone);
        userMap.put("password", pass);

        FirebaseDatabase.getInstance().getReference("Users")
                .child(workId).setValue(userMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   pd.dismiss();
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