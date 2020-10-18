package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medjay.suivigrossesse.Models.Activite_sportiv;

public class SportDetail extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.content)
    TextView content;

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

        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*String nom= getIntent().getExtras().getString("nom");
        String desc= getIntent().getExtras().getString("desc");
        String featr= getIntent().getExtras().getString("image");
        String id= getIntent().getExtras().getString("id");*/

        Activite_sportiv sportiv=(Activite_sportiv) getIntent().getExtras().getSerializable("obj");

        name.setText(sportiv.getNom());
        content.setText(sportiv.getDescription());

        Glide.with(this)
                .load(sportiv.getImage())
                .centerCrop()
                .into(image);

    }

    @OnClick(R.id.feed)
    public void submit(View view){

        Activite_sportiv sportiv=(Activite_sportiv) getIntent().getExtras().getSerializable("obj");

        Bundle bundle = new Bundle();
        bundle.putString("id",sportiv.getId()+"");
        bundle.putString("action",sportiv.getNom());

        Intent intent = new Intent(getApplicationContext(), SportFeedBack.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
