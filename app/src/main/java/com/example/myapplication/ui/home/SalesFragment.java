package com.example.myapplication.ui.home;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class SalesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_sales, container, false);
        // 获取WebView对象
        WebView webView1 = root.findViewById(R.id.webview1);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient() {
        });
        webView1.loadUrl("file:///android_asset/echarts1.html", null); // 加载echarts.html文件
        // 获取WebView对象
        WebView webView = root.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
        });
        webView.loadUrl("file:///android_asset/echarts.html", null); // 加载echarts.html文件
        return root;
    }
}
