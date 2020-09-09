package com.example.googlephasetwo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.googlephasetwo.fragments.LearningLeaders;
import com.example.googlephasetwo.fragments.SkillLeaders;

import java.util.HashMap;
import java.util.Map;


public class FragmentAdapter extends FragmentPagerAdapter {
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;
    private Context mContext;

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public FragmentAdapter(FragmentManager fragmentManager, Context context){
        super(fragmentManager);

        mFragmentManager = fragmentManager;
        mFragmentTags = new HashMap<Integer, String>();
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LearningLeaders();
            case 1:
                return new SkillLeaders();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        Object object = super.instantiateItem(container, position);
        if (object instanceof Fragment){
            Fragment fragment = (Fragment) object;
            String tag = fragment.getTag();
            mFragmentTags.put(position, tag);
        }

        return object;
    }

    public Fragment getFragment(int position){
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }



}
