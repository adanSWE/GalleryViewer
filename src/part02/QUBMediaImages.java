package part02;

import console.Console; // Importing the console 
import java.awt.Color; // importing color to set console color
import java.awt.Font; // importing font to set console font
import javax.swing.ImageIcon; // import ImageIcon to display images

import java.time.LocalDate; // Importing LocalDate to use dates in the code
import java.util.InputMismatchException; // Importing mismatchexceptions to catch input mismatch exceptions
import java.util.Scanner; // importing scanner to take user input

public class QUBMediaImages {

	public static void main(String[] args) {
		// Setting up the console
		Console con = new Console(true);// Instantiating the console object with true parameter to allow user input
		con.setSize(1200, 500); // Setting the width and height of the console
		con.setVisible(true);// Making the console visible to the user
		con.setBgColour(Color.BLACK); // setting background colour for console: black
		con.setFont(new Font("Courier", Font.PLAIN, 20)); // set font for console
		con.setColour(Color.WHITE); // set text colour for console
		
		Scanner scanner = new Scanner(System.in); // Creating a scanner object to take user inputs
		ImageManager imgManager = new ImageManager(); // Instantiating a single imageManager object to append the ImageRecordss
		ImageRecord imgRec = null; // Instantiating a single imgRec object to create all the image records
		menuFunctionality(scanner, imgManager, imgRec, con); // Running the Menu

	}

	/**
	 * This method runs the Main menu 
	 * @param scanner: takes the scanner object as formal parameter
	 * @param imgManager: takes the imgManager object as formal parameter
	 * @param imgRec: takes the imgRec object as formal parametr
	 */
	public static void menuFunctionality(Scanner scanner, ImageManager imgManager, ImageRecord imgRec, Console con) {
		boolean flag = false; // Using a flag to check whether the user choice entered is valid/invalid
		while (!flag) { // while loop to keep displaying the menu until a valid option has been entered
			displayMenu(con); // Calling the menu method 
			// Surrounding the user input with a try and except to avoid InputMismatch Errors:
			try {
				con.print("Enter your choice (1/2/3/4): "); // Asking the user to enter a choice
				String choice = con.readLn(); // storing the user choice in choice variable of String type (console takes only strings)
				con.println(); // leaving a line (for formatting purposes)
				
				//scanner.nextLine(); // Going to be using nextLine(), therefore refreshing the scanner before

				switch (choice) { // switching on user inputted choice
				case "1": // If user enters 1
					addImage(scanner, imgManager, imgRec, con); // running the addImage method using scanner, imgManager and imgRec as actual parameters
					flag = true; // setting flag to true to get out of the while loop
					break; // break statement to prevent all the cases running at once

				case "2": // If user enters 2
					searchImages(scanner, imgManager, imgRec, con); // running the searchImages method using scanner, imgManager and imgRec as actual parameters
					flag = true; // setting flag to true to get out of the while loop
					break;  // break statement to prevent all the cases running at once

				case "3": // If user enters 3
					displayImageInfo(scanner, imgManager, imgRec, con); // running the displayImageInfo method using scanner, imgManager and imgRec as actual parameters
					flag = true; // setting flag to true to get out of the while loop
					break;  // break statement to prevent all the cases running at once

				case "4": // If user enters 4
					con.println("Exiting..."); // Exits out of the menu
					flag = true; // setting flag to true to get out of the while loop
					break;  // break statement to prevent all the cases running at once

				default: // Runs if user input doesnt match any of the cases above
					con.println("Invalid option! Please Try again."); // printing out invalid option
					con.println(); // Leaving a line (formatting purposes)
				}

			} catch (InputMismatchException exception) { // catch an inputmismatch exception (variable: exception)
				con.print("Invalid Input. Please enter an integer! "); // letting user know that input entered is invalid.
				con.println(exception); // printing out and giving the user an idea of whats wrong
				con.println(); // leaving a line (formatting purposes)
			}
		}
	}
	
	/**
	 * Creating a seperate method for displaying system Menu, easier to make changes in the future, if needed.
	 */
	public static void displayMenu(Console con) {
		con.println("QUBImages: Your Gallery Menu ");
		con.println("-----------------------------");
		con.println("1.) Add an Image");
		con.println("2.) Search for an Image");
		con.println("3.) Display Image information");
		con.println("4.) Exit");
		con.println("-----------------------------");
	}
	
