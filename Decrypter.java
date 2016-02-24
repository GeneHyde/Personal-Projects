/* This is mainly here because it was something i worked on very early in my codeing career to make doing
 * this kind of thing quickly and eaily. Its defenitly still pretty ruff and I can see plenty of places where it could be
 * vastly improved in terms of speed, ease of use, harding, and abstraction.
 * Another thing about this is that the format is very spcific and can break it easily.
 * long before I knew of RegExp, and since it was only for my self i didn't worry about try catch structures.
*/
import java.util.*;

public class Decrypter
{
   public static void main(String...args)
   {
      int option;
      Scanner kb = new Scanner(System.in);
      
      option = displayMenu(kb);
      
      while (option != 7) //my menu
      {
         switch (option)
         {
            case 1:
               System.out.println(Caesar(phrase(kb)));
               break;
            case 2:
               System.out.println(Atbash(phrase(kb)));
               break;
            case 3:
               System.out.println(A1Z26(phrase(kb)));
               break;
            case 4:
               System.out.println(Combined(phrase(kb)));
               break;
            case 5:
               Vigenere(kb);
               break;
            case 6:
               AdvCaesar(kb);
               break;
         }
         option = displayMenu(kb);
      }
   }
   
   public static String phrase(Scanner kb)
   {
      System.out.println("Enter pharase to be decrypted");
      String crypt = kb.nextLine();
      crypt = crypt.toLowerCase();
      return crypt;
   }
   
   public static String Caesar(String crypt)
   {
      String decrypt = "";
         for(int i = 0; i < crypt.length(); i++)
         {
            char c = crypt.charAt(i);
            int num = toNum(c);
            if(!(num == 0))
               num = num + 24;
            if(num == 25 || num == 26 || num == 27)
               num = num - 1;
            decrypt = decrypt + toChar(num);
         }
         
         return decrypt;
   }
   //Atbash is basicly just reversing the alphabet, so A becomes Z and Z becomes A
   //could of also writen it using the % operation
   public static String Atbash(String crypt)
   {
      String encrypt = "";
      for(int i = 0; i < crypt.length(); i++)
      {
         char c = crypt.charAt(i);
         int num = toNum(c);
         if(num != 0)
            num = 27 - num;
         encrypt = encrypt + toChar(num);
      }   
      
      return encrypt;
   }
   
   //This one is pretty self explitory, A is a 1 and Z is a 26, the formating is pretty weird
   //to keep everything seprate you have - marks between the numbers and spaces between words (which i replace with -0-)
   public static String A1Z26(String crypt)
   {
      crypt = crypt.replace(" ", "-0-");
      String decrypt = "";
      while(true)
      {
    	  int dash = crypt.indexOf('-');
        String s;
        if(dash == -1)
         s = crypt.substring(0);
        else
    	   s = crypt.substring(0, dash);
        int i = Integer.parseInt(s);
    	  decrypt = decrypt + toChar(i);
        if(dash == -1)
         break;
        else
    	   crypt = crypt.substring(dash + 1);
      }
      return decrypt;
   
   }
   //As you may guess, this is a combnation of 3 different ciphers to form one.
   public static String Combined(String crypt)
   {
      crypt = A1Z26(crypt);
      crypt = Atbash(crypt);
      return Caesar(crypt);
   }
   
   //This one is a much less intuitive, this one requires a key and a phrase to be translated
   //and the letters in the key determin the shift that the phrase undergoes
   //A is a shift of 0 B is a shift of 1 and so on
   public static void Vigenere(Scanner kb)
   {
      crypt = phrase(kb);
      String decrypt = "";
      boolean good = false;
      while(!(good))
      {
         System.out.println("Enter key");
         String key = kb.nextLine();
         key = key.toLowerCase();
         int h = 0;
         for(int i = 0; i < crypt.length(); i++)//this could use work
         {
            if(!(h < key.length()))
               h = 0;
            char c = crypt.charAt(i);
            char k = key.charAt(h);
            int num = toNum(c);
            if(!(num == 0))
            {
               num = num - toNum(k) + 1;
               h++;
               if(num <= 0)
                  num = 26 + num;
            }
            decrypt = decrypt + toChar(num);
         }//I would probably do this loop differently now
         
         System.out.println(decrypt);
         System.out.println("Finished? (y/n)");
         String ok = kb.nextLine();
         if(ok.equals("y"))
            good = true;
         decrypt = "";
      }
   }
   
