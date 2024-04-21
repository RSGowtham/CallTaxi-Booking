package TaxiBooking.src;

import java.util.Scanner;

public class TaxiBooking {
    public static Scanner sc = new Scanner(System.in);
    public static boolean creation=true;
    public static void detailCollection()
    {
    	System.err.println("Welcome to Taxi Booking");
    	System.out.print("Enter Customer Id:");
    	int custId=sc.nextInt();
    	sc.nextLine();
    	System.out.print("\nEnter PickUp Point:");
    	char pu=sc.next().charAt(0);
    	int from=charToInt(pu);
    	//Checking the Input is Right or Wrong
    	if(from>TaxiAndPlaces.totalStop || from<0)
    	{
    		System.err.println("\nInvalid Input");
    		TaxiBooking.repeat();
    	}
    	System.out.print("\nEnter Drop Point:");
    	char dp=sc.next().charAt(0);
    	int to=charToInt(dp);
    	if(to>TaxiAndPlaces.totalStop || to<0)
    	{
    		System.err.println("\nInvalid Input");
    		TaxiBooking.repeat();
    	}
    	System.out.print("\nTime");
    	int time=sc.nextInt();
    	sc.nextLine();
    	booking(custId,from,to,time);
    	
    }
    public static void booking(int custId,int from,int to,int time)
    {
    	if(Places.initalTaxis(custId,from,to,time))
    	{
    		System.out.println("Thanks for Choosing Us");
    	}
    	else if(Places.pickPointTaxi(custId,from,to,time))
    	{
    		System.out.println("Thanks for Choosing Us");
    	}
    	else if(Places.nearestTaxi(custId,from,to,time))
    	{
    		System.out.println("Thanks for Choosing Us");
    	}
    	else
    	{
    		Taxis.noTaxi();
    	}
    }
    public static int charToInt(char ch)
    {
    	return (int)(Character.toLowerCase(ch)-'a');
    }
    
    public static void repeat()
    {
    	while(true)
		{
			if(creation)
			{
				creation=false;
				System.err.println("Cold Starting the CallTaxi Application");
				System.out.print("Enter Total number of Taxi fleet:");
				int taxiCount=sc.nextInt();
				sc.nextLine();
				System.out.print("\nEnter Stop List:");
				String stops=sc.nextLine();
				System.out.print("\nCreate new Password:");
				String password=sc.nextLine();
				new TaxiAndPlaces(taxiCount,stops,password);
			}
			else
			{
				System.out.println("To Book a Call Taxi Press 1\nTo Check Today Rides History Press 2(Only for the Drivers)\nTo See all the Taxi Rides Press 3(Only for Autority)\nTo Exit Press 0");
				System.out.print("Enter a number:"); 
				int choise=sc.nextInt();
				sc.nextLine();
				if(choise==0)
				{
					System.err.println("Pleasure to Help You :)");
					return;
				}
				if(choise==1)
				{
					detailCollection();
				}
				else if(choise==2)
				{
					System.err.print("Enter TaxiId:");
					String driver=sc.next();
					System.err.print("Enter Authenticate Password:");
					String pass=sc.next();
					if(pass.equals(TaxiAndPlaces.password))
					{
					Taxis tx=Taxis.driveId(driver);
					if(tx!=null){
						tx.todayRides();
					}
					else
					{
						System.err.println("Invalid DriverId");
						TaxiBooking.repeat();
					}
					}
					else
					{
						System.err.println("Invalid Password");
						TaxiBooking.repeat();
					}
				}
				else if(choise==3)
				{
					System.err.print("Enter Authenticate Password:");
					String pass=sc.next();
					if(pass.equals(TaxiAndPlaces.password))
					Taxis.AllTaxiList();
					else
					{
						System.err.println("Invalid Password");
					}
					
				}
				else
				{
					System.err.println("Invalid DriverId");
					TaxiBooking.repeat();
				}
				
			}
		}
    }
	public static void main(String[] args) {
		TaxiBooking.repeat();
	}

}
