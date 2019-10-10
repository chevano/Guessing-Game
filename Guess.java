import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Guess
{
    public static boolean isString(String isString)
    {
        boolean result  =   false;
        int isDigit     =   0;
        
        try
        {
            for (int i = 0; i < isString.length(); i++){
                if (Character.isDigit(isString.charAt(i)))
                    isDigit++;
            }
        }
        catch(Exception e)
        {
            System.err.println("Error Message: " + e.getMessage());
            
        }
        
        if (isDigit == 0)
            result=true;
        
        return result;
    }// checks if a String contains only letters
    
    public static String[] gameResume(String filename, int remainingTries, int numOfAttempts, int validAttempts, int wordLocation, String[] lines, int size )
    {
        
        Scanner scanner  = null;
        File file        = new File(filename);
        int counter      = 1;
        String firstWord = "";              // used as the size of the String array(lines) after converting it to an integer value
        
        try
        {
            scanner          = new Scanner(file);
            //firstWord        = scanner.nextLine();
            //size             = Integer.parseInt(firstWord.trim());
            //lines            = new String[size];
            //lines[0]         = firstWord;
            
            //System.out.println("size in read = " + size);
            while (scanner.hasNextLine())
            {
                String line       = scanner.nextLine();
                lines[counter]    = line;
                
                counter++;
            }
            printInfo(lines, remainingTries, numOfAttempts, validAttempts);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File doesn't exist");
        }
        return lines;
    }                                   //It is used to load the previous progression of the game
    
    public static void printInfo(String[] arr, int remainingTries, int numOfAttempts, int validAttempts)
    {
        int doesNothing = 0;
        System.out.println("Number of Attempts        = " + arr[arr.length - 3]);
        System.out.println("Number of valid Attempts  = " + arr[arr.length - 2]);
        System.out.println("Number of Remaining Tries = " + arr[arr.length - 1]);
        
        for(int i = 0; i<arr.length-5; i++)
        {
            if((i == 0) && (arr[i] != null))
                System.out.print("Previous guesses were     = " + arr[i]);
            else if(arr[i] != null)
                System.out.print("," + arr[i]);
            else
                doesNothing++;
        }
        System.out.println("");
        System.out.print("Which corresponds to ");
    }                                 //Used to display the last session of the game
    
    public static void gameProgress(String output, int remainingTries, int numOfAttempts, int validAttempts, int wordLength, String[] parts,  int fileSize, int wordIndex)
    {
        int counter             = 0;
        int doesNothing         = 0;
        int size                = wordLength + 5 ;
        fileSize                = 5;
        String[] newArray       = new String[100];
        File file               = new File(output);
        PrintWriter printWriter = null;
        
         try
            {
                printWriter = new PrintWriter(file);
               
                
                while(size != 0)
                {
                    if(parts[counter] == null || parts[counter].equals("1") || parts[counter].equals(""))
                        doesNothing++;
                    else
                        fileSize++;
                    
                    size--;
                    counter++;
                }
                
                //fileSize += validAttempts;
                
                System.out.println("size in write = " + size);
                
                for(int i = 0; i < counter; i++)
                {
                    newArray[i] = parts[i];
                }
                size                = fileSize;
                counter             = 0;
                while(size != 0 && validAttempts != 0)
                {
                    if(parts[counter] == null || parts[counter].equals("1"))
                    {
                        printWriter.println("");
                        printWriter.flush();
                    }
                    else
                    {
                        printWriter.println(parts[counter]);
                        printWriter.flush();
                    }
                    size--;
                    validAttempts--;
                    counter++;
                }
                
                printWriter.println(fileSize);
                printWriter.println(numOfAttempts);
                printWriter.println(validAttempts);
                printWriter.println(remainingTries);
                printWriter.println(wordIndex);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if ( printWriter != null )
                {
                    printWriter.close();
                }
            }
    }                       // Used to save the current state of the game as well as all the information that were used
    
    //10 --> regular String
    //9 --> reuglar String
    //8 --> Makes the String invisible(Not useful)
    //7 --> Does an Highlight of the entire String
    //6 --> regular String
    //5 --> blink
    //4 --> UnderLines
    //3 --> returns to regular String state
    //2 --> Makes the String Dull(lose its brightness)
    //1 --> Bold
    
    public static void main(String[] args)
    {
        StringBuilder sb        = new StringBuilder("");
        String s                = "_";
        Scanner input           = new Scanner(System.in);
        String myInput          = " ";
        char letter             = ' ';
        String output           = "Data.txt";
        int num1                = 0;
        int counter             = 0;
        int state               = 0;
        int arrSize             = 0;
        String newString        = "";
        
        Random r                = new Random(System.currentTimeMillis());
        Words myWord            = new Words(num1);
        int num                 = r.nextInt(myWord.wordSize);
        int numLoop             = num;
        myWord                  = new Words(num);
        String myString         = " ";
        String word             = myWord.getWord();
        int fileSize            = word.length() + 5;
        int length              = word.length();
        String[] parts          = new String[100];
        //String[] myParts        = new String[100];
        
        myWord.print("--------------------------------------------------------------------------------");
        myWord.print(String.format("%50s", "WELCOME TO HANGMAN"));
        myWord.print("Press 1. If you would like to save the current progress of this game and leave");
        myWord.print("Press 2. If you would like to continue from the last saved version of this game");
        myWord.print("Press any other key to begin a new Game");
        myWord.print("--------------------------------------------------------------------------------");
        
        myInput                 = input.next();
        
        for(int  i = 0; i < word.length(); i++)
            sb.append("_");
        
        s                     = sb.toString();
        int remainingTries    = word.length();
        int numOfAttempts     = 0;
        int validAttempts     = 0;
        
        if(myInput.equals("1"))
        {
            myWord.print("Saving... Goodbye!!!");
            gameProgress(output,remainingTries,numOfAttempts,validAttempts ,word.length(),parts,fileSize,num);
            System.exit(0);
        }
        else if(myInput.equals("2"))
        {
            myWord.print("loading saved version...");
            myWord.print("");
            parts = gameResume(output, remainingTries, numOfAttempts, validAttempts, word.length(),parts,fileSize);
            
            for(int i = 0; i < parts.length; i++)
                if(parts[i] != null)
                    counter++;
            
            System.out.println("Counter in main read = " + counter );
        
            numOfAttempts     = Integer.parseInt(parts[counter - 3].trim());
            validAttempts     = Integer.parseInt(parts[counter - 2].trim());
            remainingTries    = Integer.parseInt(parts[counter - 1].trim());
            num               = Integer.parseInt(parts[counter].trim());
            numLoop           = num;
            myWord            = new Words(num);
            word              = myWord.getWord();
            fileSize          = word.length() + 5;
            
            //sb.setLength(0);
            sb.delete(0,sb.length());
            System.out.println("This is read in main!!!");
            System.out.println("output = " + output);
            System.out.println("remainingTries = " + remainingTries);
            System.out.println("numOfAttempts = " + numOfAttempts);
            System.out.println("validAttempts = " + validAttempts);
            System.out.println("word = " + word);
            System.out.println("word.length() = " + word.length());
            System.out.println("parts.length = " + parts.length);
            for(String st: parts)
                System.out.println("parts = " + st);
            
            System.out.println("fileSize = " + fileSize);
            System.out.println("num = " + num);
            for(int  i = 0; i < word.length(); i++)
                sb.append("_");
            
            s                 = sb.toString();
            
            for(int i = 0; i < parts.length; i++)
                newString = newString + parts[i];
            
            
            for(int j = 0; j < parts.length; j++)
            {
                for(int i = 0; i < word.length(); i++)
                {
                    
                    if((myWord.getWord().toUpperCase().charAt(i)) == Character.toUpperCase(newString.charAt(j)) && (parts[i] != null))
                    {
                        System.out.println("newString.charAt(j) = " + newString.charAt(j));
                        sb.setCharAt(i,myWord.getWord().charAt(i));
                    }
                }
            }
    
            s                = sb.toString();
            myWord.print(s);
            myWord.print("");
            //parts          = new String[fileSize];
        }
        else
            myWord.print("");
        
            myWord.print("Please Enter a letter or a word:");
            myWord.print("\033[0;1mHint \033[0;3m");
            myWord.print("This word has " + word.length() + " letters");
            
            numLoop++;// lets the the indexing start at 1 instead of 0 (needed for the hints method)
            
            while(remainingTries != 0 && s.contains("_") && !s.equalsIgnoreCase(word))
            {
                myString        = input.next();
                
                if((letter >='a') && (letter <='z') || (letter >='A') && (letter <='Z') || (isString(myString) == true))
                {
                    remainingTries--;
                    validAttempts++;
                    
                    parts[counter]  = myString;
                    
                    if(myString.length() == 1)
                        letter      = myString.charAt(0);
                }
               
                
                for(int i = 0; i < myWord.getWord().length(); i++)
                {
                    if((myWord.getWord().toUpperCase().charAt(i)) == Character.toUpperCase(letter))
                        sb.setCharAt(i,myWord.getWord().charAt(i));
                }
                
                if(myString.equalsIgnoreCase(myWord.getWord()))
                {
                    //sb.setLength(0);
                    sb.delete(0,sb.length());
                    sb.append(myString);
                }
                
                s = sb.toString();
                
                if(s.contains("_") || !s.equalsIgnoreCase(myWord.getWord()))
                {
                    myWord.print("\033[0;1mHint \033[0;3m");
                    myWord.hints(numLoop);
                    numLoop *= 10; // use to iterate through the method hints.
                    s       = sb.toString();
                    myWord.print(s);
                }
                
                if(myString.equals("1"))
                {
                    state   = -1;
                    System.out.println("This is in the loop");
                    System.out.println("output = " + output);
                    System.out.println("remainingTries = " + remainingTries);
                    System.out.println("numOfAttempts = " + numOfAttempts);
                    System.out.println("validAttempts = " + validAttempts);
                    System.out.println("word.length() = " + word.length());
                    System.out.println("parts.length = " + parts.length);
                    for(String st: parts)
                         System.out.println("parts = " + st);
                   
                    System.out.println("fileSize = " + fileSize);
                     System.out.println("num = " + num);
                    gameProgress(output,remainingTries,numOfAttempts,validAttempts,length,parts,fileSize,num);
                    break;
                }
                numOfAttempts++;
                counter++;
            }
            
            if  (!s.contains("_")) // checks if all the letters are revealed
            {
                myWord.print("\033[0;5mCongratulation you won!!!\033[0;3m");
                myWord.print("\033[0;1mThe word is " + myWord.getWord() + "\033[0;3m");
            }
            else if(state == -1)
            {
                 myWord.print("Saving... Goodbye!!!");
            }
            else
            {
                myWord.print("\033[0;5mSorry!!! you lost\033[0;3m");
                myWord.print("\033[0;1mThe word was " + myWord.getWord() + "\033[0;3m");
            }
        }
}



