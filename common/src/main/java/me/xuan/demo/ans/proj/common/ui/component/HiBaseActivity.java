package me.xuan.demo.ans.proj.common.ui.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import me.xuan.demo.ans.proj.common.ui.component.HiBaseActionInterface;

public class HiBaseActivity extends AppCompatActivity implements HiBaseActionInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}