package my.utils.myconvertercurrency.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import my.utils.myconvertercurrency.R;
import my.utils.myconvertercurrency.model.Currency;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Currency> arrayList;

    public CurrencyAdapter(Context context, ArrayList<Currency> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.model_item, parent, false);
        final ViewHolder vHolder = new ViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency cardCyrrencyCurrent = arrayList.get(position);

        String titleName = cardCyrrencyCurrent.getNameCyrrency();
        String titleNominal = cardCyrrencyCurrent.getNominal();
        String titleCod = cardCyrrencyCurrent.getCodeCyrrency();
        String titleValue = cardCyrrencyCurrent.getValue();
        int imageFlag = cardCyrrencyCurrent.getImageCyrrency();


        holder.textNameCyrrency.setText(titleName);
        holder.textNominalCyrrency.setText(titleNominal);
        holder.textCodeCyrrency.setText(titleCod);
        holder.textValueCyrrency.setText(titleValue);
        holder.imageCyrrency.setImageResource(imageFlag);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNameCyrrency;
        TextView textCodeCyrrency;
        TextView textNominalCyrrency;
        TextView textValueCyrrency;
        ImageView imageCyrrency;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNameCyrrency = itemView.findViewById(R.id.textNameID);
            textCodeCyrrency = itemView.findViewById(R.id.textCodeID);
            textNominalCyrrency = itemView.findViewById(R.id.textNominalID);
            textValueCyrrency = itemView.findViewById(R.id.textValueID);
            imageCyrrency = itemView.findViewById(R.id.imageID);
        }
    }
}
