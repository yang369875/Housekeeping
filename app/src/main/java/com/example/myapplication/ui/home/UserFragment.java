package com.example.myapplication.ui.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
public class UserFragment extends Fragment implements View.OnClickListener{
    private LinearLayout userContainer;
    private List<User> userList = new ArrayList<>();
    private int userCount = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);
        userContainer = root.findViewById(R.id.user_container);
        Button addUserButton = root.findViewById(R.id.add_user_button);
        addUserButton.setOnClickListener(this);

        //添加默认数据
        User user1 = new User(1, "张阿姨", 43, 12, "育护婴幼", "98%");
        User user2 = new User(2, "李阿姨", 38, 8, "家务清洁", "95%");
        User user3 = new User(3, "林阿姨", 56, 22, "烹饪美食", "99%");
        User user4 = new User(4, "陈大爷", 61, 26, "养花园艺", "90%");
        User user5 = new User(5, "王奶奶", 75, 15, "老人陪护", "92%");
        User user6 = new User(6, "赵阿姨", 48, 18, "照顾小孩", "97%");
        User user7 = new User(7, "孙大爷", 68, 30, "照顾宠物", "85%");
        User user8 = new User(8, "钱阿姨", 51, 14, "保姆月嫂", "96%");
        User user9 = new User(9, "周爷爷", 84, 35, "老年护理", "91%");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);
        userList.add(user9);
        refreshUserList();

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_user_button:
                addUserDialog();
                break;
        }
    }

    private void addUserDialog() {
        //使用AlertDialog.Builder来创建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加用户信息");

        //用一个布局来放置对话框中的控件
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.add_user_dialog, null);
        builder.setView(dialogView);

        //获取对话框中的控件，准备获取用户填写的信息
        final EditText nameEditText = dialogView.findViewById(R.id.name_edittext);
        final EditText ageEditText = dialogView.findViewById(R.id.age_edittext);
        final EditText serviceYearEditText = dialogView.findViewById(R.id.service_year_edittext);
        final EditText skillEditText = dialogView.findViewById(R.id.skill_edittext);
        final EditText remarkEditText = dialogView.findViewById(R.id.remark_edittext);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //当用户点击确定时，获取用户填写的信息，并添加新用户到列表中
                userCount++;
                User newUser = new User(userCount,
                        nameEditText.getText().toString(),
                        Integer.parseInt(ageEditText.getText().toString()),
                        Integer.parseInt(serviceYearEditText.getText().toString()),
                        skillEditText.getText().toString(),
                        remarkEditText.getText().toString());
                userList.add(newUser);
                refreshUserList();
                Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();
    }

    private void refreshUserList() {
        userContainer.removeAllViews();
        for (User user : userList) {
            View userView = LayoutInflater.from(getActivity()).inflate(R.layout.user_item, null);
            ((TextView) userView.findViewById(R.id.user_id_textview)).setText(String.valueOf(user.getId()));
            ((TextView) userView.findViewById(R.id.user_name_textview)).setText(user.getName());
            ((TextView) userView.findViewById(R.id.user_age_textview)).setText(String.valueOf(user.getAge()));
            ((TextView) userView.findViewById(R.id.user_service_year_textview)).setText(String.valueOf(user.getServiceYear()));
            ((TextView) userView.findViewById(R.id.user_skill_textview)).setText(user.getSkill());
            ((TextView) userView.findViewById(R.id.user_remark_textview)).setText(user.getRemark());
            userContainer.addView(userView);
        }
    }

    private static class User {
        private int id;
        private String name;
        private int age;
        private int serviceYear;
        private String skill;
        private String remark;

        public User(int id, String name, int age, int serviceYear, String skill, String remark) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.serviceYear = serviceYear;
            this.skill = skill;
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public int getServiceYear() {
            return serviceYear;
        }

        public String getSkill() {
            return skill;
        }

        public String getRemark() {
            return remark;
        }
    }
}
