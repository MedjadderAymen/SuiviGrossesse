package com.medjay.suivigrossesse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.medjay.suivigrossesse.Adapters.ConsAdapter;
import com.medjay.suivigrossesse.Adapters.OrdonanncesAdapter;
import com.medjay.suivigrossesse.DetailConseil;
import com.medjay.suivigrossesse.Models.Conseil;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;
import com.medjay.suivigrossesse.R;

import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class MedConseils extends Fragment {

    WebService service;
    Call<List<Conseil>> call;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_med_conseils, container, false);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(context.getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getConseils(tokenManager.getToken().getToken());

        call.enqueue(new Callback<List<Conseil>>() {
            @Override
            public void onResponse(Call<List<Conseil>> call, Response<List<Conseil>> response) {

                assert response.body() != null;

                ConsAdapter adapter=new ConsAdapter(getActivity(),R.layout.cons_layout, response.body());
                ListView lv = (ListView) view.findViewById(R.id.lv);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Call<String> call=service.setConseils(response.body().get(i).getId()+"");

                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> rep) {

                                if (rep.code()==200){

                                    Bundle bundle=new Bundle();
                                    bundle.putSerializable("obj",response.body().get(i));

                                    getActivity().startActivity(new Intent(getActivity(), DetailConseil.class).putExtras(bundle));

                                }

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                                Toasty.error(context, Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();

                            }
                        });

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Conseil>> call, Throwable t) {

                Toasty.error(context, Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
