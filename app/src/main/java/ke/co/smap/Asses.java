package ke.co.smap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

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
   private StorageReference storageReference;
    private Uri  uri;



    //VIEWS VARIABLES
    DatePickerDialog datePickerDialog;
    private EditText work_id, details;
    private ImageView employee_consent;
    private TextView station;
    private TextView displayStationTv;
    private TextView shift_select;
    private TextView supervisorTv;
    private TextView contact;
    private TextView display_selected_points;
    private Spinner select_spinner;
    private Spinner select_station_spinner;
    ProgressBar progressBar;
    //Dialog dialog;
    //Toolbar toolbar;
    private FloatingActionButton floatingActionButton;
          private String saveCurrentDate;
    private String saveCurrentTime;
    private String assessment_Random_key;
    private final String[] shift_string = {" ", "Day shift", "Night shift"};
    private final String [] station_string = {" ", "Mlolongo", "Syokimau", "sgr", "Jkia","Eastern Bypass", "Southern Bypass"
    , "Capital Center", "Haile Selassie","The Mall", "Westlands"};
    private final String[] points_string = {" ","1","2","4","5","8","16","32"};
    private  final int GalleryPick = 1;


//model variables
private AssessmentInfoPojo assessmentInfoPojo;
    ProgressDialog pd;
    ProgressDialog dialog;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asses);
        work_id = findViewById(R.id.editText_workid);
        details = findViewById(R.id.editText_details);
        employee_consent = findViewById(R.id.imageView_employee_consent);
        shift_select = findViewById(R.id.Display_selected_shift);
        station = findViewById(R.id.station);
        displayStationTv = findViewById(R.id.DisplayStation);
        display_selected_points = findViewById(R.id.points_displayTV);
        supervisorTv = findViewById(R.id.Display_assessor);
        progressBar = findViewById(R.id.transferredBytesProgressBar);
        Spinner select_shift_spinner = findViewById(R.id.select_shift);
        floatingActionButton = findViewById(R.id.floating_action_bar);
        select_spinner = findViewById(R.id.select_points_spinner);
        contact = findViewById(R.id.contact);
        TextView review_assessment = findViewById(R.id.textview_review_assesment);
        select_station_spinner = findViewById(R.id.station_spinner);

  pd = new ProgressDialog(Asses.this);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("assessmentInfo");
/*contact.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent pIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: +254758536280"));
        pIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(pIntent);
    }
});*/

        review_assessment.setOnClickListener(v -> {


               if (uri != null){
            uploadData();
               }
            else {
                Toast.makeText(Asses.this, "please select image", Toast.LENGTH_SHORT).show();
           employee_consent.requestFocus();
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


        employee_consent.setOnClickListener(v -> {
            //Here call OpenGalleryMethod



            Intent gallerIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallerIntent,GalleryPick);
        });

           /* Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            ActivityResultLauncher.launch(photoPicker);*/


        //SPINNER ADAPTER LISTENERS
        ArrayAdapter<String>adapter_station = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, station_string);
        adapter_station.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_station_spinner.setAdapter(adapter_station);
        select_station_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_station = station_string[position];
                displayStationTv.setText(selected_station);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
        //initialize adapter for spinner shift
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
        String station = displayStationTv.getText().toString();
        String shift = shift_select.getText().toString();
        String supervisor = supervisorTv.getText().toString();
        String imageUrl = uri.toString();



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
        if (TextUtils.isEmpty(station)) {
            displayStationTv.setError("Please select date");
            displayStationTv.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(shift)) {
            shift_select.setError("Please select night or day shift");
            shift_select.requestFocus();
        } else {


            Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM, dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        assessment_Random_key = saveCurrentDate + saveCurrentTime;
       // AlertDialog.Builder builder = new AlertDialog.Builder(
              //  Asses.this);
       // builder.setCancelable(false);
       // builder.setView(R.layout.progress_bar_1);
       // dialog = builder.create();
       // dialog.show();
        //TODO create a progressbar showing uploaded total bits here

pd.setTitle("Uploading files...");
pd.setMessage("Please wait..");
pd.show();

Map<String, String> assessmentInfoPojoMap = new HashMap<>();
assessmentInfoPojoMap.put("details", detail);
            assessmentInfoPojoMap.put("points", points);
            assessmentInfoPojoMap.put("tarehe", saveCurrentDate);
            assessmentInfoPojoMap.put("shift", shift);
            assessmentInfoPojoMap.put("supervisor", supervisor);
            assessmentInfoPojoMap.put("imageUrl", imageUrl);
            assessmentInfoPojoMap.put("station", station);
            assessmentInfoPojoMap.put("workId", workId);

            FirebaseDatabase.getInstance().getReference("assessmentInfo")
                    .child(assessment_Random_key).setValue(assessmentInfoPojoMap)





       /* assessmentInfoPojo = new AssessmentInfoPojo(workId, detail,
                 points, tarehe
        , shift,score,supervisor, imageUrl);
        FirebaseDatabase.getInstance().getReference("assessmentInfo")
                .child(assessment_Random_key).setValue(assessmentInfoPojo)*/

                    .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {


                                uploadImage();
                                //TODO Extend this dialog to after u=image is uploaded successfully
                                //dialog.dismiss();


                            }

                        })
                    .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Asses.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }}



    private void uploadImage( ) {

            SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
            Date now = new Date();
            String fileName = formater.format(now);
       /* storageReference = FirebaseStorage.getInstance()
                .getReference("images/" + fileName);
        UploadTask uploadTask = storageReference.putFile(uri);
        Task<Uri>urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    Toast.makeText(Asses.this, "Task failed, kev sorry !", Toast.LENGTH_SHORT).show();
                }
                return storageReference.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {

                }
                else{
                    Toast.makeText(Asses.this, "getting successful task", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
            storageReference = FirebaseStorage.getInstance()
                    .getReference("images/" + fileName);
            storageReference.putFile(uri).


                 addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            pd.dismiss();

                            //Check whether this code opens the next activity after assessment successful
                            Intent intent = new Intent(Asses.this, AssessHistory.class);
                            startActivity(intent);



                            //employee_consent.setImageURI(null);
                            Toast.makeText(Asses.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();


                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {


                          //  pd.setMessage("uploaded: " + transfered + "/" + totalKb);
                     long perc = (100 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                     pd.setMessage("Uploaded " +  perc + "%");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        }








    @Override

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView imageView = findViewById(R.id.imageView_employee_consent);


        if (resultCode == RESULT_OK && reqCode == GalleryPick && data.getData()!=null
        && data!=null)

        {


            uri = data.getData();
            imageView.setImageURI(uri);

            try {
                Bitmap bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            Picasso.get().load(uri).into(imageView);

        } else {
            Toast.makeText(Asses.this, "please select employee consent", Toast.LENGTH_SHORT).show();
        }
    }
    //TODO create a method that uploads
    // assessment and when successful return the review assessment activity else throw
    //Todo an exception/error



}




