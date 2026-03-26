import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ContactManagementApp {

    public static void addContact(Scanner sc, ArrayList<Contact> contacts){
        String name;
        while(true){
            System.out.println("Enter Name:");
            name = sc.nextLine();

            if(name.matches("[a-zA-Z ]+")){
                break;
            } 
            else 
            {
                System.out.println("Invalid name! Only letters allowed.");
            }
        }

        
        String email;
        while(true){
            System.out.println("Enter Email:");
            email = sc.nextLine();

            if(email.contains("@") && email.contains(".")){
                break;
            } 
            else {
                System.out.println("Invalid email format!");
            }
        }

        
        String phone;
        while(true){
            System.out.println("Enter Phone:");
            phone = sc.nextLine();

            if(phone.matches("\\d{10}")){
                break;
            } 
            else {
                System.out.println("Invalid phone! Must be 10 digits.");
            }
        }

        Contact c = new Contact();
        c.name = name;
        c.email = email;
        c.phone = phone;
        contacts.add(c);
        try {
            FileWriter fw = new FileWriter("C:\\ProdigyInfoTech_Task\\contactmanagementapp_task_3\\src\\contacts.txt", true); // true = append
            fw.write(c.name + "," + c.email + "," + c.phone + "\n");
            System.out.println("Contact Added Successfully");
            fw.close();
        } 
        catch(IOException e){
            System.out.println("Error saving contact");
        }
    
    }
    public static void loadContacts(ArrayList<Contact> contacts){
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader("C:\\ProdigyInfoTech_Task\\contactmanagementapp_task_3\\src\\contacts.txt"));
            String line;

            while((line = br.readLine()) != null){
                String[] data = line.split(",");2
                Contact c = new Contact();
                c.name = data[0];
                c.email = data[1];
                c.phone = data[2];

                contacts.add(c);
            }

            br.close();
        } 
        catch(IOException e){
            System.out.println("Error loading contacts");
        }
    }

    public static void saveAllContacts(ArrayList<Contact> contacts){
        try {
            FileWriter fw = new FileWriter("C:\\ProdigyInfoTech_Task\\contactmanagementapp_task_3\\src\\contacts.txt");
            for(Contact c : contacts){
                fw.write(c.name + "," + c.email + "," + c.phone + "\n");
            }
            fw.close();
        } 
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void viewContacts(ArrayList<Contact> contacts){
        if(contacts.isEmpty()){
            System.out.println("No contacts available");
        }
        else{
            for(Contact c1 : contacts){
                System.out.println("Name: " + c1.name);
                System.out.println("Email: " + c1.email);
                System.out.println("Phone: " + c1.phone);
                System.out.println("----------------------");
            }
        }

    }

    public static void deleteContacts(Scanner sc , ArrayList<Contact> contacts ){
        if(contacts.isEmpty()){
            System.out.println("No contacts to delete");
        }
                    
        System.out.println("Enter Email to delete:");
        String Email = sc.nextLine();
        boolean found = false;
        for(int i = 0; i < contacts.size(); i++){
            if(contacts.get(i).email.equalsIgnoreCase(Email))
            {   
                contacts.remove(i);
                saveAllContacts(contacts);
                found = true;
                System.out.println("Contact deleted");
            }
        }
                    
        if(!found){
            System.out.println("Contact not found");
         }

    }

    public static void deleteAll(Scanner sc,ArrayList<Contact> contacts){
        if(contacts.isEmpty()){
            System.out.println("No contacts to delete");
        } 
        else {
            System.out.println("Are you sure you want to delete ALL contacts? (yes/no)");
            String confirm = sc.nextLine();

            if(confirm.equalsIgnoreCase("yes")){
                contacts.clear();  // clear list
                saveAllContacts(contacts); // clear file

                System.out.println("All contacts deleted!");
            } 
            else {
                System.out.println("Operation cancelled");
            }
        }

    }
    public static void editContacts(Scanner sc , ArrayList<Contact> contacts ){
        if(contacts.isEmpty()){
            System.out.println("No contacts to edit");
        } 
        else {
            System.out.println("Enter Your email: ");
            String Email = sc.nextLine();

            boolean found = false;

            for(int i = 0; i < contacts.size(); i++){
                if(contacts.get(i).email.equalsIgnoreCase(Email)){

                    System.out.println("What do you want to edit?");
                    System.out.println("1. Name");
                    System.out.println("2. Email");
                    System.out.println("3. Phone");
                    System.out.println("4. All");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch(choice){
                        case 1:
                            System.out.println("Enter new name:");
                            contacts.get(i).name = sc.nextLine();
                            break;

                        case 2:
                            System.out.println("Enter new email:");
                            contacts.get(i).email = sc.nextLine();
                            break;

                        case 3:
                            System.out.println("Enter new phone:");
                            contacts.get(i).phone = sc.nextLine();
                            break;

                        case 4:
                            System.out.println("Enter new name:");
                            contacts.get(i).name = sc.nextLine();

                            System.out.println("Enter new email:");
                            contacts.get(i).email = sc.nextLine();

                            System.out.println("Enter new phone:");
                            contacts.get(i).phone = sc.nextLine();
                            break;

                        default:
                            System.out.println("Invalid choice");
                    }

                    System.out.println("Contact updated!");
                    saveAllContacts(contacts);  
                    found = true;
                    break;
                }
            }

            if(!found){
                System.out.println("Contact not found");
            }
        }

    }
    
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Contact> contacts = new ArrayList<>();
        loadContacts(contacts);
        System.out.println("=======CONTACTS MANAGEMENT APP========");

        boolean isRunning = true;

        while(isRunning){
            System.out.println();
            System.out.println("Enter your choice: ");
            System.out.println("1.Add\n2.View\n3.Delete \n4.Edit \n5.Delete All \n6.Exit Application");
            int ch = sc.nextInt();
            sc.nextLine();

            switch(ch){
                case 1:
                    addContact(sc, contacts);
                    break;

                case 2:
                    viewContacts(contacts);
                    break;
                case 3:
                    deleteContacts(sc, contacts);
                    break;
                case 4:
                    editContacts(sc, contacts);
                    break;
                case 5:
                    deleteAll(sc, contacts);
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Exiting Application");
                    break;
                default:
                    System.out.println("Invalid Option, Please enter a valid Option");
                    break;
            }
        }
    }
    
}
