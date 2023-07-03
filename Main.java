import java.text.DecimalFormat;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

class Main 
{
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            Queue qCounter1 = new Queue();
            Queue qCounter2 = new Queue();
            Queue qCounter3 = new Queue();

            Stack completedStack = new Stack();

            // Crate file reader to read input file
            BufferedReader br = new BufferedReader(new FileReader("customer1.txt"));

            // Create ArrayList
            LinkedList<CustomerInformation> customerList = new LinkedList<CustomerInformation>();
                        // Create object
            CustomerInformation cust;

            // Declare indata (a line in input file)
            String inline = null;

            double Total = 0.0;
            int totalCustomer = 0;

            while ((inline = br.readLine()) != null) 
            {
            StringTokenizer st = new StringTokenizer(inline, ";");

            String custId = st.nextToken();
            String custIC = st.nextToken();
            int counterPaid = Integer.parseInt(st.nextToken());

            ArrayList<ItemInformation> itemList = new ArrayList<>();
            StringTokenizer itemToken = new StringTokenizer(st.nextToken(), ":");
            while (itemToken.hasMoreTokens()) 
            {
            StringTokenizer infoToken = new StringTokenizer(itemToken.nextToken(), ",");
            String itemId = infoToken.nextToken();
            String itemName = infoToken.nextToken();
            double itemPrice = Double.parseDouble(infoToken.nextToken());
            String date = infoToken.nextToken();
            ItemInformation item = new ItemInformation(itemId, itemName, itemPrice, date);
            itemList.add(item);
            counterPaid += itemPrice;
             }
            cust = new CustomerInformation(custId, custIC, counterPaid);
            cust.addItem(itemList);
            customerList.add(cust);
            totalCustomer++;
            }
            br.close();

            new Gui(customerList, qCounter1, qCounter2, qCounter3, totalCustomer, completedStack);

        } catch (FileNotFoundException fnfe)
        {
            System.out.println("File cannot been found");
        } catch (IOException ioe)
        {
            System.out.println(ioe.getMessage());
        }
    }
}


