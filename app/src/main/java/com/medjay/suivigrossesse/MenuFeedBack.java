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

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;

public class MenuFeedBack extends AppCompatActivity {

    @BindView(R.id.comment)
    TextInputEditText comment;

    WebService service;
    Call<String> call;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    String reaction="Difficile";

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

        setContentView(R.layout.activity_menu_feed_back);

        ButterKnife.bind(this);

        spinner.setItems("Difficile", "Moyen", "Facile");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                //Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                reaction=item;

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        comment.setText(getIntent().getExtras().getString("action")+" ");

    }

    @OnClick(R.id.feed)
    public void submit(View view){

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(getApplicationContext().getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.setFeedBackRegime(tokenManager.getToken().getToken(),comment.getText().toString(),reaction);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code()==200){
                    Toasty.success(getApplicationContext(),"success",Toasty.LENGTH_LONG).show();
                    comment.setText(getIntent().getExtras().getString("action")+" ");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toasty.error(getApplicationContext(),t.getMessage()+"",Toasty.LENGTH_LONG).show();
            }
        });
    }
}
