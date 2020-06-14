package FilesIOTutorial.vezba;
import java.io.*;
import java.sql.SQLOutput;

public class DerekBanasIOJava {
    public static void main(String[] args) {
        Customer[] customers = getCustomers();
    //printWriter => se pisuva karakteri vo fajl

        PrintWriter custOut = createFile("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\vezba\\persons.txt");
        for(Customer person: customers){

            createCustomers(person, custOut);
        }
        custOut.close();


        getFileInfo("C:\\Users\\Nikolaj\\Desktop\\JavaPrograms\\operativni\\src\\FilesIOTutorial\\vezba\\persons.txt");
    }

    private static void createCustomers(Customer person, PrintWriter custOut) {
        String info = person.firstName + " " + person.lastName + " ";
        info += Integer.toString(person.custAge);

        custOut.println(info);

    }

    private static PrintWriter createFile(String fileName) {
        try{


            File listOfNames = new File(fileName);
            //
            // bufferedWriter gi baferira site karakteri
            // i gi zapisuva site od ednas, bez ova
            // pisuvas bukva po bukva, sto e mnogu loso
            PrintWriter infoToWrite = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(listOfNames)));
            return infoToWrite;
        } catch (IOException e) {
            System.out.println("An io error occured");
            System.exit(0);
        }
        return null;
    }


    private static class Customer{
        public String firstName;
        public String lastName;
        public int custAge;

        public Customer(String firstName, String lastName, int custAge) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.custAge = custAge;
        }
    }

    private static Customer[] getCustomers(){
        Customer[] customers = new Customer[5];
        customers[0] = new Customer("John", "Smith", 24);
        customers[1] = new Customer("Andrew", "Smith", 16);
        customers[2] = new Customer("Thomas", "Griffin", 21);
        customers[3] = new Customer("Anna", "Kendrick", 31);
        customers[4] = new Customer("Elise", "Smith", 19);

        return customers;
    }


    private static void getFileInfo(String fileName){
        System.out.println("Info written to file: \n");

        File listOfNames = new File(fileName);

        try{
            // cita sto e mozno poise karakteri
            // od fajlot i gi cuva pred da gi
            // ovozmozi za koristenje

            // a file reader gi zema bukva po bukva i go
            // pravi procesot mnogu pospor
            BufferedReader getInfo = new BufferedReader(
                    new FileReader(listOfNames)
            );

            String custInfo = getInfo.readLine();

            while(custInfo != null){
             //  System.out.println(custInfo);
                String[] individualCustData = custInfo.split("\\s+");

                int custAge = Integer.parseInt(individualCustData[2]);

                System.out.println("Customer " + individualCustData[0] +" is "
                + custAge +"\n");

                custInfo = getInfo.readLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("An I/O error occured");
            System.exit(0);
        }
    }
}
