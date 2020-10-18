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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.Rendez_vous;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

public class Profile extends AppCompatActivity {

    WebService service;
    Call<Patiente> call;

    PopupMenu popup;

    @BindView(R.id.patient_name)
    TextView patient_name;

    @BindView(R.id.doctor_name)
    TextView doctor_name;

    @BindView(R.id.date_rdv)
    TextView date_rdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);
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

                    patient_name.setText(new StringBuilder().append("Bonjour ").append(response.body().getFirst_last_name()).toString());
                    doctor_name.setText(new StringBuilder().append("Dr ").append(response.body().getMedecin().getUser().getNom_prenom()).toString());

                    int item=response.body().getRedez_vous().length;
                    Rendez_vous[] rdv=response.body().getRedez_vous();
                    date_rdv.setText(rdv[item-1].getRendez_vous_date());

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

    @OnClick(R.id.menu)
    public void submit(View view) {
        popup = new PopupMenu(getApplicationContext(), view);
        MenuInflater inflater = popup.getMenuInflater();

        inflater.inflate(R.menu.menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.exit) {
                    finishAffinity();
                    System.exit(0);
                }
                if (id==R.id.settings){

                    startActivity(new Intent(Profile.this,Settings.class));

                }
                return true;
            }
        });

        popup.show();
    }
}