	// Creating a seperate method for adding an image record, easier to maintain code this way.
	public static void addImage(Scanner scanner, ImageManager imgManager, ImageRecord imgRec, Console con) {
		boolean flag = false; // Using a flag to run a while loop, until a proper instance of ImageRecord is instantiated.
		int originalImageID = ImageRecord.getID(); // Storing the OrignalImageID in a variable, incase of instantiation failure.
		while (!flag) { // Running a while loop using the flag variable
			// Surrounding with a try, catch and finally, to counter errors while instantiating an ImageRecord.
			try {
				con.print("Enter Image Title: "); // User inputted Image Title
				String title = con.readLn(); // Storing the user ImageTitle input in title variable

				con.print("Enter Image Description: "); // User inputted Image Description
				String desc = con.readLn(); // Storing the user ImageDescription input in desc variable

				con.print("Enter Image Genre: "); // User inputted Image Genre
				String genreInput = con.readLn(); // Storing the user ImageType input in genreInput variable
				genreInput = genreInput.toUpperCase(); // Making the input in all caps, to compare with the values in enum ImageType
				ImageType genre = ImageType.genreFinder(genreInput); // Using the genreFinder method in ImageType class to find the appropriate genre equivalent to user input

				con.print("Enter Image Thumbnail file name (.png extenstion): "); // User inputted Image Thumbnail file
				String thumbnail = con.readLn(); // Storing the user ImageThumbnail input in thumbnail variable

				imgRec = new ImageRecord(title, desc, genre, null, thumbnail); // Creating an instance of ImageRecord
				
				con.println("Image added successfully!"); // print statement to let user know that a record has been instantiated.
				con.println(); // Leave a line before printing menu (after exiting while loop)
				flag = true; // Setting flag to true to exit while loop (true indicates that instantiation has been successful)
			} catch (IllegalArgumentException exception) { // Catch IllegalArgumentException (for invalid input for the ImageRecord attributes
				con.println("Invalid input: " + exception.getMessage()); // Printing out error statement
				con.println("Please try again."); // Telling the user to try again with valid inputs
				
			} finally { // This finally block is always executed regardless of an exception
				// Checking if the flag is true or not, i.e, if the record has been instantiated successfully or not.
				if(!flag) { 
					ImageRecord.setNextID(originalImageID); // If the record is NOT instantiated successfully, then the ImageID is set to the initial ID (because of instantiating errors, i had to do it this way)
				} else if ((flag) && (imgRec != null)) { // If the record is instantiated successfully, then check if the record is null or not.
					imgManager.addImage(imgRec); // Add the record to the image manager class
				}
			}
		}
		menuFunctionality(scanner, imgManager, imgRec, con); // Going back to menu after instantiating an image record
	}
	
	/**
	 * Creating a seperate method for displaying search Menu
	 */
	public static void searchMenu(Console con) {
		con.println("Search Menu ");
		con.println("-------------------------------");
		con.println("1.) Search by Image ID         ");
		con.println("2.) Search by Image Title      ");
		con.println("3.) Search by Image Description");
		con.println("4.) Search by Image Type       ");
		con.println("5.) Search by Date Range       ");
		con.println("6.) Return to Main Menu        ");
		con.println("-------------------------------");
	}
	
