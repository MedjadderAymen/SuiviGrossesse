package com.medjay.suivigrossesse.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.medjay.suivigrossesse.Adapters.ViewPagerAdapter;
import com.medjay.suivigrossesse.R;


public class Mesures extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_mesures, container, false);

        tabLayout=view.findViewById(R.id.tabs);
        viewPager=view.findViewById(R.id.container);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new Tenssion(),"Tenssion");
        adapter.addFragment(new Gleycime(),"Gleycime");
        adapter.addFragment(new Poids(),"Poids");
        adapter.addFragment(new Temperature(),"Temperature");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}