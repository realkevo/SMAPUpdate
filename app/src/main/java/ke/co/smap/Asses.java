package ke.co.smap;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import ke.co.smap.model.AssessmentInfoPojo;


public class Asses extends AppCompatActivity {

    //FIREBASE

    DatabaseReference reference;
    FirebaseDatabase database;
    FirebaseStorage storage;
    StorageReference storageReference;


    DatePickerDialog datePickerDialog;
    private EditText work_id, details;
    private ImageView employee_consent;
  private String //tarehe,shift, score, supervisor, points,
  saveCurrentDate, saveCurrentTime,ImageUri,
          assessment_Random_key;
    private Uri uri;


    private TextView review_assesment, date,
            dispalyDateTv, shift_select, scoreTv,
            supervisorTv, display_selected_points;
    private Spinner select_shift_spinner, select_spinner;
    private String[] shift_string = {" ", "day shift", "night shift"};
    private String[] points_string = {" ","1", "2", "4","5", "8", "16", "32"};
    private  final int GalleryPick = 1;
    ProgressBar loadingBar;
    Dialog dialog;
    //Toolbar toolbar;
    private ImageButton menuBtn;
private FloatingActionButton floatingActionButton;

//model
private AssessmentInfoPojo assessmentInfoPojo;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asses);
        work_id = findViewById(R.id.editText_workid);
        details = findViewById(R.id.editText_details);
        employee_consent = findViewById(R.id.imageView_employee_consent);
        shift_select = findViewById(R.id.Display_selected_shift);
        date = findViewById(R.id.date);
        dispalyDateTv = findViewById(R.id.Display_assessment_date);
        display_selected_points = findViewById(R.id.points_displayTV);
        supervisorTv = findViewById(R.id.Display_assessor);
        scoreTv = findViewById(R.id.score_displayTV);

        menuBtn = findViewById(R.id.menu_button);
        select_shift_spinner = findViewById(R.id.select_shift);
        floatingActionButton = findViewById(R.id.floating_action_bar);
        select_spinner = findViewById(R.id.select_points_spinner);
        review_assesment = findViewById(R.id.textview_review_assesment);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("assessmentConsents");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("assessmentInfo");


        review_assesment.setOnClickListener(v -> {
            String workId = work_id.getText().toString();
            String detail = details.getText().toString();
            String points = display_selected_points.getText().toString();
            String tarehe = date.getText().toString();
            String  shift = shift_select.getText().toString();
            String  score = scoreTv.getText().toString();
            String supervisor = supervisorTv.getText().toString();

            if (TextUtils.isEmpty(workId)) {
                work_id.setError("Please enter work id");
                work_id.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(detail)) {
                details.setError("Please enter details");
                details.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(points)) {
                display_selected_points.setError("Please select deductible points from dropdown");
                display_selected_points.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(tarehe)) {
                date.setError("Please select date");
                date.requestFocus();
                return;
            }


            if (TextUtils.isEmpty(shift)) {
                shift_select.setError("Please select night or day shift");
                shift_select.requestFocus();
            } else {

                //uploadImage();
              uploadData();

            }
        });

//onClicks
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asses.this, AssessHistory.class);
                startActivity(intent);
            }
        });

        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(Asses.this,
                    (view, year, month, dayOfMonth) -> dispalyDateTv.setText(dayOfMonth + "/" + (month + 1) + "/" + year), mYear, mMonth, mDay
            );
            datePickerDialog.show();

        });
        employee_consent.setOnClickListener(v -> {
            //Here call OpenGalleryMethod



            Intent gallerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallerIntent,GalleryPick);
        });

           /* Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            ActivityResultLauncher.launch(photoPicker);*/

        ArrayAdapter<String> adapter_points = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, points_string);
        adapter_points.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_spinner.setAdapter(adapter_points);
        select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem_points = points_string[position];
                display_selected_points.setText(selectedItem_points);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //initialize adapater for spinner shift
        ArrayAdapter<String> adapter_shift = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, shift_string);
        adapter_shift.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_shift_spinner.setAdapter(adapter_shift);
        select_shift_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem1 = shift_string[position];
                shift_select.setText(selectedItem1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public void uploadData(){

        String workId = work_id.getText().toString();
        String detail = details.getText().toString();
        String points = display_selected_points.getText().toString();
        String tarehe = date.getText().toString();
        String shift = shift_select.getText().toString();
        String score = scoreTv.getText().toString();
        String supervisor = supervisorTv.getText().toString();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM, dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        assessment_Random_key = saveCurrentDate + saveCurrentTime;
        AlertDialog.Builder builder = new AlertDialog.Builder(
                Asses.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar_1);
        dialog = builder.create();
        dialog.show();


        assessmentInfoPojo = new AssessmentInfoPojo(workId, detail,
                 points, tarehe
        , shift,score,supervisor, ImageUri);
        FirebaseDatabase.getInstance().getReference("assessmentInfo")
                .child(assessment_Random_key).setValue(assessmentInfoPojo).addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    Toast.makeText(Asses.this, "data upload success", Toast.LENGTH_SHORT).show();
                                }
                                uploadImage();
                                dialog.dismiss();


                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Asses.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
    private void uploadImage() {

       if(uri != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(Asses.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Asses.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
    @Override

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView imageView = findViewById(R.id.imageView_employee_consent);

        if (resultCode == RESULT_OK && reqCode == GalleryPick && data.getData()!=null
        && data!=null)

        {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            imageView.setImageURI(uri);
            Picasso.get().load(uri).into(imageView);

        } else {
            Toast.makeText(Asses.this, "please select employee consent"
                    , Toast.LENGTH_SHORT).show();
        }
    }
    //TODO create a method that uploads
    // assessment and when successful return the review assessment activity else throw
    //Todo an exception/error



}