	/**
	 * A method to search for images using the different searching types
	 * @param scanner: takes the scanner object as formal parameter
	 * @param imgManager:  takes the imgManager object as formal parameter
	 * @param imgRec: takes the imgRec object as formal parameter
	 */
	public static void searchImages(Scanner scanner, ImageManager imgManager, ImageRecord imgRec, Console con) {
		boolean flag = false; // Initialzing a flag to run a while loop as long
		String choice=""; // initializing the choice variable with 0
		
		while (!flag) { // while the flag is false, keep running (i.e while no valid option picked, display the menu 
			searchMenu(con); // displaying menu method
			con.print("Enter your choice (1/2/3/4/5/6): "); // printing out a statement asking for user choice input
			choice = con.readLn(); // setting the value of the choice variable 
			
			//scanner.nextLine(); // To refresh the scanner
			
			try { // Surrounding the switch with a try and catch (to catch inputmismatchexceptions)
				
				switch (choice) { // switching on user inputted choice
				case "1": // if choice == 1
					con.print("Enter Image ID: "); // asking user for input of image ID
					String imageID = con.readLn(); // storing the imageID in the imageID variable
					int id = Integer.parseInt(imageID);
					imgRec = imgManager.searchId(id); // initializing the imgRec object with the imageManager method searchId(imageID)
					if(imgRec != null) { // Checking if the imgRec object is NOT null
						con.println("Found Record: "); // printing out a a statement for formatting purposes
						con.println(imgRec); // printing our the imgRec object
						con.println(); // leaving a line (for formatting purposes)
						
						displayImage(imgRec, con, imgManager);
						
					} else { // if the imgRec object is null
						con.println("No such record found."); // printing out a string indicating that no such records are found
						con.println(); // leaving a line (for formatting purposes)
					}
					break; // break statement to prevent all the cases running at once

				case "2": // if choice == 2
					con.print("Enter Image Title: "); // Asking user for input 
					String imageTitle = con.readLn(); // storing imageTitle string
					ImageAlbum imgAlbum = imgManager.searchTitle(imageTitle); // instantiating an imageAlbum object 
					if(imgAlbum != null) { // checking if the imgAlbum is not null
						
						
						ImageRecord record = imgAlbum.getFirst();
						while(record != null) {
							con.println(record);
							displayImage(record, con, imgManager);
							con.println();
							record = imgAlbum.getNext();
						}
						
						
					} else { // if the imgAlbum is null
						con.println("No such records found."); // printing out a string to indicate that no such string is found
						con.println(); // leaving a line (for formatting purposes)
					}
					break; // break statement to prevent all the cases running at once

				case "3": // if choice == 3
					con.print("Enter Image Description: "); // printing out a string indicating that the system is asking for user reponse for image description 
					String imageDesc = con.readLn(); // storing the user input in variable imageDesc
					
					ImageAlbum imgAlbum3 = imgManager.searchDescription(imageDesc);
					
					if (imgAlbum3 != null) { // checking if the searchDescription(imgDesc) doesnt return null
						//con.println(imgManager.searchDescription(imageDesc)); // printing out the searchDescription(imageDesc) method of imgManager class
						//con.println(); // leaving a line (for formatting purposes)
						
						ImageRecord record = imgAlbum3.getFirst();
						while(record != null) {
							if (record.getImageDescription().contains(imageDesc)) {
								con.println(record);
								displayImage(record, con, imgManager);
								con.println();
							}
							record = imgAlbum3.getNext();
						}
						
					} else { // if the searchDescription(imageDesc) returns null
						con.println("No such records found."); // printing out a string to let the user know that no records are found
						con.println(); // leaving a line (for formatting purposes)
					}
					break; // break statement to prevent all the cases running at once

				case "4": // if choice == 4
					con.print("Enter Image Type: "); // Printing out a string to ask user for a valid image type
					String imgGenre = con.readLn(); // takes the image genre string and stores it in the imgGenre variable 
					imgGenre = imgGenre.toUpperCase(); // Changing all the letters to uppercase in the imgGenre string
					ImageType genre = ImageType.genreFinder(imgGenre); // Instantiating the imageType object (genre) and initializing it with the genreFinder() method with the inputted genre as a parameter
					
					ImageAlbum imgAlbum4 = imgManager.searchGenre(genre);
					
					if(imgAlbum4 != null) { // Checking if the genre is not null
						
						ImageRecord record = imgAlbum4.getFirst();
						while(record != null) {
							if (record.getImageGenre() == genre) {
								con.println(record);
								displayImage(record, con, imgManager);
								con.println();
							}
							record = imgAlbum4.getNext();
						}
						
					} else { // If the searchGenre(genre) returns null
						con.println("No such records found."); // Print out a statement to let the user know that no such records are found
						con.println(); // leaving a line for formatting purposes
					}
					break; // break statement to prevent all the cases running at once
					
				case "5": // if choice == 5
					con.print("Enter start date (DD-MM-YYYY) [earliest]: "); // Ask user for start date input
					String startDate = con.readLn(); // Storing users inputted starting date in variable startDate
					String[] startDateComponents = startDate.split("-"); // Splitting the start date based on "-",therefore three elements.
					int startDay = Integer.parseInt(startDateComponents[0]); // Converting the day from string to integer, from the string array of date componenets.
					int startMonth = Integer.parseInt(startDateComponents[1]); // Converting the month from string to integer, from the string array of date componenets.
					int startYear = Integer.parseInt(startDateComponents[2]); // Converting the year from string to integer, from the string array of date componenets.
					
					LocalDate start = LocalDate.of(startYear, startMonth, startDay); // Since the default format is YYYY/MM/DD of localDate library.
					
					con.print("Enter end date (DD-MM-YYYY) [latest]: "); // Ask user for end date input
					String endDate = con.readLn(); // Storing users inputted ending date in variable endDate
					String[] endDateComponents = endDate.split("-"); // Splitting the end date based on "-", therefore three elements.

					int endDay = Integer.parseInt(endDateComponents[0]); // Converting the day from string to integer, from the string array of date componenets.
					int endMonth = Integer.parseInt(endDateComponents[1]); // Converting the month from string to integer, from the string array of date componenets.
					int endYear = Integer.parseInt(endDateComponents[2]); // Converting the year from string to integer, from the string array of date componenets.

					LocalDate end = LocalDate.of(endYear, endMonth, endDay); // Since the default format is YYYY/MM/DD of localDate library.
					
					if (imgManager.searchDates(start, end) != null) { // Checking if the searchDates method doesnt return null
						con.println("Searching for records between " + startDate + " and " + endDate+": "); // If not null, printing out a statement indicating records found b/w start and end date.
						
						ImageAlbum imgAlbum5 = imgManager.searchDates(start, end);
						
						ImageRecord record = imgAlbum5.getFirst();
						while(record != null) {
							String[] imageDateComponents = record.getImageDate().split("-");
					        int imageDay = Integer.parseInt(imageDateComponents[0]);
					        int imageMonth = Integer.parseInt(imageDateComponents[1]);
					        int imageYear = Integer.parseInt(imageDateComponents[2]);
					        LocalDate imageDate = LocalDate.of(imageYear, imageMonth, imageDay);
					        
					        if (imageDate.isAfter(start.minusDays(1)) && imageDate.isBefore(end.plusDays(1))) { // Checking if the date of that record is b/w the start and end dates (inclusive)
					        	con.print(record); // If the dates are b/w the specified dates (inclusive), then the record is printed
					            displayImage(record, con, imgManager); // The image for that particular record is also printed
					            con.println(); // leaving a line for formatting purposes
					        }
							record = imgAlbum5.getNext();
						}
						
						
					} else { // if null is returned
						con.println("No such records found."); // printing out a statement to indicate that no such records are found.
						con.println(); // Leaving a line (for formatting purposes)
					}
					break; // break statement to prevent all the cases running at once
					
				case "6": // if choice == 6
					flag = true; // If user choice is 6, change value of flag to true and break out of the loop
					break; // break statement to prevent all the cases running at once
				default:
					con.println("Invalid option! Please Try again."); // printing out a statement indicating that the user choice is invalid
					con.println(); // leaving a line (for formatting purposes
					break; // break statement to prevent all the cases running at once
				}

			} catch (InputMismatchException exception) { // Catching inputmismatchexception (incase of any other data type than strings) and storing it in "exception" variable
				con.print("Invalid Input. Please enter an integer! "); // Printing out an indicating string to let the user know that inputted data type is invalid
				con.println(exception); // printing out the exact exception
				con.println(); // leaving a line (for formatting purposes)
				con.println(); // It clears the scanner for a fresh input. (preventing an infinite loop)
			}
		}
		con.println(); // leaving a line (for formatting purposes)
		if(choice.equals("6")) { // Checking if the user input is 6 (return to menu)
			menuFunctionality(scanner, imgManager, imgRec, con); // if it is, then go back to menu method (the menu method takes scanner, imgManager and imgRec as actual parameters
		}
	}
	
