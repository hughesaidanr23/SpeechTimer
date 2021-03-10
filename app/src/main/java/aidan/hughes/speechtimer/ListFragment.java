package aidan.hughes.speechtimer;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ListFragment extends Fragment
{
    private String text;
    private String landscapetext;
    private String portraittext;
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
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && count > 14)
        {
            String[] str = text.split("\\n");
            landscapetext = "";
            for (int i = count - 14; i < count; i++)
            {
                landscapetext = landscapetext + str[i] + "\n";
            }
        }
        else if (count > 25)
        {
            String[] str = text.split("\\n");
            portraittext = "";
            for (int i = count - 25; i < count; i++)
            {
                portraittext = portraittext + str[i] + "\n";
            }
        }
    }

    public String onRotation()
    {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && count > 14)
        {
            String[] str = text.split("\\n");
            landscapetext = "";
            for (int i = count - 14; i < count; i++)
            {
                landscapetext = landscapetext + str[i] + "\n";
            }
            return landscapetext;
        }
        else if (count > 25)
        {
            String[] str = text.split("\\n");
            portraittext = "";
            for (int i = count - 25; i < count; i++)
            {
                portraittext = portraittext + str[i] + "\n";
            }
            return portraittext;
        }
        return text;
    }

    public void reset()
    {
        count = 0;
        text = "";
    }

    public String getText()
    {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && count > 14)
        {
            return landscapetext;
        }
        else if (count > 25) {
            return portraittext;
        }
        return text;
    }

    public TextView getTextView()
    {
        return list;
    }
}