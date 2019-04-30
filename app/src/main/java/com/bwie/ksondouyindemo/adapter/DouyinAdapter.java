package com.bwie.ksondouyindemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bwie.ksondouyindemo.R;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class DouyinAdapter  extends RecyclerView.Adapter<DouyinAdapter.ViewHolder> {

   private List<String> videos;
    private int index = 0;
    private Context mContext;

    public DouyinAdapter(Context context, List<String> videos) {
        this.mContext = context;
        this.videos = videos;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.gsyVideoPlayer.setUpLazy(videos.get(index), true, null, null, "这是title");
        //增加title
        holder.gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        holder.gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
        //设置全屏按键功能
        holder.gsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.gsyVideoPlayer.startWindowFullscreen(mContext, false, true);
            }
        });
        holder.gsyVideoPlayer.setPlayPosition(position);
        //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
        holder.gsyVideoPlayer.setAutoFullWithSize(true);
        //音频焦点冲突时是否释放
        holder.gsyVideoPlayer.setReleaseWhenLossAudio(false);
        //全屏动画
        holder.gsyVideoPlayer.setShowFullAnimation(true);
        //小屏时不触摸滑动
        holder.gsyVideoPlayer.setIsTouchWiget(false);

        index++;
        if (index >= videos.size()-1) {
            index = 0;
        }
    }

    @Override
    public int getItemCount() {
        return 88;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_thumb;
        private StandardGSYVideoPlayer gsyVideoPlayer;
        private ImageView img_play;
        private RelativeLayout rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            img_thumb = itemView.findViewById(R.id.img_thumb);
            gsyVideoPlayer = itemView.findViewById(R.id.detail_player);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
        }
    }
}