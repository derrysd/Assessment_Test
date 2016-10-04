package derry.assessmenttest.views;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;

import derry.assessmenttest.R;
import derry.assessmenttest.utils.RoundedImageView;

public class AboutFragment extends Fragment {
    RoundedImageView photo;
    public AboutFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        WebView webResume = (WebView) view.findViewById(R.id.viewResume);
        String textResume;
        textResume = "<html><body><p align=\"justify\">";
        textResume+=  "A passionate software engineer with specialization in object-oriented with MVP or MVC approaches to Android platform development.</p>" +
                "<p align=\"justify\">Creative, resourceful and flexible, able to adapt to fast paced developments, eager to learn and maintain a positive attitude and strong work ethic.<p>" +
                "<p align=\"justify\">Solid understanding of Android development life cycles, UIX, REST HTTP/JSON/OAUTH, and agile methodologies.</p>" +
                "<p align=\"justify\"> Dedicated to continuously developing, implementing, and adopting new technologies to maximize development efficiency and produce innovative applications.";
        textResume+= "</p></body></html>";
        webResume.loadData(textResume, "text/html", "utf-8");

        WebView webHobby = (WebView) view.findViewById(R.id.viewHobby);
        String textHobby;
        textHobby = "<html><body><p align=\"justify\">";
        textHobby+=  "My hobbies are differs from time to time because i love to learn something new and explore new knowledge.</p>" +
                "<p align=\"justify\">I used to love Capoeira, that martial art from brazil and going to gym, but currently just playing games, reading comics on the internet and doing some random development stuffs";
        textHobby+= "</p></body></html>";
        webHobby.loadData(textHobby, "text/html", "utf-8");
        photo = (RoundedImageView) view.findViewById(R.id.photo);
        return view;
    }

    public void animatePhoto(Context context){
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.photo_anim);
        photo.startAnimation(animation);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        animatePhoto(view.getContext());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }
}
