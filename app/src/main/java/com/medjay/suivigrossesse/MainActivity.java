package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.medjay.suivigrossesse.Models.AccessToken;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    WebService service;
    Call<Patiente> call;

    Call<String> callToken;
    boolean result;

    @BindView(R.id.matricule_id)
    TextInputEditText matricule_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.connect)
    public void submit(View view) {

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.Login(Objects.requireNonNull("5f495e2f7c740"));

        call.enqueue(new Callback<Patiente>() {
            @Override
            public void onResponse(Call<Patiente> call, Response<Patiente> response) {
                if (response.code()==200){

                    startActivity(new Intent(MainActivity.this,home.class));
                    finish();

                }else if (response.code()==215){
                    TokenManager tokenManager;
                    tokenManager=TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs",MODE_PRIVATE));
                    callToken=service.AddToken(tokenManager.getToken().getToken(),"5f495e2f7c740");

                    callToken.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.code()==200){
                                startActivity(new Intent(MainActivity.this,home.class));
                                finish();
                            } }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toasty.warning(getApplicationContext(),"Veuillez réinstaller l'application.",Toasty.LENGTH_LONG).show();
                        }
                    });

                }else if(response.code()==400){
                    Toasty.warning(getApplicationContext(),"Vouillez verifir votre matricule.",Toasty.LENGTH_LONG).show();
                }else {
                    Toasty.success(getApplicationContext(),"une autre erreur",Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Patiente> call, Throwable t) {
                Toasty.error(getApplicationContext(),t.getMessage(),Toasty.LENGTH_LONG).show();
            }
        });

    }

}
