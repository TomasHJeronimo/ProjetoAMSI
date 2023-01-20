package com.example.huntingjobs.Vistas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.huntingjobs.R;


public class SobreNosFragment extends Fragment {


    private Button learn_more;
    private ImageView imgAbout;

    public SobreNosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sobre_nos, container, false);

        learn_more = view.findViewById(R.id.btn_learn_more);
        imgAbout = view.findViewById(R.id.ivAboutus);

        Glide.with(getContext())
                .load("https://bootstrapious.com/i/snippets/sn-about/img-1.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgAbout);

        learn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://10.0.2.2/HuntingJobs/frontend/web/site/about"; // Replace with your website URL
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        return view;
    }
}