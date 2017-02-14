package com.ops.newsurvey;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class PopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);


        final ArrayList<Question> questions = new ArrayList<>();
        ArrayList<String> opt = new ArrayList<String>();
        opt.add("Yes");
        opt.add("No");
        opt.add("Can't Say");
        questions.add(new Question("Pop Question 1",opt));
        questions.add(new Question("Pop Question 2",opt));
        questions.add(new Question("Pop Question 3",opt));
        questions.add(new Question("Pop Question 4",opt));
        questions.add(new Question("Pop Question 5",opt));
        questions.add(new Question("Pop Question 6",opt));
        questions.add(new Question("Pop Question 7",opt));
        questions.add(new Question("Pop Question 8",opt));
        questions.add(new Question("Pop Question 9",opt));
        questions.add(new Question("Pop Question 10",opt));

        QuestionAdapter Adapter=new QuestionAdapter(this,questions);
        ListView listView=(ListView) findViewById(R.id.popList);
        listView.setAdapter(Adapter);
    }
}