package com.sahilasopa.memoryenhancer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sahilasopa.memoryenhancer.Adapters.VpAdapter;
import com.sahilasopa.memoryenhancer.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reference;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        reference = FirebaseDatabase.getInstance().getReference();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ViewPager viewPager = binding.viewPager;
        TabLayout tabLayout = binding.tabLayout;
        tabLayout.setupWithViewPager(viewPager);
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HomeFragment(), "EXERCISES");
        vpAdapter.addFragment(new SummaryFragment(), "SUMMARY");
        viewPager.setAdapter(vpAdapter);
    }

}