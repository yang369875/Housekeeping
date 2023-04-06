package com.example.myapplication.ui.notifications;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNotificationsBinding;
import com.example.myapplication.ui.LoginActivity;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private SharedPreferences sharedPreferences; // SharedPreferences 对象
    private SharedPreferences.Editor editor; // SharedPreferences 编辑器

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // 初始化SharedPreferences和SharedPreferences编辑器
        sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // 找到退出登录按钮
        Button logoutButton = root.findViewById(R.id.btn_logout);

        // 为退出登录按钮添加点击事件监听器
        logoutButton.setOnClickListener(view -> {
            // 从SharedPreferences中删除保存的登录信息
            editor.remove("username");
            editor.apply();

            // 重定向用户至登录页面
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
