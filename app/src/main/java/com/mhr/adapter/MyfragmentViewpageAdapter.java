package com.mhr.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyfragmentViewpageAdapter extends FragmentPagerAdapter {
 
	private ArrayList<Fragment> list;
	private ArrayList<String> titles;
	public MyfragmentViewpageAdapter(FragmentManager fm, ArrayList<Fragment> list,ArrayList<String> titles) {
		super(fm);
		this.list=list;
		this.titles = titles;
	}
 
	@Override
	public Fragment getItem(int arg0) {
		return list.get(arg0);
	}
 
	@Override
	public int getCount() {
		return list.size();
	}

	@Nullable
	@Override
	public CharSequence getPageTitle(int position) {
		return titles.get(position);
	}
}
