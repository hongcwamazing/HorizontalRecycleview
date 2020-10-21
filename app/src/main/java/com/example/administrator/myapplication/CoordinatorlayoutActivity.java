package com.example.administrator.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

@Route(path = ActivityUrlConstant.CoordinatorlayoutActivity)
public class CoordinatorlayoutActivity extends AppCompatActivity {

    List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);

        ARouter.getInstance().inject(this);

        for (int i = 0; i < 20; i++) {
            mDataList.add("哈哈哈" + i);
        }
        initAdapterFenLei();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapterFenLei);
    }

    CommentAdapter<String> commentAdapterFenLei;

    private void initAdapterFenLei() {

        commentAdapterFenLei = new CommentAdapter<String>(R.layout.item_shouye_fenlei, mDataList) {
            @Override
            public void setViewData(BaseViewHolder holder, String item, int position) {
                TextView l = holder.getView(R.id.tvName);
                l.setText(item);
            }

            @Override
            public void setEvent(BaseViewHolder holder, String item, int position) {

            }
        };
    }

}
