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

import com.medjay.suivigrossesse.Adapters.Days_Adapter;
import com.medjay.suivigrossesse.Adapters.Programme_Adapter;
import com.medjay.suivigrossesse.Models.Activite_sportiv;
import com.medjay.suivigrossesse.Models.Food;
import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.Periode;
import com.medjay.suivigrossesse.Models.PeriodeActivites;
import com.medjay.suivigrossesse.Models.PeriodeAliments;
import com.medjay.suivigrossesse.Models.Plat;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Exercices extends AppCompatActivity {

    WebService service;
    Call<Journee> call;

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

        setContentView(R.layout.activity_exercices);

        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        List<Activite_sportiv> Activite_sportiv_ma =new ArrayList<>();
        List<Activite_sportiv> Activite_sportiv_so =new ArrayList<>();
        List<Activite_sportiv> Activite_sportiv_apr =new ArrayList<>();

        List<PeriodeActivites> periodeActivites=new ArrayList<>();

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getDay(getIntent().getStringExtra("id"));

        call.enqueue(new Callback<Journee>() {
            @Override
            public void onResponse(Call<Journee> call, Response<Journee> response) {


                if (response.code()==200){

                    /*List<Periode> Periodes=response.body().getPeriods();

                    Programme_Adapter adapter=new Programme_Adapter(Exercices.this,R.layout.programme_layout, Periodes);
                    ListView lv = (ListView) findViewById(R.id.lv);
                    lv.setAdapter(adapter);*/

                    for (Activite_sportiv activite_sportiv:response.body().getActivite_sportivs()) {

                        switch (activite_sportiv.getPivot().getPeriode()){
                            case "Matin":
                                Activite_sportiv_ma.add(activite_sportiv);
                                break;
                            case "Soir":
                                Activite_sportiv_so.add(activite_sportiv);
                                break;
                            case "Aprés dinner":
                                Activite_sportiv_apr.add(activite_sportiv);
                                break;
                        }

                    }

                    if (!Activite_sportiv_ma.isEmpty()){periodeActivites.add(new PeriodeActivites("Matin",Activite_sportiv_ma));}
                    if (!Activite_sportiv_so.isEmpty()){periodeActivites.add(new PeriodeActivites("Soir",Activite_sportiv_so));}
                    if (!Activite_sportiv_apr.isEmpty()){periodeActivites.add(new PeriodeActivites("Aprés Dinner",Activite_sportiv_apr));}

                    Programme_Adapter adapter=new Programme_Adapter(Exercices.this,R.layout.programme_layout, periodeActivites);
                    ListView lv = (ListView) findViewById(R.id.lv);
                    lv.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<Journee> call, Throwable t) {

                Toasty.error(getApplicationContext(), Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();

            }
        });


    }
}
