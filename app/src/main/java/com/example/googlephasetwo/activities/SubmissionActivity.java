package com.example.googlephasetwo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.googlephasetwo.R;
import com.example.googlephasetwo.models.ApiHolder;
import com.example.googlephasetwo.models.PostObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {
    private EditText firstname, lastname, email, link;
    private Button submit;
    private ImageView back;
    private ApiHolder apiHolder;

    private RelativeLayout confirm_layout, succesful_layout, failed_layout, form;
    private Button yes;
    private ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiHolder = retrofit.create(ApiHolder.class);

        back = findViewById(R.id.back);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        link = findViewById(R.id.link);
        submit = findViewById(R.id.submit);

        form = findViewById(R.id.form);
        succesful_layout = findViewById(R.id.confirmed_layout);
        failed_layout = findViewById(R.id.failed_layout);
        confirm_layout = findViewById(R.id.are_you_sure_layout);
        yes = findViewById(R.id.yes_button);
        cancel = findViewById(R.id.cancel);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String e_mail = email.getText().toString();
                String githubLink = link.getText().toString();
                submit(fname, lname, e_mail, githubLink);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm_layout.setVisibility(View.GONE);
                form.setVisibility(View.VISIBLE);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmissionActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String e_mail = email.getText().toString();
                String githubLink = link.getText().toString();

                if (!fname.equals("") && !lname.equals("") && !e_mail.equals("") && !githubLink.equals("")){
                    form.setVisibility(View.GONE);
                    confirm_layout.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(SubmissionActivity.this, "All input fields must be filled", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void submit(String name, String lname, String mail, String git_link) {

        Call<PostObject> post = apiHolder.submitProject(mail, name, lname, git_link);

        post.enqueue(new Callback<PostObject>() {
            @Override
            public void onResponse(Call<PostObject> call, Response<PostObject> response) {
                if (!response.isSuccessful()){
                    confirm_layout.setVisibility(View.GONE);
                    succesful_layout.setVisibility(View.GONE);
                    failed_layout.setVisibility(View.VISIBLE);
                }

                confirm_layout.setVisibility(View.GONE);
                succesful_layout.setVisibility(View.VISIBLE);
                failed_layout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PostObject> call, Throwable t) {
                confirm_layout.setVisibility(View.GONE);
                succesful_layout.setVisibility(View.GONE);
                failed_layout.setVisibility(View.VISIBLE);
            }
        });
    }
}