	/**
	 * A method to display the information about images (option 3)
	 * @param scanner: the scanner parameter takes the scanner object to read user input
	 * @param imgManager: image manager parameter takes the imgmanager object to access the images in image manager class
	 * @param imgRec: image record parameter takes the imgRecord object to access imgRecord class 
	 */
	public static void displayImageInfo(Scanner scanner, ImageManager imgManager, ImageRecord imgRec, Console con) {
		ImageAlbum imgAlbum = imgManager.getAllImages();
		if (imgAlbum != null) {
			
	        ImageRecord record = imgAlbum.getFirst();
	        while(record != null) {
	        	con.print(record);
	        	displayImage(record, con, imgManager);
	        	con.println();
	        	record = imgAlbum.getNext();
	        	
	        }
			con.println();
			menuFunctionality(scanner, imgManager, imgRec, con); // Run the menu
	    } else {
	        con.println("No images found."); // Let the user know that there are no images to display.
	        con.println(); // leaving a line (for formatting purposes only)
	        menuFunctionality(scanner, imgManager, imgRec, con); // Run the menu
	    }
	}
	
	
	public static void displayImage(ImageRecord imgRec, Console con, ImageManager imgManager) {
		String userdir = System.getProperty("user.dir");
		String ImageThumbnail = imgRec.getImageThumbnail();
		String path = userdir + "/Images/" + ImageThumbnail + "/";
		ImageIcon img = new ImageIcon(path);
		
		if (img.getIconWidth() <= 0 || img.getIconHeight() <= 0) {
	        con.print("No image available.");
	        con.println();
	    } else {
	    	con.print(img);
			con.println();
	    }

	}



}


