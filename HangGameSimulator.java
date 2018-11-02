import java.util.*;
import java.io.*;

public class HangGameSimulator{
   public static void main (String[] args) throws IOException{
      
      
      char g;
      
      Scanner kb = new Scanner(System.in);
      Scanner inFile;    
      
      boolean requestedRepeat = true;
      do{
      GuessWord guess = new GuessWord();
      StringBuilder usedChar = new StringBuilder();
      boolean[] used = new boolean[255];
      String randomChar = guess.getRandWord(kb);
      guess.setPhrase(randomChar);
      
      
      System.out.println("\n" + guess.toString());
      
      while(!guess.checkWin() && !guess.getGameOver()){
         do{
            System.out.println("\nEnter Your character: ");
            g = kb.nextLine().charAt(0);
         }while(used[g]);
         
         used[g] = true;
         usedChar.append(g);
         guess.find(g);
         System.out.println("\n" + guess.toString());
         guess.lettersUsed(usedChar);
      }
      
      if (guess.checkWin()){
         System.out.println("You guessed a words !!! You win !!!");
      }
      
      if (guess.getGameOver()){
         System.out.println("You are failed to guess the words");
         System.out.println("Game Over !!!");
         System.out.println("Game Over !!!");
         System.out.println("Game Over !!!");
      }
      
      System.out.println("Do you want to play again? Enter y: Yes || n : No");
      String userChoice = kb.nextLine();
      char uChoice = userChoice.charAt(0);
      if(uChoice == 'n')
      {
         requestedRepeat = false;
      }else{
         requestedRepeat = true;
      }
   }while(requestedRepeat == true);
   System.out.println("Program are quit!!! ");
   }// end main

}// end class