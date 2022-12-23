package com.example.baitapqt_1;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class Relative_Layout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relativelayout_activity_main);
        RelativeLayout rel1 =(RelativeLayout) findViewById(R.id.re1);
        RelativeLayout rel2 =(RelativeLayout) findViewById(R.id.re2);
        RelativeLayout rel3 =(RelativeLayout) findViewById(R.id.re3);
        RelativeLayout rel4 =(RelativeLayout) findViewById(R.id.re4);
        RelativeLayout rel5 =(RelativeLayout) findViewById(R.id.re5);

        SeekBar sb1 =(SeekBar) findViewById(R.id.sb1);

        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                rel1.setBackgroundColor(Color.rgb(i,100,0));
                rel2.setBackgroundColor(Color.rgb(98,0,i));
                rel3.setBackgroundColor(Color.rgb(0,i,100));
                rel4.setBackgroundColor(Color.rgb(98,200,i));
                rel5.setBackgroundColor(Color.rgb(i,100,156));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im =getMenuInflater();
        im.inflate(R.menu.menu_main,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId()==R.id.item1)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Nhóm 6 Hỏi. Bạn có muốn qua trang titok ko?");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dli , int i) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tiktok.com"));
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create();
            builder.show();
        }
        if(item.getItemId()==R.id.item2) {
            Intent intent =new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.item3) {
            Intent intent =new Intent(this, Relative_Layout.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.item4){
            Intent intent =new Intent(this,Table_Layout.class);
            startActivity(intent);
        }


        return  true;
    }
}
