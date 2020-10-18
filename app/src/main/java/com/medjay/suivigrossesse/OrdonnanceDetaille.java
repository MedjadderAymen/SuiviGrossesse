package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import com.medjay.suivigrossesse.Adapters.MedicGrid;
import com.medjay.suivigrossesse.Models.Activite_sportiv;
import com.medjay.suivigrossesse.Models.Ordonnance;

public class OrdonnanceDetaille extends AppCompatActivity {

    @BindView(R.id.grideOrd)
    GridView grideOrd;

    @BindView(R.id.tv1)
    TextView tv1;

    @BindView(R.id.tv2)
    TextView tv2;

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

        setContentView(R.layout.activity_ordonnance_detaille);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Ordonnance ordonnance=(Ordonnance) getIntent().getExtras().getSerializable("obj");

        assert ordonnance != null;
        tv2.setText(ordonnance.getCreated_at().substring(0,10));
        tv1.setText("Num : "+ getIntent().getExtras().getInt("num")+1);

        grideOrd.setAdapter(new MedicGrid(OrdonnanceDetaille.this,getApplicationContext(),ordonnance.getMedicament()));

    }
}
