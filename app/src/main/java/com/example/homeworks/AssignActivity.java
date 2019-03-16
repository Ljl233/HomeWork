package com.example.homeworks;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.lool.homeworks.AssignListService;
import com.lool.homeworks.CourseBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignActivity extends AppCompatActivity {
    private List<TasksBean.DataBean> mAssignList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AssignAdapter assignAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_main);

        init();

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.task_recycle_view);

        setSupportActionBar(toolbar);

        request();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        assignAdapter = new AssignAdapter(mAssignList);
        recyclerView.setAdapter(assignAdapter);

        assignAdapter.setOnItemClickListener(new AssignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    private void request() {
        AssignListService assignListService = RetrofitWrapper.getInstance().create(AssignListService.class);
        String siteId = getIntent().getStringExtra("siteId");
        Call<TasksBean> call = assignListService.getTasksBean(siteId,new CourseBean().getCookie(), new CourseBean().getToken());
        call.enqueue(new Callback<TasksBean>() {
            @Override
            public void onResponse(Call<TasksBean> call, Response<TasksBean> response) {
                if (response.isSuccessful()) {
                    mAssignList.add((TasksBean.DataBean) response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<TasksBean> call, Throwable t) {

            }
        });
    }

}

