package com.tirth.library;

import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {
    public static File data = new File("C:\\Users\\graha\\Documents\\GitHub\\library-management\\data.ser");
    public static File oneTimeLogIn = new File("C:\\Users\\graha\\Documents\\GitHub\\library-management\\oneTimeLogIn.ser");

    public static void newRecord() {
        Scanner scanner = new Scanner(System.in);

        //Stores username
        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        //Stores address
        System.out.println("Enter address: ");
        String address = scanner.nextLine();

        //Stores email
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        //Stores admin
        System.out.println("Enter admin: ");
        String admin = scanner.nextLine();

        //Assign values to Library object
        Library record =  new Library(name, address, email, admin);

        //Saves data to file as serialized object
        try {
            FileOutputStream fileOutput = new FileOutputStream(data);
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);

            //Serializes record
            output.writeObject(record);

            output.close();
            fileOutput.close();

        } catch(IOException exception) {
            System.out.println("IOException");
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creates file that stores collection
        try {
            data.createNewFile();

        } catch (IOException exception){
            System.out.println("Unable to create file!");
        }

        //If file is empty prompt user to create account
        if(data.length() == 0) {
            System.out.println("File is empty");
            //call method
            newRecord();

        }

        //Enables one time log in
        if(!oneTimeLogIn.exists()) {

            //if password matches log in user
            if (data.length() != 0) {
                System.out.println("Enter password to view library collection: ");
                String userInput = scanner.nextLine();

                Library r = null;

                try {
                    FileInputStream fileInput = new FileInputStream(data);
                    ObjectInputStream input = new ObjectInputStream(fileInput);

                    //Deserializes object
                    r = (Library) input.readObject();

                    input.close();
                    fileInput.close();

                } catch (IOException | ClassNotFoundException ioException) {
                    System.out.println("Exception");
                }

                assert r != null;

                //If user credentials are validated alert user of access level
                if (userInput.equals(r.getAdmin())) {
                    System.out.println("You have access to " + r.getName() + "'s " + "library collection");

                    //Create one time log in file
                    try {
                        oneTimeLogIn.createNewFile();

                    } catch (IOException exception) {
                        System.out.println("Unable to create file!");
                    }

                } else {
                    System.out.println("You do not have access to " + r.getName() + "'s " + "library collection");
                }
            }
        } else {
            Library r = null;

            try {
                FileInputStream fileInput = new FileInputStream(data);
                ObjectInputStream input = new ObjectInputStream(fileInput);

                //Deserializes object
                r = (Library) input.readObject();

                input.close();
                fileInput.close();

                System.out.println("Deserialized obj: " + r.getName());
            } catch (IOException | ClassNotFoundException ioException) {
                System.out.println("Exception");
            }

            System.out.println("You have access to " + r.getName() + "'s " + "library collection");

        }

    }
}
