package com.example.homeworks.AssignDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.homeworks.R;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {
        TextView tv_begin2, tv_ddl2, tv_state, tv_content, tv_pointNum, tv_studentNum, tv_submitContent,
                tv_feedback, tv_assignAttachmentNum, tv_assignAttachment_name;
    }

}
