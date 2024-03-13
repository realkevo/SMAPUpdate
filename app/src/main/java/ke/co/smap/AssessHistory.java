package ke.co.smap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ke.co.smap.model.AssessmentInfoPojo;


public class AssessHistory extends AppCompatActivity {





    private RecyclerView AssessmentList_recyclerView;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    List<AssessmentInfoPojo> dataList;
    AssessAdapter adapter;
    SearchView searchView;



 @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_assess_history);

     AssessmentList_recyclerView = findViewById(R.id.assessment_recyclerview);
searchView = findViewById(R.id.searchViewV);
searchView.clearFocus();

     GridLayoutManager gridLayoutManager = new GridLayoutManager(
             AssessHistory.this, 1);
     AssessmentList_recyclerView.setLayoutManager(gridLayoutManager);

     dataList = new ArrayList<>();
     adapter = new AssessAdapter(AssessHistory.this,
             dataList);
     AssessmentList_recyclerView.setAdapter(adapter);


     databaseReference = FirebaseDatabase
             .getInstance().getReference("assessmentInfo");

     valueEventListener =
             databaseReference.addValueEventListener(new ValueEventListener() {
                 @SuppressLint("NotifyDataSetChanged")
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     dataList.clear();
                     for (DataSnapshot itemSnapShot : snapshot.getChildren()) {
                         AssessmentInfoPojo
                                 assessmentInfoPojo
                                 = itemSnapShot.getValue(AssessmentInfoPojo.class);

                         //HERE SET AND GET KEY
                         // assessmentInfoPojo.setkey(itemsnapshot.getKey());

                         dataList.add(assessmentInfoPojo);
                     }
                     adapter.notifyDataSetChanged();

                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {
                     Toast.makeText(AssessHistory.this, "data retrieve failed", Toast.LENGTH_SHORT).show();

                 }
             });
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchList(newText);
        return true;
    }
});

//databaseReference = FirebaseDatabase.getInstance().getReference().child("AssessmentInfo");

 }

    public void searchList(String text) {
     ArrayList<AssessmentInfoPojo> searchList = new ArrayList<>();
     for (AssessmentInfoPojo assessmentInfoPojo: dataList){
         if (assessmentInfoPojo.getStation().toLowerCase()
                 .contains(text.toLowerCase())){
             searchList.add(assessmentInfoPojo);
         }
         if (assessmentInfoPojo.getWorkId().toLowerCase()
                 .contains(text.toLowerCase())){
             searchList.add(assessmentInfoPojo);
         }
         //enable supervisor search key
         if (assessmentInfoPojo.getSupervisor().toLowerCase()
                 .contains(text.toLowerCase())){
             searchList.add(assessmentInfoPojo);
         }
     }
     adapter.searchDataList(searchList);
    }

    //<<ACODEVELOPERS>>//

}


