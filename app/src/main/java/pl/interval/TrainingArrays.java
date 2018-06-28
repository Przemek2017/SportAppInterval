package pl.interval;

import android.app.LauncherActivity;

import java.util.ArrayList;
import java.util.List;

import pl.interval.model.Interval;

/**
 * Created by Przemek on 2018-03-28.
 */

public class TrainingArrays {

    public static List<Interval> getIntervalList() {

        ArrayList<Interval> intervalList = new ArrayList<>();

        String[] trainingList = {
                "rolka",
                "przysiady",
                "brzuszki",
                "sztanga"
        };

        String[] descriptionList = {
                "ćwiczenie z rolką",
                "przysiady z hantlami",
                "brzuszki na podłodze",
                "przysiady ze sztangą"
        };

        Integer[] thumbnailList = {
                R.drawable.exercise,
                R.drawable.exercise1,
                R.drawable.exercise2,
                R.drawable.weightlifting
        };

        String[] gifsList = {
                "https://gfycat.com/GlisteningDistantDrever",
        };

        for (int i = 0; i < trainingList.length; i++) {
           Interval interval = new Interval();
           interval.training = trainingList[i];
           interval.description = descriptionList[i];
           interval.thumbnail = thumbnailList[i];
           intervalList.add(interval);
        }
        return intervalList;
    }

}
