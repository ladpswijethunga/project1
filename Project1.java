/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project1;
import java.util.Scanner;
import java.text.DecimalFormat;




/**
 *
 * @author pamod
 */
 public class Project1{
       static String [] city=new String[30];//created an array for store up to maximum30 cities
       static int [][] distances=new int[30] [30];//created a symmetric 2D array for store distances
       static int cityCount=0;
       final static int MAX_DELIVERIES=50;//maximum delivery count is 50    
       //creating arrays for storing delivery details
            
             static String [] deliverySource= new String [MAX_DELIVERIES];
            static   String [] deliveryDestination= new  String [MAX_DELIVERIES];
             static int [] deliveryDistance= new int [MAX_DELIVERIES];
             static  String [] deliveryVehicle= new  String [MAX_DELIVERIES];
            static  double [] deliveryWeight= new double[MAX_DELIVERIES];
            static String [] deliveryCharge= new String[MAX_DELIVERIES];//data type changed to the string ,while converting it to only two decimal places
       static  int delievryCount=0;


    public static void main(String[] args) {
        //calling methods in main method
        
                distances();
                cities();
             
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("====== MAIN MENU======");
            System.out.println("1.City and Distance Management");
            System.out.println("2.Add Delivery ");
            System.out.println("3.Show All Deliveries");
            System.out.println("4.Exit");
        
            System.out.print("CHOOSE AN OPTION:");
            int option1=sc.nextInt();
            
            switch(option1){
                case 1:
                   cityManagement(); 
                    break;
                case 2:
                    addDelivery();
                    break;
                case 3:
                    deliveryTable();
                     break;
                case 4:
                    return;
                default:
                     System.out.println("Invalid Input!");
            }
            
            
             
        }
        
                
        
}
                
                

     public static void cityManagement(){
        //CREATING THE CITY MENU
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("_____CITY & DIATANCE MANAGEMENT_____");
            System.out.println("1. ADD CITY");
            System.out.println("2. RENAME CITY");
            System.out.println("3.REMOVE CITY");
            System.out.println("4.INPUT/EDIT DISTANCE");
            System.out.println("5.DISPLAY DISTANCE TABLE");
            System.out.println("6.EXIT");

        //choosing what is going to do
            System.out.print("Choose an option:");
            int choice =sc.nextInt(); 
          
            
        
         //calling methods using a switch according to the menu input    
            switch (choice){
                case 1:
                    addCity();// to add a new city to the list
                    break;
                case 2:   
                    renameCity();// to rename a city name
                    break;
                case 3:
                    removeCity();//to remove a city from the list 
                    break;
                case 4: 
                    editDistance();// to change distance between two cities
                    break;
                case 5:
                    displayDistance();// to display the city-distance table
                    break;
                case 6:
                   return; // user choosed to exit from the system
                default:
                    System.out.println("Invalid Input!"); }
              
        }
  
        
     }
    
    public static void cities(){
     // input several  cities to the system 
        city [0]="Colombo";
        city [1]="Gampaha";
        city [2]="Kandy";
        city [3]="Kurunegala";
         cityCount=4; //changed cituCount because it has initialized globally as 0
        
     }
     public static void distances(){
         //to add distances 
       
        distances [0][0]=0;//keeping distance between same city as 0
        distances [1][1]=0;
        distances [2][2]=0;
        distances [3][3]=0;
        
       distances [0][1]=distances [1][0]=40;// keeping the distance between same 2 cities symmetric
       distances [0][2]=distances [2][0]=115;
       distances [0][3]=distances [3][0]=130;
       
       distances [1][2]=distances [2][1]=140;
       distances [1][3]=distances [3][1]=100;
       distances [2][3]=distances [3][2]=90;
  
     }
        
     public static void addCity(){
         Scanner sc=new Scanner(System.in);

        //prevent add more cities than 30
    
         if (cityCount>=30){
              System.out.println("Maximum number of cities reached!");
              return;
          }
          System.out.print("Enter new city name:");
           String name=sc.nextLine();
           System.out.println();
           
          //checking the city name already exsist or not before add to the array
          for (int i=0;i<cityCount;i++){
              if(city[i]!= null && city[i].equalsIgnoreCase(name)){
                  System.out.println("City already exist!");
                  return;}}
          //now adding the city after checking existence
              city[cityCount]=name;
          //prevent adding more coloumns to the distance table
             for (int i=0;i<=cityCount;i++){
                 distances [cityCount][i]=distances [i][cityCount]=0;}
                 cityCount++;//only increment once
                 
             
          
            System.out.println("City added successfully!");
          
        }  
                
                 
              
    public static void renameCity(){
        Scanner sc=new Scanner(System.in);
        //getting the city index to rename
             System.out.print("Enter the city index that wants to rename (0 to " + (cityCount - 1) +"):");
                int index= sc.nextInt();
                sc.nextLine();//to prevent skipping inputs and clear extra new lines
              
                
        //checking the index exsist or not
                if (index < 0 || index >= cityCount){
                   System.out.println("Invalid index!");
                   return;}
        //getting new city name that wants to add because the index is valid
                 System.out.print("Enter new name:");
                 String newName=sc.nextLine();
                   
                city[index]=newName;
                System.out.println("City name Renamed successfully!");
          
    }


    
    public static void removeCity(){
        Scanner sc=new Scanner(System.in);
         //getting the city index to remove
               System.out.print("Enter the city index that wants to remove (0 to "+ (cityCount-1) +"):");
               int index =sc.nextInt();
               sc.nextLine();
        
         //checking the index exsist or not
               if (index<0 || index >= cityCount){
                   System.out.println("Invalid index!");
                   return;}
         //shift cities and distance
               for (int i=index; i< cityCount-1;i++){
                   city[i]=city[i+1];
               for (int j=0; j< cityCount;j++){
                  distances [i][j]=distances [i+1][j];
                  distances [j][i]=distances [j][i+1];}
              }
              
                 cityCount--;
               System.out.println("City name Removed successfully!");      

    }

    public static void editDistance(){
        Scanner sc=new Scanner(System.in);
        // to comapre distances atleast 2 cities should have 
           if (cityCount <2){
               System.out.println("Add atleast 2 cities first!");
               return ;
       }
           System.out.print("Enter first city index(0 to "+(cityCount-1)+ "):");
                int cityIndex1=sc.nextInt();
                sc.nextLine();
           System.out.print("Enter second city index(0 to "+(cityCount-1)+ "):");
                int cityIndex2=sc.nextInt();
                 sc.nextLine();
                
        //checking indexes are same or not
            if(cityIndex1==cityIndex2){
                   System.out.println("Distance to itself is always 0!");
                   return;}
            System.out.print("Enter distance from "+ (city[cityIndex1])+" to "+(city[cityIndex2])+":");
            int distance=sc.nextInt();
            sc.nextLine();
            distances[cityIndex1][cityIndex2]=distances[cityIndex2][cityIndex1]=distance; //kept symmetric
            System.out.println("Distance updated sucessfully!");

    }

    public static void displayDistance(){
          // checking avilability of cities to dispaly
            if (cityCount==0){
               System.out.println("NO cities to display!");
               return;
            }
          // creating the city-distance table to display
             System.out.printf("%15s"," ");
             for (int i=0; i< cityCount;i++){
                System.out.printf("%15s",city[i]);}
                System.out.println();
              for (int i=0; i< cityCount;i++){
                 System.out.printf("%15s",city[i]);

                for (int j=0; j< cityCount;j++){
                    System.out.printf("%15d",distances[i][j]);
                }
         System.out.println();
 
    
             } 
    

   }
    public static void addDelivery(){
        boolean addAnother=true; 
        while(addAnother){ //created for continuous delivery addings
        
        //adding arrays for vehicle details as fixed types
        String [] vehicle={"Three-Wheel","Van","Lorry"};//added 3 vehicles
         double [] kgCapacity ={300,1000,10000};
         double [] ratePerKm ={20,30,80};
         double [] averageSpeed={40,60,45};
         double[]fuelEfficiency ={25,12,4};
         
         //checking delivery count
         if (delievryCount>= MAX_DELIVERIES){
             System.out.println("MAXIMUM DELIVERY LIMIT REACHED!");
              return;}
            
         //getting user inputs because delivery count is less than 50
         Scanner sc=new Scanner(System.in);
         
         System.out.println("CHOOSE COURCE AND DESTINATION CITIES FIRST(Enter the number)" );
         //showiing vehicles list to the user
         System.out.println("Available cities-> ");
         //going through the city array
         for( int i=0;i< cityCount;i++){
             System.out.println(i+"-"+city[i]);  }
         
         //getting source city index from user
         System.out.print("Source city:");
         int sourceCity=sc.nextInt();
          sc.nextLine();//to avoid user input errors
         
         //getting destination city index from user
         System.out.print("Destination city:");
         int destinationCity=sc.nextInt();
          sc.nextLine();//to avoid user input errors
         
         //make sure source and destination cities are not same
         if(sourceCity==destinationCity){
         System.out.println("Source and Destination can't be same! ");
         return;}
         
         //giving user the chance to choose the vehicle
         System.out.println("CHOOSE VEHICLE");
         System.out.println("Available vehicles-> ");
         
         //going through vehucle array
         for( int i=0;i<vehicle.length;i++){
             System.out.println(i+"-"+vehicle[i]);  }
         
         //geting user input
         System.out.print("Vehicle:");
         int vehicleType=sc.nextInt();
          sc.nextLine();//to avoid user input errors
         
         //make sure user giving the valid input
         if(vehicleType< 0 || vehicleType>vehicle.length){
             System.out.println("Invalid input!");
             return;    }
     
         
        
    
         //getting user input about weight
         System.out.print("ENTER TOTAL WEIGHT HAS TO DELIVER IN kg:");
         double weight=sc.nextDouble();
          sc.nextLine();//to avoid user input errors
         
         //make sure weight doesn't exceed the weight limit of choosed vehicle
         if( weight >kgCapacity[vehicleType] ){
             System.out.println("The selected vehicle can't carry this weight.Try another vehicle!");
             return;
             
         }
         
         
         //calculating base cost
         double baseCost=((distances [sourceCity][destinationCity]* ratePerKm[vehicleType])* (1+(weight/10000)));
         DecimalFormat value1=new DecimalFormat("0.00");
         
         // calculating used fuel
         double usedFuel=(distances [sourceCity][destinationCity]/fuelEfficiency[vehicleType]);
         DecimalFormat value2=new DecimalFormat("0.00");
         
        
         //calculating fuel cost
         double fuelCost=(usedFuel * 310);//fuel price got as 310 LKR per Liter
         DecimalFormat value3=new DecimalFormat("0.00");
         
         //calculating operational cost
         double totalCost=(baseCost+fuelCost);
         DecimalFormat value4=new DecimalFormat("0.00");
         
         //calculating profit
         double profit =(baseCost*0.25);//assumed 25% markup on base delivery cost
         DecimalFormat value5=new DecimalFormat("0.00");
         
         //Calculating customer charge
         double finalCharge=(totalCost+profit);
         DecimalFormat value6=new DecimalFormat("0.00");
         
         //Time estimating
         double time=( distances [sourceCity][destinationCity]/ averageSpeed[vehicleType]);
         DecimalFormat value7=new DecimalFormat("0.00");
         
         //storing delivery details
          deliverySource[deliveryCount]= city[sourceCity];
          deliveryDestination [deliveryCount]=city[destinationCity];
          deliveryDistance [deliveryCount]=distances [sourceCity][destinationCity];
          deliveryVehicle [deliveryCount]=vehicle[vehicleType];
          deliveryWeight [deliveryCount]=weight;
          deliveryCharge [deliveryCount]=value1.format(finalCharge );
             deliveryCount++;
         
         
         
          // making output estimation
         System.out.println("=====DELIVERY COST ESTIMATION=====");
         System.out.println("FROM:"+city[sourceCity]);
         System.out.println("TO:"+city[destinationCity]);
         System.out.println("MINIMUM DISTANCE:"+distances [sourceCity][destinationCity]+"km" );
         System.out.println("VEHICLE:"+vehicle[vehicleType] );
         System.out.println("WEIGHT:"+weight + "kg");
         System.out.println("___________________________________");
         System.out.println("BASE COST:"+ ((distances [sourceCity][destinationCity]+" x " +ratePerKm[vehicleType])+" x "+ "(1+("+weight+"/10000)) = ")+value1.format(baseCost) + " LKR");
         System.out.println("FUEL USED :" + value2.format(usedFuel) +" L" );
         System.out.println("FUEL COST:"+ value1.format(fuelCost) +"LKR" );
         System.out.println("OPERATIONAL COST:" + value1.format(totalCost) +"LKR"  );  
         System.out.println("PROFIT:"+value1.format( profit)+ "LKR");
         System.out.println("CUSTOMER CHARGE:"+ value1.format(finalCharge )+ "LKR");
         System.out.println("ESTIMATED TIME:"+value1.format(time)+"hours" );
         
         
         System.out.println();
         
         System.out.println("DO YOU WANT TO ADD MORE DELIVERIES? (YES/NO)");
                 String answer=sc.nextLine().toUpperCase();
                 addAnother=answer.equals("YES"); //continue loop
                 
        }
         
    }System.out.println("Delivery details added Successfully!");
         
    }
     public static void deliveryTable(){
         
  
          // checking avilability of cities to dispaly
            if (deliveryCount==0){
               System.out.println("NO deliveries to display!");
               return;
            }
          //display delivery records
          
         System.out.println("\n  _____ DELIVERY RECORDS ____");
         for(int i=0;i<deliveryCount;i++){
         System.out.println("FROM:"+deliverySource[i]);
         System.out.println("TO:"+deliveryDestination [i]); 
         System.out.println("MINIMUM DISTANCE:"+deliveryDistance [i]+"km" );
          System.out.println("VEHICLE:"+deliveryVehicle[i] );
         System.out.println("WEIGHT:"+deliveryWeight[i]+ "kg");   
          System.out.println("CUSTOMER CHARGE:"+ deliveryCharge [i] + "LKR");
     }
 
    
             } 
    


     
 }  
         
         
         
     
   

