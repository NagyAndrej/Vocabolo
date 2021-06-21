import java.io.FileNotFoundException;
import java.util.Random;

public class methods {

dataWriter data = new dataWriter();
      String jazyk;
      int index;



      public methods(String jazyk){
        this.jazyk = jazyk;

    }


public boolean overenie(String slovo) throws FileNotFoundException {

          if(getSlovo().equals(slovo)){
              return true;
          }else
              return false;

}

public String getSlovoSk() throws FileNotFoundException {

    System.out.println("index: "+ index);
    String slovo = data.readFileSk(jazyk,index);


    return slovo;

}

public String getSlovo() throws FileNotFoundException {

          String slovo = data.readFileForeign(jazyk,index);

          return slovo;
}

public void index() throws FileNotFoundException {
    Random rand = new Random();
    int index = rand.nextInt(data.getFileLength(jazyk));
    this.index = index;

}

public int getIndex(){
         return index;
}

}
