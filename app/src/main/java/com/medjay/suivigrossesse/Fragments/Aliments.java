package com.medjay.suivigrossesse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medjay.suivigrossesse.Adapters.AlimentsDays_Adapter;
import com.medjay.suivigrossesse.Adapters.Days_Adapter;
import com.medjay.suivigrossesse.Exercices;
import com.medjay.suivigrossesse.Menu;
import com.medjay.suivigrossesse.Models.Journee;
import com.medjay.suivigrossesse.Models.JourneeAlimentaire;
import com.medjay.suivigrossesse.Models.PlanSportif;
import com.medjay.suivigrossesse.Models.ProgrammeAlimentaire;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;
import com.medjay.suivigrossesse.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class Aliments extends Fragment {

    WebService service;
    Call<List<ProgrammeAlimentaire>> call;

    List<JourneeAlimentaire> journees =new ArrayList<>();

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_aliments, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(context.getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getProgrammesAliments(tokenManager.getToken().getToken());

        call.enqueue(new Callback<List<ProgrammeAlimentaire>>() {
            @Override
            public void onResponse(Call<List<ProgrammeAlimentaire>> call, Response<List<ProgrammeAlimentaire>> response) {
                if (response.code()==200){

                    for (ProgrammeAlimentaire plAlimentaire: response.body()) {
                        journees.addAll(plAlimentaire.getJournee_alimentaire());
                    }

                    AlimentsDays_Adapter adapter=new AlimentsDays_Adapter(getActivity(),R.layout.days_layout, journees);
                    ListView lv = (ListView) view.findViewById(R.id.lv);
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent exercices = new Intent(getActivity(), Menu.class);
                            exercices.putExtra("id", journees.get(i).getId()+"");
                            startActivity(exercices);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<List<ProgrammeAlimentaire>> call, Throwable t) {
                Toasty.error(context, Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();
            }
        });

    }

}
