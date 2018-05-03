package com.dict.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dict.android.db.WordsSQLiteOpenHelper;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    private WordsSQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new WordsSQLiteOpenHelper(this,"Dictionary.db",null,1);
        Button createDatabase=(Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
//                LitePal.getDatabase();
            }
        });
    }
}
