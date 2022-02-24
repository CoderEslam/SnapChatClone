package com.doubleclick.snapchatclone;


import android.content.Context;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.doubleclick.snapchatclone.fragment.CameraFragment;
import com.doubleclick.snapchatclone.fragment.ChatFragment;
import com.doubleclick.snapchatclone.fragment.StoryFragment;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {

//    FragmentPagerAdapter adapterViewPager;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        UserInformation userInformationListener = new UserInformation();
//        userInformationListener.startFetching();
//
//        ViewPager viewPager = findViewById(R.id.viewPager);
//
//        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapterViewPager);
//        viewPager.setCurrentItem(1);
//
//    }
//
//    public static class MyPagerAdapter extends FragmentPagerAdapter{
//
//        public MyPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch(position){
//                case 0:
//                    return ChatFragment.newInstance();
//                case 1:
//                    return CameraFragment.newInstance();
//                case 2:
//                    return StoryFragment.newInstance();
//            }
//            return null;
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }

    FragmentPagerAdapter adapterViewPager;

    ImageButton fpaCamera, fpaChat, fpaPeople;

    ViewPager viewPager;

    public static ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       UserInformation userInformationListener = new UserInformation();
        userInformationListener.startFetching();

        fpaCamera = findViewById(R.id.imageButtonCamera);
        fpaChat = findViewById(R.id.imageButtonChat);
        fpaPeople = findViewById(R.id.imageButtonPeople);

        viewPager = findViewById(R.id.viewPager);
//        adapterViewPager = new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @NonNull
//            @NotNull
//            @Override
//            // to determinde what is fragment to go
//            public Fragment getItem(int position) {
//                return null;
//            }
//
//            @Override
//            // return the count of fragment.
//            public int getCount() {
//                return 0;
//            }
//        };

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);

        bar = findViewById(R.id.loadingMain);

        //https://developer.android.com/reference/android/view/inputmethod/InputMethodManager
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);

        fpaCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() == 1) {
                    CameraFragment page = (CameraFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + viewPager.getCurrentItem());
                    if (page != null) {
                        page.captureImage();
                    }
                } else {
                    viewPager.setCurrentItem(1);
                }
            }
        });

        fpaChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });

        fpaPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });

        viewPager.setCurrentItem(1);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ChatFragment.newInstance();
                case 1:
                    return CameraFragment.newInstance();
                case 2:
                    return StoryFragment.newInstance();
            }
            return null;
        }

        @Override
        // to get Count all fragment
        public int getCount() {
            return 3;
        }
    }

    @Override
    //to check permissions Requested.
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
