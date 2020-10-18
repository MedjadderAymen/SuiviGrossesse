package com.medjay.suivigrossesse.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.medjay.suivigrossesse.MesuresActivity;
import com.medjay.suivigrossesse.Models.Donnee;
import com.medjay.suivigrossesse.Models.Patiente;
import com.medjay.suivigrossesse.Models.TokenManager;
import com.medjay.suivigrossesse.Network.RetrofitBuilder;
import com.medjay.suivigrossesse.Network.WebService;
import com.medjay.suivigrossesse.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class Gleycime extends Fragment {

    WebService service;
    Call<List<Donnee>> call;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gleycime, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        TokenManager tokenManager;
        tokenManager=TokenManager.getInstance(context.getSharedPreferences("prefs",MODE_PRIVATE));

        service= RetrofitBuilder.getRetrofitInstance().create(WebService.class);
        call=service.getMesure(tokenManager.getToken().getToken(),"Glycémie");

        call.enqueue(new Callback<List<Donnee>>() {
            @Override
            public void onResponse(Call<List<Donnee>> call, Response<List<Donnee>> response) {
                if(response.code()==200){

                    LineChart chart = (LineChart) view.findViewById(R.id.chart);

                    LimitLine upper_limit_ajeun=new LimitLine(1.26f,"hyper-glycécmie à jeun");
                    upper_limit_ajeun.setLineWidth(4f);
                    upper_limit_ajeun.enableDashedLine(10f,10f,0f);
                    upper_limit_ajeun.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
                    upper_limit_ajeun.setTextSize(15f);

                    LimitLine lower_limit_ajeun=new LimitLine(0.92f,"hypo-glycémie à jeun");
                    lower_limit_ajeun.setLineWidth(4f);
                    lower_limit_ajeun.enableDashedLine(10f,10f,0f);
                    lower_limit_ajeun.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
                    lower_limit_ajeun.setTextSize(15f);

                    YAxis leftAxis=chart.getAxisLeft();
                    leftAxis.removeAllLimitLines();
                    leftAxis.addLimitLine(upper_limit_ajeun);
                    leftAxis.addLimitLine(lower_limit_ajeun);
                    leftAxis.setAxisMinimum(2);
                    leftAxis.setAxisMinimum(0);
                    leftAxis.enableGridDashedLine(10f,10f,0f);
                    leftAxis.enableGridDashedLine(10f,10f,0f);
                    leftAxis.setDrawLimitLinesBehindData(true);

                    chart.getAxisRight().setEnabled(false);

                    List<Entry> entries = new ArrayList<Entry>();
                    List<Donnee> data = new ArrayList<Donnee>();

                    for (int i=0;i<response.body().size();i++) {
                        // turn your data into Entry objects
                        entries.add(new Entry(i,response.body().get(i).getValeur()));
                        data.add(response.body().get(i));
                    }



                    LineDataSet dataSet = new LineDataSet(entries, "Glycémie"); // add entries to dataset
                    dataSet.setColor(R.color.colorPrimaryDark);
                    dataSet.setValueTextColor(R.color.colorAccent);

                    LineData lineData = new LineData(dataSet);
                    chart.setData(lineData);
                    chart.invalidate(); // refresh


                    chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                        @Override
                        public void onValueSelected(Entry e, Highlight h) {


                            Toasty.success(getActivity(), data.get((int)e.getX()).getCreated_at()+"",Toasty.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Donnee>> call, Throwable t) {
                Toasty.error(getActivity(), Objects.requireNonNull(t.getMessage()),Toasty.LENGTH_LONG).show();
            }
        });

    }

    @OnClick(R.id.send)
    public void submit(View view){

        getActivity().startActivity(new Intent(getContext(), MesuresActivity.class));

    }
}
