/*
 * Course:     SWE 2410 - 111
 * Assignment: Lab 3
 * Author:     Dr. Yoder and Kelsey Collins
 */
package mketour;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mketour.actors.Person;


/**
 * Challenge class will display the challenge objective into the screen.
 * This class will also handle the update, checking if museum tagged person, then display the image
 */
public class MuseumChallenge implements Observer{
    public ImageView imageView;
    public Pane pane = new Pane();
    public VBox vBox;
    public Label label = new Label();

    /**
     * Displays the challenge description onto the pane
     */
    public MuseumChallenge(){
        vBox = new VBox();
        imageView = new ImageView();
        label.setText("Challenge: Find art ");
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        vBox.getChildren().add(label);
        vBox.getChildren().add(imageView);
        pane.getChildren().add(vBox);

    }

    /**
     * return the pane that displays the challenge
     * @return pane
     */
    public Pane getPane(){
        return pane;
    }

    /**
     * Displays the Wood Gather Art
     */
    public void completeChallenge(){
        imageView.setImage(new Image(CityMap.class.getResource("img/wood-gatherer.png").toString()));
        label.setText("Challenge: Find art \nArtistic work found: ");
    }

    @Override
    public void update(Taggable tag1, Taggable tag2) {
        if(tag1 instanceof Museum && tag2 instanceof Person){
            this.completeChallenge();
        }
    }
}
