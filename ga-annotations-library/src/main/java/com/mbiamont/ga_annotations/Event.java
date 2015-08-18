package com.mbiamont.ga_annotations;

/**
 * Created by mbiamont on 04/08/15.
 */
public class Event {

    private String category;
    private String action;
    private String label;
    private long    value;

    private Event() {

    }

    public Event(String category, String action, String label, long value) {
        this.category = category;
        this.action = action;
        this.label = label;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }


    public static class Builder {
        private Event event = new Event();

        public Builder withCategory(String category) {
            event.setCategory(category);
            return this;
        }

        public Builder withAction(String action) {
            event.setAction(action);
            return this;
        }

        public Builder withLabel(String label) {
            event.setLabel(label);
            return this;
        }

        public Builder withValue(long value) {
            event.setValue(value);
            return this;
        }

        public Event build() {
            return event;
        }
    }
}