package com.example.administrator.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


@Route(path = ActivityUrlConstant.MainActivity)
public class MainActivity extends AppCompatActivity {

    RecyclerView recycleViewFenLei;

    SeekBar seekBar;

    CommentAdapter<String> commentAdapterFenLei;
    List<String> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ARouter.getInstance().inject(this);

        recycleViewFenLei = findViewById(R.id.recycleViewFenLei);
        seekBar = findViewById(R.id.seekBar);
        DisPlayUtils.init(this);

        for (int i = 0; i < 20; i++) {
            mDataList.add("哈哈哈" + i);
        }

        initAdapterFenLei();
        //==============分类=======================
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2, LinearLayoutManager.HORIZONTAL, false);
        recycleViewFenLei.setLayoutManager(gridLayoutManager);
        recycleViewFenLei.setAdapter(commentAdapterFenLei);

        //设置导航条
        seekBar.setPadding(0, 0, 0, 0);
        seekBar.setThumbOffset(0);
        recycleViewFenLei.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //显示区域的高度。
                int extent = recycleViewFenLei.computeHorizontalScrollExtent();
                //整体的高度，注意是整体，包括在显示区域之外的。
                int range = recycleViewFenLei.computeHorizontalScrollRange();
                //已经向下滚动的距离，为0时表示已处于顶部。
                int offset = recycleViewFenLei.computeHorizontalScrollOffset();
                Log.i("dx------", range + "****" + extent + "****" + offset);
                //此处获取seekbar的getThumb，就是可以滑动的小的滚动游标
                GradientDrawable gradientDrawable = (GradientDrawable) seekBar.getThumb();
                //根据列表的个数，动态设置游标的大小，设置游标的时候，progress进度的颜色设置为和seekbar的颜色设置的一样的，所以就不显示进度了。
                gradientDrawable.setSize(extent / (commentAdapterFenLei.getData().size() / 2), 5);
                //设置可滚动区域
                seekBar.setMax((int) (range - extent));
                if (dx == 0) {
                    seekBar.setProgress(0);
                } else if (dx > 0) {
//                    int ss = (int)(tt/2.3f);
                    Log.i("dx------", "右滑");
                    seekBar.setProgress(offset);
                } else if (dx < 0) {
                    Log.i("dx------", "左滑");
                    seekBar.setProgress(offset);
                }


            }

        });


    }

    private void initAdapterFenLei() {

        commentAdapterFenLei = new CommentAdapter<String>(R.layout.item_shouye_fenlei, mDataList) {
            @Override
            public void setViewData(BaseViewHolder holder, String item, int position) {
                TextView l = holder.getView(R.id.tvName);
                l.setWidth(DisPlayUtils.getWidthPx() / 5);
                l.setText(item);

            }

            @Override
            public void setEvent(BaseViewHolder holder, String item, int position) {
                holder.getView(R.id.llAll).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build(ActivityUrlConstant.CoordinatorlayoutActivity).navigation();
                    }
                });
            }
        };
    }
}
