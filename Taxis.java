package TaxiBooking.src;

import java.util.LinkedList;

public class Taxis {
	    public static int bookingId=1;
	    public String taxiName;
		public int currentTime;
		public int totalAmount;
		
		public LinkedList<TaxiRecord>record;
		Taxis(int currentTime,int amount,String name)
		{
			this.totalAmount=amount;
			this.currentTime=currentTime;
			this.taxiName=name;
			this.record=new LinkedList<TaxiRecord>();
		}
		public void booking(int pick,int drop,int custid)
		{
			int dis=Math.abs(pick-drop);
			int amount=amountCal(dis);
			TaxiRecord rcd=new TaxiRecord(custid,bookingId,amount,pick,drop);
			this.record.add(rcd);
			bookingId++;
			this.totalAmount+=amount;

			System.out.println(this.taxiName+" has been Booked");
			System.out.println("TotalAmount:"+this.totalAmount+"  for the Ride");
			// Adding the Taxi to New Location
			Places plc=TaxiAndPlaces.places.get(drop);
			LinkedList<Taxis> tl=plc.taxiList;
			tl.add(this);
			
		}
		public static void noTaxi()
		{
			System.err.println("No Taxi are Available\nSorry for inconvenients");
		}
		public static int amountCal(int dis)
		{
			int total=0;
			dis*=15;
			dis-=5;
			total+=100;
			total=total+(dis*10);
			return total;
		}
		public static Taxis driveId(String driver)
		{
			LinkedList<Taxis> taxis=TaxiAndPlaces.taxis;
			int s=taxis.size();
			for(int i=0;i<s;i++)
			{
				Taxis tx=taxis.get(i);
				if(driver.equals(tx.taxiName))
					return tx;
			}
			return null;
		}
		public void todayRides()
		{
			System.err.println("Today Rides History of "+this.taxiName);
			System.out.println("BookingId  CustomerId  From  To  Amount");
			int l=this.record.size();
			for(int i=0;i<l;i++)
			{
				TaxiRecord tr=this.record.get(i);
				System.err.println(tr.bookingId+"  "+tr.custId+"  "+tr.pick+"  "+tr.drop+"  "+tr.amount);
			}
		}
		public static void AllTaxiList()
		{
			LinkedList<Taxis> taxis=TaxiAndPlaces.taxis;
			int s=taxis.size();
			for(int i=0;i<s;i++)
			{
				Taxis tx=taxis.get(i);
				System.err.println(tx.taxiName);
				int l=tx.record.size();
				for(int j=0;j<l;j++)
				{
					TaxiRecord tr=tx.record.get(j);
					System.err.println(tr.bookingId+"  "+tr.custId+"  "+tr.pick+"  "+tr.drop+"  "+tr.amount);
				}
			}
		}
}
