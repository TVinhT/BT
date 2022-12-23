package com.example.bai3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Vitamins extends AppCompatActivity {

    ListView lvTitle;
    ArrayList<String> arrayTitle, arrayLink, arrayDecription1, arrayDecription2, arrayPicture, arrayPubDate;
    ArrayAdapter adapterTitle;
    TextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listviewform);

        lvTitle = (ListView) findViewById(R.id.listviewTitle);
        arrayTitle = new ArrayList<>();
        arrayDecription1 = new ArrayList<>();
        arrayDecription2 = new ArrayList<>();
        arrayPicture = new ArrayList<>();
        arrayLink = new ArrayList<>();
        arrayPubDate = new ArrayList<>();

        adapterTitle = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayTitle);
        lvTitle.setAdapter(adapterTitle);

        new ReadRSS().execute("https://www.petfoodindustry.com/rss/topic/296-vitamins");

        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder mbuider = new AlertDialog.Builder(Vitamins.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_diablog, null);
                mbuider.setView(mview);
                AlertDialog dialog = mbuider.create();
                dialog.show();

                editText= (TextView) mview.findViewById(R.id.textViewTitle);
                editText.setText(arrayTitle.get(i));
//              editText= (TextView) findViewById(R.id.textViewDescription1);
//              editText.setText(description1);
                editText= (TextView) mview.findViewById(R.id.textViewDescription2);
                editText.setText(arrayDecription2.get(i));
                editText= (TextView) mview.findViewById(R.id.textViewpubDate);
                editText.setText(arrayPubDate.get(i));
                ImageView imageView = mview.findViewById(R.id.imageView);
                Picasso.get().load(arrayPicture.get(i)).into(imageView);
                Button buttonMore = mview.findViewById(R.id.buttonMore);
                Button buttonClose = mview.findViewById(R.id.buttonClose);
                buttonMore.setText("More");
                buttonClose.setText("Close");
                buttonClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                buttonMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Vitamins.this, NewActivity.class);
                        intent.putExtra("link", arrayLink.get(i));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");
            NodeList nodeList1 = document.getElementsByTagName("media:content");

            String title = "";
            String description1 = "";
            String description2 = "";
            String picture = "";
            String link = "";
            String date = "";

            for (int i = 0; i < nodeList.getLength(); i++){
                Element element = (Element) nodeList.item(i);
                title = parser.getValue((element), "title");
                arrayTitle.add(title);
                link = parser.getValue((element), "link");
                arrayLink.add(link);
                date = parser.getValue((element), "pubDate");
                arrayPubDate.add(date);
            }

            for (int i = 0; i < nodeList1.getLength(); i++){
                Element element = (Element) nodeList1.item(i);
                picture = element.getAttribute("url");
                arrayPicture.add(picture);
                description2 = parser.getValue((element), "media:description");
                arrayDecription2.add(description2);
            }

            adapterTitle.notifyDataSetChanged();
        }
    }
}