package com.alaaelshazly.freeyourmind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {
    ListView lvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        lvBooks=findViewById(R.id.lvBooks);

        DataQueryBuilder builder=DataQueryBuilder.create();
        builder.setSortBy("name");

        Backendless.Data.of(Book.class).find(builder, new AsyncCallback<List<Book>>() {
            @Override
            public void handleResponse(List<Book> response) {
                ArrayList<String> names=new ArrayList<>();
                for (Book book:response){
                    names.add(book.getName());
                }
                ArrayAdapter adapter=new ArrayAdapter(BookActivity.this,android.R.layout.simple_list_item_1,names);
                lvBooks.setAdapter(adapter);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(BookActivity.this, "Book loading problem", Toast.LENGTH_SHORT).show();

            }
        });

    }
}