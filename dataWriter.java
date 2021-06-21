import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.BufferOverflowException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class dataWriter {



       public void createFileSK(String nazov) throws IOException {
           try {
               File FileSk = new File("C:\\Users\\42191\\Desktop\\Vocabolo\\" + nazov + "Sk1.txt");
               FileSk.createNewFile();

           }catch (Exception e){
               e.getMessage();
           }

       }

    public void writeFileSk(String nazov, String slovo) throws IOException {
        FileWriter writerSK = new FileWriter("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+"Sk1.txt", true);
        slovo = slovo.toLowerCase();
        writerSK.append(slovo);
        writerSK.append("\n");
        writerSK.close();
       }

    public String readFileSk(String nazov, int index) throws FileNotFoundException {
        File skFile = new File("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+"Sk1.txt");
        Scanner scan = new Scanner(skFile);
        String slovo = null;
        int pocitadlo = 0;

        while(scan.hasNextLine()){

            System.out.println("1");
            if(pocitadlo==index){
                slovo = scan.nextLine();
                break;
            }
                scan.nextLine();
                pocitadlo++;


        }
        return slovo;

    }

    //Create File of Foreign dictionery

    public void createFileForeign(String nazov) throws IOException {

           try{
        File File = new File("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+".txt");
        File.createNewFile();

    }catch (Exception e){
        e.getMessage();
    }
    }

    public void writeFileForeign(String nazov, String slovo) throws IOException {
        FileWriter writerSK = new FileWriter("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+".txt",true);
        slovo = slovo.toLowerCase();
        writerSK.append(slovo);
        writerSK.append("\n");
         writerSK.close();

    }

    public String readFileForeign(String nazov, int index) throws FileNotFoundException {
        File File = new File("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+".txt");
        Scanner scan = new Scanner(File);
        String slovo = null;
        int pocitadlo = 0;

        while(scan.hasNextLine()){

            if(pocitadlo == index){
                slovo = scan.nextLine();
                break;
            }
                scan.nextLine();
                pocitadlo++;


        }
        return slovo;

    }



    public int getFileLength(String nazov) throws FileNotFoundException {
        File file = new File("C:\\Users\\42191\\Desktop\\Vocabolo\\"+nazov+".txt");
        Scanner scan = new Scanner(file);
        int pocitadlo = 0;

        while(scan.hasNext()){
            pocitadlo++;
            scan.nextLine();
        }
        return pocitadlo;

    }

    public void skuska(){
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
    }




}
