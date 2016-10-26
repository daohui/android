package com.vitech.sandbox.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitech.sandbox.R;

public class ScreenSlidePageFragment extends Fragment {
    public static final String ARG_PAGE = "page";

    private int pageNumber;

    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ScreenSlidePageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (pageNumber == 0) {
            return createDrawing(inflater, container);
        }
        return createScreenSlider(inflater, container);
    }

    private View createDrawing(LayoutInflater inflater, ViewGroup container) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_draw, container, false);
        return rootView;
    }

    private View createScreenSlider(LayoutInflater inflater, ViewGroup container) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.viewpager__screen_slider, container, false);

        // Set the title view to show the page number.
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.screenslide__title_template_step, pageNumber + 1));

        return rootView;
    }
}
