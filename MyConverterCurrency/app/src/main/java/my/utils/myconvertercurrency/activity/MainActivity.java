package my.utils.myconvertercurrency.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import my.utils.myconvertercurrency.utils.OnReturnValue;
import my.utils.myconvertercurrency.data.MainActivityViewModel;
import my.utils.myconvertercurrency.R;
import my.utils.myconvertercurrency.adapter.AdapterDialogConverter;
import my.utils.myconvertercurrency.adapter.CurrencyAdapter;
import my.utils.myconvertercurrency.model.Currency;

public class MainActivity extends AppCompatActivity implements OnReturnValue {
    private Toolbar toolbar;
    private ImageView imageFirstCurrency, imageSecondCurrency, changeCurrency;
    private TextView nameFirstCurrency, nameSecondCurrency, textResultConvert;
    private EditText editTextResult, editTextSearch;
    private ArrayList<Currency> arrayList;
    private CurrencyAdapter adapterCurrency;
    private AdapterDialogConverter adapterDialogConverter;
    private MainActivityViewModel mainActivityViewModel;
    private RecyclerView recyclerViewDialog, recyclerViewMain;
    private boolean flag = true;
    private boolean flagOnClickCurrency;
    private SwipeRefreshLayout swipeRefreshLayout;
    private double bufferValue1 = 1;
    private double bufferValue2;
    private int nominal1 = 1;
    private int nominal2 = 1;
    private AdView mAdView;

    AlertDialog mDialog;
    String dialogTitle, dialogCancel, textToolBar, formatResult, currentDate;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        toolbar = findViewById(R.id.toolbar);
        dialogTitle = getResources().getString(R.string.DialogTitle);
        dialogCancel = getResources().getString(R.string.DialogCancel);
        textToolBar = getResources().getString(R.string.TextToolBar);

        setActionBar(toolbar);
        getAllCurrency();


        editTextResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editTextResult.length() > 0) {
                    double result = Double.parseDouble(editTextResult.getText().toString()) * (bufferValue1 / nominal1) * (nominal2 / bufferValue2);
                    formatResult = String.format("%.3f", result);
                    textResultConvert.setText(formatResult);
                    int i = 0;

                } else {
                    textResultConvert.setText("");
                }

            }
        });





        // Подключаем свайп для обновления данных
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                filRecyclerView();
            }
        });

        // слушатель на кнопку смена местами валюты
        changeCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buffer1Text = nameFirstCurrency.getText().toString();
                String buffer2Text = nameSecondCurrency.getText().toString();
                Drawable imageFlag1 = imageFirstCurrency.getDrawable();
                Drawable imageFlag2 = imageSecondCurrency.getDrawable();
                double container1 = bufferValue1;
                double container2 = bufferValue2;
                int nominalContainer1 = nominal1;
                int nominalContainer2 = nominal2;

                if (flag) {
                    imageFirstCurrency.setImageDrawable(imageFlag2);
                    nameFirstCurrency.setText(buffer2Text);
                    imageSecondCurrency.setImageDrawable(imageFlag1);
                    nameSecondCurrency.setText(buffer1Text);
                    bufferValue1 = container2;
                    bufferValue2 = container1;
                    nominal1 = nominalContainer2;
                    nominal2 = nominalContainer1;

                    changeCurrency.animate().rotation(180).setDuration(100);
                    editTextResult.setText("");
                    textResultConvert.setText("");
                    flag = false;

                } else {
                    imageFirstCurrency.setImageDrawable(imageFlag2);
                    nameFirstCurrency.setText(buffer2Text);
                    imageSecondCurrency.setImageDrawable(imageFlag1);
                    nameSecondCurrency.setText(buffer1Text);
                    bufferValue1 = container2;
                    bufferValue2 = container1;
                    nominal1 = nominalContainer2;
                    nominal2 = nominalContainer1;

                    changeCurrency.animate().rotation(0).setDuration(100);
                    flag = true;
                    editTextResult.setText("");
                    textResultConvert.setText("");
                }
            }
        });
    }



    private void setActionBar(Toolbar toolbar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        currentDate = simpleDateFormat.format(new Date());
        toolbar.setTitle(textToolBar + " " + currentDate + " г.");
    }


    private void init() {
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
        editTextResult = (EditText) findViewById(R.id.editTextValueCurrency);
        imageFirstCurrency = (ImageView) findViewById(R.id.imageFirstCurrency);
        imageSecondCurrency = (ImageView) findViewById(R.id.imageSecondCurrency);
        nameFirstCurrency = (TextView) findViewById(R.id.nameFirstCurrency);
        nameSecondCurrency = (TextView) findViewById(R.id.nameSecondCurrency);
        textResultConvert = (TextView) findViewById(R.id.textViewResultValue);
        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        changeCurrency = (ImageView) findViewById(R.id.buttonChangeCyrrency);
        arrayList = new ArrayList<>();
        mainActivityViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);
    }


    //Получаем и добавляем данные  валюты в recyclerViewMain
    private void getAllCurrency() {
        mainActivityViewModel.getAllCurrency().observe(this, new Observer<List<Currency>>() {
            @Override
            public void onChanged(List<Currency> currencies) {
                arrayList = (ArrayList<Currency>) currencies;
                Currency currentCurrency = arrayList.get(10);
                bufferValue2 = Double.parseDouble(currentCurrency.getValue());
                filRecyclerView();
            }
        });
    }

    private void filRecyclerView() {
        recyclerViewMain.setHasFixedSize(true);
        recyclerViewMain.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapterCurrency = new CurrencyAdapter(getApplicationContext(), arrayList);
        recyclerViewMain.setAdapter(adapterCurrency);
        adapterCurrency.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    public void onClick(View view) {

        switch (view.getId()) {                                                           // проверка какой флаг был нажат
            case R.id.imageFirstCurrency:
                flagOnClickCurrency = true;
                break;
            case R.id.imageSecondCurrency:
                flagOnClickCurrency = false;
                break;
        }


        view = LayoutInflater.from(this).inflate(R.layout.dilog_valute, null, false);
        recyclerViewDialog = view.findViewById(R.id.recyclerViewDialog);
        recyclerViewDialog.setHasFixedSize(true);
        recyclerViewDialog.setLayoutManager(new LinearLayoutManager(this));
        adapterDialogConverter = new AdapterDialogConverter(this, (ArrayList<Currency>) arrayList);
        recyclerViewDialog.setAdapter(adapterDialogConverter);

        editTextSearch = (EditText) view.findViewById(R.id.editTextSearsh);
        editTextSearch.addTextChangedListener(new TextWatcher() {                            //  поиск валюты  в диалоге
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view)
                .setTitle(dialogTitle)
                .setNegativeButton(dialogCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        mDialog = builder.create();
        mDialog.show();


    }

    @Override
    public void onReturnData(String name, String value, int flag, String nominal) {

        if (flagOnClickCurrency == true) {
            imageFirstCurrency.setImageResource(flag);
            nameFirstCurrency.setText(name);
            bufferValue1 = Double.parseDouble(value);
            nominal1 = Integer.parseInt(nominal.trim());
            textResultConvert.setText("");
            editTextResult.setText("");
            mDialog.dismiss();
            flagOnClickCurrency = false;
        } else {
            imageSecondCurrency.setImageResource(flag);
            nameSecondCurrency.setText(name);
            bufferValue2 = Double.parseDouble(value);
            nominal2 = Integer.parseInt(nominal.trim());
            textResultConvert.setText("");
            editTextResult.setText("");
            mDialog.dismiss();
        }
    }


    // фильтр для поиска  валюты в AlertDialog
    public void filter(String text) {
        ArrayList<Currency> filterList = new ArrayList<>();
        for (Currency CyrrencyItem : arrayList) {
            if (CyrrencyItem.getNameCyrrency().toLowerCase().contains(text.toLowerCase()) ||
                    CyrrencyItem.getCodeCyrrency().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(CyrrencyItem);
            }
        }
        adapterDialogConverter.filterList(filterList);
    }
}



