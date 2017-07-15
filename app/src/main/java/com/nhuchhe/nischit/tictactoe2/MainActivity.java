package com.nhuchhe.nischit.tictactoe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    Button start,singlePlayer;
    final int requestCodeLauncherDual=222,requestCodeSingle=111;
    TextView win;
    ImageView whoWonImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start =(Button)findViewById(R.id.buttonStart);
        singlePlayer=(Button)findViewById(R.id.SinglePlayer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //-----------------------------------------------------------------------
        //ADS
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //------------------------------------------------------------------------

        win=(TextView)findViewById(R.id.winText);
        whoWonImage=(ImageView)findViewById(R.id.whoWonImage);

        //requestCodeLauncherDual=222;//123
        //requestCodeSingle=111;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game.class);
                intent.putExtra("Start", 1);
                startActivityForResult(intent, requestCodeLauncherDual);
            }
        });

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameSingle.class);
                intent.putExtra("Start", 1);
                startActivityForResult(intent, requestCodeSingle);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case requestCodeLauncherDual:
                if(resultCode==data.getIntExtra("ResultCode",-1)){
                    win.setText("WON");
                    if(data.getStringExtra("whoWon").equals("Circle")){
                        whoWonImage.setImageResource(R.drawable.circle);
                    }else if(data.getStringExtra("whoWon").equals("Cross")){
                        whoWonImage.setImageResource(R.drawable.cross);
                    }else if(data.getStringExtra("whoWon").equals("Draw")){
                        win.setText("Draw");
                        whoWonImage.setImageResource(R.drawable.draw);
                    }else{
                        win.setText("");
                        whoWonImage.setImageResource(R.drawable.logo);
                    }
                }
                break;

            case requestCodeSingle:
                if(resultCode==data.getIntExtra("ResultCode",-1)){
                    win.setText("WON");
                    if(data.getStringExtra("whoWon").equals("Circle")){
                        whoWonImage.setImageResource(R.drawable.circle);
                    }else if(data.getStringExtra("whoWon").equals("Cross")){
                        whoWonImage.setImageResource(R.drawable.cross);
                    }else if(data.getStringExtra("whoWon").equals("Draw")){
                        win.setText("Draw");
                        whoWonImage.setImageResource(R.drawable.draw);
                    }else{
                        win.setText("");
                        whoWonImage.setImageResource(R.drawable.logo);
                    }
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