   public static void AdvCaesar(Scanner kb)
   {
      crypt = phrase(kb);
      String decrypt = "";
      boolean good = false;
      while(!(good))
      {
         System.out.println("Enter # to shift");
         int shift = kb.nextInt();
         kb.nextLine();
         for(int i = 0; i < crypt.length(); i++)
         {
            char c = crypt.charAt(i);
            int num = toNum(c);
            if(!(num == 0))
               num = num - shift;
            if(num < 0)
               num = 26 + num;
            decrypt = decrypt + toChar(num);
         }
         
         System.out.println(decrypt);
         System.out.println("Finished? (y/n)");
         String ok = kb.nextLine();
         if(ok.equals("y"))
            good = true;
         decrypt = "";
      }
   }
   
   public static int toNum(char c) //I very much hate this huge if, if else block
   {
       if(c == 'a')
          return 1;
       else if(c == 'b')
          return 2; 
       else if(c == 'c')
          return 3; 
       else if(c == 'd')
          return 4; 
       else if(c == 'e')
          return 5; 
       else if(c == 'f')
          return 6; 
       else if(c == 'g')
          return 7; 
       else if(c == 'h')
          return 8; 
       else if(c == 'i')
          return 9; 
       else if(c == 'j')
          return 10; 
       else if(c == 'k')
          return 11; 
       else if(c == 'l')
          return 12; 
       else if(c == 'm')
          return 13; 
       else if(c == 'n')
          return 14; 
       else if(c == 'o')
          return 15; 
       else if(c == 'p')
          return 16; 
       else if(c == 'q')
          return 17; 
       else if(c == 'r')
          return 18; 
       else if(c == 's')
          return 19; 
       else if(c == 't')
          return 20; 
       else if(c == 'u')
          return 21; 
       else if(c == 'v')
          return 22; 
       else if(c == 'w')
          return 23;
       else if(c == 'x')
          return 24; 
       else if(c == 'y')
          return 25; 
       else if(c == 'z')
          return 26;
       else
          return 0;
   }
   
   public static char toChar(int i) //This is a cut and paste of above, but in reverse
   {
          i = i % 27;
	       if(i == 1)
	          return 'a';
	       else if(i == 2)
	          return 'b'; 
	       else if(i == 3)
	          return 'c'; 
	       else if(i == 4)
	          return 'd'; 
	       else if(i == 5)
	          return 'e'; 
	       else if(i == 6)
	          return 'f'; 
	       else if(i == 7)
	          return 'g'; 
	       else if(i == 8)
	          return 'h'; 
	       else if(i == 9)
	          return 'i'; 
	       else if(i == 10)
	          return 'j'; 
	       else if(i == 11)
	          return 'k'; 
	       else if(i == 12)
	          return 'l'; 
	       else if(i == 13)
	          return 'm'; 
	       else if(i == 14)
	          return 'n'; 
	       else if(i == 15)
	          return 'o'; 
	       else if(i == 16)
	          return 'p'; 
	       else if(i == 17)
	          return 'q'; 
	       else if(i == 18)
	          return 'r'; 
	       else if(i == 19)
	          return 's'; 
	       else if(i == 20)
	          return 't'; 
	       else if(i == 21)
	          return 'u'; 
	       else if(i == 22)
	          return 'v'; 
	       else if(i == 23)
	          return 'w';
	       else if(i == 24)
	          return 'x'; 
	       else if(i == 25)
	          return 'y'; 
	       else if(i == 26)
	          return 'z';
	       else
	          return ' ';
   }
   //My simple menu
   private static int displayMenu(Scanner kb)
   {
      int myOption = 0;
      while (myOption != 1 && myOption != 2 && myOption != 3 && myOption != 4 && myOption != 5 && myOption != 6 && myOption != 7)
      {
         System.out.println("Please select a decryption."); 
         System.out.println("\t1. Caesar cipher.");
         System.out.println("\t2. Atbash cipher");
         System.out.println("\t3. A1Z26");
         System.out.println("\t4. Combined cipher.");
         System.out.println("\t5. Vigenere cipher.");
         System.out.println("\t6. Advance Ceasar cipher.");
         System.out.println("\t7. Quit the program.");
      
         myOption = kb.nextInt();
         kb.nextLine();
         if (!(myOption == 1 || myOption == 2 || myOption == 3 || myOption == 4 || myOption == 5 || myOption == 6 || myOption == 7))
            System.out.println("Invalid choice");
      }
      return myOption;
   }
}

