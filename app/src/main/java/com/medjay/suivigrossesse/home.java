package com.medjay.suivigrossesse;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.flarebit.flarebarlib.FlareBar;
import com.flarebit.flarebarlib.Flaretab;
import com.flarebit.flarebarlib.TabEventObject;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.medjay.suivigrossesse.Fragments.Aliments;
import com.medjay.suivigrossesse.Fragments.Conseils;
import com.medjay.suivigrossesse.Fragments.Mesures;
import com.medjay.suivigrossesse.Fragments.Orodoannce;
import com.medjay.suivigrossesse.Fragments.Sports;
import com.medjay.suivigrossesse.Models.Ordonnance;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    @BindView(R.id.space)
    SpaceNavigationView space;

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

        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        space.initWithSaveInstanceState(savedInstanceState);
        space.addSpaceItem(new SpaceItem("settings", R.drawable.ic_assignment_black_24dp));
        space.addSpaceItem(new SpaceItem("shopping", R.drawable.ic_restaurant_menu_black_24dp));
        space.addSpaceItem(new SpaceItem("my chart", R.drawable.ic_fitness_center_black_24dp));
        space.addSpaceItem(new SpaceItem("profile", R.drawable.ic_local_hospital_black_24dp));

        /*space.changeCurrentItem(1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Conseils()).commit();*/
        space.showIconOnly();

        space.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Mesures()).commit();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Conseils()).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Aliments()).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Sports()).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new Orodoannce()).commit();
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                //Toast.makeText(Home.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick(R.id.profile)
    public void submit(View view){
        startActivity(new Intent(this,Profile.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        space.onSaveInstanceState(outState);
    }

}