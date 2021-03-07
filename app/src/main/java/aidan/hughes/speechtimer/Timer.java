package aidan.hughes.speechtimer;

import android.os.AsyncTask;
import android.widget.TextView;

public class Timer extends AsyncTask
{
    int seconds;
    int minutes;
    int hours;
    int lapSeconds;
    int lapMinutes;
    int lapHours;
    boolean paused;
    TextView time;
    ListFragment list;
    public Timer(TextView x, ListFragment y)
    {
        super();
        seconds = 0;
        minutes = 0;
        hours = 0;
        lapSeconds = 0;
        lapMinutes = 0;
        lapHours = 0;
        time = x;
        list = y;
        paused = false;
    }

    protected Object doInBackground(Object[] objects)
    {
        while (!isCancelled())
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!paused) {
                seconds++;
                lapSeconds++;
                if (seconds == 60) {
                    seconds = 0;
                    minutes++;
                }
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                }
                if (lapSeconds == 60) {
                    lapSeconds = 0;
                    lapMinutes++;
                }
                if (lapMinutes == 60) {
                    lapMinutes = 0;
                    lapHours++;
                }
                this.publishProgress(String.format("%1$02d", hours) + ":" + String.format("%1$02d", minutes) + ":" + String.format("%1$02d", seconds));
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values)
    {
        super.onProgressUpdate(values);
        time.setText((String) values[0]);
    }

    @Override
    protected void onCancelled()
    {
        super.onCancelled();
        seconds = 0;
        minutes = 0;
        hours = 0;
        lapSeconds = 0;
        lapMinutes = 0;
        lapHours = 0;
        time.setText("00:00:00");
    }

    public void lap()
    {
        list.lap(String.format("%1$02d", lapHours) + ":" + String.format("%1$02d", lapMinutes) + ":" + String.format("%1$02d", lapSeconds));
    }

    public void pause(Boolean x)
    {
        paused = x;
    }
}
