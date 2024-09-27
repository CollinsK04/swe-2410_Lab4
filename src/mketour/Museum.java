/*
 * Course:     SWE 2410 - 111
 * Assignment: Lab 3
 * Author:     Dr. Yoder and Kelsey Collins
 */
package mketour;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mketour.actors.MobileEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A rectangular area representing an art museum.
 *
 * The Museum is similar to a MobileEntity, except that it does not move
 * and it has a rectangular rather than circular tag area.
 */
public class Museum implements Taggable, Subject {
    public static final int MUSEUM_WIDTH = 40;
    public static final int MUSEUM_HEIGHT = 50;
    public static final int MUSEUM_LEFT_CORNER = 500-64;
    public static final int MUSEUM_TOP_CORNER = 250+3;
    private final Rectangle area;
    private List<Observer> observers;


    public Museum(CityMap cityMap) {
        area = new Rectangle(MUSEUM_WIDTH, MUSEUM_HEIGHT);
        area.setStroke(Color.RED);
        area.setFill(Color.RED.deriveColor(1, 1, 1, 0.2));
        area.relocate(MUSEUM_LEFT_CORNER, MUSEUM_TOP_CORNER);

        cityMap.addNodeToMap(area);
        cityMap.addTaggableToMap(this);
        observers = new ArrayList<>();
    }

    /**
     * Determines if point falls within the rectangular region shown on the map
     * @param location The location to check
     * @return true of within region
     */
    @Override
    public boolean isTagged(Point2D location) {
        return location.getX() > MUSEUM_LEFT_CORNER
                && location.getY() > MUSEUM_TOP_CORNER
                && location.getX() < MUSEUM_LEFT_CORNER + MUSEUM_WIDTH
                && location.getY() < MUSEUM_TOP_CORNER + MUSEUM_HEIGHT;
    }

    /**
     * Respond to being tagged by another entity.
     * @param entity The entity performing the tagging.
     */
    @Override
    public void taggedBy(MobileEntity entity) {
        // TODO: Replace with your code.
        notifyObservers(this,entity);
    }

    /**
     * @return a unique description of this Museum, including a hashcode
     */
    @Override
    public String toString() {
        return "Museum "+Integer.toHexString(super.hashCode());
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }


    /**
     * Loops through the list of observers and checks if museum tags the person
     * @param tag1 museum
     * @param tag2 other object
     */
    @Override
    public void notifyObservers(Taggable tag1, Taggable tag2){
        for(int i = 0; i < observers.toArray().length;i++){
            observers.get(i).update(tag1,tag2);
        }
    }

}
