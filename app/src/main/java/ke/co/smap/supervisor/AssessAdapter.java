package ke.co.smap.supervisor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ke.co.smap.R;
import ke.co.smap.model.AssessmentInfoPojo;

public class AssessAdapter
extends RecyclerView.Adapter<MyViewHolder>{

private Context context;
private List <AssessmentInfoPojo> dataList;


    public AssessAdapter(Context mcontext, List<AssessmentInfoPojo> dataList) {
        this.context = mcontext;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.assess_history_item
        , parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       // AssessmentInfoPojo assessmentInfoPojo = new AssessmentInfoPojo();
holder.detailDate.setText(dataList.get(position).getTarehe());
        holder.detailWorkId.setText(dataList.get(position).getWorkId());
        holder.detailImage.setText(dataList.get(position).getImageUrl());
        holder.detailDetails.setText(dataList.get(position).getDetails());
        holder.detailPoints.setText(dataList.get(position).getPoints());
        holder.detailShift.setText(dataList.get(position).getShift());
        holder.detailSupervisor.setText(dataList.get(position).getSupervisor());
      holder.detailStation.setText(dataList.get(position).getStation());
      holder.urlClick.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              holder.detailImage.setVisibility(View.VISIBLE);
              holder.urlClick.setVisibility(View.INVISIBLE);

          }
      });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public void searchDataList (ArrayList<AssessmentInfoPojo> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder{
    // CardView itemcardView;
     TextView detailWorkId, detailDate, detailDetails,
          //  detailStation,
            detailSupervisor, detailPoints,
    detailStation,
             detailShift
              , detailImage, urlClick;



    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
       // itemcardView = itemView.findViewById(R.id.cardView_item_tv);
        detailWorkId = itemView.findViewById(R.id.work_id_item_tv);
       detailDate = itemView.findViewById(R.id.date_item_tv);
        detailDetails = itemView.findViewById(R.id.details_item_tv);
        detailStation = itemView.findViewById(R.id.station_item_tv);
        detailSupervisor = itemView.findViewById(R.id.supervisor_item_tv);
        detailPoints = itemView.findViewById(R.id.points_item_tv);
        detailShift = itemView.findViewById(R.id.shift_item_tv);
        detailImage = itemView.findViewById(R.id.image_Url_itemTV);
        urlClick = itemView.findViewById(R.id.image_Url_item);



    }
}


/*public class AssessAdapter extends RecyclerView.Adapter<AssessAdapter.ViewHolder> {
   private Context mcontext;
    private List<AssessmentInfoPojo> dataList;
    public AssessAdapter( Context context, List<AssessmentInfoPojo> dataList) {
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
        AssessmentInfoPojo assessPojo = dataList.get(position);

        holder.detailDetails.setText(assessPojo.getDETAILS());
        holder.detail_ImageUrl.setText(assessPojo.getIMAGE_URL());
        holder.detailPoints.setText(assessPojo.getPOINTS());
      //  holder.detailShift.setText(assessPojo.getSHIFT());
       // holder.detailStation.setText(assessPojo.get);
        holder.detailSupervisor.setText(assessPojo.getSUPERVISOR());
        holder.detailDate.setText(assessPojo.getTAREHE());
        holder.detailWorkId.setText(assessPojo.getWORK_ID());}
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView detailDate, detailDetails, detailStation,
                detailPoints,
                detailWorkId,  detailShift,   detail_ImageUrl,  detailSupervisor;
        //detailScore,
        //detailSupervisor,
        @SuppressLint("WrongViewCast")
        public  ViewHolder (@NonNull View itemView){
            super(itemView);
            detailDate = itemView.findViewById(R.id.date_item_tv);
            detailDetails = itemView.findViewById(R.id.details_item_tv)  ;
            detailStation = itemView.findViewById(R.id.station_item_tv);
            detailSupervisor = itemView.findViewById(R.id.supervisor_item_tv);
            detailPoints = itemView.findViewById(R.id.points_item_tv);
            detailWorkId = itemView.findViewById(R.id.work_id_item_tv);
           // detailShift = itemView.findViewById(R.id.shift_item_tv);
           // detail_ImageUrl = itemView.findViewById(R.id.consent_item_tv);
        }*/



