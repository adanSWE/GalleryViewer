package part01;

import java.time.LocalDate; // Importing LocalDate to use searchDates() function
import java.util.ArrayList; // Importing ArrayList to store Image Records in them

public class ImageManager {
	// Instance variables:
	ArrayList<ImageRecord> arrayList; // Creating an arraylist of type "ImageRecord"
	
	// Constructor method:
	public ImageManager() {
		this.arrayList = new ArrayList<ImageRecord>(); // Initializing the arrayList
		initialize();
	}

	// Object behaviour methods:
	
	public void initialize() {
		ImageRecord imgRec1 = new ImageRecord("Andromeda Galaxy", "Image of the andromeda galaxy.",ImageType.ASTRONOMY, LocalDate.of(2023, 01, 01), "andromeda.png");
		this.arrayList.add(imgRec1);
		
		ImageRecord imgRec2 = new ImageRecord("Lanyon QUB", "An image of the QUB Lanyon building.",ImageType.ARCHITECTURE, LocalDate.of(2023, 02, 01), "LanyonQUB.png");
		this.arrayList.add(imgRec2);
		
		ImageRecord imgRec3 = new ImageRecord("Kermit Plays Golf", "An image of Kermit the frog playing golf.",ImageType.SPORT, LocalDate.of(2023, 03, 01), "KermitGolf.png");
		this.arrayList.add(imgRec3);
		
		ImageRecord imgRec4 = new ImageRecord("Mourne Mountains", "A panoramic view of the Mourne mountains.",ImageType.LANDSCAPE, LocalDate.of(2023, 04, 01), "Mournes.png");
		this.arrayList.add(imgRec4);
		
		ImageRecord imgRec5 = new ImageRecord("Homer Simpson", "Homer Simpson- A portrait of the man.",ImageType.PORTRAIT, LocalDate.of(2023, 03, 01), "Homer.png");
		this.arrayList.add(imgRec5);
		
		ImageRecord imgRec6 = new ImageRecord("Red Kite", "A Red Kite bird of prey in flight.",ImageType.NATURE, LocalDate.of(2023, 04, 01), "RedKite.png");
		this.arrayList.add(imgRec6);
		
		ImageRecord imgRec7 = new ImageRecord("Central Park", "An overhead view of Central Park New York USA.",ImageType.AERIAL, LocalDate.of(2023, 05, 01), "CentralPark.png");
		this.arrayList.add(imgRec7);
		
		ImageRecord imgRec8 = new ImageRecord("Apples", "A bunch of apples.",ImageType.FOOD, LocalDate.of(2023, 06, 01), "Apples.png");
		this.arrayList.add(imgRec8);
		
		ImageRecord imgRec9 = new ImageRecord("Programming Meme", "A Chat GPT programming meme.",ImageType.OTHER, LocalDate.of(2023, 07, 01), "ChatGPT.png");
		this.arrayList.add(imgRec9);
	}
	
	/**
	 * This method adds a record into the ArrayList of records. (arrayList)
	 * @param image: takes ImageRecord instance as a parameter to append the imageRecord in the arrayList
	 */
	public void addImage(ImageRecord image) {
		arrayList.add(image); // Adding an ImageRecord in an ArrayList, using add function of arraylists.
		ImageAlbum imgAlbum = new ImageAlbum(arrayList.toArray(new ImageRecord[0])); //
	}
	
	/**
	 * This method searches for an ImageRecord using ImageID
	 * @return returns an instance of ImageRecord which contains the record which has the same imageID as the inputted id, else if no such record, returns null
	 * @param id: takes user inputted ImageID to search for that particular record
	 */
	public ImageRecord searchId(int id) {
		for(int i =0; i < arrayList.size(); i++) { // Going through each record in the arrayList of all records.
			ImageRecord record = arrayList.get(i); // Assigning an individual record, of type "ImageRecord" instance, to "record" variable (using get() function of arraylists)
			if(id == record.getImageID()) { // If the given id is equals to the imageID of that particular image record instance, return the record.
				return record; // return record
			}
		}
		return null; // If in the arrayList, no matching ID is found, return null. (as given in the question)
	}
	
	/**
	 * This method searches for an image using its Title
	 * @return returns an instance of ImageAlbum which contains all the records which contain the inputted string (str), else returns null
	 * @param str: takes user inputted string to search for that particular record which contains the same title
	 */
	public ImageAlbum searchTitle(String str) {
		ArrayList<ImageRecord> foundRecords = new ArrayList<ImageRecord>(); // Initializing an arrayList "foundRecords", to store all the records that contain str in Title.
		for(int i =0; i < arrayList.size(); i++) { // Using a for loop to go through the ImageRecord ArrayList "arrayList" that holds all Image Records.
			ImageRecord record = arrayList.get(i); // Assigning each record to a variable called "record" one by one.
	        if (record.getImageTitle().contains(str)) { // Checking if the ImageTitle contains the inputted string str
	            foundRecords.add(record); // append the record in  foundRecords arraylist.
	        }
	    }
		
		// Checking if any records have been found containing that str:
		if((!foundRecords.isEmpty())) {
			ImageAlbum albumRecords = new ImageAlbum(foundRecords.toArray(new ImageRecord[0])); // the found Image records are converted to an array and initialize an ImageAlbum instance which holds the found records.
			return albumRecords; // Return albumRecords if some records contain inputted string found in title.
		} else {
			return null; // Return null if nothing is found
		}
	}
	
