package edu.hanu.a1_1901040047;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Array to conatain all button
    ImageButton[] hiratana = new ImageButton[64];
    ImageButton[] katakana = new ImageButton[64];
    MediaPlayer media;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        String name = getPackageName();
        //get 2 switch button
        Button hiraButton = findViewById(R.id.buttonhira);
        Button kataButton = findViewById(R.id.buttonkata);
        //disable switch Hiragana button default
        hiraButton.setEnabled(false);
        hiraButton.setOnClickListener(this);
        kataButton.setOnClickListener(this);
        //get all hiratana tag
        for(int i=1;i<=46;i++){
            ImageButton hira = findViewById(r.getIdentifier("hira"+String.valueOf(i),"id",name));
            hiratana[i-1] = hira;
        }
        //get all katakana tag
        for(int i = 1;i<=46;i++){
            ImageButton kata = findViewById(r.getIdentifier("kata"+String.valueOf(i),"id",name));
            katakana[i-1] = kata;
        }
        //add onClickListener to all button
        for(int i=0;i<46;i++){
            hiratana[i].setOnClickListener(this);
            katakana[i].setOnClickListener(this);
        }
    }
    @Override
    public void onClick(View view) {
        Button buttonHira = findViewById(R.id.buttonhira);
        Button buttonKata = findViewById(R.id.buttonkata);
        ScrollView hiratanaView = findViewById(R.id.scrollviewhira);
        ScrollView katakanaView = findViewById(R.id.scrollviewkata);
        switch(view.getId()){
            case R.id.buttonhira:
                //enable switch kata
                buttonKata.setEnabled(true);
                //show view hiraView
                hiratanaView.setVisibility(View.VISIBLE);
                //change color switch button
                buttonHira.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.purple_700));
                buttonHira.setTextColor(ContextCompat.getColorStateList(this, R.color.white));
                buttonKata.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                buttonKata.setTextColor(ContextCompat.getColorStateList(this, R.color.black));
                //hide view karaView
                katakanaView.setVisibility(View.GONE);
                //disable switch Hira
                buttonHira.setEnabled(false);
                break;
            case R.id.buttonkata:
                //Same as in button switch Hira
                buttonHira.setEnabled(true);
                katakanaView.setVisibility(View.VISIBLE);
                katakanaView.setAlpha(1.0F);
                buttonKata.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.purple_700));
                buttonKata.setTextColor(ContextCompat.getColorStateList(this, R.color.white));
                buttonHira.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.white));
                buttonHira.setTextColor(ContextCompat.getColorStateList(this, R.color.black));
                hiratanaView.setVisibility(View.GONE);
                buttonKata.setEnabled(false);
                break;
            default:
                if(media!=null){
                    media.reset();
                }
                ImageButton currentTarget = findViewById(view.getId());
                media = MediaPlayer.create(getApplicationContext(),getResources().getIdentifier(currentTarget.getContentDescription().toString(),"raw",getPackageName()));
                media.start();
                break;
        }
    }
}