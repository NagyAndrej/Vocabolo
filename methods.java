import java.io.FileNotFoundException;
import java.util.Random;

public class methods {

dataWriter data = new dataWriter();
      String jazyk;
      int index;


public methods(String jazyk){
    this.jazyk = jazyk;

    }


public boolean overenie(String slovo) throws FileNotFoundException {                //CHECK IF THE TRANSLATION IS CORRECT

    if(getSlovo().equals(slovo)){
        return true;
    }else
        return false;

}

public String getSlovoSk() throws FileNotFoundException {                           //GET WORD IN SLOVAK IN SPECIFIC INDEX

    System.out.println("index: "+ index);
    String slovo = data.readFileSk(jazyk,index);


    return slovo;

}

public String getSlovo() throws FileNotFoundException {                             //GET WORD IN FOREIGN LANGUAGE IN SPECIFIC INDEX

          String slovo = data.readFileForeign(jazyk,index);

          return slovo;
}

public void index() throws FileNotFoundException {                                  //GET RANDOM INDEX
    Random rand = new Random();
    int index = rand.nextInt(data.getFileLength(jazyk));
    this.index = index;

}

public int getIndex(){
         return index;
}

}
