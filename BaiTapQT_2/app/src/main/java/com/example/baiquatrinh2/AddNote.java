package com.example.baiquatrinh2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class AddNote extends AppCompatActivity {
//khởi tạo biến
    String title = "", desc = "", time = "";
    int id = 0;
    TextView textTitle, textDesc;
// tạo và khởi tạo contentView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
// lấy đối tượng textView thông qua id
        textTitle = findViewById(R.id.editTitle);
        textDesc = findViewById(R.id.editDesc);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            title = bundle.getString("title");
            desc = bundle.getString("desc");
            time = bundle.getString("time");
            title = bundle.getString("title");
            id = Integer.parseInt(bundle.getString("id"));

            textTitle.setText(title);
            textDesc.setText(desc);
        }
// tạo và xữ lý sự kiện xóa
        Button deleteBtn = findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            // thông báo xác nhận  xóa
            public void onClick(View view) {
                if(id == 0){
                    Toast.makeText(AddNote.this,"Vui lòng chọn note để xóa",Toast.LENGTH_LONG).show();
                }else{

                    AlertDialog.Builder alert = new AlertDialog.Builder(AddNote.this);
                    alert.setTitle("Delete");
                    alert.setMessage("Are you sure you want to delete?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        // xữ lý button xóa dữ liệu
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundleDelete = new Bundle();
                            bundleDelete.putString("action","delete");
                            bundleDelete.putString("id",id + "");
                            Intent i = new Intent(AddNote.this, MainActivity.class);
                            i.putExtras(bundleDelete);
                            startActivity(i);

                            dialog.dismiss();
                        }
                    });

                    alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    alert.show();

                }
            }
        });
    }
    // thêm ghi chú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        im.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    // xử lý lưu ghi chú
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
// lấy time hiện tại khi tạo ghi chú
                Date currentTime = Calendar.getInstance().getTime();
                time = currentTime.toString();

// lấy dữ liệu textview ra listview
                title = textTitle.getText().toString();
                desc = textDesc.getText().toString();

                Bundle bundle = new Bundle();
// dùng bundle để truyền dữ liệu sang note_item
                if(id == 0){
                    bundle.putString("title",title);
                    bundle.putString("time",time);
                    bundle.putString("desc",desc);
                    bundle.putString("action","add");
                }else{
                    bundle.putString("id",id + "");
                    bundle.putString("title",title);
                    bundle.putString("time",time);
                    bundle.putString("desc",desc);
                    bundle.putString("action","update");
                }
// chuyển trang qua layout mainActivity
                Intent i = new Intent(this, MainActivity.class);
                i.putExtras(bundle);
                startActivity(i);
                break;
        }
        return true;
    }
}