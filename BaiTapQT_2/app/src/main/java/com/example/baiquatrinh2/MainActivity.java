package com.example.baiquatrinh2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
// danh sách noteDTO để đưa vào ListView
    ListView listView;
    // tạo và khởi tạo  arrayList
    public static ArrayList<NoteDTO> listNotes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Lấy đối tượng Listview dựa vào id
        listView = findViewById(R.id.listNotes);


// tạo đối tượng adapter
        NoteAdapter adapter = new NoteAdapter(this, listNotes);
       // gán Adapter vào cho ListView
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            String title = bundle.getString("title");
            String desc = bundle.getString("desc");
            String time = bundle.getString("time");
            String action = bundle.getString("action");
            if(action.equalsIgnoreCase("add")){

                int id = 0;
                for(NoteDTO note:listNotes){
                    if(note.getId() > id){
                        id = note.getId();
                    }
                }
                // xử lý tính ID mỗi lần tạo thêm 1 ghi chú thì nó sẽ tính id+1
                NoteDTO note = new NoteDTO(id + 1, title,desc,time);
                listNotes.add(note);
                // nếu chỉnh sửa ghi chú thì id sẽ ko thay đổi và cập nhật lại các dữ liệu khác
            }else if(action.equalsIgnoreCase("update")){
                int id = Integer.parseInt(bundle.getString("id"));
                for(NoteDTO note:listNotes){
                    if(note.getId() == id){
                        note.setDesc(desc);
                        note.setTitle(title);
                        note.setTime(time);
                    }
                }
            }else{
                int id = Integer.parseInt(bundle.getString("id"));
                for(NoteDTO note:listNotes){
                    if(note.getId() == id){
                       listNotes.remove(note);
                       break;
                    }
                }
            }

            adapter.notifyDataSetChanged();
        }

    }

    @Override
    // khởi tạo menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater im = getMenuInflater();
        // dẫn lên giao diện chính 
        im.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent i = new Intent(this, AddNote.class);
                startActivity(i);
            break;
        }
        return true;
    }
}