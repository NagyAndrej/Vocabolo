import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends Application {

    Insets insets = new Insets(0,10,0,10);
    dataWriter data = new dataWriter();
    String adresa = "adresa programu...";
    String adresaObrazku = "adresa obrazku...";

    //STAGES
    Stage stage1;
    Stage stage2;
    Stage stageCvicenie;
    Stage stageZapisovanie;

    //CONTAINERS
    VBox container1;
    VBox container2;
    GridPane containerCvicenie;
    GridPane containerZapisovanie;


    //CHOSEN LANGUAGE
    String jazyk;

    public TextField jazykText;
    public Button saveBut;
    public Button plusBut;

    @Override
    public void start(Stage stage) throws Exception{
       prveOkno();
    }


    public static void main(String[] args) {
        launch(args);
    }

    //FIRST WINDOW
    public void prveOkno() throws FileNotFoundException {
        stage1 = new Stage();
        container1 = new VBox();
        Scene scene = new Scene(container1);

        stage1.setTitle("Vocabolo");
        stage1.setWidth(330);
        stage1.setHeight(500);

        Image image = new Image(new FileInputStream(adresaObrazku));
        ImageView imageView = new ImageView(image);

        //TextField - jazykText
        {
            jazykText = new TextField("Jazyk");
            jazykText.setMaxWidth(150);
            jazykText.setMinWidth(150);
            jazykText.setVisible(false);
        }

        //Button - save
        {
            saveBut = new Button("Save");
            saveBut.setVisible(false);
            saveBut.setOnAction(e -> {
                try {
                    onSaveClick();
                    stage1.close();
                    prveOkno();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }

        //Button plus
        {
            plusBut = new Button("+");
            plusBut.setVisible(true);
            plusBut.setOnAction(e -> {
                onPlusClick();
            });
        }

        //CONTAINER - GET CHILDREN,SPACING
        {
            container1.getChildren().addAll(imageView);
            container1.getChildren().addAll(jazykText, saveBut, plusBut);
            container1.setSpacing(5);
            container1.setPadding(insets);
            container1.setStyle("-fx-background-color: #c7a9c2;");
        }

        startOkno1();

        stage1.setScene(scene);
        stage1.show();

    }
    //SECOND WINDOW
    public void druheOkno(){
        stage2 = new Stage();
        stage2.setWidth(200);
        stage2.setHeight(400);

        //NASTAVENIA MEDZIER MEDZI CONTROLLORMI
        container2 = new VBox();
        container2.setPadding(insets);
        container2.setSpacing(5);
        Scene scene = new Scene(container2);

        Label text = new Label("Vyberte z mozností: ");
        Button cvicit = new Button("Precvičovať");
        Button zapisat = new Button("Zapísať");
        Button spat = new Button("Späť");
         
        //BUTTON FUNCTIONS
        cvicit.setOnAction(e->{
            try {
                oknoCvicenie();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            stage2.close();
        });

        zapisat.setOnAction(e->{
            oknoZapisovanie();
            stage2.close();
        });

        spat.setOnAction(e->{
            try {
                prveOkno();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            stage2.close();
        });

        container2.getChildren().addAll(text, cvicit,zapisat,spat);
        container2.setStyle("-fx-background-color: #c7a9c2;");

        stage2.setScene(scene);
        stage2.show();
    }

    //WINDOW FOR EXERCISING
    public void oknoCvicenie() throws FileNotFoundException {
        methods met = new methods(jazyk);
        dataWriter data = new dataWriter();

        //NASTAVENIA MEDZIER MEDZI CONTROLLORMI
        stageCvicenie = new Stage();
        containerCvicenie = new GridPane();
        containerCvicenie.setPadding(insets);
        containerCvicenie.setHgap(5);
        containerCvicenie.setVgap(5);

        Scene scene2 = new Scene(containerCvicenie);
        stageCvicenie.setTitle(jazyk);

        //NASTAVENIA OKNA - CVICENIE
        stageCvicenie.setHeight(400);
        stageCvicenie.setWidth(400);

        met.index();                                                 //VYTVORENIE NOVEHO INDEXU(DOLEZITE PRE OBMIENANIE SLOVICOK)
        System.out.println(data.getFileLength(jazyk));


        Label preklad = new Label(met.getSlovoSk());
        Label spravnost = new Label();
        TextField text = new TextField();
        Button overenie = new Button("Overiť");
        Button spat = new Button("Späť");

        // PRACA S BUTTNOM NA OVERENIE
        overenie.setOnAction(e->{
            try {
                if(met.getSlovo().equals(text.getText())){
                    spravnost.setText("Správne");
                    met.index();                                    //OPAT NOVY INDEX
                    preklad.setText(met.getSlovoSk());
                    text.setText("");
                }else{
                    spravnost.setText("Nesprávne");
                    text.setText("");
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        //VRATENIE DO PREDCHADZAJUCEHO OKNA
        spat.setOnAction(e->{
            druheOkno();
            stageCvicenie.close();
        });

        //CONTAINER - ADD ALL CONTROLLERS
        {
            containerCvicenie.add(preklad, 1, 1);
            containerCvicenie.add(text, 1, 2);
            containerCvicenie.add(overenie, 2, 2);
            containerCvicenie.add(spravnost, 1, 3);
            containerCvicenie.add(spat, 2, 3);
            containerCvicenie.setStyle("-fx-background-color: #c7a9c2;");
        }


        stageCvicenie.setScene(scene2);
        stageCvicenie.show();

    }

    public void oknoZapisovanie(){
        stageZapisovanie = new Stage();
        containerZapisovanie = new GridPane();
        containerZapisovanie.setPadding(new Insets(10,10,10,10));
        containerZapisovanie.setVgap(5);
        containerZapisovanie.setHgap(5);


        Scene scene2 = new Scene(containerZapisovanie);
        stageZapisovanie.setTitle(jazyk);

        stageZapisovanie.setHeight(400);
        stageZapisovanie.setWidth(400);

        //CONTROLLERS

            TextField slovoSk = new TextField();
            TextField slovo = new TextField();
            Label textSk = new Label("Slovo");
            Label text = new Label("Preklad");
            Button save = new Button("Uložiť");
            Button back = new Button("Späť");

            save.setOnAction(e->{
                try {
                    data.writeFileSk(jazyk,slovoSk.getText());
                    data.writeFileForeign(jazyk,slovo.getText());
                    slovoSk.setText("");
                    slovo.setText("");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            back.setOnAction(e->{
                druheOkno();
                stageZapisovanie.close();
            });

            //CONTAINER - ADD ALL CONTROLLERS
        {
            containerZapisovanie.add(textSk, 1, 1);
            containerZapisovanie.add(text, 1, 2);
            containerZapisovanie.add(slovoSk, 2, 1);
            containerZapisovanie.add(slovo, 2, 2);
            containerZapisovanie.add(save, 1, 3);
            containerZapisovanie.add(back, 1, 4);
            containerZapisovanie.setStyle("-fx-background-color: #c7a9c2;");
        }

        stageZapisovanie.setScene(scene2);
        stageZapisovanie.show();

    }



    public void onPlusClick(){                                         //ADD NEW LANGUAGE
        jazykText.setVisible(true);
        saveBut.setVisible(true);

    }

    public void onSaveClick() throws IOException {                     //SAVE NEW LANGUAGE
        String jazyk = jazykText.getCharacters().toString();
        data.createFileForeign(jazyk);
        data.createFileSK(jazyk);

        jazykText.setVisible(false);
        saveBut.setVisible(false);

    }

    public void startOkno1(){

        int pocitadlo = 0;
        File folder = new File(adresa);
        File[] listOfFiles = folder.listFiles();

        //zisti pocet folderov vo Vocabole(jazykov)
        for (File file : listOfFiles) {
            if(file.getName().contains("Sk1")){

            }else{
                Button novy = new Button(file.getName().replace(".txt", ""));
                container1.getChildren().add(novy);
                novy.setId(file.getName().replace(".txt", ""));
                novy.setOnAction(e->{
                    druheOkno();
                    jazyk = novy.getId();
                    stage1.close();
                });
            }
        }

    }


}
