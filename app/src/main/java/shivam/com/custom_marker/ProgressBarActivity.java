package shivam.com.custom_marker;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarActivity extends AppCompatActivity {

    private ProgressBar mProgressBar1, mProgressBar2, mProgressBar3;
    private TextView mTotal1, mTotal2, mTotal3, mRemaining1, mRemaining2, mRemaining3;
    private EditText mTotal, mRemaining;
    private Button mCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        initViews();
        initListeners();
    }

    private void initViews()
    {
        mProgressBar1 = findViewById(R.id.progress1);
        mProgressBar2 = findViewById(R.id.progress2);
        mProgressBar3 = findViewById(R.id.progress3);

        mTotal1 = findViewById(R.id.tv_total1);
        mTotal2 = findViewById(R.id.tv_total2);
        mTotal3 = findViewById(R.id.tv_total3);
        mRemaining1 = findViewById(R.id.tv_remaining1);
        mRemaining2 = findViewById(R.id.tv_remaining2);
        mRemaining3 = findViewById(R.id.tv_remaining3);

        mTotal = findViewById(R.id.et_total);
        mRemaining = findViewById(R.id.et_remaining);

        mCommit =findViewById(R.id.btn_commit);
    }

    private void initListeners() {
        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = Integer.parseInt(mTotal.getText().toString().trim());
                final int remaining = Integer.parseInt(mRemaining.getText().toString().trim());

                mProgressBar1.setMax(total);
               // mProgressBar1.setProgress(remaining);
                mRemaining1.setText(String.valueOf(remaining));
                mTotal1.setText(String.valueOf("of " + total));

                mProgressBar2.setMax(total);
                mProgressBar2.setProgress(remaining);
                mRemaining2.setText(String.valueOf(remaining));
                mTotal2.setText(String.valueOf("of " + total));

                mProgressBar3.setMax(total);
                mProgressBar3.setProgress(remaining);
                mRemaining3.setText(String.valueOf(remaining));
                mTotal3.setText(String.valueOf("of " + total));



                ProgressBarActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ObjectAnimator animation = ObjectAnimator.ofInt(mProgressBar1, "progress",0, remaining);
                        animation.setDuration(300);
                        animation.setInterpolator(new DecelerateInterpolator());
                        animation.start();
                    }
                });
            }
        });
    }

}
