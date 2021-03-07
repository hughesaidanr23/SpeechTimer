package aidan.hughes.speechtimer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment
{
    private String text;
    private int count;
    TextView list;
    View v;

    public ListFragment()
    {
        text = "";
        count = 0;
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
        if (v == null)
        {
            v = inflater.inflate(R.layout.fragment_list, container, false);
        }
        list = (TextView) v.findViewById(R.id.lapList);
        list.setText(text);
        return v;
    }

    public void lap(String time)
    {
        count++;
        text = text + count + ". " + time + "\n";
    }

    public void reset()
    {
        count = 0;
        text = "";
    }


}