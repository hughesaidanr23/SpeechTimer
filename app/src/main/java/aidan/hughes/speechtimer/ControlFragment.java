package aidan.hughes.speechtimer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ControlFragment extends Fragment
{
    Button reset;
    Button lap;
    Button start;
    Timer timer;
    TextView time;
    boolean running;
    boolean paused;
    View v;
    ListFragment list;
    public ControlFragment(ListFragment x)
    {
        running = false;
        paused = false;
        list = x;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_control, container, false);
        }
        reset = (Button) v.findViewById(R.id.reset);
        lap = (Button) v.findViewById(R.id.lap);
        start = (Button) v.findViewById(R.id.start);
        time = (TextView) v.findViewById(R.id.time);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lap();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        return v;
    }
    public void start()
    {
        if (!running) {
            timer = new Timer(time, list, list.getTextView());
            timer.execute();
            running = true;
        }
        if (!paused)
        {
            start.setText("Stop");
        }
        else
        {
            start.setText("Start");
        }
        timer.pause(paused);
        paused = !paused;
    }

    public void lap()
    {
        if (running)
        {
            timer.lap();
        }
    }

    public void reset()
    {
        timer.cancel(true);
        running = false;
        list.reset();
        start.setText("Start");
        paused = false;
    }

}