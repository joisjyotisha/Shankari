package ui;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import sql.Country;
public class NewInfo {
    public static void takeInfo(){
        Stage info = new Stage();
        info.initModality(Modality.APPLICATION_MODAL);          //Refer Documentation
        info.setTitle("Info");
        info.setMinWidth(300);
        info.setMinHeight(300);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));


        Label date = new Label("Date:");
        grid.add(date,0,0);

        TextField dd = new TextField();
        dd.setId("dd");
        dd.setPromptText("dd");
        dd.setStyle("-fx-pref-width:3em;-fx-pref-height:2em;");
        grid.add(dd,1,0,1,1);

        TextField mm = new TextField();
        mm.setPromptText("mm");
        mm.setStyle("-fx-pref-width:3em;-fx-pref-height:2em;");
        grid.add(mm,2,0,1,1);

        TextField yyyy = new TextField();
        yyyy.setPromptText("yyyy");
        yyyy.setStyle("-fx-pref-width:3.8em;-fx-pref-height:2em;");
        grid.add(yyyy,3,0,1,1);

        Label time = new Label("Time");
        grid.add(time,0,1,1,1);

        TextField hrs = new TextField();
        hrs.setPromptText("HH");
        hrs.setStyle("-fx-pref-width:3em;-fx-pref-height:2em;");
        grid.add(hrs,1,1,1,1);

        TextField min = new TextField();
        min.setPromptText("MM");
        min.setStyle("-fx-pref-width:3em;-fx-pref-height:2em;");
        grid.add(min,2,1,1,1);

        Label country =  new Label("Country:");
        grid.add(country,0,3,1,1);
        Country con = new Country();
        ObservableList<String> observableList = FXCollections.observableList(Country.getCountryList());

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        observableList.sorted()
                );

        String c = new String();
        ComboBox<String> countryList = new ComboBox<String>(options);
        grid.add(countryList,1,3,4,1);
        /*
         * Set Action on Combo box
         */
        countryList.setOnAction(e->{
            Country.getPlace(countryList.getSelectionModel().selectedItemProperty().getValue());
            System.out.println(countryList.getSelectionModel().selectedItemProperty().getValue());
        });
        countryList.getSelectionModel().selectFirst();
        Label place = new Label("Place:");
        grid.add(place,0,4,1,1);

        String tmp = countryList.getSelectionModel().selectedItemProperty().getValue();
        ComboBox<String> placeList = new ComboBox<String>(
                FXCollections.observableArrayList(Country.getPlace(countryList.getSelectionModel().selectedItemProperty().getValue()))
        );
        grid.add(placeList,1,4,4,1);

        Button okay = new Button("OK");
        grid.add(okay,2,5,1,1);
        okay.setOnAction(event->loadnewInfo(dd,mm,yyyy,hrs,min,countryList,placeList));

        Scene scene = new Scene(grid);
        info.setScene(scene);
        info.showAndWait();
    }
    public static void loadnewInfo(TextField dd,TextField mm, TextField yyyy,
                                   TextField hrs, TextField min,
                                   ComboBox country,ComboBox place){
        double day = Double.valueOf(dd.getText());
        double mon  = Double.valueOf(mm.getText());
        double year = Double.valueOf(yyyy.getText());
        double hours = Double.valueOf(hrs.getText());
        double mins = Double.valueOf(min.getText());
        String cou = (String)country.getValue();
        String location = (String)place.getValue();





    }
}
