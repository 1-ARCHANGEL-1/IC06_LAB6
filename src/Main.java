import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        String name, breed;
        int age, count = 0;
        Dog[] dogPound  = new Dog[10];
        Scanner keyboard = new Scanner(System.in);
        //Reading from binary file Dogs.data
        File binaryFile = new File("Dogs.dat") ;
        //Check to see if file exist and non zero size
        if(binaryFile.exists()&& binaryFile.length()>1L)
        {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                //Read the entire array into dogPound
                //readObject returns object
                dogPound =(Dog[]) fileReader.readObject();
                //Loop through the array and print out all numbers
                while(dogPound[count]!= null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e)
            {
                System.out.println("Error: "+ e.getMessage());
            }
        }
        else
            System.out.println("[None, please enter a new Dog data]");
        do
        {
            System.out.println("Please enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if(name.equalsIgnoreCase("quit"))
                break;
            System.out.println("Please enter the dog's breed: ");
            breed = keyboard.nextLine();
            System.out.println("Please enter the dog's age: ");
            age = keyboard.nextInt();

            //TODO: 1) Create a new Dog object 2)Add to array 3)Increment count
            dogPound[count++] = new Dog(name,breed,age);

            //get rid of dangling
            keyboard.nextLine();
        }while(true);
        //After the user enters quit, write the dogPound array to Binary File
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
