package com.example.eduardo.hotelcasamia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class GalleryActivity extends AppCompatActivity {

    private FeatureCoverFlow coverFlow;
    private GalleryAdapter galleryAdapter;
    private List<Gallery> galleryList = new ArrayList<>();
    private TextSwitcher mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        initData();//<----
        mTitle = (TextSwitcher)findViewById(R.id.title);//<-----
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(GalleryActivity.this);
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
                return txt;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.slide_out_buttom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        /* Set AdapterCoverFlow */
        galleryAdapter = new GalleryAdapter(galleryList,this);
        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverFlow);
        coverFlow.setAdapter(galleryAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(galleryList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });
    }//End "onCreate"

    /* Create this method */
    private void initData() {
        galleryList.add(new Gallery("Noria Hotel CasaMia","https://78.media.tumblr.com/e6d3e33a4976ed613e986be323fdbbfa/tumblr_p5prsvS2ur1x9ezg5o1_1280.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/b71f014de02ec53920aacf7a0c9485c5/tumblr_p6qrliuxPO1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/3a21a49e9af684a25dd8fa5f4ab2c29c/tumblr_p5ps9wkiGK1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/54a600b0fab927b0e4159ef47eda54b9/tumblr_p5psayRaaL1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/afaf8f649407e2b43dbbe8f3efbdfd0c/tumblr_p6qrstWYTk1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/c738fdbe3cc06556e6d2a8213402420a/tumblr_p5psciGAzb1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/f85f6aed8aef4db4664c70bbd50aa54e/tumblr_p5psdbfkKz1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/a18e8df4559444a8a14642709212bcba/tumblr_p5psduWs4t1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/a1976d007caac6e14c4123fa69d0aa80/tumblr_p5psepGJ0z1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/91e88d9ed5d7be6cc9ef51a048127016/tumblr_p5psfbTQPA1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/f631b5e1158d23f56caf961aef76361f/tumblr_p6qrwlVQnv1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/127f848c8fb6452d71cd8455d7c3457b/tumblr_p6qs2tGOqi1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/67950672ef47fd2003acfb643ba4d5f1/tumblr_p6qs63aOvs1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/68d6d5eb36e6837c83842d434f4a7a99/tumblr_p6qs9iIuln1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/c2e671c71c24f319c4ccd17fdddb4133/tumblr_p6qsc6V1iM1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/0c0f602f7fdf64d6157d5bf99fecf58a/tumblr_p6qsfnbkRl1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/c1ba1b91eada3bc9e44772fee595ecd1/tumblr_p6qsi2HUdB1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/3d8604d59a3e087a4eb4a4ae17993090/tumblr_p6qslgSOrl1x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/07a765c2f575953e87c414f1fed68899/tumblr_p6qsnrJAg01x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/6944c2802a817aea0d9bd5b1847a5f8f/tumblr_p6qspsL2h41x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/fc8dc83fca84e8f2b4051c953ce3fd25/tumblr_p6qss5OVS61x9ezg5o1_540.jpg"));
        galleryList.add(new Gallery("Fotografía","https://78.media.tumblr.com/4817ee9a7b0dd1f62a1eecb404fa3422/tumblr_p6qstkZ9QE1x9ezg5o1_540.jpg"));
    }
}//End Class
