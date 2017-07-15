package com.nhuchhe.nischit.tictactoe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Game extends AppCompatActivity implements View.OnClickListener {

    ImageView whoseTurn;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,button,restartButton;
    long lastToast;
    LinearLayout mainLayout;
    Intent intent;
    long backButtonPressed=0;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        //-----------------------------------------------------------------------
        //ADS
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //------------------------------------------------------------------------

        intent=new Intent(getApplicationContext(),MainActivity.class);
        whoseTurn=(ImageView)findViewById(R.id.whoseTurnImage);

        mainLayout=(LinearLayout)findViewById(R.id.mainLayout);
        button=new Button(this);
        b1=(Button)findViewById(R.id.square1);
        b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.square2);
        b2.setOnClickListener(this);
        b3=(Button)findViewById(R.id.square3);
        b3.setOnClickListener(this);
        b4=(Button)findViewById(R.id.square4);
        b4.setOnClickListener(this);
        b5=(Button)findViewById(R.id.square5);
        b5.setOnClickListener(this);
        b6=(Button)findViewById(R.id.square6);
        b6.setOnClickListener(this);
        b7=(Button)findViewById(R.id.square7);
        b7.setOnClickListener(this);
        b8=(Button)findViewById(R.id.square8);
        b8.setOnClickListener(this);
        b9=(Button)findViewById(R.id.square9);
        b9.setOnClickListener(this);
        restartButton=(Button)findViewById(R.id.restartButton);
        restartButton.setOnClickListener(this);
        counter=0;
        lastToast=0;
    }

    @Override
    public void onClick(View v) {
        button=(Button) findViewById(v.getId());
        if(button.getTag().equals("empty")){
            counter++;
            if((counter&1)==1){
                //player 1
                button.setBackgroundResource(R.drawable.circle);
                button.setTag("Circle");
                whoseTurn.setImageResource(R.drawable.cross);

            }else if((counter&1)==0){
                //player 2
                whoseTurn.setImageResource(R.drawable.circle);
                button.setBackgroundResource(R.drawable.cross);
                button.setTag("Cross");

            }
            if(Checker()==true){
                intentHandler();
            }
        }else if(button.getTag().equals("restart")) {
            b1.setTag("empty");
            b1.setBackgroundResource(R.drawable.empty);
            b2.setTag("empty");
            b2.setBackgroundResource(R.drawable.empty);
            b3.setTag("empty");
            b3.setBackgroundResource(R.drawable.empty);
            b4.setTag("empty");
            b4.setBackgroundResource(R.drawable.empty);
            b5.setTag("empty");
            b5.setBackgroundResource(R.drawable.empty);
            b6.setTag("empty");
            b6.setBackgroundResource(R.drawable.empty);
            b7.setTag("empty");
            b7.setBackgroundResource(R.drawable.empty);
            b8.setTag("empty");
            b8.setBackgroundResource(R.drawable.empty);
            b9.setTag("empty");
            b9.setBackgroundResource(R.drawable.empty);
            counter=0;
            whoseTurn.setImageResource(R.drawable.circle);


        }else {
            if ((System.currentTimeMillis()-lastToast)>2500) {
                lastToast=System.currentTimeMillis();
                Toast.makeText(getApplicationContext(), "Not Empty !!", Toast.LENGTH_SHORT).show();
            }
        }
        //Full
        if(counter==9){
            //mainLayout.addView(restart);
            intentHandler();
        }
    }
    private void intentHandler(){
        intent.putExtra("ResultCode",100);
        if((counter&1)==1&&Checker()){
            intent.putExtra("whoWon","Circle");
        }else if((counter&1)==0&&Checker()){
            intent.putExtra("whoWon","Cross");
        }else{
            intent.putExtra("whoWon","Draw");
        }
        setResult(100,intent);
        finish();
    }
    public boolean Checker(){
        b1=(Button)findViewById(R.id.square1);
        b2=(Button)findViewById(R.id.square2);
        b3=(Button)findViewById(R.id.square3);
        b4=(Button)findViewById(R.id.square4);
        b5=(Button)findViewById(R.id.square5);
        b6=(Button)findViewById(R.id.square6);
        b7=(Button)findViewById(R.id.square7);
        b8=(Button)findViewById(R.id.square8);
        b9=(Button)findViewById(R.id.square9);
        boolean finish=false;
        //horizontal
        if((b1.getTag()==b2.getTag())&&(b2.getTag()==b3.getTag())&&(!b1.getTag().equals("empty"))){
            b1.setBackgroundColor(getResources().getColor(R.color.win));
            b2.setBackgroundColor(getResources().getColor(R.color.win));
            b3.setBackgroundColor(getResources().getColor(R.color.win));
            finish=true;
        }else
        if((b4.getTag()==b5.getTag())&&(b5.getTag()==b6.getTag())&&(!b4.getTag().equals("empty"))){
            b4.setBackgroundColor(getResources().getColor(R.color.win));
            b5.setBackgroundColor(getResources().getColor(R.color.win));
            b6.setBackgroundColor(getResources().getColor(R.color.win));
            finish=true;
        }else
        if((b7.getTag()==b8.getTag())&&(b8.getTag()==b9.getTag())&&(!b7.getTag().equals("empty"))){
            b7.setBackgroundColor(getResources().getColor(R.color.win));
            b8.setBackgroundColor(getResources().getColor(R.color.win));
            b9.setBackgroundColor(getResources().getColor(R.color.win));
            finish=true;
        }else
            //Vertical
            if((b1.getTag()==b4.getTag())&&(b4.getTag()==b7.getTag())&&(!b1.getTag().equals("empty"))){
                b1.setBackgroundColor(getResources().getColor(R.color.win));
                b4.setBackgroundColor(getResources().getColor(R.color.win));
                b7.setBackgroundColor(getResources().getColor(R.color.win));
                finish=true;
            }else
            if((b2.getTag()==b5.getTag())&&(b5.getTag()==b8.getTag())&&(!b2.getTag().equals("empty"))){
                b2.setBackgroundColor(getResources().getColor(R.color.win));
                b5.setBackgroundColor(getResources().getColor(R.color.win));
                b8.setBackgroundColor(getResources().getColor(R.color.win));
                finish=true;
            }else
            if((b3.getTag()==b6.getTag())&&(b6.getTag()==b9.getTag())&&(!b3.getTag().equals("empty"))){
                b3.setBackgroundColor(getResources().getColor(R.color.win));
                b6.setBackgroundColor(getResources().getColor(R.color.win));
                b9.setBackgroundColor(getResources().getColor(R.color.win));
                finish=true;
            }else
                //diagonal
                if((b1.getTag()==b5.getTag())&&(b5.getTag()==b9.getTag())&&(!b1.getTag().equals("empty"))){
                    b1.setBackgroundColor(getResources().getColor(R.color.win));
                    b5.setBackgroundColor(getResources().getColor(R.color.win));
                    b9.setBackgroundColor(getResources().getColor(R.color.win));
                    finish=true;
                }else
                if((b3.getTag()==b5.getTag())&&(b5.getTag()==b7.getTag())&&(!b3.getTag().equals("empty"))){
                    b3.setBackgroundColor(getResources().getColor(R.color.win));
                    b5.setBackgroundColor(getResources().getColor(R.color.win));
                    b7.setBackgroundColor(getResources().getColor(R.color.win));
                    finish=true;
                }
        return finish;
    }
    @Override
    public void onBackPressed(){
        if((System.currentTimeMillis()-backButtonPressed)>2500){
            Toast.makeText(getApplication(),"Press again to go Back",Toast.LENGTH_SHORT).show();
            backButtonPressed=System.currentTimeMillis();
        }else{
            intent.putExtra("ResultCode",100);
            intent.putExtra("whoWon","BackButton");
            setResult(100,intent);
            finish();
        }

    }
}
