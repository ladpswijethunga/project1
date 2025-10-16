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
 
        
        //calling methods in main method
        distances();
        cities();
       
        System.out.println("================================");
        System.out.println("____DELIVERY COST ESTIMATION____");
        System.out.println("________________________________");
        
              
    }
  
        
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
              cityCount++;
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

     //for (int i=0;i< cityCount-1;i++){
           

         }
         
        
        
              
              
          
          
              

         }  
        
        
        
     public static void cities(){
         //cities can add,remove,edit
      
         
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
   
                     
}
         
         
         
     
   

