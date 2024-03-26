package ke.co.smap.EveryOne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.NetPermission;
import java.util.Objects;

import ke.co.smap.R;
import ke.co.smap.supervisor.Asses;

public class Login extends AppCompatActivity {
    private Button mLoginBtn;
    private EditText mworkId, mPassword;
    private long pressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginBtn = findViewById(R.id.ButtonLogin);
        mworkId = findViewById(R.id.editTextLoginWorkId);
        mPassword = findViewById(R.id.editTextLoginPassword);


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workId = mworkId.getText().toString();
                String pass = mPassword.getText().toString();
                if (TextUtils.isEmpty(workId)) {
                    mworkId.setError("fill in work Id");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    mPassword.setError("fill in password");
                    return;
                } else {
                    NetInformation();

                    //  loginUser();

                }


            }
        });
    }

    private void loginUser() {
      /*  if (checkNet()){
            Toast.makeText(getApplicationContext(), "connected to internet", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "not connected to internet", Toast.LENGTH_SHORT).show();


        }
*/

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.setMessage("log in user..");
        progressDialog.setTitle("please wait..");
        String userName = mworkId.getText().toString();
        String password = mPassword.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserDatabase = reference.orderByChild("workId").equalTo(userName);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    mworkId.setError("user does not exist!");

                    // Toast.makeText(Login.this, "user does not exist", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                if (snapshot.exists()) {
                    mworkId.setError(null);


                    String passwordDb = snapshot.child(userName).child("password").getValue(String.class);
                    if (!Objects.equals(passwordDb, password)) {
                        mPassword.setError("wrong password!");
                        //    Toast.makeText(Login.this, "check credentials", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();
                    } else {
                        String workId = mworkId.getText().toString();
                        Toast.makeText(Login.this, "log in successful", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent i = new Intent(Login.this, Asses.class);
                        i.putExtra("workId", workId);
                        startActivity(i);
//upload

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onBackPressed() {
        if (pressedTime + 5000 > System.currentTimeMillis()) {


            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast
                    .LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

    public boolean checkNet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE
        );
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Toast.makeText(Login.this, "wifi network connected", Toast.LENGTH_SHORT).show();
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            Toast.makeText(Login.this, "mobile network connected", Toast.LENGTH_SHORT).show();
        }

        return networkInfo.isConnected();
    }





    public void NetInformation(){
        if (checkNet()){
          //  Toast.makeText(getApplicationContext(), "connected to internet", Toast.LENGTH_SHORT).show();
            loginUser();

        }
        else {
            Toast.makeText(getApplicationContext(), "please connect to internet", Toast.LENGTH_SHORT).show();


        }
    }
}
