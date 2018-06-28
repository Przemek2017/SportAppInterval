package pl.interval.model;

/**
 * Created by Przemek on 2018-03-27.
 */

public class Interval {
    public int id;
    public String training;
    public String description;
    public int thumbnail;
    public boolean isSelected;

    public Interval(int id, String training, String description, int thumbnail, boolean isSelected) {
        this.id = id;
        this.training = training;
        this.description = description;
        this.thumbnail = thumbnail;
        this.isSelected = isSelected;
    }

    public Interval() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

//    @Override
//    public String toString() {
//        return "Training " + training;
//    }
}