package arrays;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static String[] first_name = new String[8];
    public static String[] sur_name = new String[8];
    public static String[] credit_card = new String[8];
    public static String[] guests = new String[8];

    //created methods so that it can be called for the upcoming codes.
    public static String menu() {  //created a menu system for the user to select an option.
        System.out.println("\n--------------------Menu-------------------- ");
        System.out.println("\nV: View all rooms");
        System.out.println("A: Adds customer to room");
        System.out.println("E: Display empty rooms");
        System.out.println("D: Delete customer from room");
        System.out.println("F: Find room from customer name");
        System.out.println("S: Store program data into file");
        System.out.println("L: Load program data from file");
        System.out.println("O: View guests Ordered alphabetically by name.");
        System.out.println("\nSelect an option from the menu: ");

        return scanner.next().toLowerCase();  //the saved variable is returned
    }

    public static void initialise() {   //empty all the room's details
        for (   int x = 0; x < first_name.length; x++) {
            first_name[x] = "e";
            sur_name [x] = "";
            credit_card[x] = "";
            guests[x] = "";
        }
    }
    public static void main(String[] args) {
        initialise();

        while (true) {
            String option = menu();

            if (option.equals("v")) {
                view_rooms();
            } else if (option.equals("a")) {
                add_customer();
            } else if (option.equals("e")) {
                empty_rooms();
            } else if (option.equals("d")) {
                delete_customer();
            } else if (option.equals("f")) {
                find_room();
            }  else if (option.equals("s")) {
                try {
                    store_data();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (option.equals("l")) {
                try {
                    load_data();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (option.equals("o")) {
                ordered_view();
            }
            else {
            System.out.println("Invalid option!");
            }

            System.out.print("\nEnter Y to continue or enter any other key to terminate: ");
            String select = scanner.next().toLowerCase();   // user is asked if he wants to continue using the program

            if (!select.equals("y"))
                break;

        }
    }
    public static void view_rooms() {
        for (int x = 0; x < 8; x++) {
            System.out.println("\n The room " + x + " occupied by " + first_name[x] + " " + sur_name[x]);
            if (credit_card[x].equals("")) {
            continue;
            } else {
                System.out.println("Credit Card Number : " + credit_card[x]);
            }
        }
    }

    public static void add_customer() {  //adds customer names to the selected rooms
        System.out.println("Enter a room number from (0-7):");
        int room_num = scanner.nextInt() ;   //gets input from the customer

        if (room_num < 0 || room_num > 7) {
            System.out.println("Invalid room number");
        }else {
            if (first_name[room_num].equals("e")) {
                System.out.print("Enter the number of guests to be add in Room number  " + room_num + ":");
                guests[room_num] =  scanner.next();
                System.out.print("Enter your First Name : ");
                first_name[room_num] = scanner.next();
                System.out.println("Enter your Surname : ");
                sur_name[room_num] = scanner.next();
                System.out.println("Enter your Credit Card Number : ");
                credit_card[room_num] = scanner.next();
                System.out.println("The data is added ");
            }else {
                System.out.println("The room is Occupied!");
            }
        }
    }
    public static void empty_rooms() {   //display which rooms are currently empty
        for (int x = 0; x < first_name.length; x++) {
            if (first_name[x].equals("e")) System.out.println("The room " + x + " is empty");
        }
    }
    public static void delete_customer() {  //method to delete customer from room using customer's name
        System.out.println("Enter customer's first name : ");
        String fname = scanner.next().toLowerCase();
        System.out.println("Enter customer's surname : ");
        String sName = scanner.next().toLowerCase();
        for (int i = 0; i < first_name.length; i++) {
            if (first_name[i].equals(fname) && sur_name[i].equals(sName)) {
                first_name[i] = "e";
                sur_name[i] = "";
                credit_card[i] = "";
                System.out.println("Customer : " + fname + " " + sName + " Staying in Room Number " + i + " has been removed");
                break;
            }
        }
    }

    public static void find_room() {   //Find the room's details by the customer's name
        System.out.println("Enter customer's first name : ");
        String fname = scanner.next().toLowerCase();
        System.out.println("Enter customer's surname : ");
        String sName = scanner.next().toLowerCase();
        for (int i = 0; i < first_name.length; i++) {
            if (first_name[i].equals(fname) && sur_name[i].equals(sName)) {
                System.out.println("Customer : "+ fname + " " + sName + " is in room number : " + i);
                break;
            }
        }
    }
    public static void store_data() throws IOException{  //store details saved in the program
        File store = new File("first_name.txt");
        store.createNewFile();  //A new file is created
        FileWriter myWriter = new FileWriter(store.getName());
        for(int i = 0; i<first_name.length; i++){
            myWriter.write( first_name[i] + "\n");
        }
        myWriter.close();
        System.out.println("The data is stored.");

        File store1 = new File("sur_name.txt");
        store1.createNewFile();
        FileWriter myWriter1 = new FileWriter(store1.getName());
        for(int i = 0; i<sur_name.length; i++){
            myWriter1.write( sur_name[i] + "\n");
        }
        myWriter.close();
        System.out.println("The data is stored.");

        File store2 = new File("credit_card.txt");
        store2.createNewFile();
        FileWriter myWriter2 = new FileWriter(store2.getName());
        for(int i = 0; i<credit_card.length; i++){
            myWriter2.write( credit_card[i] + "\n");
        }
        myWriter2.close();
        System.out.println("The data is stored.");
    }



    public static void load_data() throws IOException {  //save data file.
        int i = 0;
        File store = new File("first_name.txt");
        Scanner myReader = new Scanner(store);//reading the data
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            first_name[i]= data;
            i++;
        }
        myReader.close();
        System.out.println("The data is loaded.");

        int x = 0;
        File store1 = new File("sur_name.txt");
        Scanner myReader1 = new Scanner(store1);
        while (myReader1.hasNextLine()) {
            String data = myReader1.nextLine();
            sur_name[x]= data;
            i++;
        }
        myReader1.close();
        System.out.println("The data is loaded.");

        int y = 0;
        File store2 = new File("credit_card.txt");
        Scanner myReader2 = new Scanner(store2);
        while (myReader2.hasNextLine()) {
            String data2 = myReader2.nextLine();
            credit_card[y]= data2;
            y++;
        }
        myReader2.close();
        System.out.println("The data is loaded.");
    }




    public static void ordered_view(){  //arranges the customer's data in alphabetical order.
        for (int x = 0; x <first_name.length; x++)
        {
            for (int y = x + 1; y < first_name.length; y++)
            {
                if (first_name[x].compareTo(first_name[y]) > 0 && sur_name[x].compareTo(sur_name[y]) > 0)
                {
                    String a = first_name[x]; //using temporary variables to rearrange the data
                    String b = sur_name[x];
                    first_name[x] = first_name[y];
                    sur_name[x] = sur_name[y];
                    first_name[y] = a;
                    sur_name[y] = b;
                }
            }
        }
        for (int x = 0; x < 8; x++) {
            System.out.println("The room " + x + " occupied by " + first_name[x] + " " + sur_name[x]);
        }
    }
}
