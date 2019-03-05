package com.example.homeworks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.lool.homeworks.CourseBean;
import com.lool.homeworks.CourseService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_signin;
    EditText et_password, et_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        btn_signin = findViewById(R.id.btn_signin);
        et_password = findViewById(R.id.et_password);
        et_userName = findViewById(R.id.et_userName);
        btn_signin.setOnClickListener(this);

    }


    public void onClick(View v) {

        request();
    }


    private void request() {
        CourseService courseService = RetrofitWrapper.getInstance().create(CourseService.class);
        Call<CourseBean> call = courseService.getCourseBean(et_userName.getText().toString(), et_password.getText().toString());
        call.enqueue(new Callback<CourseBean>() {
            @Override
            public void onResponse(Call<CourseBean> call, Response<CourseBean> response) {
                Intent intent = new Intent(MainActivity.this, WorkActivity.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<CourseBean> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("!!!!!!!!!!!!!!!!!!!!!!",t.getMessage());
            }
        });
    }
}