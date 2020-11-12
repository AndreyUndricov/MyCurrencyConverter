package my.utils.myconvertercurrency.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import my.utils.myconvertercurrency.model.Currency;

public class MainActivityViewModel extends AndroidViewModel {
    private CurrencyRepository currencyRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        currencyRepository = new CurrencyRepository(application);
    }
    public LiveData<List<Currency>> getAllCurrency(){
        return currencyRepository.getMutableLiveData();
    }
}
