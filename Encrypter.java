/* This is very much the exact same as the Decrypter, except that all the input and output are reversed, so you put in 
 * normal English and are given back an encrypted sequence of letters or numbers. I have all the same 
 * problems with this as i do with the Decrypter as far as its effeciency and checking for things that would kill it
 * but I don't really have the time for it. Maybe one day, for now its just proof that I do stuff outside of class.
*/
import java.util.*;

public class Encrypter
{
   public static void main(String...args)
   {
      int option;
      Scanner kb = new Scanner(System.in);
      
      option = displayMenu(kb);
      
      while (option != 7)
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
      System.out.println("Enter pharase to be encrypted");
      String crypt = kb.nextLine();
      crypt = crypt.toLowerCase();
      return crypt;
   }
   
   public static String Caesar(String crypt)
   {
      String encrypt = "";
         for(int i = 0; i < crypt.length(); i++)
         {
            char c = crypt.charAt(i);
            int num = toNum(c);
            if(!(num == 0))
               num = num + 3;
            if(num == 27 || num == 28 || num == 29)
               num = num + 1;
            encrypt = encrypt + toChar(num);
         }
         
         return encrypt;
   }
   
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
   
   public static String A1Z26(String crypt)
   {
      String encrypt = "";
      for(int i = 0; i < crypt.length(); i++)
      {
         char c = crypt.charAt(i);
         int num = toNum(c);
         encrypt = encrypt + num + '-';
      }
      encrypt = encrypt.substring(0, encrypt.length() - 1);
      encrypt = encrypt.replace("-0-", " ");
      return encrypt;
   
   }
   
   public static String Combined(String crypt)
   {
      crypt = Caesar(crypt);
      crypt = Atbash(crypt);
      return A1Z26(crypt);
   }
   
   public static void Vigenere(Scanner kb)
   {
      System.out.println("Enter pharase to be encrypted");
      String crypt = kb.nextLine();
      crypt = crypt.toLowerCase();
      String encrypt = "";
      System.out.println("Enter key");
      String key = kb.nextLine();
      key = key.toLowerCase();
      int h = 0;
      for(int i = 0; i < crypt.length(); i++)
      {
         if(!(h < key.length()))
            h = 0;
         char c = crypt.charAt(i);
         char k = key.charAt(h);
         int num = toNum(c);
         if(!(num == 0))
         {
            num = num + toNum(k) - 1;
            h++;
         }
         if(num >= 27)
            num = num + 1;
         encrypt = encrypt + toChar(num);
      }
      System.out.println(encrypt);
   }
   
   public static void AdvCaesar(Scanner kb)
   {
      System.out.println("Enter pharase to be encrypted");
      String crypt = kb.nextLine();
      crypt = crypt.toLowerCase();
      String encrypt = "";
      System.out.println("Enter # to shift");
      int shift = kb.nextInt();
      kb.nextLine();
      for(int i = 0; i < crypt.length(); i++)
      {
         char c = crypt.charAt(i);
         int num = toNum(c);
         if(!(num == 0))
            num = num + shift;
         if(num >= 27)
               num = num + 1;
         encrypt = encrypt + toChar(num);
      }
         
      System.out.println(encrypt);
   }
   
   public static int toNum(char c)
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
       else if(c == '-')
         return -1;
       else
          return 0;
   }
   
   public static char toChar(int i)
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
          else if(i == -1)
            return '-';
	       else
	          return ' ';
   }
   
   
   private static int displayMenu(Scanner kb)
   {
      int myOption = 0;
      while (myOption != 1 && myOption != 2 && myOption != 3 && myOption != 4 && myOption != 5 && myOption != 6 && myOption != 7)
      {
         System.out.println("Please select a encryption."); 
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
