package mketour;

public interface Subject {

    /**
     * Add observer to observer list
     * @param observer type of challenge
     */
    void attach(Observer observer);
    /**
     * removed observer from the list
     */
    void detach(Observer observer);

    /**
     * Goes through the observer and calls in update
     * @param tag1 the entity that hit
     * @param tag2 the entity that got hit
     */
    void notifyObservers(Taggable tag1, Taggable tag2);
}
