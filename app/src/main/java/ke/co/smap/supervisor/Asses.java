package ke.co.smap.supervisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import ke.co.smap.EveryOne.AssessHistory;
import ke.co.smap.R;


public class Asses extends AppCompatActivity implements View.OnClickListener {
    private long pressedTime;

    //FIREBASE

    DatabaseReference reference;
    FirebaseDatabase database;
    private StorageReference storageReference;
    private Uri uri;
    NetworkInfo networkInfo;


    //VIEWS VARIABLES
    private EditText work_id, details;
    private ImageView employee_consent;
    private TextView
            shift_select,
            supervisorTv,
            display_selected_points,
            displayStation;
    private Spinner select_shift_spinner,
    //select_spinner,
    select_StationSpinner;
    ProgressBar progressBar;
    //Dialog dialog;
    //Toolbar toolbar;
    private ImageButton MassesHistoryButton, profileBtn;
    private String imageUrl,
            saveCurrentDate, saveCurrentTime,
            assessment_Random_key;
    private Button mUpload, review_assesment, points, Fivepoints, Tenpoints, Twentypoints, Thirtypoints;
    private String[] shift_string = {" ", "day shift", "night shift"};
    private String[] station_string = {" ", "Mlolongo", "Syokimau",
            "SGR", "JKIA", "Eastern Bypass", "Southern Bypass", "Capital Center", "Haile Selassie",
            "The Mall", "Westlands"};

    //private String[] points_string = {" ","5", "10", "20","30"};
    private final int GalleryPick = 1;


    //model variables
    CardView cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asses);

        work_id = findViewById(R.id.editText_workid);
        details = findViewById(R.id.editText_details);
        employee_consent = findViewById(R.id.imageView_employee_consent);
        shift_select = findViewById(R.id.Display_selected_shift);
        points = findViewById(R.id.points);
        Fivepoints = findViewById(R.id.FivePoints);
        Tenpoints = findViewById(R.id.TenPoints);
        Twentypoints = findViewById(R.id.TwentyPoints);
        Thirtypoints = findViewById(R.id.ThirtyPoints);
        profileBtn = findViewById(R.id.ImageBtnAccount);

        display_selected_points = findViewById(R.id.points_displayTV);
        supervisorTv = findViewById(R.id.Display_assessor);
        select_shift_spinner = findViewById(R.id.select_shift);
        MassesHistoryButton = findViewById(R.id.imageButtonAssessHistory);
        // select_spinner = findViewById(R.id.select_points_spinner);
        select_StationSpinner = findViewById(R.id.station_spinner);
        displayStation = findViewById(R.id.DisplayStation);
        cardView = findViewById(R.id.cardImage);
        mUpload = findViewById(R.id.textViewUpload);
        review_assesment = findViewById(R.id.textview_review_assesment);

String supervisor = "" + getIntent().getExtras().getString("workId");
supervisorTv.setText(supervisor);
       /* database = FirebaseDatabase.getInstance();
        reference = database.getReference("assessmentInfo");*/
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();

            }
        });
        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points.setVisibility(View.INVISIBLE);
                Fivepoints.setVisibility(View.VISIBLE);
                Tenpoints.setVisibility(View.VISIBLE);
                Twentypoints.setVisibility(View.VISIBLE);
                Thirtypoints.setVisibility(View.VISIBLE);

            }
        });
        Fivepoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_selected_points.setText("5");
                Fivepoints.setBackgroundColor(Color.WHITE);
                Tenpoints.setBackgroundColor(Color.BLACK);
                Twentypoints.setBackgroundColor(Color.BLACK);
                Thirtypoints.setBackgroundColor(Color.BLACK);
                Toast.makeText(Asses.this, "5 points selected", Toast.LENGTH_SHORT).show();


            }
        });
        Tenpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_selected_points.setText("10");
                Fivepoints.setBackgroundColor(Color.BLACK);
                Tenpoints.setBackgroundColor(Color.WHITE);
                Twentypoints.setBackgroundColor(Color.BLACK);
                Thirtypoints.setBackgroundColor(Color.BLACK);
                Toast.makeText(Asses.this, "10 points selected", Toast.LENGTH_SHORT).show();



            }
        });
        Twentypoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_selected_points.setText("20");
                Twentypoints.setBackgroundColor(Color.WHITE);
                Thirtypoints.setBackgroundColor(Color.BLACK);
                Fivepoints.setBackgroundColor(Color.BLACK);
                Tenpoints.setBackgroundColor(Color.BLACK);
                Toast.makeText(Asses.this, "20 points selected", Toast.LENGTH_SHORT).show();



            }
        });
        Thirtypoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_selected_points.setText("30");
                Thirtypoints.setBackgroundColor(Color.WHITE);
                Twentypoints.setBackgroundColor(Color.BLACK);
                Fivepoints.setBackgroundColor(Color.BLACK);
                Tenpoints.setBackgroundColor(Color.BLACK);
                Toast.makeText(Asses.this, "30 points selected", Toast.LENGTH_SHORT).show();



            }
        });
        review_assesment.setOnClickListener(v -> {
            if (uri != null) {
                validateEntry();
            } else {
                Toast.makeText(Asses.this, "please select image", Toast.LENGTH_SHORT).show();
                employee_consent.requestFocus();


            }
        });
        MassesHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Asses.this, AssessHistory.class);
                startActivity(intent);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View v) {


                // Intent intent = new Intent(Asses.this, Profile.class);

                // startActivity(intent);
            }

        });


