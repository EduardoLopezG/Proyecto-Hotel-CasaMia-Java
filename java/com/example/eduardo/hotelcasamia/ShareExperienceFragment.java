package com.example.eduardo.hotelcasamia;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class ShareExperienceFragment extends Fragment {

    /* Creating Buttons To Share */
    private ImageButton buttonShare;
    private ImageButton buttonTwitter;
    private ImageButton buttonFacebook;
    private ImageButton buttonWhatssapp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_experience, container, false);
    }


    /* Have to contain the method "onActivityCreated" */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        /* Relation of the buttons with it's elements in the desing file (fragment_share_experience.xml)*/
        buttonShare = getView().findViewById(R.id.btn_share);
        buttonTwitter = getView().findViewById(R.id.btn_twitter);
        buttonFacebook = getView().findViewById(R.id.btn_facebook);
        buttonWhatssapp = getView().findViewById(R.id.btn_whatssapp);

        /* Asing the actions to the Buttons */
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Here is the action to realize */
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share_experience));//Here is the text
                startActivity(Intent.createChooser(intent, "Share with"));
            }
        });

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share_experience));
                /* Here is where the social app have to be specifiqued */
                intent.setPackage("com.facebook.katana");
                startActivity(intent);
            }
        });

        buttonTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share_experience));
                /* Here is where the social app have to be specifiqued */
                intent.setPackage("com.twitter.android");
                startActivity(intent);
            }
        });

        buttonWhatssapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.text_share_experience));
                /* Here is where the social app have to be specifiqued */
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });
    }//End of "onActivityCreated"
}//End class
