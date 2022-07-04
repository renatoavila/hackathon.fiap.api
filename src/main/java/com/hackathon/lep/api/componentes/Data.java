package com.hackathon.lep.api.componentes;

 
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Data {

	public static LocalDateTime  Agora() {
		
		return	 LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	 
	}
	
	 public static Date AgoraUTC()  {  // handling ParseException  
	        // create an instance of the SimpleDateFormat class  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");  
	        // set UTC time zone by using SimpleDateFormat class  
	        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));  
	        //create another instance of the SimpleDateFormat class for local date format  
	        SimpleDateFormat ldf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");  
	        // declare and initialize a date variable which we return to the main method  
	        Date d1 = null;  
	        // use try catch block to parse date in UTC time zone  
	        try {  
	            // parsing date using SimpleDateFormat class  
	            d1 = ldf.parse( sdf.format(new Date()) );  
	        }   
	        // catch block for handling ParseException  
	        catch (java.text.ParseException e) {  
	        	d1 = null;
	        }  
	        // pass UTC date to main method.  
	        return d1;  
	    }  
}
