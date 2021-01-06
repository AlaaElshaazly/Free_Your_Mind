package com.alaaelshazly.freeyourmind;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class ListOfMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_movies);

        DataQueryBuilder builder=DataQueryBuilder.create();
        builder.setSortBy("name");
        Backendless.Data.of(Book.class).find(builder, new AsyncCallback<List<Book>>() {
            @Override
            public void handleResponse(List<Book> response) {
                ArrayList<String> names=new ArrayList<>();
                for (Book book:response){
                    names.add(book.getName());
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(ListOfMoviesActivity.this, "Book loading problem", Toast.LENGTH_SHORT).show();

            }
        });

    }

    }
