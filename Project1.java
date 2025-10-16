/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project1;
import java.util.Scanner;




/**
 *
 * @author pamod
 */
public class Project1 {
    static String [] city=new String[30];//created an array for store up to maximum30 cities
    static int [][] distances=new int[30] [30];//created a symmetric 2D array for store distances
    static int cityCount=0;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

      
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("====== MAIN MENU======");
            System.out.println("1.City and Distance Management");
            System.out.println("2.Delivery Confirmation");
            System.out.println("3.exit");
        
            System.out.print("CHOOSE AN OPTION:");
            int option1=sc.nextInt();
            
            switch(option1){
                case 1:
                   cityManagement(); 
                case 2:
                    deliveryDetails();
                case 3:
                    return;
                default:
                     System.out.println("Invalid Input!");
            }
            
        }
       
        //calling methods in main method
        distances();
        cities();
       
        
}
    
     public static void cityManagement(){
       
        //CREATING THE CITY MENU
        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println(_____CITY & DIATANCE MANAGEMENT_____);
            System.out.println("1. ADD CITY");
            System.out.println("2. RENAME CITY");
            System.out.println("3.REMOVE CITY");
            System.out.println("4.INPUT/EDIT DISTANCE");
            System.out.println("5.DISPLAY DISTANCE TABLE");
            System.out.println("6.EXIT");
            System.out.print("Choose an option:");
            int choice =sc.nextInt();
             
            switch (choice){
                case1:
                    addCity(sc);
                case2:   
                    renameCity(sc);
                case3:
                    removeCity(sc);
                case4: 
                    editDistance(sc);
                case5:
                    displayDistance(sc);
                case6:
                   return;
                default:
                    System.out.println{"Invalid Input");
               
                    
            }
                    
        }
 
        
        
              
    }
  
        


    
     public static void cities(){
         
      
         
         city [0]="Colombo";
         city [1]="Gampaha";
         city [2]="Kandy";
         city [3]="Kurunegala";
         
         
}
     public static void distances(){
         //to add distances 
       
        distances [0][0]=0;
        distances [1][1]=0;
        distances [2][2]=0;
        distances [3][3]=0;
        
        distances [0][1]=distances [1][0]=40;
        distances [0][2]=distances [2][0]=115;
        distances [0][3]=distances [3][0]=130;
       
        distances [1][2]=distances [2][1]=140;
        distances [1][3]=distances [3][1]=100;
        distances [2][3]=distances [3][2]=90;
  
     }
   
        public static void addCity(Scanner sc){
          if (cityCount>=30){
              System.out.println("Maximum number of cities reached!");//prevent add more cities than 30
              return;
          }
          System.out.print("Enter new city name:");
           String name=sc.nextLine();
           
          //checking the city name already exsist or not before add to the array
          for (int i=0;i<cityCount;i++){
              if(city[i].equalsIgnoreCase(name)){
                  System.out.println("City already exist");
                  return;}}
          
              
          //now adding the city after checking existence
              city[cityCount]=name;
          //prevent adding more coloumns to the distance table
             for (int i=0;i<=cityCount;i++){
                 distances [cityCount][i]=distances [i][cityCount]=0;}
              cityCount++;//only increment once

            System.out.println("City added successfully!");



                  
              }
    public static void renameCity(Scanner sc){
//getting the city index to remove
         System.out.print("Enter the city index that wants to remove (0 to:"+ (cityCount-1) +"):");
         int index= sc.nextInt();
//checking the index exsist or not
         if (index<0 || index >= cityCount){
              System.out.println("Invalid index!");
              return;}
//getting new city name that wants to add because the index is valid
          System.out.print("Enter new name:");
          
          String newName=sc.nextLine();
         city[index]=newName;
          System.out.println("City name renamed successfully!");

    
           

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
         
        
        
              
              
          
          
              

         }  public static void deliveryDetails(){
        
        //adding arrays for vehicle details as fixed types
        String [] vehicle={"Three-Wheel","Van","Lorry"};//added 3 vehicles
         double [] kgCapacity ={300,1000,10000};
         double [] ratePerKm ={20,30,80};
         double[] averageSpeed={40,60,45};
         double []fuelEfficiency ={25,12,4};
         
         //getting user inputs
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
         
         //getting destination city index from user
         System.out.print("Destination city:");
         int destinationCity=sc.nextInt();
         
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
         
         //make sure user giving the valid input
         if(vehicleType< 0 || vehicleType>vehicle.length){
             System.out.println("Invalid input!");
             return;    }
     
         
        
    
         //getting user input about weight
         System.out.print("ENTER TOTAL WEIGHT HAS TO DELIVER IN kg:");
         double weight=sc.nextDouble();
         
         //make sure weight doesn't exceed the weight limit of choosed vehicle
         if( weight >kgCapacity[vehicleType] ){
             System.out.println("The selected vehicle can't carry this weight.Try another vehicle!");
             return;
             
         }
          // making output estimation
         System.out.println("=====DELIVERY COST ESTIMATION=====");
         System.out.println("FROM:"+city[sourceCity]);
         System.out.println("TO:"+city[destinationCity]);
         System.out.println("MINIMUM DISTANCE:"+distances [sourceCity][destinationCity]+"km" );
         System.out.println("VEHICLE:"+vehicle[vehicleType] );
         System.out.println("WEIGHT:"+weight + "kg");
         
         
         
    }



        
     
                     
}
         
         
         
     
   

