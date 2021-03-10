package aidan.hughes.speechtimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
{
    private boolean frag = false;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ListFragment list;
    private ControlFragment control;
    private Button swap;
    private boolean landscape;
    private FrameLayout frame;
    private FrameLayout frame2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
        {
            initialize();
        }
    }

    public void initialize()
    {
        frag = false;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            landscape = false;
        }
        else
        {
            landscape = true;
        }
        frame = (FrameLayout) findViewById(R.id.frame);
        frame2 = (FrameLayout) findViewById(R.id.frame2);
        swap = (Button) findViewById(R.id.swap);
        swap.setText(">>>");
        fm = getSupportFragmentManager();
        list = new ListFragment();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame2, list);
        ft.commit();
        control = new ControlFragment(list);
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, control);
        ft.commit();
        frame2.setVisibility(View.INVISIBLE);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switchFragment();
            }
        });
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        landscape = !landscape;
        if (landscape)
        {
            swap.setVisibility(View.INVISIBLE);
            frame.setVisibility(View.VISIBLE);
            frame2.setVisibility(View.VISIBLE);
        }
        else
        {
            frag = !frag;
            switchFragment();
            swap.setVisibility(View.VISIBLE);
        }
    }

    public void switchFragment()
    {
        if (!frag)
        {
            frame.setVisibility(View.INVISIBLE);
            frame2.setVisibility(View.VISIBLE);
            swap.setText("<<<");

        }
        else
        {
            swap.setText(">>>");
            frame2.setVisibility(View.INVISIBLE);
            frame.setVisibility(View.VISIBLE);
        }
        frag = !frag;
    }
}