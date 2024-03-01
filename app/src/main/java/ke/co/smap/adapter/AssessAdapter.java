package ke.co.smap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.smap.R;
import ke.co.smap.model.AssessPojo;

public class AssessAdapter extends RecyclerView.Adapter<AssessAdapter.ViewHolder> {
    private Context mcontext;
    private List<AssessPojo> dataList;
    public AssessAdapter( Context context, List<AssessPojo> dataList) {
        this.mcontext = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.assess_history_item,
                parent, false);

        return new AssessAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssessPojo assessPojo = dataList.get(position);

        holder.detailDetails.setText(assessPojo.getDetails());
        holder.detail_ImageUrl.setText(assessPojo.getImageUrl());
        holder.detailPoints.setText(assessPojo.getPoints());
        holder.detailShift.setText(assessPojo.getShift());
        holder.detailStation.setText(assessPojo.getStation());
        holder.detailSupervisor.setText(assessPojo.getSupervisor());
        holder.detailDate.setText(assessPojo.getTarehe());
        holder.detailWorkId.setText(assessPojo.getWorkId());}
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView detailDate, detailDetails, detailStation, detailPoints,
                detailWorkId,  detailShift,   detail_ImageUrl,  detailSupervisor;
        //detailScore,
        //detailSupervisor,
        public  ViewHolder (@NonNull View itemView){
            super(itemView);
            detailDate = itemView.findViewById(R.id.date_item_tv);
            detailDetails = itemView.findViewById(R.id.details_item_tv)  ;
            detailStation = itemView.findViewById(R.id.station_item_tv);
            detailSupervisor = itemView.findViewById(R.id.supervisor_item_tv);
            detailPoints = itemView.findViewById(R.id.points_item_tv);
            detailWorkId = itemView.findViewById(R.id.work_id_item_tv);
            detailShift = itemView.findViewById(R.id.shift_item_tv);
            detail_ImageUrl = itemView.findViewById(R.id.image_Url_itemTV);
        }
    }}

