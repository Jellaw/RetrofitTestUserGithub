package com.example.retrofittest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.retrofittest.data.model.User;
import com.example.retrofittest.data.model.Comment;
import com.example.retrofittest.data.remote.JsonPlaceHolderApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText txtUs;
    EditText txtName;
    ListView textViewResult;
    JsonPlaceHolderApi jsonPlaceHolderApi;
    String logname;
    ArrayAdapter<User> aa;
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.list);
        txtName = findViewById(R.id.editText2);
        txtUs = findViewById(R.id.txtUs);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<User>> call = jsonPlaceHolderApi.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                list = (ArrayList<User>) response.body();
                setAdapter();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                txtUs.setText(t.getMessage());
            }
        });


        textViewResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = textViewResult.getItemAtPosition(position);
                User user = (User) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + user, Toast.LENGTH_LONG).show();
                txtUs.setText(user.getLogin());
                logname = user.getLogin();
                getComments();

            }
        });
    }

    private void getComments() {
        Call<Comment> call = jsonPlaceHolderApi.getComments(logname);

        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {

                Comment comment =  response.body();
                txtName.setText(comment.getName());

            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
            }
        });
    }

    private void setAdapter() {
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        textViewResult.setAdapter(aa);
    }
}
