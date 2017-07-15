package com.nhuchhe.nischit.tictactoe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class GameSingle extends AppCompatActivity implements View.OnClickListener{

    ImageView whoseTurn;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,button,restartButton;
    long lastToast,backButtonPressed=0;
    LinearLayout mainLayout;
    Intent intent;
    int counter;
    boolean firstmovecorner;
    int[] map=new int[9];
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
        firstmovecorner=false;
    }

    @Override
    public void onClick(View v) {
        button=(Button) findViewById(v.getId());
        int id=-1;
        if(button.getTag().equals("empty")){
            counter++;
            //if((counter&1)==1){
                switch (v.getId()){
                    case R.id.square1:
                        id=0;
                        break;
                    case R.id.square2:
                        id=1;
                        break;
                    case R.id.square3:
                        id=2;
                        break;
                    case R.id.square4:
                        id=3;
                        break;
                    case R.id.square5:
                        id=4;
                        break;
                    case R.id.square6:
                        id=5;
                        break;
                    case R.id.square7:
                        id=6;
                        break;
                    case R.id.square8:
                        id=7;
                        break;
                    case R.id.square9:
                        id=8;
                        break;
                }
                map[id]=1;
                //player 1
                button.setBackgroundResource(R.drawable.circle);
                button.setTag("Circle");
                whoseTurn.setImageResource(R.drawable.cross);
                //counter++;
                if ((counter <8)&&!Checker()) {
                    aiBackgroundChanger();
                    counter++;
                }
            /*
            }/*else if((counter&1)==0){
                //player 2
                whoseTurn.setImageResource(R.drawable.circle);
                button.setBackgroundResource(R.drawable.cross);
                button.setTag("Cross");

            }*/
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
            firstmovecorner=false;
            for(int i=0;i<9;i++){
                map[i]=0;
            }

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
        intent.putExtra("ResultCode",111);
        if((counter&1)==1&&Checker()){
            intent.putExtra("whoWon","Circle");
        }else if((counter&1)==0&&Checker()){
            intent.putExtra("whoWon","Cross");
        }else{
            intent.putExtra("whoWon","Draw");
        }
        setResult(111,intent);
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
//-----------------------------------------------------------------------------------------------






 //-----------------------------------------------------------------------------------------------
    private int ai(){

        //ai plan---------------------------------
        //check if ai wins
        //check if player wins

        //if first move is corner
            //fill center
            //fill side
        //if first move is center
            //fill corner
        //if two moves are at the side
            //fill corner
        //make ai move from corner
        //----------------------------------------
        int winCheck=winCheckAi();
        if(winCheck!=-1){
            return winCheck;
        }

        int firstCorner=firstMoveCorner();
        if(firstCorner!=-1){
            return firstCorner;
        }

        int firstCenter=firstMoveCenter();
        if(firstCenter!=-1){
            return firstCenter;
        }
        //fill center if not filled
        if(map[4]==0){
            return 4;
        }
        int moveSide=MoveSide();
        if(moveSide!=-1){
            return moveSide;
        }
        //
        //any jpt value
        while(true){
            String number="012345678";
            for(int i=0;i<9;i++){
                if(map[i]!=0){
                    number=number.replace(String.valueOf(i),"");
                }
            }
            int random=(int)(Math.random()*number.length());
            int num=number.charAt(random)-48;
            if(map[num]==0){
                return num;
            }
        }
    }
    int MoveSide(){
        if(counter<=3){
            for(int i=1;i<6;i+=2){
                if((map[i]==map[i+2]&&map[i]==1)||(map[1]==map[5]&&map[1]==1)){
                    String number="0268";
                    int random=(int)(Math.random()*number.length());
                    int num=number.charAt(random)-48;
                    if(map[num]==0){
                        return num;
                    }
                }
            }
        }
        return -1;
    }
    int firstMoveCenter(){
        if(counter==1&&map[4]!=0){
            //return 0|2|6|8;
            String number="0268";
            int random=(int)(Math.random()*4);
            int num=number.charAt(random)-48;
            if(map[num]==0){
                return num;
            }else{
                return -1;
            }

        }
        return -1;
    }
    int firstMoveCorner(){
        if(counter==1&&(map[0]!=0||map[2]!=0||map[6]!=0||map[8]!=0)){
            firstmovecorner=true;

            /*String number="13457";
            int random=(int)(Math.random()*number.length());
            int num=number.charAt(random)-48;
            while(map[num]!=0){
                random=(int)(Math.random()*number.length());
                num=number.charAt(random)-48;
            }
            return num;*/
            if(map[4]==0){
                return 4;//center position
            }else{
                return -1;
            }

        }
        if(firstmovecorner){
            firstmovecorner=false;
            //return 1|3|5|7;
            String number="1357";
            int random=(int)(Math.random()*4);
            int num=number.charAt(random)-48;
            if(map[num]==0){
                return num;
            }else{
                return -1;
            }

        }
        return -1;
    }

    int winCheckAi(){
        int countEmpty,posZero,aiWin,finalPosZero;
        //horizontal
        finalPosZero=-1;
        for(int i=0;i<9;i+=3){
            for(int j=i;j<(i+3);j++){
                countEmpty=0;
                posZero=-1;
                aiWin=-1;
                for(int k=i;k<(i+3);k++){
                    if(map[j]==map[k]&&map[j]!=0){
                        countEmpty++;
                        if(map[j]==2){
                            aiWin=j;
                        }
                    }
                    if(map[k]==0){
                        posZero=k;
                    }
                    if(countEmpty==2&&posZero!=-1){
                        if(aiWin!=-1&&map[aiWin]==2){
                            return posZero;
                        }
                        finalPosZero=posZero;
                    }
                }
            }
        }
        if(finalPosZero!=-1){
            return finalPosZero;
        }
        //vertical
        finalPosZero=-1;
        for(int i=0;i<3;i++){
            for(int j=i;j<=(i+6);j+=3){
                countEmpty=0;
                posZero=-1;
                aiWin=-1;
                for(int k=i;k<=(i+6);k+=3){
                    if(map[j]==map[k]&&map[j]!=0){
                        countEmpty++;
                        if(map[j]==2){
                            aiWin=j;
                        }
                    }
                    if(map[k]==0){
                        posZero=k;
                    }
                    if(countEmpty==2&&posZero!=-1){
                        if(aiWin!=-1&&map[aiWin]==2){
                            return posZero;
                        }
                        finalPosZero=posZero;
                    }
                }
            }
        }
        if(finalPosZero!=-1){
            return finalPosZero;
        }
        //diagonal
        //0 4 8
        finalPosZero=-1;
        for(int i=0;i<9;i+=4){
            countEmpty=0;
            posZero=-1;
            aiWin=-1;
            for(int j=0;j<9;j+=4){
                if(map[i]==map[j]&&map[i]!=0){
                    countEmpty++;
                }
                if(map[j]==0){
                    posZero=j;
                }
                if(countEmpty==2&&posZero!=-1){
                    if(aiWin!=-1&&map[aiWin]==2){
                        return posZero;
                    }
                    finalPosZero=posZero;
                }
            }
        }
        if(finalPosZero!=-1){
            return finalPosZero;
        }
        //2 4 6
        finalPosZero=-1;
        for(int i=2;i<7;i+=2){
            countEmpty=0;
            posZero=-1;
            aiWin=-1;
            for(int j=2;j<7;j+=2){
                if(map[i]==map[j]&&map[i]!=0){
                    countEmpty++;
                }
                if(map[j]==0){
                    posZero=j;
                }
                if(countEmpty==2&&posZero!=-1){
                    if(aiWin!=-1&&map[aiWin]==2){
                        return posZero;
                    }
                    finalPosZero=posZero;
                }
            }
        }
        if(finalPosZero!=-1){
            return finalPosZero;
        }
        return -1;
    }
    private void aiBackgroundChanger(){
        int Result=ai();
        int id=0;
        switch (Result){
            case 0:
                id=R.id.square1;
                break;
            case 1:
                id=R.id.square2;
                break;
            case 2:
                id=R.id.square3;
                break;
            case 3:
                id=R.id.square4;
                break;
            case 4:
                id=R.id.square5;
                break;
            case 5:
                id=R.id.square6;
                break;
            case 6:
                id=R.id.square7;
                break;
            case 7:
                id=R.id.square8;
                break;
            case 8:
                id=R.id.square9;
                break;
        }
        button=(Button)findViewById(id);
        if((counter&1)==1){
            //player 1
            button.setTag("cross");
            button.setBackgroundResource(R.drawable.cross);
            whoseTurn.setImageResource(R.drawable.circle);
            map[Result]=2;//cross
        }else if((counter&1)==0){
            //player 2
            button.setTag("circle");
            button.setBackgroundResource(R.drawable.circle);
            whoseTurn.setImageResource(R.drawable.cross);
            map[Result]=1;//circle
        }

    }
}
