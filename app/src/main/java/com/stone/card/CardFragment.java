package com.stone.card;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.stone.card.CardSlidePanel.CardSwitchListener;

/**
 * 卡片Fragment
 *
 * @author susyimes
 */
@SuppressLint({"HandlerLeak", "NewApi", "InflateParams"})
public class CardFragment extends Fragment {
    private Context context;
    private boolean bool = false;
    private ImageView imgView;
    private CardSwitchListener cardSwitchListener;


    private String imagePaths[]={"assets://front1.jpg","assets://front1.jpg","assets://front1.jpg","assets://front1.jpg","assets://front1.jpg","assets://front1.jpg"};

    private String names[]={"01","02","03","04","05","06"};

    private List<CardDataItem> dataList = new ArrayList<CardDataItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.card_layout, null);
        initView(rootView);

        return rootView;

    }



    private void initView(View rootView) {
        CardSlidePanel slidePanel = (CardSlidePanel) rootView
                .findViewById(R.id.image_slide_panel);
        imgView = (ImageView) rootView.findViewById(R.id.imgView);

        cardSwitchListener = new CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.d("CardFragment", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("CardFragment", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
            }

            @Override
            public void onItemClick(View cardView, int index) {
                Log.d("CardFragment", "卡片点击-" + dataList.get(index).userName);


            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);

        prepareDataList();
        slidePanel.fillData(dataList);
    }

    private void prepareDataList() {
        int num = imagePaths.length;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < num; i++) {
                CardDataItem dataItem = new CardDataItem();
                dataItem.userName = names[i];
                dataItem.imagePath = imagePaths[i];
                dataItem.likeNum = (int) (Math.random() * 10);
                dataItem.imageNum = (int) (Math.random() * 6);
                dataList.add(dataItem);
            }
        }
    }

}
