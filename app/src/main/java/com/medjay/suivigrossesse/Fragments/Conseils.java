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


public class Conseils extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    View RootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RootView= inflater.inflate(R.layout.fragment_conseils, container, false);

        tabLayout=RootView.findViewById(R.id.tabs);
        viewPager=RootView.findViewById(R.id.container);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new MedConseils(),"Conseils");
        adapter.addFragment(new GenConseils(),"Generale");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return RootView;
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
