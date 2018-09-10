package shivam.com.custom_marker.resize_dialog;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import shivam.com.custom_marker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);

        final FragmentManager fm=getFragmentManager();
        final  TVShowFragment tv=new TVShowFragment();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.show(fm,"TV_tag");
            }
        });
    }

}