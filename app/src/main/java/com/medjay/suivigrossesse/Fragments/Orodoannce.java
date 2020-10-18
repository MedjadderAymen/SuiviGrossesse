package com.medjay.suivigrossesse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medjay.suivigrossesse.Adapters.AlimentsDays_Adapter;
import com.medjay.suivigrossesse.Adapters.ExercicesRecyclerAdapter;
import com.medjay.suivigrossesse.Adapters.MedicamentsRecyclerAdapter;
import com.medjay.suivigrossesse.Adapters.OrdonanncesAdapter;
import com.medjay.suivigrossesse.Menu;
import com.medjay.suivigrossesse.Models.Activite_sportiv;
import com.medjay.suivigrossesse.Models.Medicament;
import com.medjay.suivigrossesse.Models.Ordonnance;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.ProgrammeAlimentaire;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;
import com.medjay.suivigrossesse.OrdonnanceDetaille;
import com.medjay.suivigrossesse.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class Orodoannce extends Fragment {


    WebService service;
    Call<Patiente> call;

    List<Ordonnance> ordonnances=new ArrayList<>();

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_oroddance, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(context.getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getOrdonnaces(tokenManager.getToken().getToken());

        call.enqueue(new Callback<Patiente>() {
            @Override
            public void onResponse(Call<Patiente> call, Response<Patiente> response) {
                if (response.code()==200){

                    ordonnances=response.body().getOrdonnance();

                    OrdonanncesAdapter adapter=new OrdonanncesAdapter(getActivity(),R.layout.odonnance_layout, ordonnances);
                    ListView lv = (ListView) view.findViewById(R.id.lv);
                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), OrdonnanceDetaille.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("obj",ordonnances.get(i));
                            bundle.putInt("num",i);
                            intent.putExtras(bundle);
                            getActivity().startActivity(intent);
                        }
                    });

                    try {

                        List<Medicament> medicaments=response.body().getOrdonnance().get(0).getMedicament();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                        RecyclerView recyclerView = view.findViewById(R.id.medic);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        MedicamentsRecyclerAdapter medicamentsRecyclerAdapter=new MedicamentsRecyclerAdapter(view,getActivity(),getContext(),medicaments);
                        recyclerView.setAdapter(medicamentsRecyclerAdapter);

                    }catch (Exception e){
                        Log.d("no medications",response.body().getOrdonnance().size()+"");
                    }


                }
            }

            @Override
            public void onFailure(Call<Patiente> call, Throwable t) {
                Toasty.error(context, Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();
            }
        });

    }


}
