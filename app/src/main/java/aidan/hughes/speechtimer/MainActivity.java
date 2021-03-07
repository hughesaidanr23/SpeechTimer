package aidan.hughes.speechtimer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity
{
    private boolean frag = true;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private ListFragment list;
    private ControlFragment control;
    private Button swap;
    private boolean landscape;
    FrameLayout controlFrame;
    FrameLayout frame;
    FrameLayout listFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlFrame = (FrameLayout) findViewById(R.id.controlFrag);
        listFrame = (FrameLayout) findViewById(R.id.listFrag);
        frame = (FrameLayout) findViewById(R.id.frame);
        swap = (Button) findViewById(R.id.swap);
        swap.setText(">>>");
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        list = new ListFragment();
        ft.replace(R.id.frame, list);
        ft.commit();
        control = new ControlFragment(list);
        switchFragment();
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragment();
            }
        });
        landscape = false;
        //changeOrientation();
    }

    public void switchFragment()
    {
        ft = fm.beginTransaction();
        if (!frag) {
            ft.replace(R.id.frame, list);
            swap.setText("<<<");
        } else {
            ft.replace(R.id.frame, control);
            swap.setText(">>>");
        }
        ft.commit();
        frag = !frag;
    }

    public void changeOrientation()
    {
        if (landscape)
        {
            ft = fm.beginTransaction();
            ft.replace(R.id.controlFrag, control);
            ft.commit();
            ft = fm.beginTransaction();
            ft.replace(R.id.listFrag, list);
            ft.commit();
            frame.setVisibility(View.INVISIBLE);
            controlFrame.setVisibility(View.VISIBLE);
            listFrame.setVisibility(View.VISIBLE);
        }
        else
        {
            switchFragment();
            frame.setVisibility(View.VISIBLE);
            controlFrame.setVisibility(View.INVISIBLE);
            listFrame.setVisibility(View.INVISIBLE);
        }
    }
}