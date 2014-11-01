package com.mzule.swipe.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import com.mzule.swipe.R;
import com.mzule.swipe.SwipeRefreshLayout;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, SwipeRefreshLayout.OnScrollListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setProgressBarHeight(dipToPixels(2));
        swipeRefreshLayout.setRefreshTriggerDistance(600);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_red_light, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setOnScrollListener(this);

        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    public int dipToPixels(float dipValue) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    @Override
    public void onTriggerPercentChanged(float percent) {
        int p = (int) (percent * 100);
        textView.setText(p + "%");
    }

    @Override
    public void onContentOffsetChanged(int targetTop) {
        textView.setText(targetTop + "");
    }
}
