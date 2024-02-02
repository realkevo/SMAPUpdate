package ke.co.smap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;


public class AssessHistory extends AppCompatActivity {




   private RecyclerView AssessmentList;
   RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;

    private TextView startDate, endDate, search;
    private DatePickerDialog datePickerDialog;


 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assess_history);
        startDate = findViewById(R.id.editTextText_start_dateTv);
        endDate = findViewById(R.id.editTextText_end_dateTv);
     AssessmentList = findViewById(R.id.assessment_recyclerview);
     AssessmentList.setLayoutManager( new LinearLayoutManager(this));


     startDate.setOnClickListener(v -> {
         final Calendar c = Calendar.getInstance();
         int mYear = c.get(Calendar.YEAR);
         int mMonth = c.get(Calendar.MONTH);
         int mDay = c.get(Calendar.DAY_OF_MONTH);
         datePickerDialog = new DatePickerDialog(AssessHistory.this,
                 (view, year, month, dayOfMonth) -> startDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year), mYear, mMonth, mDay
         );
         datePickerDialog.show();

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
databaseReference = FirebaseDatabase.getInstance().getReference().child("AssessmentInfo");




}
 }
/*

    @Override
    protected void onStart() {
        super.onStart();
//todo after creating and verfing the model class come here and complete this task kelvin

        FirebaseRecyclerOptions<AssessPojo> options =
                new FirebaseRecyclerOptions.Builder<AssessPojo>()
                .setQuery(databaseReference,  AssessPojo.class)
                .build();
      FirebaseRecyclerAdapter adapter =
                new FirebaseRecyclerAdapter<AssessPojo,
                        AssessHistoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull

                                            AssessHistoryViewHolder holder, int position, @NonNull AssessPojo model) {

                //d_points, details,shift, supervisor,tarehe,workId

               holder.points.setText(model.getD_points());
                holder.workId.setText(model.getWorkId());
                holder.shift.setText(model.getShift());
                holder.details.setText(model.getDetails());
                holder.date.setText(model.getTarehe());
                holder.supervisor.setText(model.getSupervisor());
                //TODO ADD HOLDER FOR IMAGE URL

            }

            @NonNull
            @Override
            public AssessHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                  View view = getLayoutInflater().inflate(R.layout.activity_assess_history, parent, false);
return  new AssessHistoryViewHolder(view);


            }
        };
        AssessmentList.setAdapter(adapter);
        adapter.startListening();
    }
    public static class AssessHistoryViewHolder extends RecyclerView.ViewHolder{

   public TextView date, details,station,supervisor, points, shift, workId;
        public AssessHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_item_tv);
            details = itemView.findViewById(R.id.details_item_tv);
            station = itemView.findViewById(R.id.station_item_tv);
            supervisor = itemView.findViewById(R.id.supervisor_item_tv);
            points = itemView.findViewById(R.id.points_item_tv);
            shift = itemView.findViewById(R.id.shift_id_item_tv);
            workId = itemView.findViewById(R.id.work_id_item_tv);


        }
    }
}*/