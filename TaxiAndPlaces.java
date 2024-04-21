package TaxiBooking.src;

import java.util.LinkedList;

public class TaxiAndPlaces {
	public static LinkedList<Taxis> taxis;
	public static LinkedList<Places> places;
	public static int totalStop;
	public static String password;
	TaxiAndPlaces(int taxicount,String stops,String password)
	{
		this.password=password;
		taxis=new LinkedList<Taxis>();
		places=new LinkedList<Places>(); 
		int i=1;
		while(i<=taxicount)
		{
			taxis.add(new Taxis(0,0,("Taxi-"+i)));
			i++;
		}
		String arr[]=stops.split(" ");
		for(int j=1;j<=arr.length;j++)
		{
			Places prev=new Places(j);
			places.add(prev);
			if(j==1)
			{
			  fillInitialStop(taxis, prev);
			}
		}
	     totalStop=arr.length;
	}
	public static void fillInitialStop(LinkedList<Taxis> taxis,Places start)
	{
		int s=taxis.size();
		for(int i=0;i<s;i++)
		{
			start.taxiList.add(taxis.get(i));
		}
	}
}
