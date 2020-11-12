package my.utils.myconvertercurrency.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import my.utils.myconvertercurrency.utils.OnReturnValue;
import my.utils.myconvertercurrency.R;
import my.utils.myconvertercurrency.model.Currency;

public class AdapterDialogConverter extends RecyclerView.Adapter<AdapterDialogConverter.ViewHolder> {
    private Context context;
    private ArrayList<Currency> arrayList;
    private OnReturnValue onReturnValue;

    public AdapterDialogConverter(Context context, ArrayList<Currency> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setOnReturnValue(OnReturnValue onReturnValue) {
        this.onReturnValue = onReturnValue;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.element_currency, parent, false);
        final ViewHolder vHolder = new ViewHolder(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Currency currentCurrency = arrayList.get(position);
        onReturnValue = (OnReturnValue) this.context;

        final String titleName = currentCurrency.getNameCyrrency();
        final String titleCod = currentCurrency.getCodeCyrrency();
        final String titleValue = currentCurrency.getValue();
        final String titleNominal = currentCurrency.getNominal();
        final int imageFlag = currentCurrency.getImageCyrrency();


        holder.textNameDialog.setText(titleName);
        holder.textCodeDialog.setText(titleCod);
        holder.imageDialog.setImageResource(imageFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.itemView.setBackgroundColor(Color.rgb(0, 191, 255));
                onReturnValue.onReturnData(titleName, titleValue, imageFlag,titleNominal);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<Currency> filterList) {
        arrayList = filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNameDialog;
        TextView textCodeDialog;
        ImageView imageDialog;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNameDialog = itemView.findViewById(R.id.textNameID);
            textCodeDialog = itemView.findViewById(R.id.textCodeID);
            imageDialog = itemView.findViewById(R.id.imageID);

        }
    }
}

