package com.bwie.ksondouyindemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bwie.ksondouyindemo.adapter.DouyinAdapter;
import com.bwie.ksondouyindemo.adapter.DouyinLayoutManager;
import com.bwie.ksondouyindemo.adapter.OnViewPagerListener;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "douyin";
    private RecyclerView mRecyclerView;
    private DouyinAdapter mAdapter;
    private DouyinLayoutManager douyinLayoutManager;
    private List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initData() {
        urlList = new ArrayList<>();
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201805/100651/201805181532123423.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803131546119319.mp4");

    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler);
        douyinLayoutManager = new DouyinLayoutManager(this, OrientationHelper.VERTICAL, false);
        mAdapter = new DouyinAdapter(this, urlList);
        mRecyclerView.setLayoutManager(douyinLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initListener() {
        douyinLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {

            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                Log.e(TAG, "释放位置:" + position + " 下一页:" + isNext);
                int index = 0;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean bottom) {
                Log.e(TAG, "选择位置:" + position + " 下一页:" + bottom);

                playVideo(0);
            }
        });
    }


    /**
     *释放视频
     * @param index
     */
    private void releaseVideo(int index) {
        View itemView = mRecyclerView.getChildAt(index);
        StandardGSYVideoPlayer standardGSYVideoPlayer = itemView.findViewById(R.id.detail_player);
        standardGSYVideoPlayer.release();
    }


    /**
     * 播放视频
     * @param position
     */
    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(position);
        final StandardGSYVideoPlayer standardGSYVideoPlayer = itemView.findViewById(R.id.detail_player);
        ImageView imgPlay = itemView.findViewById(R.id.img_play);
        ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        standardGSYVideoPlayer.setVideoAllCallBack(new GSYSampleCallBack() {
            @Override
            public void onStartPrepared(String url, Object... objects) {
                super.onStartPrepared(url, objects);
                imgThumb.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                imgThumb.setVisibility(View.GONE);
            }


        });


        standardGSYVideoPlayer.startPlayLogic();


        //播放按钮控制
        imgPlay.setOnClickListener(v -> {



        });
    }
}
