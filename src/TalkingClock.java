import java.util.Scanner;

public class TalkingClock {
	
	public static void main(String[] args){
		//limit input tries to 3
		int tries = 3;
		//ask for input
		System.out.println("Enter a time (example 05:00) : ");
		Scanner input = new Scanner(System.in);
		//read the input as a String
		String time = input.nextLine();
		while (tries > 0){
			try {
				//run the method to get the output
				CombineTime(time);
				tries = -1;
			} catch (Exception e) {
				//if the input is invalid, give users another chance if it is not their third attempt
				if (tries > 1){
					System.err.print("Error: Please enter a valid input (Ex. 00:00 or 20:30)");
					time = input.nextLine();
				}
				tries--;
			}
		}
		if (tries != -1) {
			//Close out the program is input is invalid 3 times
			System.out.println("You have entered invalid input too many times.");
		}
		input.close();
	}
	
	private static final String[] tens = {" twenty", " thirty", " forty", " fifty"};
	
	private static final String[] single = {"", " one", " two", " three", " four", " five",
		    " six", " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen",
		    " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen"};
	
	/**
	 * Return whether the string time is AM or PM
	 * 
	 * @param time
	 * @return the string "am" or "pm" for corresponding time of day
	 */
	public static String GetAMPM(String time){
		//check if hours input is 0-11 for am or else it is pm
		if (Integer.valueOf(time.substring(0, 2)) < 12) {
			return " am";
		} else {
			return " pm";
		}
	}
	
	/**
	 * Return the hour of the day in string format
	 * 
	 * @param time
	 * @return the string for corresponding hour
	 */
	public static String GetHour(String time){
		//get the hour in a 12-hour format
		Integer hour = Integer.valueOf(time.substring(0, 2)) % 12;
		if (hour == 0){
			hour = 12;
		}
		//return the corresponding hour from the array of single numbers
		return single[hour];
	}
	
	/**
	 * Return the minutes of the hour in string format
	 * 
	 * @param time
	 * @return the string for corresponding minutes
	 */
	public static String GetMinutes(String time){
		//get the minutes from the string
		int minutes = Integer.valueOf(time.substring(3, 5));
		String minuteString = " ";
		if (0 < minutes && minutes < 10){
			//if the minutes are single digit, add the oh string
			minuteString = " oh" + single[minutes];
		} else if (minutes < 20){
			//if 10-19, get the corresponding single number
			minuteString = single[minutes];
		} else {
			//floor minutes divided by 10 to get the corresponding tens digit 
			//and add the corresponding single digit by getting the reminder when dividing by 10
			minuteString = tens[Math.floorDiv(minutes, 10) - 2] + single[minutes%10];
		}
		return minuteString;
	}
	/**
	 * Print the string that combined the hour, minutes and whether the 
	 * input is AM or PM
	 * 
	 * @param time
	 */
	public static void CombineTime(String time){
		//this is the main function called to get add the hours, minutes 
		//and am/pm into a string and print to console
		String combinedTime = "It's" + GetHour(time) + GetMinutes(time) + GetAMPM(time);	
		System.out.println(combinedTime);
	}

}
