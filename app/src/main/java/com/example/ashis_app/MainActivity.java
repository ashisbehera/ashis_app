package com.example.ashis_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.*;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private static final String TAG = "MainActivity";
    private CustomAdapter adapter;
    private ArrayList<Height> list_item;
    private ImageView arrow_img;
    private LinearLayout linearLayout;
    private TextView outputTxt , showTxt;
    private Button button;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayoutManager linearLayoutManager;
    public float firstItemWidth;
    public float padding;
    public float itemWidth;
    public int totalPixelMoved;
    public int finalWidth;
    private int selectedHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Scale_app");
        arrow_img = findViewById(R.id.arrow);
        linearLayout = findViewById(R.id.list_ll);
        outputTxt = findViewById(R.id.outputTxt_v);
        button = findViewById(R.id.select);
        showTxt = findViewById(R.id.show);
        list_item = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        for (int i = 0; i < 300; i++) {
            Height height = new Height();
            if (i == 0 || i == 299) {
                height.setType(CustomAdapter.VIEW_TYPE_PADDING);
            } else {
                height.setType(CustomAdapter.VIEW_TYPE_ITEM);
            }
            height.setCm(Integer.toString(i));
            list_item.add(height);
        }

        scroll();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedHeight<=290)
                showTxt.setText("The selected Height is : "+selectedHeight);
            }
        });



    }



    public void scroll() {

        ViewTreeObserver viewTreeObserver = recyclerView.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {


            @Override
            public boolean onPreDraw() {
                recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                finalWidth = recyclerView.getMeasuredWidth();
                itemWidth = getResources().getDimension(R.dimen.item_width);
                padding = (finalWidth - itemWidth) / 2;
                firstItemWidth = padding;
                totalPixelMoved = 0;


                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        synchronized (this) {
                            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                calculatePos(recyclerView);
                            }
                        }

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        totalPixelMoved += dx;
                    }
                });


                recyclerView.setAdapter(createAdapter((int) firstItemWidth));
                return true;
            }
        });
    }

    /**
     * crate new Adapter
     * @param data
     * @return
     */
    private CustomAdapter createAdapter(int data){
        return adapter = new CustomAdapter
                (this , this  ,list_item , data);
    }

    /**
     * calculate the center pos
     * @param recyclerView
     */
    private void calculatePos(RecyclerView recyclerView) {
        int centerPos = Math.round((totalPixelMoved + padding - firstItemWidth) / itemWidth);

        if (centerPos == -1) {
            centerPos = 0;
        } else if (centerPos >= recyclerView.getAdapter().getItemCount() - 2) {
            centerPos--;
        }
        float curPos = centerPos * itemWidth + firstItemWidth - padding;
        float missingPxl = curPos - totalPixelMoved;
        if (missingPxl != 0) {
            recyclerView.smoothScrollBy((int) missingPxl, 0);
        }
        int finalPos = Math.round((totalPixelMoved + padding - firstItemWidth) / itemWidth);
        finalPos+=1;
        adapter.setCenter_item(finalPos);
        if(finalPos<=290){
            outputTxt.setText(String.valueOf(finalPos) + "cm");
            selectedHeight = finalPos;
        }
    }


    /**
     * clickListener for recycleView
     * @param position
     * @param selected
     */
    @Override
    public void onclick(int position , int selected) {

        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int lstVisiblePosition = linearLayoutManager.findLastVisibleItemPosition();
        int fastVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        if(position<selected){
            int dis = Math.abs(selected - position);
            int newpos = fastVisiblePosition-dis;
            recyclerView.smoothScrollToPosition(newpos);
        }else{
            int dis = Math.abs(selected - position);
            int newpos = lstVisiblePosition+dis;
            recyclerView.smoothScrollToPosition(newpos);
        }

    }

}