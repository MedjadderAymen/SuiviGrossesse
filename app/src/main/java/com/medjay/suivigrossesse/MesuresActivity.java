package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.medjay.suivigrossesse.Models.Donnee;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

import java.util.List;
import java.util.Objects;

public class MesuresActivity extends AppCompatActivity {

    WebService service;
    Call<String> call;

    String mesure="Tenssion";

    @BindView(R.id.valeur)
    TextInputEditText valeur;

    @BindView(R.id.iv1)
    ImageView iv1;

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

        setContentView(R.layout.activity_mesures);

        ButterKnife.bind(this);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.spinner);
        spinner.setItems("Tenssion", "Glycémie", "Poids","Temperature");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                switch (item){
                    case "Tenssion" : iv1.setImageResource(R.drawable.hypertension); break;
                    case "Glycémie" : iv1.setImageResource(R.drawable.blood); break;
                    case "Poids" : iv1.setImageResource(R.drawable.weight); break;
                    case "Temperature" : iv1.setImageResource(R.drawable.hot); break;
                }

                mesure=item;
            }
        });
    }

    @OnClick(R.id.send)
    public void submit(View view){

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.setMesure(tokenManager.getToken().getToken(), mesure, Float.parseFloat(valeur.getText().toString()));

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.code()==200){

                    Toasty.success(getApplicationContext(), response.body(),Toasty.LENGTH_LONG).show();
                    valeur.setText("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Toasty.error(getApplicationContext(), Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();

            }
        });

    }
}
