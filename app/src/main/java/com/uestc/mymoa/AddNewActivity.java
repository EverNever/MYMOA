package com.uestc.mymoa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by HeGang on 2015/7/27.
 */
public class AddNewActivity extends Activity {
    private EditText etTitle;
    private EditText etArticle;
    private Button btnStart;
    private Button btnFinal;
    private Button btnSelect;
    private Button btnIssus;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        initLayout();
        initListener();
    }

    private void initLayout(){
        etTitle=(EditText)findViewById(R.id.title);
        etArticle=(EditText)findViewById(R.id.article);
        btnStart=(Button)findViewById(R.id.start_date);
        btnFinal=(Button)findViewById(R.id.final_date);
        btnSelect=(Button)findViewById(R.id.select_file);
        btnIssus=(Button)findViewById(R.id.issue);
    }

    private void initListener(){
        btnIssus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Affiche affiche=new Affiche();
                AfficheDBAdapter afficheDBAdapter=new AfficheDBAdapter();
                affiche.title=String.valueOf(etTitle.getText());
                affiche.article=String.valueOf(etArticle.getText());
            }
        });

    }
}
