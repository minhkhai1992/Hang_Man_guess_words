import java.util.*;
import java.io.*;
import java.lang.*;

public class GuessWord{
   
   private String phrase; // guess phrase goal
   private StringBuilder inProgress; // The blanks the user try to match the phrase
   private StringBuilder state;  // The state of the hanged man
   private int numberWrongGuesses;  // The state of the game loss
   private boolean gameOver;

   public GuessWord(){
      phrase = new String();              // default string phrase as a new string
      inProgress = new StringBuilder();  // default Stringbuilder as a new StringBuilder
      state = new StringBuilder();        // default Stringbuilder as a new StringBuilder 
      numberWrongGuesses = 0;             // default number user guess wrong as 0
      gameOver = false;                   // default game Over as false
   }

   public GuessWord(String gPhrase){
      phrase = new String(gPhrase);
      state = new StringBuilder();
      inProgress = new StringBuilder();
      for (int i = 0; i < phrase.length(); i++){
         if(phrase.charAt(i) < 'A' || (phrase.charAt(i) > 'Z' && phrase.charAt(i) < 'a') || phrase.charAt(i) > 'z')
         {
            inProgress.append(phrase.charAt(i) + " ");
         }else{
            inProgress.append( "_ ");
         }
      
      }
      numberWrongGuesses = 0;
      gameOver = false;
   }
   
   public void setPhrase(String pToGuess){
      phrase = new String(pToGuess);
      state = new StringBuilder();
      inProgress = new StringBuilder();
      
      for (int i = 0; i < phrase.length(); i++){
         if (phrase.charAt(i) < 'A' || (phrase.charAt(i) > 'Z' && phrase.charAt(i) < 'a') || phrase.charAt(i) > 'z'){
            inProgress.append(phrase.charAt(i) + " ");
         }else{
            inProgress.append("_ ");
         }    
      }
      numberWrongGuesses = 0;
      state = new StringBuilder();
      gameOver = false;    
   }
   
   public String getRandWord(Scanner k)throws IOException {
      String categoryFileName = "";
      char userChoice;
      String randomPhrase = "";
      Scanner readFile;
      
      do{
      System.out.println("Please choose from the following categories:"
                        + "\n 1/ Animal"
                        + "\n 2/ Car Brands"
                        + "\n 3/ Disney Movies"
                        + "\n 4/ Foods"
                        + "\n 5/ Halloween"
                        + "\n 6/ Quit !!!"        
                        );
      userChoice = k.nextLine().charAt(0);
      }while(userChoice < '1' || userChoice > '6');
      
      
      
      switch(userChoice){
            case '1': categoryFileName = "Animal.txt"; break;
            case '2': categoryFileName = "CarBrands.txt"; break;
            case '3': categoryFileName = "DisneyMovies.txt"; break;
            case '4': categoryFileName = "Foods.txt"; break;
            case '5': categoryFileName = "Halloween.txt"; break;
            default: System.out.println("Program are quit !"); break;      
         }//end switch
      
      
      
      readFile = new Scanner(new File(categoryFileName));
      
      int randomPick = (int)(Math.random() *25) + 1;
      for(int i = 0; i < randomPick; i++){
         randomPhrase = readFile.nextLine();
      
      }
      readFile.close();   
      return randomPhrase;
   }
   
   public boolean getGameOver(){
      return gameOver;
   }   
   
   public boolean checkWin(){
      boolean match = true;
      int currentLetter = 0;
      
      while(match == true && (currentLetter < phrase.length())) {
         if (phrase.charAt(currentLetter) != inProgress.charAt(currentLetter*2)) {
            match = false;
         } else {
            currentLetter++;
         }
      
      
      }
      return match;
   
   }

   public void lettersUsed(StringBuilder letters){
      System.out.print("Letter used: [");
      for(int i = 0; i < letters.length(); i++){
         System.out.print(letters.charAt(i));
         System.out.print(",");
      }
      System.out.println("]");
   
   }
   
   public int length(){
      return phrase.length();
   }
   
   public void find(char s)
    {
        int count = 0;
        int pos = 0;
        String lowercasePhrase = phrase.toLowerCase();
        pos = lowercasePhrase.indexOf(s);
        while(pos != -1)
        { 
          count++;
          inProgress.setCharAt(pos*2,phrase.charAt(pos));
          pos = lowercasePhrase.indexOf(s, pos + 1);
        }
        if(count == 0)
        {
            numberWrongGuesses++;
            updateState( );
        }
    }
   
   private void updateState(){
      switch(numberWrongGuesses){
         case 1: state.append("\n\t\t\t\t\t 0");
                   break;
           case 2: state.append( "\n\t\t\t\t       /");
                   break;
           case 3: state.append(" |");
                   break;
           case 4: state.append( " \\");
                   break;
           case 5: state.append("\n\t\t\t\t         |");
                   break;
           case 6: state.append("\n\t\t\t\t        /");
                   break;
           case 7: state.append(" \\\n\n\t\t\t\t      OH NO!!\n");
                   gameOver = true;
      }
   }
   
   
   
   
   public String toString(){
      return new String("\n********************************************\n" 
                        + inProgress + "\n" + state.toString());
   }
   
   
   
}//end class



