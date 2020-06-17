package com.example.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter mAdapter;
    MyAdapter adapter;
    LinkedList<HashMap<String, Object>> data;
    ArrayList<String> arraylist=new ArrayList<String>();
    int img[] = {R.drawable.facebook,R.drawable.instagram, R.drawable.twitter, R.drawable.whatsapp,R.drawable.youtube};
    String[] title = {"FB","whatsapp","Twiter","IG","youtube"};
    String comment[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        dodata();
        mAdapter=new MyAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    public void dodata(){
     data=new LinkedList<HashMap<String, Object>>();

     for (int i=0;i<title.length;i++){
         HashMap<String,Object>row=new HashMap<>();

         row.put("title",title[i]);
         row.put("comment",comment[i]);
         row.put("img",img[i]);
         data.add(row);

     }

    }


    public class MyAdapter extends RecyclerView.Adapter <MyAdapter.MyViewHolder>{ //泛型

        class MyViewHolder extends RecyclerView.ViewHolder {

            public View itemview;
            public TextView title;
            public TextView comment;
            public ImageView images;
            public MyViewHolder(View view) {
                super(view);
                itemview=view;
                title=itemview.findViewById(R.id.textView);
                comment=itemview.findViewById(R.id.textView2);
                images=itemview.findViewById(R.id.imageView);
            }
        }
        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);  //取得xml layout

         MyViewHolder vh=new MyViewHolder(itemview);


            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
           holder.title.setText((CharSequence) data.get(position).get("title")); //取得 xml元件
           holder.comment.setText((CharSequence) data.get(position).get("comment"));
           holder.images.setImageResource((Integer) data.get(position).get("img"));
           holder.images.setOnClickListener(new View.OnClickListener() { //When click image 執行底下
               @Override
               public void onClick(View view) {
                   if (position == 0) {
                       Toast.makeText(getApplicationContext(), "Facebook Description", Toast.LENGTH_SHORT).show();
                   }

                   if (position == 1) {
                       Toast.makeText(getApplicationContext(), "Whatsapp Description", Toast.LENGTH_SHORT).show();
                   }
                   if (position == 2) {
                       Toast.makeText(getApplicationContext(), "Twitter Description", Toast.LENGTH_SHORT).show();
                   }
                   if (position == 3) {
                       Toast.makeText(getApplicationContext(), "Instagram Description", Toast.LENGTH_SHORT).show();
                   }
                   if (position == 4) {
                       Toast.makeText(getApplicationContext(), "Youtube Description", Toast.LENGTH_SHORT).show();
                   }
               }
           });

        }

        @Override
        public int getItemCount() {
            return data.size(); //回傳資料長度
        }
    }
}