	/**
	 * This method searches for an image using its Description
	 * @return returns an instance of ImageAlbum which contains all the records which contain the inputted string (str), else returns null
	 * @param str: takes user inputted string to search for that particular record which contains the same description
	 */
	public ImageAlbum searchDescription(String str) {
		ArrayList<ImageRecord> foundRecords = new ArrayList<ImageRecord>(); // Initializing an arrayList "foundRecords", to store all the records that contain str in Description.
		for(int i =0; i < arrayList.size(); i++) { // Using a for loop to go through the ImageRecord ArrayList "arrayList" that holds all Image Records.
			ImageRecord record = arrayList.get(i); // Assigning each record to a variable called "record" one by one.
	        if (record.getImageDescription().contains(str)) { // Checking if the ImageDescription contains the inputted string str
	            foundRecords.add(record); // append the record in  foundRecords arraylist.
	        }
	    }
		
		// Checking if any records have been found containing that str:
		if((!foundRecords.isEmpty())) {
			ImageAlbum albumRecords = new ImageAlbum(foundRecords.toArray(new ImageRecord[0])); // the found Image records are converted to an array and initialize an ImageAlbum instance which holds the found records.
			return albumRecords; // Return albumRecords if some records contain inputted string found in description.
		} else {
			return null; // Return null if nothing is found
		}
	}
	
	/**
	 * This method basically finds all the records of ImageType "type" and returns them, if none found, returns null
	 * @param type: Takes ImageType parameter to find all the records of type "type"
	 * @return returns an instance of ImageAlbum which contains all the records where ImageType is "type" or null if no ImageType of that sort exists
	 */
	public ImageAlbum searchGenre(ImageType type) {
		ArrayList<ImageRecord> foundRecords = new ArrayList<ImageRecord>(); // Initializing an arrayList "foundRecords", to store all the records that have ImageType of type, "type"
		for(int i =0; i < arrayList.size(); i++) { // Using a for loop to go through the ImageRecord ArrayList "arrayList" that holds all Image Records.
			ImageRecord record = arrayList.get(i); // Assigning each record to a variable called "record" one by one.
	        if (record.getImageGenre() == type) { // Checking if the ImageType contains the inputted ImageType "type"
	            foundRecords.add(record); // append the record in  foundRecords arraylist.
	        }
	    }
		
		// Checking if any records have been found containing that str:
		if((!foundRecords.isEmpty())) {
			ImageAlbum albumRecords = new ImageAlbum(foundRecords.toArray(new ImageRecord[0])); // the found Image records are converted to an array and initialize an ImageAlbum instance which holds the found records.
			return albumRecords; // Return albumRecords for all records where the imageType == type
		} else {
			return null; // Return null if nothing is found
		}
	}
	
	/**
	 * This method searches for records b/w the specified dates
	 * @param start: the starting date, to search for records from since this date.
	 * @param end: the ending date, to search for records before this date.
	 * @return returns an instance of ImageAlbum which contains all the image records b/w the specified date.
	 */
	public ImageAlbum searchDates(LocalDate start, LocalDate end) {
		ArrayList<ImageRecord> foundRecords = new ArrayList<ImageRecord>(); // Initializing an arrayList "foundRecords", to store all the records 
		for(int i =0; i < arrayList.size(); i++) { // Using a for loop to go through the ImageRecord ArrayList "arrayList" that holds all Image Records.
			ImageRecord record = arrayList.get(i); // Assigning each record to a variable called "record" one by one.
			
			String date = record.getImageDate(); // Getting the date of a record and storing in a string variable called data.
			String[] dateComponents = date.split("-"); // Splitting the date based on "-", therefore three elements.

			int dateDay = Integer.parseInt(dateComponents[0]); // Converting the day from string to integer, from the string array of date componenets.
			int dateMonth = Integer.parseInt(dateComponents[1]); // Converting the month from string to integer, from the string array of date componenets.
			int dateYear = Integer.parseInt(dateComponents[2]); // Converting the year from string to integer, from the string array of date componenets.
			
			
			LocalDate dateFormatted = LocalDate.of(dateYear, dateMonth, dateDay); // Assigning the date of a record to a variable "dateFormatted" of type LocalDate, in order to compare with the start and end parameters of type LocalDate
	        
			if (dateFormatted.isAfter(start.minusDays(1)) && dateFormatted.isBefore(end.plusDays(1))) { // Checking if the date is between the specified dates (start and end) (INCLUSIVE)
	            foundRecords.add(record);// append the record in  foundRecords arraylist.
	        }
	    }	
		
		// Checking if any records have been found between the specified dates:
		if((!foundRecords.isEmpty())) {
			ImageAlbum albumRecords = new ImageAlbum(foundRecords.toArray(new ImageRecord[0])); // the found Image records are converted to an array and initialize an ImageAlbum instance which holds the found records.
			return albumRecords; // Return albumRecords for all records where the imageType == type
		} else {
			return null; // Return null if nothing is found
		}
	}
	
	/**
	 * This method gets all the images in the ImageRecord object
	 * @return returns all the image records from ImageRecord class, if none found then returns null
	 */
	public ImageAlbum getAllImages() {
		ArrayList<ImageRecord> foundRecords = new ArrayList<ImageRecord>(); // calling the arrayList of all the Image Records.
		for(int i =0; i < arrayList.size(); i++) { // Going through each record in the arrayList
			ImageRecord record = arrayList.get(i); // Initializing an image record variable called record with a record from the arraylist
			foundRecords.add(record); // adding the imagerecord to the arraylist
		}
		
		// Checking if there are any elements in the arraylist or not
		if(!foundRecords.isEmpty()) {
			ImageAlbum albumRecords = new ImageAlbum(foundRecords.toArray(new ImageRecord[0])); // the found Image records are converted to an array and initialize an ImageAlbum instance which holds the found records.
			return albumRecords; // return all the found records
		} else {
			return null; // return null if no records are found
		}
	}

}
