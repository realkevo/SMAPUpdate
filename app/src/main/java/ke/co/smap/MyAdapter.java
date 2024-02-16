package ke.co.smap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ke.co.smap.model.AssessmentInfoPojo;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<AssessmentInfoPojo> dataList;
    public MyAdapter(Context context, List<AssessmentInfoPojo> dataList){
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assess_history_item, parent, false);
return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.detailDate.setText(dataList.get(position).getTAREHE());
       holder.detailDetails.setText(dataList.get(position).getDETAILS());
        holder.detailPoints.setText(dataList.get(position).getPOINTS());
        holder.detailStation.setText(dataList.get(position).getSTATION());
        holder.detailWorkId.setText(dataList.get(position).getWORK_ID());

      //holder.detailSupervisor.setText(dataList.get(position).getSUPERVISOR());
      //  holder.detailScore.setText(dataList.get(position).getSCORE());
        holder.detailShift.setText(dataList.get(position).getSHIFT());
//TODO fix this by adding station



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView
                detailDate,
                detailDetails,
                //detailScore,
                detailStation,
        //detailSupervisor,
        detailPoints,
                detailWorkId,



        detailShift;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            detailDate = itemView.findViewById(R.id.date_item_tv);
            detailDetails = itemView.findViewById(R.id.details_item_tv);
            detailStation = itemView.findViewById(R.id.station_item_tv);
            // detailSupervisor = itemView.findViewById(R.id.supervisor_item_tv);
            detailPoints = itemView.findViewById(R.id.points_item_tv);
            detailWorkId = itemView.findViewById(R.id.work_id_item_tv);
            detailShift = itemView.findViewById(R.id.shift_item_tv);
           // detailScore = itemView.findViewById(R.id.score_displayTV);
        }
    }
}


