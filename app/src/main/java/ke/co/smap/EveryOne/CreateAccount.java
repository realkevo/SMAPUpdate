package ke.co.smap.EveryOne;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import ke.co.smap.R;
import ke.co.smap.model.UserPojo;

public class CreateAccount extends AppCompatActivity {
    private EditText mfullName, mworkId, mcompId, mstation, mphone,
    mpassword, mRank;
    private Button msignUp;
  //  private Button mlogin;
    private ProgressDialog pd;
     private UserPojo userPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mfullName = findViewById(R.id.editTextTextFN);
        mworkId = findViewById(R.id.editText_workid);
        mcompId = findViewById(R.id.editTextTextCompId);
        mstation = findViewById(R.id.editTextTextStation);
        mphone= findViewById(R.id.editTextPhone);
        mRank = findViewById(R.id.editTextRank);
        mpassword = findViewById(R.id.editTextPassword);
        msignUp = findViewById(R.id.ButtonRegister);
      //  mlogin = findViewById(R.id.ButtonLogin);

        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });
    }

    private void SignUp() {
       String name = mfullName.getText().toString();
        String workId = mworkId.getText().toString();
        String compId = mcompId.getText().toString();
        String station = mstation.getText().toString();
        String phone = mphone.getText().toString();
        String password = mpassword.getText().toString();
        String rank = mRank.getText().toString();


if (TextUtils.isEmpty(name)){
    Toast.makeText(CreateAccount.this,
            "name can not be empty",
            Toast.LENGTH_SHORT).show();
    mworkId.requestFocus();

}
        if (TextUtils.isEmpty(workId)){
            Toast.makeText(CreateAccount.this,
                    "work id can not be empty",
                    Toast.LENGTH_SHORT).show();
mworkId.requestFocus();
        }if (TextUtils.isEmpty(compId)){
            Toast.makeText(CreateAccount.this,
                    "company id can not be empty",
                    Toast.LENGTH_SHORT).show();
            mcompId.requestFocus();

        }if (TextUtils.isEmpty(station)){
            Toast.makeText(CreateAccount.this,
                    "station can not be empty",
                    Toast.LENGTH_SHORT).show();
            mstation.requestFocus();

        }if (TextUtils.isEmpty(phone)){
            Toast.makeText(CreateAccount.this,
                    "phone can not be empty",
                    Toast.LENGTH_SHORT).show();
            mphone.requestFocus();

        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(CreateAccount.this,
                    "password can not be empty",
                    Toast.LENGTH_SHORT).show();
            mpassword.requestFocus();

        }
        else {



            pd.setTitle("Registering user...");
            pd.setMessage("Please wait..");
            pd.show();

            HashMap<String, String> userPojo = new HashMap<>();
            userPojo.put("name", name);
            userPojo.put("workId", workId);
            userPojo.put("compId", compId);
            userPojo.put("station", station);
            userPojo.put("phone", phone);
            userPojo.put("password", password);
            userPojo.put("rank", rank);
            FirebaseDatabase.getInstance().getReference("users")
                    .child(workId).setValue(userPojo)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Toast.makeText(CreateAccount.this,
                                    "registration successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateAccount.this,
                                    "registration failed, ", Toast.LENGTH_SHORT).show();


                        }
                    });

            return;

            //proceed to signUp

          /*  ProgressDialog pd = new ProgressDialog(this);
            pd.show();
            pd.setMessage("Uploading user");

            FirebaseDatabase filePath = FirebaseDatabase.getInstance()
                    .getReference("users").getDatabase();
            filePath.putFile(workId


            */
        }


    }


}