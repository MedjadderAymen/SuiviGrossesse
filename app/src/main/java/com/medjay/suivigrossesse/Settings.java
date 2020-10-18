package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.medjay.suivigrossesse.Alarm.AlarmMe;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.Rendez_vous;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Objects;

public class Settings extends AppCompatActivity {

    WebService service;
    Call<Patiente> call;
    Call<Patiente> callPhoneNumber;

    @BindView(R.id.patient_number)
    TextView patient_number;

    @BindView(R.id.patient_number_edit)
    TextInputEditText patient_number_edit;

    @BindView(R.id.ctLayout)
    ConstraintLayout ctLayout;

    @BindView(R.id.lng)
    Button lng;

    Locale locale ;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

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

        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        preferences=getApplicationContext().getSharedPreferences("My_pref",MODE_PRIVATE);
        editor=preferences.edit();

        if(Objects.equals(preferences.getString("language", null), "fr_FR")) {
            lng.setText("العربية");
        }else {
            lng.setText("Français");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);

        call=service.Patient(tokenManager.getToken().getToken());
        call.enqueue(new Callback<Patiente>() {
            @Override
            public void onResponse(Call<Patiente> call, Response<Patiente> response) {
                if (response.code()==200){

                    patient_number.setText(response.body().getTelephone_number());
                    patient_number_edit.setText(response.body().getTelephone_number());


                }else if (response.code()==215){
                    Toasty.success(getApplicationContext(),"token required",Toasty.LENGTH_LONG).show();
                }else if(response.code()==400){
                    Toasty.warning(getApplicationContext(),"Vouillez verifir votre matricule.",Toasty.LENGTH_LONG).show();
                }else {
                    Toasty.success(getApplicationContext(),"une autre erreur",Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Patiente> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.lng)
    public void submit(Button lng){

                if (Objects.equals(preferences.getString("language", null), "ar_AR")){
                    editor.putString("language","fr_FR");
                    editor.apply();
                    locale = new Locale("fr", "FR");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = locale;
                    res.updateConfiguration(conf, dm);
                    lng.setText("Français");
                    Intent refresh = new Intent(Settings.this, Settings.class);
                    startActivity(refresh);
                    finish();
                    Toasty.info(getApplicationContext(),"changer en Francais",Toast.LENGTH_LONG).show();
                }else {
                    editor.putString("language","ar_AR");
                    editor.apply();
                    locale = new Locale("ar", "AR");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = locale;
                    res.updateConfiguration(conf, dm);
                    lng.setText("عربي");
                    Intent refresh = new Intent(Settings.this, Settings.class);
                    startActivity(refresh);
                    finish();
                    Toasty.info(getApplicationContext(),"تغيير إلى اللغة العربية",Toast.LENGTH_LONG).show();
                }
    }

    @OnClick(R.id.patient_number)
    public void edit(TextView patient_number){

        ctLayout.setVisibility(View.VISIBLE);
        patient_number.setVisibility(View.GONE);

    }

    @OnClick(R.id.save)
    public void save(View view){

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);

        callPhoneNumber=service.editPhone(tokenManager.getToken().getToken(),patient_number_edit.getText().toString());

        callPhoneNumber.enqueue(new Callback<Patiente>() {
            @Override
            public void onResponse(Call<Patiente> call, Response<Patiente> response) {
                if (response.code()==200){

                    ctLayout.setVisibility(View.GONE);
                    patient_number.setText(response.body().getTelephone_number());
                    patient_number.setVisibility(View.VISIBLE);

                }else if (response.code()==215){
                    Toasty.success(getApplicationContext(),"token required",Toasty.LENGTH_LONG).show();
                }else if(response.code()==400){
                    Toasty.warning(getApplicationContext(),"Vouillez verifir votre matricule.",Toasty.LENGTH_LONG).show();
                }else {
                    Toasty.success(getApplicationContext(),"une autre erreur",Toasty.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Patiente> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.rappel)
    public void click(){

        Intent intent = new Intent(Settings.this, AlarmMe.class);
        startActivity(intent);
    }

}
