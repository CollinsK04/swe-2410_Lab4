/*
 * Course:     SWE 2410 - 111
 * Assignment: Lab 4
 * Author:     Dr. Yoder and Kelsey Collins
 * Date: 9/26/2024
 */
package mketour;



import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import mketour.actors.Bus;
import mketour.actors.Car;
import mketour.actors.Person;

/**
 * Easter Egg Challenge is a challenge if the person tags a bus, and have to gather
 * letter to create the word bus
 */
public class EasterEggChallenge implements Observer {
    private String store_Answer = "";
    private final String target_Word = "BUS";
    private final Pane pane;
    private Label label_Challenge;
    private Label label_Found;
    private VBox vBox;
    private boolean visible = false;

    public EasterEggChallenge(){
        pane = new Pane();
        label_Challenge = new Label();
        label_Found = new Label();
        vBox = new VBox();
        label_Challenge.setText("Easter Egg Challenge: Find all the letters in BUS");
        label_Found.setText("Goal: ***");
        vBox.getChildren().add(label_Challenge);
        vBox.getChildren().add(label_Found);
        pane.getChildren().add(vBox);
        pane.setVisible(false);
    }

    /**
     * Sorts through the license plate and checks if any of the letters matches the target word
     * @param license_Plate cars
     */
    public void sort_LicensePlate(String license_Plate){
        String plate = license_Plate.substring(7);
        plate.replace(" ","");
        license_Plate = plate;
        for(int i = 0; i < license_Plate.length(); i++){
            if(target_Word.indexOf(license_Plate.charAt(i)) >= 0 && store_Answer.indexOf(license_Plate.charAt(i)) < 0){
                store_Answer += license_Plate.charAt(i);
                System.out.println(store_Answer);
                displayFound();
            }
        }
    }

    /**
     * Display the letters that were found.
     */
    public void displayFound(){
        String wordFound = "Goal: ";
        if(store_Answer.contains("B")){
            wordFound += "B";
        }else{
            wordFound += "*";
        }
        if(store_Answer.contains("U")){
            wordFound += "U";
        }else{
            wordFound += "*";
        } if(store_Answer.contains("S")){
            wordFound += "S";
        }else{
            wordFound += "*";
        }
        label_Found.setText(wordFound);
        checkChallengeComplete();
    }

    /**
     * checks if the user found all the words, if they did than it will display complete
     */
    private void checkChallengeComplete(){
        if(label_Found.getText().contains(target_Word)){
            label_Challenge.setText("EasterEgg CHALLENGE COMPLETED!");
        }
    }

    /**
     * return the pane that displays the challenge
     * @return pane
     */
    public Pane getPane(){
        return pane;
    }

    @Override
    public void update(Taggable tag1, Taggable tag2) {
        if(tag1 instanceof Person && tag2 instanceof Bus){
            pane.setVisible(true);
            visible = true;
        }
        if((tag1 instanceof Person && tag2 instanceof Car) && visible){
            sort_LicensePlate(((Car) tag2).getName());
        }
    }

}
