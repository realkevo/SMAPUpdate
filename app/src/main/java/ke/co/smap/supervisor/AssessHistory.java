package ke.co.smap.supervisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ke.co.smap.R;
import ke.co.smap.adapter.AssessAdapter;
import ke.co.smap.model.AssessPojo;


public class AssessHistory extends AppCompatActivity {




   private RecyclerView AssessmentList_recyclerView;
   DatabaseReference databaseReference;
   ValueEventListener valueEventListener;
    List<AssessPojo> dataList;
 AssessAdapter adapter;
    private TextView startDate, endDate, search;
    private DatePickerDialog datePickerDialog;
    EditText search_query;



 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_history);
       startDate = findViewById(R.id.editTextText_start_dateTv);
        endDate = findViewById(R.id.editTextText_end_dateTv);
        search = findViewById(R.id.search);
     search_query = findViewById(R.id.editTextText_search_workId);

        AssessmentList_recyclerView = findViewById(R.id.assessment_recyclerview);
   //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
        //    AssessHistory.this);
   // AssessmentList_recyclerView.setLayoutManager(linearLayoutManager);

GridLayoutManager gridLayoutManager = new GridLayoutManager(
       AssessHistory.this, 1);
AssessmentList_recyclerView.setLayoutManager(gridLayoutManager);

   dataList = new ArrayList<>();
   adapter = new AssessAdapter(AssessHistory.this,
           dataList);
      databaseReference = FirebaseDatabase
            .getInstance().getReference("assessmentInfo");
    valueEventListener =
databaseReference.addValueEventListener(new ValueEventListener() {
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        dataList.clear();
        for (DataSnapshot itemSnapShot: snapshot.getChildren()){
            AssessPojo
                    assessmentInfoPojo
                    = itemSnapShot.getValue(AssessPojo.class);

            //HERE SET AND GET KEY
            //dataclass.setkey(itemsnapshot.getKey());

            dataList.add(assessmentInfoPojo);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

    startDate.setOnClickListener(v -> {
         final Calendar c = Calendar.getInstance();
         int mYear = c.get(Calendar.YEAR);
         int mMonth = c.get(Calendar.MONTH);
         int mDay = c.get(Calendar.DAY_OF_MONTH);
         datePickerDialog = new DatePickerDialog(AssessHistory.this,
                 (view, year, month, dayOfMonth) ->
                         startDate.setText(dayOfMonth +
                                 "/" + (month + 1) + "/"
                                 + year), mYear, mMonth, mDay
         );
         datePickerDialog.show();

     });
     search.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             //todo im[plemement the search method here
             //search();
             Toast.makeText(AssessHistory.this,
                     "Yeah,baby! that is what i am talking about", Toast.LENGTH_SHORT).show();

         }
     });
     endDate.setOnClickListener(v -> {
         final Calendar c = Calendar.getInstance();
         int mYear = c.get(Calendar.YEAR);
         int mMonth = c.get(Calendar.MONTH);
         int mDay = c.get(Calendar.DAY_OF_MONTH);
         datePickerDialog = new DatePickerDialog(AssessHistory.this,
                 (view, year, month, dayOfMonth) -> endDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year), mYear, mMonth, mDay
         );
         datePickerDialog.show();

     });

//databaseReference =
    FirebaseDatabase.getInstance()
              .getReference().child("AssessmentInfo");



}
//search method
  /*  private void search() {
        search_query.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
DatabaseReference rootRef = FirebaseDatabase.getInstance()
        .getReference();
DatabaseReference datarRef = rootRef.child("assessmentInfo").child("tarehe");
Query query = datarRef.orderByChild("tarehe").equalTo(s.toString());
query.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        for (DataSnapshot itemSnapShot: snapshot.getChildren()){
            AssessPojo
                    assessmentInfoPojo
                    = itemSnapShot.getValue(AssessPojo.class);

            //HERE SET AND GET KEY
            //dataclass.setkey(itemsnapshot.getKey());

            dataList.add(assessmentInfoPojo);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});}});

 }*/
}
