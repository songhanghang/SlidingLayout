package com.facefont.songhang.slidinglayout.slidingview;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.facefont.songhang.slidinglayout.R;

public class HalfPlayCardFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private SlidingLayout slidingLayout;
    private Button closeBtn;

    public static HalfPlayCardFragment newInstance(String param1, String param2) {
        HalfPlayCardFragment fragment = new HalfPlayCardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public HalfPlayCardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.half_play_card_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slidingLayout = (SlidingLayout) view.findViewById(R.id.slidingview);

        closeBtn = (Button) view.findViewById(R.id.half_play_card_close_btn);
        closeBtn.setOnClickListener(this);

    }

    /**
     * 得到List容器
     *
     * @return
     */
    public SlidingListView generateContainerListView() {
        SlidingListView view = new SlidingListView(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        view.setDivider(null);
        view.setFadingEdgeLength(0);
        view.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        bindSlidingLayout(view);
        return view;
    }

    /**
     * 得到Grid容器
     *
     * @return
     */
    public SlidingGridView generateContainerGridView() {
        SlidingGridView view = new SlidingGridView(getContext());
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        view.setNumColumns(3);
        view.setSelector(new ColorDrawable(Color.TRANSPARENT));
        view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        view.setClipChildren(false);
        view.setClipToPadding(false);
        view.setVerticalScrollBarEnabled(false);
        view.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        bindSlidingLayout(view);
        return view;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.half_play_card_close_btn:
                closeSlidingLayout();
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    public void openListSlidingLayout() {
        SlidingListView listView = generateContainerListView();
        listView.setOnBorderListener(slidingLayout);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1 );
        arrayAdapter.add("我是第1行");
        arrayAdapter.add("我是第2行");
        arrayAdapter.add("我是第3行");
        arrayAdapter.add("我是第4行");
        arrayAdapter.add("我是第5行");
        arrayAdapter.add("我是第6行");
        arrayAdapter.add("我是第7行");
        arrayAdapter.add("我是第8行");
        arrayAdapter.add("我是第9行");
        arrayAdapter.add("我是第10行");
        arrayAdapter.add("我是第11行");
        arrayAdapter.add("我是第12行");
        arrayAdapter.add("我是第13行");
        arrayAdapter.add("我是第14行");
        arrayAdapter.add("我是第15行");
        arrayAdapter.add("我是第16行");
        arrayAdapter.add("我是第17行");
        listView.setAdapter(arrayAdapter);
        slidingLayout.setContentView(listView);
        slidingLayout.openFormOrigin();
    }

    public void openGridSlidingLayout() {
        SlidingGridView gridView = generateContainerGridView();
        gridView.setOnBorderListener(slidingLayout);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1 );
        arrayAdapter.add("我是第1行");
        arrayAdapter.add("我是第2行");
        arrayAdapter.add("我是第3行");
        arrayAdapter.add("我是第4行");
        arrayAdapter.add("我是第5行");
        arrayAdapter.add("我是第6行");
        arrayAdapter.add("我是第7行");
        arrayAdapter.add("我是第8行");
        arrayAdapter.add("我是第9行");
        arrayAdapter.add("我是第10行");
        arrayAdapter.add("我是第11行");
        arrayAdapter.add("我是第12行");
        arrayAdapter.add("我是第13行");
        arrayAdapter.add("我是第14行");
        arrayAdapter.add("我是第15行");
        arrayAdapter.add("我是第16行");
        arrayAdapter.add("我是第17行");
        arrayAdapter.add("我是第18行");
        arrayAdapter.add("我是第19行");
        arrayAdapter.add("我是第20行");
        arrayAdapter.add("我是第21行");
        arrayAdapter.add("我是第22行");
        arrayAdapter.add("我是第23行");
        arrayAdapter.add("我是第24行");
        arrayAdapter.add("我是第25行");
        arrayAdapter.add("我是第26行");
        arrayAdapter.add("我是第27行");
        arrayAdapter.add("我是第28行");
        arrayAdapter.add("我是第29行");
        gridView.setAdapter(arrayAdapter);
        slidingLayout.setContentView(gridView);
        slidingLayout.openFormOrigin();
    }

    public void closeSlidingLayout() {
        slidingLayout.closeFromOrigin();
    }

}
