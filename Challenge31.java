import java.io.BufferedReader;
import java.io.FileReader;

public class Challenge31{
    public static void main(String[] args) {
        int i;
        i=Reader.readnumberofline("data\\purchase_details.csv");
        System.out.println("Total number of records found are:- "+i);
        // String[] Product_Catogery = new String[i];
        Reader.Product_Data(i, "data\\purchase_details.csv");

    }
}
class Reader{
    public static int readnumberofline(String filename){
        int count=0;
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String Line;
            br.readLine();
            while((Line =br.readLine())!=null){
                count++;
            }
            br.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
        return count;
    }
    public static void Product_Data(int i ,String filename){
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String fileData;
            br.readLine();
            int j=0;
            String[][] Unique_Product =new String[i][7];
            while((fileData=br.readLine())!=null){
                Unique_Product[j]=fileData.split(",");
                j++;
            }
            int m=0,credit=0,paypal=0;
            int low=100;
            int LowIndex;
            int high=0;
            int highIndex=0;
            int number_of_uniqueProduct=0;
            int no_of_uniqueProduct=0;
            int no_of_clicks=0;
            for(int k=0; k<j-1; k++){
                for (int l=k+1; l<j; l++){
                    if(Unique_Product[k][2].equals(Unique_Product[l][2])){
                        m++;
                        break;
                    }
                }
            }
            for(int k=0; k<j-1; k++){
                high=0;
                for(int n=k+1; n<j; n++){
                    if (Unique_Product[k][2].equals(Unique_Product[n][2])){
                        if(no_of_uniqueProduct<high){
                            no_of_uniqueProduct++;
                            highIndex=k;
                        }
                        high++;
                    }
                }
                
            }
            float Value_earned_on_min_visite=0.00f;
            float Value_earned_on_max_visite=0.00f;
            for(int k=0; k<i; k++){
                if (Unique_Product[k][3].equals("credit")){
                    credit++;
                }
                else if(Unique_Product[k][3].equals("paypal")){
                    paypal++;
                }
                no_of_clicks=no_of_clicks+Integer.parseInt(Unique_Product[k][6]);
            }
            float val;
            for(int k=0; k<i; k++){
                val= Float.parseFloat(Unique_Product[k][5]);
                if(val>15){
                    Value_earned_on_max_visite=Value_earned_on_max_visite+ Float.parseFloat(Unique_Product[k][4]);
                }
                else if(val<=15){
                    Value_earned_on_min_visite=Value_earned_on_min_visite+ Float.parseFloat(Unique_Product[k][4]);
                }
            }
            number_of_uniqueProduct=i-m;
            int Avg_clicks = no_of_clicks/i;
            System.out.println("The unique number of product_categories that has been sold in this tenure."+number_of_uniqueProduct);
            if(credit>paypal){
                System.out.println("Prefered Payment method is credit with : "+ credit+" times");
            }
            else if(paypal>credit){
                System.out.println("Prefered Payment method is paypal with :"+paypal+" times");
            }
            System.out.println("The category of the product which was sold the maximum no. of times: "+Unique_Product[highIndex][2]+" with number of times: "+no_of_uniqueProduct);
            System.out.println("The avg number of clicks made by the customers before making their purchasing decision: "+Avg_clicks);
            if(Value_earned_on_max_visite>Value_earned_on_min_visite){
                System.out.println("spending more time on the site generates more sales.");
            }
            else if(Value_earned_on_min_visite>Value_earned_on_max_visite){
                System.out.println("spending more time on the site doesn't generates more sales.");
            }
            else{
                System.out.println("spending time on the site  doesn't effect on sales.");
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
