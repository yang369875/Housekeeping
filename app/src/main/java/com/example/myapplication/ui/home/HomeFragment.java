package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);

        // 设置 ViewPager 和 TabLayout 的关联
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                // 根据不同的位置返回不同的 Fragment
                switch (position) {
                    case 0:
                        return new SalesFragment();
                    case 1:
                        return new ServiceFragment();
                    case 2:
                        return new UserFragment();
                    case 3:
                        return new TrainFragment();
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // 返回总共有多少个 TabItem
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // 返回 TabItem 的标题
                switch (position) {
                    case 0:
                        return "销售管理";
                    case 1:
                        return "服务管理";
                    case 2:
                        return "用户管理";
                    case 3:
                        return "培训管理";
                    default:
                        return null;
                }
            }
        });

        // 将 TabLayout 和 ViewPager 绑定在一起
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}