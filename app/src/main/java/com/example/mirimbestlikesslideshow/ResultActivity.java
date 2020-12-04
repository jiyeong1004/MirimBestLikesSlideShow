package com.example.mirimbestlikesslideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    int[] imgvIds = {R.id.img_result1, R.id.img_result2, R.id.img_result3, R.id.img_result4, R.id.img_result5, R.id.img_result6, R.id.img_result7, R.id.img_result8, R.id.img_result9};
    int[] imgRes = {R.drawable.o1, R.drawable.o2, R.drawable.o3, R.drawable.o4, R.drawable.o5, R.drawable.o6, R.drawable.o7, R.drawable.o8, R.drawable.o9};
    ImageView[] imgvs = new ImageView[imgvIds.length];
    ViewFlipper flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int[] voteCount = intent.getIntArrayExtra("voteCount");
        int temp1 = 0;
        int temp2 = 0;
        for(int i = 0; i < imgRes.length - 1; i++){
            for(int j = i + 1; j < imgRes.length; j++){
                if(voteCount[i] < voteCount[j]){
                    temp1 = voteCount[i];
                    voteCount[i] = voteCount[j];
                    voteCount[j] = temp1;

                    temp2 = imgRes[i];
                    imgRes[i] = imgRes[j];
                    imgRes[j] = temp2;
                }
            }
        }

        //Log.v("투표수 정렬결과", Arrays.toString(voteCount));

        for(int i = 0; i < imgvIds.length; i++){
            imgvs[i] = findViewById(imgvIds[i]);
            imgvs[i].setImageResource(imgRes[i]);
        }

        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        btnStart.setOnClickListener(btnListener);
        btnStop.setOnClickListener(btnListener);

        flipper = findViewById(R.id.flipper);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    flipper.setFlipInterval(1000);
                    flipper.startFlipping();
                    break;
                case R.id.btn_stop:
                    flipper.stopFlipping();
                    break;
            }
        }
    };
}