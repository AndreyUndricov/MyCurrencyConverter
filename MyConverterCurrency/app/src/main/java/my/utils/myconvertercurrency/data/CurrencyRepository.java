package my.utils.myconvertercurrency.data;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import my.utils.myconvertercurrency.R;
import my.utils.myconvertercurrency.model.Currency;

import com.android.volley.RequestQueue;

public class CurrencyRepository {
    private Application application;
    private ArrayList<Currency> results = new ArrayList<>();
    private RequestQueue requestQueue;
    private MutableLiveData<List<Currency>> mutableLiveData = new MutableLiveData<>();



    public CurrencyRepository(Application application) {
        this.application = application;
    }




    public MutableLiveData<List<Currency>> getMutableLiveData() {
        requestQueue = Volley.newRequestQueue(application);
        String url = "https://www.cbr-xml-daily.ru/daily_json.js";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String string = response.toString();
                    int indexV = string.indexOf("Valute");
                    int indexE = 0;
                    string = "{" + string.substring(indexV, string.length());
                    JSONObject json = new JSONObject(string);
                    while (true) {
                        indexV = string.indexOf("ID");
                        if (indexV < 0) break;
                        indexE = string.indexOf("}");
                        String parse = string.substring(indexV, indexE);
                        parse = parse.replace(':', '\0');
                        parse = parse.replace(',', '\0');
                        parse = parse.trim();
                        final char dm = (char) 34;
                        String[] pars = parse.split(String.valueOf(dm));
                        String CharCode = pars[10];
                        String Nominal = pars[13];
                        String Name = pars[16];
                        String Value = pars[19];
                        string = string.substring(indexE + 1, string.length());
                        int i = 0;

                        Currency cardCyrrency = new Currency();
                        cardCyrrency.setNameCyrrency(Name);
                        cardCyrrency.setNominal(Nominal);
                        cardCyrrency.setCodeCyrrency(CharCode);
                        cardCyrrency.setValue(Value);


                        switch (CharCode) {
                            case "AZN":
                                cardCyrrency.setImageCyrrency(R.drawable.azerbaijan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.azerbaijan));
                                break;
                            case "AUD":
                                cardCyrrency.setImageCyrrency(R.drawable.australia_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.australia));
                                break;
                            case "GBP":
                                cardCyrrency.setImageCyrrency(R.drawable.united_kingdom_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.british));
                                break;
                            case "AMD":
                                cardCyrrency.setImageCyrrency(R.drawable.armenia_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.armenia));
                                break;
                            case "BYN":
                                cardCyrrency.setImageCyrrency(R.drawable.belarus_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.belorus));
                                break;
                            case "BGN":
                                cardCyrrency.setImageCyrrency(R.drawable.bulgaria_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.bulgaria));
                                break;
                            case "BRL":
                                cardCyrrency.setImageCyrrency(R.drawable.brazil_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.brazilia));
                                break;
                            case "HUF":
                                cardCyrrency.setImageCyrrency(R.drawable.hungary_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.hungaria));
                                break;
                            case "HKD":
                                cardCyrrency.setImageCyrrency(R.drawable.hong_kong_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.gonkong));
                                break;
                            case "DKK":
                                cardCyrrency.setImageCyrrency(R.drawable.denmark_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.dania));
                                break;
                            case "USD":
                                cardCyrrency.setImageCyrrency(R.drawable.united_states_of_america_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.usa));
                                break;
                            case "EUR":
                                cardCyrrency.setImageCyrrency(R.drawable.european_union_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.evro ));
                                break;
                            case "INR":
                                cardCyrrency.setImageCyrrency(R.drawable.india_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.indiya));
                                break;
                            case "KZT":
                                cardCyrrency.setImageCyrrency(R.drawable.kazakhstan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.kazahstan));
                                break;
                            case "CAD":
                                cardCyrrency.setImageCyrrency(R.drawable.canada_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.canada));
                                break;
                            case "KGS":
                                cardCyrrency.setImageCyrrency(R.drawable.kyrgyzstan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.kirgiz));
                                break;
                            case "CNY":
                                cardCyrrency.setImageCyrrency(R.drawable.china_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.china));
                                break;
                            case "MDL":
                                cardCyrrency.setImageCyrrency(R.drawable.moldova_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.moldova));
                                break;
                            case "NOK":
                                cardCyrrency.setImageCyrrency(R.drawable.norway_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.norwegian));
                                break;
                            case "PLN":
                                cardCyrrency.setImageCyrrency(R.drawable.poland_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.polish));
                                break;
                            case "RON":
                                cardCyrrency.setImageCyrrency(R.drawable.romania_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.romania));
                                break;
                            case "XDR":
                                cardCyrrency.setImageCyrrency(R.drawable.saint_pierre_and_miquelon_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.SDR));
                                break;
                            case "SGD":
                                cardCyrrency.setImageCyrrency(R.drawable.singapore_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.singapore));
                                break;
                            case "TJS":
                                cardCyrrency.setImageCyrrency(R.drawable.tajikistan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.tadjikistan));
                                break;
                            case "TRY":
                                cardCyrrency.setImageCyrrency(R.drawable.turkey_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.turkish));
                                break;
                            case "TMT":
                                cardCyrrency.setImageCyrrency(R.drawable.turkmenistan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.turkmenia));
                                break;
                            case "UZS":
                                cardCyrrency.setImageCyrrency(R.drawable.uzbekistan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.uzbekistan));
                                break;
                            case "UAH":
                                cardCyrrency.setImageCyrrency(R.drawable.ukraine_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.ukrain));
                                break;
                            case "CZK":
                                cardCyrrency.setImageCyrrency(R.drawable.czech_republic_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.czech));
                                break;
                            case "SEK":
                                cardCyrrency.setImageCyrrency(R.drawable.sweden_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.swedish));
                                break;
                            case "CHF":
                                cardCyrrency.setImageCyrrency(R.drawable.switzerland_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.swisss));
                                break;
                            case "ZAR":
                                cardCyrrency.setImageCyrrency(R.drawable.south_africa_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.afrika));
                                break;
                            case "KRW":
                                cardCyrrency.setImageCyrrency(R.drawable.korea_south_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.korea));
                                break;
                            case "JPY":
                                cardCyrrency.setImageCyrrency(R.drawable.japan_rectangular_icon_with_frame_256);
                                cardCyrrency.setNameCyrrency(application.getString(R.string.japan));
                                break;
                        }
                        results.add(cardCyrrency);

                    }
                    results.add(new Currency(application.getString(R.string.russia), "1", "RUB", R.drawable.russia_rectangular_icon_with_frame_256, "1"));
                    mutableLiveData.setValue(results);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

        return mutableLiveData;
    }
}
