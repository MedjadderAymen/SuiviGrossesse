package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.medjay.suivigrossesse.Adapters.MenuAdapter;
import com.medjay.suivigrossesse.Adapters.Programme_Adapter;
import com.medjay.suivigrossesse.Models.Aliment;
import com.medjay.suivigrossesse.Models.Food;
import com.medjay.suivigrossesse.Models.JourneeAlimentaire;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.PeriodeAliments;
import com.medjay.suivigrossesse.Models.Plat;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    WebService service;
    Call<JourneeAlimentaire> call;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.white));

        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Food> foodList_pd =new ArrayList<>();
        List<Food> foodList_d =new ArrayList<>();
        List<Food> foodList_c =new ArrayList<>();
        List<Food> foodList_dinner =new ArrayList<>();

        List<PeriodeAliments> periodeAliments =new ArrayList<>();

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs",MODE_PRIVATE));


        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getMenu(getIntent().getStringExtra("id"));

        call.enqueue(new Callback<JourneeAlimentaire>() {
            @Override
            public void onResponse(Call<JourneeAlimentaire> call, Response<JourneeAlimentaire> response) {
                if (response.code()==200){

                    for (Plat plat:response.body().getPlat()) {

                        switch (plat.getPivot().getPeriode()){
                            case "Petit déjeuner":
                                foodList_pd.add(new Food(plat.getNom(),plat.getImage(),plat.getDescription(),plat.getId()));
                                break;
                            case "Déjeuner":
                                foodList_d.add(new Food(plat.getNom(),plat.getImage(),plat.getDescription(),plat.getId()));
                                break;
                            case "Collation":
                                foodList_c.add(new Food(plat.getNom(),plat.getImage(),plat.getDescription(),plat.getId()));
                                break;
                            case "Dinner":
                                foodList_dinner.add(new Food(plat.getNom(),plat.getImage(),plat.getDescription(),plat.getId()));
                                break;
                        }

                    }

                    for (Aliment aliment:response.body().getAliment()) {
                        switch (aliment.getPivot().getPeriode()){
                            case "Petit déjeuner":
                                foodList_pd.add(new Food(aliment.getNom(),aliment.getImage(),aliment.getDescription(),aliment.getId()));
                                break;
                            case "Déjeuner":
                                foodList_d.add(new Food(aliment.getNom(),aliment.getImage(),aliment.getDescription(),aliment.getId()));
                                break;
                            case "Collation":
                                foodList_c.add(new Food(aliment.getNom(),aliment.getImage(),aliment.getDescription(),aliment.getId()));
                                break;
                            case "Dinner":
                                foodList_dinner.add(new Food(aliment.getNom(),aliment.getImage(),aliment.getDescription(),aliment.getId()));
                                break;
                        }
                    }

                    if (!foodList_pd.isEmpty()){periodeAliments.add(new PeriodeAliments("Petit déjeuner",foodList_pd));}
                    if (!foodList_d.isEmpty()){periodeAliments.add(new PeriodeAliments("Déjeuner",foodList_d));}
                    if (!foodList_c.isEmpty()){periodeAliments.add(new PeriodeAliments("Collation",foodList_c));}
                    if (!foodList_dinner.isEmpty()){periodeAliments.add(new PeriodeAliments("Dinner",foodList_dinner));}

                    MenuAdapter adapter=new MenuAdapter(Menu.this,R.layout.programme_layout, periodeAliments);
                    ListView lv = (ListView) findViewById(R.id.lv);
                    lv.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<JourneeAlimentaire> call, Throwable t) {

            }
        });
    }
}
