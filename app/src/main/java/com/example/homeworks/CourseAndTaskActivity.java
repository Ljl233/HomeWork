package com.example.homeworks;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.lool.homeworks.Course;
import com.lool.homeworks.CourseAdapter;
import com.lool.homeworks.CoursesBean;
import com.lool.homeworks.CoursesService;
import com.lool.homeworks.VPAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAndTaskActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar tb_head;
    private TabLayout tl_CourseAndTask;
    private ViewPager vp_CourseAndTask;
    private DrawerLayout dl_navigation;
    private List<Course> CourseList = new ArrayList<>();
    private RecyclerView rv_course;
    private RecyclerView rv_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_and_task);
        initView();
    }

    private void initCourse(List<CoursesBean.CourseListBean> courselist , int total) {
        int[] images = {R.drawable.books,R.drawable.caculator,R.drawable.glasses,R.drawable.notebook,R.drawable.pen};
        int t = 0;
        int i = 0;
        while (i<total) {
            CourseList.add(new Course(courselist.get(i).getCourseName(),images[t],courselist.get(i).getSiteId()));
            i++; t++;
            if(t==5) t = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_course.setLayoutManager(layoutManager);
        CourseAdapter courseAdapter = new CourseAdapter(CourseList);
        rv_course.setAdapter(courseAdapter);

        courseAdapter.setOnRecyclerViewItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CourseAndTaskActivity.this,MainActivity.class);
                intent.putExtra("siteId",CourseList.get(position-1).getSiteId());
                startActivity(intent);
            }
        });
    }

    private void request() {
        CoursesService coursesService = RetrofitWrapper.getInstance().create(CoursesService.class);
        Call<CoursesBean> call = coursesService.getCoursesBean(getIntent().getStringExtra("cookie"),getIntent().getStringExtra("token"));
        call.enqueue(new Callback<CoursesBean>() {
            @Override
            public void onResponse(Call<CoursesBean> call, Response<CoursesBean> response) {
                if (response.isSuccessful()) {
                    initCourse(response.body().getCourseList(),response.body().getTotal());
                }else{
                    Toast.makeText(CourseAndTaskActivity.this, "请求失败，请重试",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CoursesBean> call, Throwable t) {

            }
        });
    }

    private void initView () {

        tb_head = findViewById(R.id.tb_head);
        dl_navigation = findViewById(R.id.dl_navigation);
        vp_CourseAndTask = findViewById(R.id.vp_CourseAndTask);
        tl_CourseAndTask = findViewById(R.id.tl_CourseAndTask);
        rv_course = findViewById(R.id.rv_course);
        rv_task = findViewById(R.id.rv_task);

        setSupportActionBar(tb_head);

        tb_head.setNavigationIcon(R.mipmap.menu_icon);
        tb_head.setTitle("HomeWorks");
        tb_head.inflateMenu(R.menu.search);
        tb_head.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(CourseAndTaskActivity.this,SearchActivity.class);
                startActivity(intent);
                return true;
            }
        });

        List<View> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        titleList.add("课堂列表");
        titleList.add("任务列表");

        LayoutInflater inflater = LayoutInflater.from(this);
        View tl1 = inflater.inflate(R.layout.course_view, null);
        View tl2 = inflater.inflate(R.layout.task_view, null);

        viewList.add(tl1);
        viewList.add(tl2);

        VPAdapter viewPagerAdapter = new VPAdapter(titleList,viewList);
        vp_CourseAndTask.setAdapter(viewPagerAdapter);

        tl_CourseAndTask.setupWithViewPager(vp_CourseAndTask);

        request();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl_navigation, tb_head, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl_navigation.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        dl_navigation = findViewById(R.id.dl_navigation);
        if (dl_navigation.isDrawerOpen(GravityCompat.START)) {
            dl_navigation.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_menu view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_assignment) {

        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_information) {

        } else if (id == R.id.nav_logoff) {

        } else if (id == R.id.nav_remind) {

        } else if (id == R.id.nav_notice) {

        }

        dl_navigation = findViewById(R.id.dl_navigation);
        dl_navigation.closeDrawer(GravityCompat.START);
        return true;
    }
}
