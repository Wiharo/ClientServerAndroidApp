package it.mirea.kursovaya;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FestAdapter extends RecyclerView.Adapter<FestAdapter.FestViewHolder> {
    private List<FestModel> festList;
    private OnItemClickListener listener;

    public FestAdapter(List<FestModel> festList) {
        this.festList = festList;
    }
    public interface OnItemClickListener {
        void onItemClick(FestModel fest);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setFestList(List<FestModel> festList) {
        this.festList = festList;
        notifyDataSetChanged();
    }

    public List<FestModel> getFestList() {
        return festList;
    }



    @NonNull
    @Override
    public FestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fest, parent, false);
        return new FestViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FestViewHolder holder, int position) {
        FestModel fest = festList.get(position);
        holder.nameTextView.setText(fest.getName());
        holder.dateTimeTextView.setText(fest.getDate_time());
        holder.priceTextView.setText(fest.getPrice());

        if (fest.getPrice().equalsIgnoreCase("free")) {
            holder.priceTextView.setText("free");
        } else {
            holder.priceTextView.setText(fest.getPrice());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(festList.get(holder.getAdapterPosition()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return festList.size();
    }

    public class FestViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView dateTimeTextView;
        public TextView priceTextView;


        public FestViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);

        }
    }




}


