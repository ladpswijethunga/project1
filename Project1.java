/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project1;
import java.util.*;
import java.text.DecimalFormat;
import java.io.*; //for all java.io classes





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
            static String [] deliveryDestination= new String [MAX_DELIVERIES];
             static int [] deliveryDistance= new int [MAX_DELIVERIES];
             static String [] deliveryVehicle= new String[MAX_DELIVERIES];
            static  double [] deliveryWeight= new double[MAX_DELIVERIES];
            static String [] deliveryCharge= new String[MAX_DELIVERIES];//data type changed to the string ,while converting it to only two decimal places
            static  int deliveryCount=0;


    public static void main(String[] args) {
        
         //loading files at the start
        cityCount= loadRoutes (city,distances);
           
        //calling methods in main method
        if (cityCount == 0){ // to confirm availibility of data
                distances();
                cities();
        } 
        deliveryCount = loadDeliveries(deliverySource,deliveryDestination,deliveryDistance, deliveryVehicle ,deliveryWeight,deliveryCharge);
           
        Scanner sc=new Scanner(System.in);

        while (true){
            System.out.println("====== MAIN MENU======");
            System.out.println("1.City and Distance Management");
            System.out.println("2.Add Delivery ");
            System.out.println("3.Delivery Histories"); // additionally added to get a report of past choosed deliveries if wanted
            System.out.println("4.Performance Report");
            System.out.println("5.Exit");
        
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
                    reportMenu();
                    break;
                case 5:
                    // CALLING DATA  SAVING METHODS WHEN CHOOSED TO EXIT,so changes persist
              saveRoutesToFile(city,distances,cityCount);
              saveDeliveriesToFils (deliverySource,
                 deliveryDestination ,deliveryDistance,deliveryVehicle ,deliveryWeight,deliveryCharge, deliveryCount);
              System.out.println("ALL DATA SAVED!EXITING!");
              
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
         if (deliveryCount>= MAX_DELIVERIES){
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
         
         
          int sourceCity;
          int destinationCity;
         
        while(true) {
         //getting source city index from user
         System.out.print("Source city:");
          sourceCity=sc.nextInt();
             sc.nextLine();//to avoid user input errors
         if (sourceCity<0 || sourceCity>=cityCount){
             System.out.println("invalid input!give a number between 0 and "+ (cityCount-1));
             continue;}
         break;}
             
      
       while(true) {  
         //getting destination city index from user
         System.out.print("Destination city:");
         destinationCity=sc.nextInt();
         sc.nextLine();//to avoid user input errors
         if (destinationCity<0 || destinationCity>cityCount){
             System.out.println("invalid input!give a number between 0 and "+ (cityCount-1));
             continue;}
        break; }
             
          
         
         //make sure source and destination cities are not same
         if(sourceCity==destinationCity){
         System.out.println("Source and Destination can't be same! ");
         return;}
         
         //giving user the chance to choose the vehicle
         System.out.println("CHOOSE VEHICLE");
         System.out.println("Available vehicles-> ");
         
         //going through vehicle array
         for( int i=0;i<vehicle.length;i++){
             System.out.println(i+"-"+vehicle[i]);  }
         
         int vehicleType;
         double weight;
        
            while(true){
         //geting user input
         System.out.print("Vehicle:");
         vehicleType=sc.nextInt();
          sc.nextLine();//to avoid user input errors
   

         //make sure user giving the valid input
         if(vehicleType< 0 || vehicleType>vehicle.length){
             System.out.println("Invalid input! Enter a number in between o and "+(vehicle.length-1));
           continue; }
           
  
         //getting user input about weight
         System.out.print("ENTER TOTAL WEIGHT HAS TO DELIVER IN kg:");
          weight = sc.nextDouble();
          sc.nextLine();//to avoid user input errors
         
         //make sure weight doesn't exceed the weight limit of choosed vehicle
         if( weight >kgCapacity[vehicleType] ){
             System.out.println("The selected vehicle can't carry this weight.Try another vehicle!");
            continue;     }
        
         break;} 
            
            
    
         
         int shortestDistance= LeastDistanceRoute(city,distances,cityCount,sourceCity,destinationCity);//calling the LeastDistanceRoute method
        
         
         //calculating base cost
         double baseCost=((shortestDistance* ratePerKm[vehicleType])* (1+(weight/10000)));
         DecimalFormat value1=new DecimalFormat("0.00");
         
         // calculating used fuel
         double usedFuel=(shortestDistance/fuelEfficiency[vehicleType]);
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
         double time=( shortestDistance/ averageSpeed[vehicleType]);
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
         System.out.println("MINIMUM DISTANCE:"+shortestDistance+"km" );
         System.out.println("VEHICLE:"+vehicle[vehicleType] );
         System.out.println("WEIGHT:"+weight + "kg");
         System.out.println("____________shortestDistance_______________________");
         System.out.println("BASE COST:"+ ((shortestDistance +" x " +ratePerKm[vehicleType])+" x "+ "(1+("+weight+"/10000)) = ")+value1.format(baseCost) + " LKR");
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
                 
        }System.out.println("Delivery details added Successfully!");
        
            }
         
    
     
     public static int LeastDistanceRoute(String[]city,int[][] distances,int cityCount,int sourceCity,int destinationCity){
         //created for find least distance route from one city to another(<=4 Ccities)
         if (sourceCity==destinationCity){//make sure source and destination is different
             System.out.println("Source and destination can't be same!");
             return 0;
             
         }
         
         int leastDistance =Integer.MAX_VALUE; // first got least distance as a large number like infinity
         List<Integer> bestPath=new ArrayList<>();
         
         //consider the direct route
         if(distances[sourceCity][destinationCity]> 0){ //if direct route is gtreater than 0
             leastDistance=distances[sourceCity][destinationCity];//least distance= direct route
             bestPath=Arrays.asList(sourceCity,destinationCity);
         }
         
         //  consider routes with one intermediate city
         for( int x=0;x<cityCount;x++){  // to get count in cities.x is the intemediate city index
             if(x==sourceCity || x == destinationCity)
                 continue; // prevent repeating the indexes of source and destination
             if(distances[sourceCity][x] > 0 && distances[x][destinationCity] >0){
                 int total=distances[sourceCity][x] +distances[x][destinationCity];// getting the distances between source,x and destination
                 if(total<leastDistance){
                        leastDistance =  total;// if total is less than the direct route distance,now total become least distance route
                        bestPath=Arrays.asList(sourceCity,x,destinationCity);
                 }
             }    
                 
        }
         
         //consider routes with two intermediate cities
          for( int x=0;x<cityCount;x++){  // to get count in cities.x is one intemediate city index
             if(x==sourceCity || x == destinationCity)
                 continue; // prevent repeating the indexes of source and destination
                  for( int y=0;y<cityCount;y++){ //y is for other intermediate city
                      if(y==sourceCity || y == destinationCity || y==x)
                          continue;  // prevent repeating the indexes of source , other intermediate city and destination
                      
                  
                  if(distances[sourceCity][x] > 0 && distances[x][y]>0 && distances[y][destinationCity] >0){ // confirm each distaces having a value which is greater than 0
                 int total=distances[sourceCity][x] +distances[x][y]+distances[y][destinationCity ];// getting the distances between source,x ,y and destination
                   if(total<leastDistance){
                        leastDistance =  total;// if total is less than the direct route distance,now total become least distance route
                        bestPath=Arrays.asList(sourceCity,x,y,destinationCity);
                 }
             }    
          }      
        }
         if ( bestPath.isEmpty()|| leastDistance==Integer.MAX_VALUE){ // checking the values of bestPath and leastDistance  has changed or not
                      
              System.out.println("\n No valid route found! \n");
              return 0;}
         
           System.out.println("\n ======== SH0RTEST ROUTE======== \n") ;
          System.out.print("Route:");
           for( int i=0;i<bestPath.size();i++){
               System.out.print(city[bestPath.get(i)]);// output the  least distance route by city names
               if(i< bestPath.size () -1)
                   System.out.print("->");  //repeating arrows until destination city from source city
           }
            System.out.println("\n Total Distances:"+ leastDistance+ "km");
            System.out.println("================================");
            return  leastDistance;
               
           }
     
     public static void reportMenu(){
          if (deliveryCount==0  ){
               System.out.println("NO deliveries to display!");
               return;
            }
          double totalDistance = 0;
          double totalRevenue =0;
          double totalProfit =0;
          double totalTime =0;
          double  longestRoute =deliveryDistance [0]; // this value got as a start
          double shortestRoute = deliveryDistance [0];// this value got as a start
          
          for(int i=0; i< deliveryCount;i++){
              totalDistance +=deliveryDistance [i];
              totalRevenue += Double.parseDouble(deliveryCharge [i]);
              totalProfit+= Double.parseDouble(deliveryCharge [i])  * 0.2; // 25% of base cost = 20% 0f final price    [( 0.25/(1+0.25))= 0.2 ]
              totalTime +=deliveryDistance [i]/45.0; // rough average speed got as 45 km/h 
              
              if(deliveryDistance [i] > longestRoute) // finding longestRoute by comparing routes with  deliveryDistance [0]
                  longestRoute =deliveryDistance [i]; 
              if(deliveryDistance [i] < shortestRoute) // finding longestRoute by comparing routes with  deliveryDistance [0]
                  shortestRoute=deliveryDistance [i];}

              //printing the values
             System.out.println("============ PERFORMANCE REPORT ============");
             System.out.println(" Total completed deliveries: " + deliveryCount  );
             System.out.println("Total distance covered: "+ totalDistance + "km");
             System.out.println("Average delivery time: "+ (totalTime/deliveryCount) + " hours");
             System.out.println("Total Revenue: "+ totalRevenue + " LKR");
             System.out.println("Total profit: "+ totalProfit +"LKR");
             System.out.println( "Completed longest route: "+ longestRoute + "km");
             System.out.println( "Completed shortest route: "+  shortestRoute + "km");
             System.out.println("=============================================");  
          }
          
     public static void deliveryTable(){
          Scanner sc=new Scanner (System.in);
         System.out.print("Enter the delivery number: ");
         int deliveryNumber=sc.nextInt();
        
          // checking avilability of cities to dispaly
            if (deliveryCount==0 || deliveryNumber <=0 || deliveryNumber>deliveryCount ){
               System.out.println("NO deliveries to display!");
               return;
            }
            
           
             
          //display delivery records
          
         System.out.println("\n __________ DELIVERY RECORDS ___________");
         System.out.println ("Delivery Number :"+ (deliveryNumber)) ;  
         System.out.println("FROM:"+deliverySource[deliveryNumber-1]);
         System.out.println("TO:"+deliveryDestination [deliveryNumber-1]); 
         System.out.println("MINIMUM DISTANCE:"+deliveryDistance [deliveryNumber-1]+"km" );
          System.out.println("VEHICLE:"+deliveryVehicle[deliveryNumber-1] );
         System.out.println("WEIGHT:"+deliveryWeight[deliveryNumber-1]+ "kg");   
          System.out.println("CUSTOMER CHARGE:"+ deliveryCharge [deliveryNumber-1] + "LKR");
          System.out.println("=================================================");
                
   
 
    
             }
                    // for save routes
     
         static void saveRoutesToFile(String []city,int [][]distances,int cityCount) {
             
      File tempFile =new File (" routes_temp.txt"); // created a temporary file.because  real files stays intact until the write completes
      try // prevents resource leaks and ensures the file handle is released with automatically closing file even if an exception occurs 
          (BufferedWriter writer = new BufferedWriter( new FileWriter(tempFile))){ //BufferedReader wraps FileWriter for efficient writing
          writer.write(cityCount + "\n"); //stores number of cities
          for(int i=0; i< cityCount; i++){
              writer.write(city[i]+ "\n"); //write each city in its own line
          }
          for(int i=0; i< cityCount; i++){
              for (int j=0;j< cityCount;j++){
                  writer.write(distances [i][j]+ ( j< cityCount -1 ? " " : " "));} //each matrix roe is one line  with space separated integers
               writer.newLine();}
          writer.flush(); // forces buffered data to disk cleary
                  
        //delete old file (if exists) then rename the temp file to the real file name to avoid half written files
        File realFile =new File("routes.txt");  
        if( realFile.exists()) 
            realFile.delete(); 
        tempFile.renameTo(realFile);
        System.out.println("ROUTES SAVED SUCCESSFULLY! ");
        }
      catch(IOException e){ //handles I/O isuues and prevents the whole program from crashing
          System.out.println (" ERROR SAVING IN ROUTES: " + e.getMessage()); //telling the user that something went wrong
         }
      
      }
         static int loadRoutes (String []city,int [][]distances){
             File file = new File ("routes.txt");
             if (! file.exists()){ // if no file after return 0 it can call back to default cities in code
                 System.out.println("NO ROUTES FILE FOUND");
                 return 0;  
             }
             int cityCount =0;
                // giving auto close on exit
             try
          (BufferedReader reader = new BufferedReader( new FileReader(file))){ 
                 //read the first line in cities
                 cityCount= Integer.parseInt(reader.readLine().trim());  // every readLine gives a city name
             
                 for(int i=0; i< cityCount; i++){
                     city[i]=reader.readLine().trim();
                 }
                 for(int i=0; i< cityCount; i++){
                      //splits with spaces and pass each piece with Integer.parseInt
                     String [] parts =reader.readLine().trim().split("\\s+");
                     for (int j=0;j< cityCount;j++){
                         distances [i][j]=Integer.parseInt(parts[j]);
                         
                     }
                     
                 }
                 System.out.println("ROUTES LOADED SUCCESSFULLY! ("+ cityCount+ "cities)");}
             catch (Exception e){ // giving chance to detect errors
                 System.out.println("ERROR SAVING IN ROUTES : "+ e.getMessage());
                 cityCount = 0;
             } return cityCount;
                 
             }
         
              static void saveDeliveriesToFils (String []deliverySource,
                  String [] deliveryDestination ,int []deliveryDistance, String [] deliveryVehicle ,double [] deliveryWeight,String [] deliveryCharge,int deliveryCount
              ){
                  //first using a tempory file to safe approach
                  File tempFile = new File ("deliveries_temp.txt");
                  try
                      (BufferedWriter writer = new BufferedWriter( new FileWriter(tempFile))){
                       writer.write(deliveryCount + "\n"); 
                        for(int i=0; i< cityCount; i++){
                           writer.write(String .join ( ",",
                                   deliverySource[i],
                                   deliveryDestination [i],
                                   String.valueOf(deliveryDistance[i]),
                                   deliveryVehicle [i],
                                   String.valueOf(deliveryWeight [i]),
                                  deliveryCharge[i]
                           ));
                        writer.newLine();
                        }
                        //writer.flush();
                        
                File realFile =new File ("deliveries.txt");
                if(realFile.exists())
                    realFile.delete();
                tempFile.renameTo(realFile);
                
                System.out.println("DELIVERIES SAVED SUCCESSFULLY! ("+ cityCount+ "cities)");}
             catch (IOException e){
                 System.out.println("ERROR SAVING IN DELEIVERIES : "+ e.getMessage());
                
             }
                 
                
                
         }
              
              static int loadDeliveries(String []deliverySource,
                  String [] deliveryDestination ,int []deliveryDistance, String [] deliveryVehicle ,double [] deliveryWeight,String [] deliveryCharge
              ){
                  File file = new File ( "deliveries.txt");
                      if (! file.exists()){
                 System.out.println("NO DELIVERIES FILE FOUND");
                 return 0;  
             }
                 int deliveryCount = 0;
                try 
                   (BufferedReader reader = new BufferedReader( new FileReader(file))){ 
                      deliveryCount= Integer.parseInt(reader.readLine().trim()); // tells how many records to except
                      for(int i=0; i< deliveryCount; i++){
                         String line =reader.readLine(); // read a line,skip if empty
                          if (line == null || line.trim().isEmpty())
                               continue;
                            String [] parts =line.split(",");// getiing field
                             if(parts.length < 6) //protect against partial lines
                            continue;
                       deliverySource[i]=parts[0];
                       deliveryDestination [i]=parts[1];
                       deliveryDistance[i]=Integer.parseInt(parts[2]);
                       deliveryVehicle [i]=parts[3];
                       deliveryWeight [i]=Integer.parseInt(parts[4]);
                       deliveryCharge[i]=parts[5];
                   }
                     System.out.println("DELIVERIES LOADED SUCCESSFULLY! ("+ deliveryCount+ "records)");
                  }
                    catch(Exception e){ //any parse error caughta and handle by catch 
                    System.out.println("ERROR LOADING IN DELIVERIES : "+ e.getMessage());
                        deliveryCount = 0;
                } 
                       return deliveryCount; // to caller to know how many deliveries were loaded
              }
                       
              
         }       
             
                  
            
                        

         
                                   
                                   
                                  
                           
                      
                      
                  
                  
                  
                  
             
             
             
         
         
         
          
          
          
             
             
             
         
     
     
          
          
                 
                 
             
             
             
         
     
         
             
         
         
         
         
         
     
     
     
   
    
     
 


    




     
            
         
     
             
    
         
         
         
     
         
         
         
    
    

            
     

     
 
         
         
         
     
   