//onClicks

        employee_consent.setOnClickListener(v -> {
            //Here call OpenGalleryMethod


            Intent gallerIntent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallerIntent, GalleryPick);

        });

           /* Intent photoPicker = new Intent(Intent.ACTION_PICK);
            photoPicker.setType("image/*");
            ActivityResultLauncher.launch(photoPicker);*/


        //SPINNER ADAPTER LISTENERS

        //   ArrayAdapter<String> adapter_points = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, points_string);
        //   adapter_points.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //select_spinner.setAdapter(adapter_points);
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
        /*select_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem_points = points_string[position];
                display_selected_points.setText(selectedItem_points);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Asses.this, "Station has to be selected", Toast.LENGTH_SHORT).show();
            }
        });*/
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

    private void upload() {
        String Id = work_id.getText().toString();
        String detail = details.getText().toString();
        String points = display_selected_points.getText().toString();
        String shift = shift_select.getText().toString();
        String supervisor = supervisorTv.getText().toString();
        String station = displayStation.getText().toString();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM, dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());
        assessment_Random_key = saveCurrentDate + saveCurrentTime;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait..");
        progressDialog.setTitle("Uploading files");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        if (uri != null) {
            StorageReference filePath = FirebaseStorage.getInstance().getReference("assessments")
                    .child(saveCurrentDate + saveCurrentTime + "."
                            + getFileExtension(uri));
            StorageTask uploadTask = filePath.putFile(uri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filePath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    Uri downloadUri = task.getResult();
                    imageUrl = downloadUri.toString();


                    Map<String, String> assessmentInfoPojoMap = new HashMap<>();
                    assessmentInfoPojoMap.put("workId", Id);
                    assessmentInfoPojoMap.put("details", detail);
                    assessmentInfoPojoMap.put("points", points);
                    assessmentInfoPojoMap.put("tarehe", saveCurrentDate);
                    assessmentInfoPojoMap.put("shift", shift);
                    assessmentInfoPojoMap.put("supervisor", supervisor);
                    assessmentInfoPojoMap.put("station", station);
                    assessmentInfoPojoMap.put("imageUrl", imageUrl);

                    FirebaseDatabase.getInstance().getReference("assessmentInfo")
                            .child(assessment_Random_key).setValue(assessmentInfoPojoMap).
                            addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            })


                            .addOnCompleteListener(
                                    new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(Asses.this, "Upload successful", Toast.LENGTH_SHORT).show();
                                            //   uploadDataAndImage(uri);
                                            //TODO Extend this dialog to after u=image is uploaded successfully
                                            progressDialog.dismiss();
                                            work_id.clearFocus();
                                            details.clearFocus();
                                            work_id.setText(null);
                                            details.setText(null);
                                            display_selected_points.setText(null);
                                            displayStation.setText(null);
                                            shift_select.setText(null);


                                            Intent intent = new Intent(Asses.this,
                                                    AssessHistory.class);
                                            startActivity(intent);

                                        }

                                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    Toast.makeText(Asses.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });


                }


            });

        }
    }


    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(
                this.getContentResolver().getType(uri));
    }


    @Override

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ImageView imageView = findViewById(R.id.imageView_employee_consent);


        if (resultCode == RESULT_OK && reqCode == GalleryPick && data.getData() != null
                && data != null) {


            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
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


    private void validateEntry() {
        String Id = work_id.getText().toString().trim();
        String detail = details.getText().toString().trim();
        String points = display_selected_points.getText().toString().trim();
        String shift = shift_select.getText().toString().trim();
        String station = displayStation.getText().toString().trim();
        String supervisor = supervisorTv.getText().toString().trim();


        if (TextUtils.isEmpty(Id)) {
            work_id.setError("fill in work Id");
            work_id.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(detail)) {
            details.setError("fill in details");
            details.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(points)) {
            display_selected_points.setError("fill points");
            Toast.makeText(Asses.this, "please select points", Toast.LENGTH_SHORT).show();
            display_selected_points.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(shift)) {
            shift_select.setError("fill in shift");
            shift_select.requestFocus();
            Toast.makeText(Asses.this, "select shift", Toast.LENGTH_SHORT).show();

            return;

        }
        if (TextUtils.isEmpty(station)) {
            displayStation.setError("fill in station ");
            Toast.makeText(Asses.this, "select station", Toast.LENGTH_SHORT).show();

            displayStation.requestFocus();
            return;
        } else {

mUpload.setVisibility(View.VISIBLE);
        review_assesment.setVisibility(View.INVISIBLE);}

    }

    @Override

    public void onBackPressed() {

        //super.onBackPressed();

       /* if (pressedTime + 5000 > System.currentTimeMillis()) {


            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast
                    .LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();*/
    }

    @Override
    public void onClick(View v) {

    }
}


