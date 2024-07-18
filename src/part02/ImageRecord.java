package part02;

import java.time.LocalDate; // Importing LocalDate to use dates in the code

public class ImageRecord {
	// Instance variables:
	private int imageID; // private instance variable to hold imageID
	private String imageTitle; // private instance variable to hold imageTitle
	private String imageDesc; // private instance variable to hold imageDesc
	private ImageType imageGenre; // private instance variable to hold ImageType
	private String imageDate; // private instance variable to hold imageDate
	private String imageThumbnail; // private instance variable to hold imageThumbnail

	
	// Class variables:
	private static int id = 1; // private class variable that belongs to the class and not an instance of an object

	
	// Constructor method:
	public ImageRecord(String title, String desc, ImageType genre,LocalDate dateParameter, String thumbnail) { // Takes String title, String desc, ImageType genre, String thumbnail variable as parameters.
		//Setting ImageID by default to avoid discrepancies
		this.imageID = this.id; // assign the current ID to the imageID
		this.id++; // Increment the ID for the next image instance
		
		//Using setters to set and validate inputted attributes:
		setImageTitle(title);
		setImageDescription(desc);
		setImageGenre(genre);
		setImageDate(dateParameter); // Setting the current date for the appended image. (DD-MM-YYYY format)
		setImageThumbnail(thumbnail);
	}
	
	// Getter methods:
	public int getImageID() {
		return this.imageID; // returns imageID
	}
	
	public static int getID() {
		return id; // returns static int variable: id (to ReNumber a record) [For failed instances only!]
	}
	
	public String getImageTitle() {
		return this.imageTitle; // returns imageTitle
	}
	
	public String getImageDescription() {
		return this.imageDesc; // returns imageDesc
	}
	
	public ImageType getImageGenre() {
		return this.imageGenre; // returns imageGenre
	}
	
	public String getImageDate() {
		return this.imageDate; // returns imageDate
	}
	
	public String getImageThumbnail() {
		return this.imageThumbnail; // returns imageThumbnail
	}
	

	// Setter methods:
	
	/**
	 * This method is used in cases where the imageRecord instance creation is failed, and is repeated again until user enters valid data
	 * @param nextID: nextID is the orignalID of the ImageRecord
	 */
	public static void setNextID(int orignalID) {
        id = orignalID; // Setting the id as orignalImageID
    }
	
	/**
	 * In English: For titles, each word in a title begins with a capital letter.
	 * @param title: a title appropriate to be the title of an Image object
	 */
	public void setImageTitle(String title) {
		// Initializing a flag to check if each word is grammatically accurate.
		boolean flag = false;
		
		if (title != null) { // Checking if the title is a null value.
			if (!title.isEmpty()) { // Checking if the title is an empty string or not.
				if(!title.isBlank()) { // Checking if the title is just whitespace characters or not.
					String[] titleWords = title.split(" "); // Creating an array of words of the title
					for (int i = 0; i < titleWords.length; i++) { // Going through each word in the title
						String word = titleWords[i]; // Assigning each word to a variable
						char firstLetter = word.charAt(0); // Getting the "first letter" of a "word" from title.
						// Checking if the first Letter of a word is capital or not.
						if (Character.isUpperCase(firstLetter)) {
							flag = true; // flag is true if the first letter of the word is capital
						} else {
							flag = false; // Even if one of the words doesn't begin with a capital letter, the title is NOT valid.
							break;
						}
					}
					
					
				} else {
					this.imageTitle = "N/A"; // default value for invalid input.
					throw new IllegalArgumentException("The title can NOT be just whitespace characters."); // exception is thrown if the title is just spaces
				}
			} else {
				this.imageTitle = "N/A"; // default value for invalid input.
				throw new IllegalArgumentException("The title can NOT be an empty string."); // exception is thrown if the title is blank
			}
		} else {
			this.imageTitle = "N/A"; // default value for invalid input.
			throw new IllegalArgumentException("The title can NOT be a null value."); // exception is thrown if the title is null
		}

		// Checking if all the conditions are satisfied.
		if (flag == true) {
			this.imageTitle = title; // if all conditions are satisfied, the imageTitle is set to the user inputted title
		} else {
			// Throws exception if the title IS grammatically inaccurate.
			this.imageTitle = "N/A"; // default value for invalid input.
			throw new IllegalArgumentException("The title IS grammatically inaccurate. Each first letter of a word in title, MUST be capitalized."); // If conditions are not satisfied, exception is thrown
		}
		
	}
	
	
	/** Since the description of an image is a sentence, we will follow the general grammatical rules of a sentence in English.
	 * - First word of a sentence is a capital letter.
	 * - A sentence ends with a punctuation mark.
	 * - the general, empty/blank/null string for description is invalid.
	 * 
	 * @param desc: a description appropriate for an image object
	 */
	public void setImageDescription(String desc) {
		boolean flag1 = false; // using a flag to check if the first letter of the first word is capital.
		boolean flag2 = false; // using a flag to check if the last character of the last word is a fullstop.
		
		if (desc != null) { // Checking if the description is a null value or not.
			
			if(!desc.isEmpty()) { // Checking if the description is an empty string or not.
				
				if(!desc.isBlank()) { // Checking if the description is just whitespace characters.
					String[] words = desc.split(" "); // Initializing an array of all the words in a sentence.
					
					for(int i =0; i < words.length; i++) { // Going through each word in the description
						
						// First condition: Checking if the first letter of the first word is capital.
						if(i == 0) { // If its the first word.
							char firstLetter = words[0].charAt(0); // Initialzing the first letter of the first word to a variable.
							if(Character.isUpperCase(firstLetter)) { // If the first letter of the first word is uppercase, then condition is satisfied.
								flag1 = true; // flag1 is set to true if the first letter is capital
							} else {
								flag1 = false; // else, set to false
							}
						}
							
						// Second condition: If the last character of the last word is a ' . ' (fullstop)
						if(i == words.length-1) {
							char lastLetter = words[i].charAt(words[i].length()-1); // assigning the last character of the last word to a variable.
							if(lastLetter == '.') {
								flag2 = true; // flag2 is set to true if the last character of the description string is '.'
							} else {
								flag2 = false; // else, set to false
							}
						}
					}
					
					// Checking if all the conditions have been satisfied
					if ((flag2 == false) && (flag1 == true)) { // Checking if flag2 is not satisfied but flag1 is satisfied
						this.imageDesc = "N/A"; // if true, the description is set to N/A
						
						throw new IllegalArgumentException("Description should end with a fullstop.");
					} else if((flag2 == true) && (flag1 == false)) { // Checking if flag2 is satisfied but flag1 is not satisfied
						this.imageDesc = "N/A"; // if true, the description is set to N/A
						
						throw new IllegalArgumentException("Description should begin with a capital letter.");
					} else if (flag1 == false && flag2 == false) { // Checking if flag2 and flag1 are not satisfied
						this.imageDesc = "N/A"; // if true, the description is set to N/A
						
						throw new IllegalArgumentException("Description should begin with a capital letter AND should end with a fullstop.");
					} else if (flag1 == true && flag2 == true) { // Checking if flag2 and flag1 are both satisfied
						this.imageDesc = desc; // if both conditions are satisfied, the image description is set to the user inputted description
					}
					
				} else {
					this.imageDesc = "N/A"; // default value for invalid input.
					throw new IllegalArgumentException("Description can NOT just be whitespace characters."); // throws exception if the description is just whitespace characters.
				}
				
			} else {
				this.imageDesc = "N/A"; // default value for invalid input.
				throw new IllegalArgumentException("Description can NOT be an empty string."); // throws exception if the description is an empty string.
			}
			
		} else { // Throw an exception if the description is a null value.
			this.imageDesc = "N/A"; // default value for invalid input.
			throw new IllegalArgumentException("Description can NOT be null"); // throws exception if the description is null.
		}
	}
	
	
	// This method just sets the Image Genre from the Enumerator: ImageType
	public void setImageGenre(ImageType genre) {
		this.imageGenre = genre; // Setting image genre as the user inputted genre
	}
	
	
	/**
	 * By default, LocalDate class uses: YYYY-MM-DD format,
	 * which is why the setter uses individual LocalDate class methods to get: DD/MM/YYYY
	 * ( inherited some LocalDate methods for code functionality )
	 * 
	 * - No possible chance of errors as its automatically set depending on the day. (Thus, no validation needed)
	 */
	public void setImageDate(LocalDate dateParameter) {
		if (dateParameter == null) {
			LocalDate date = LocalDate.now(); // Setting the date variable as current date of instantiation
			String formattedDate = date.getDayOfMonth() + "-" + date.getMonthValue() + "-"+ date.getYear(); // formatting the date in DD-MM-YYYY format
			// Splitting the days,months, and year to check the length of each string
			String[] individualDateElements = formattedDate.split("-"); // making an array of individual date elements (day, month, year)
			
			// Creating another String variable to hold the formatted Date (DD-MM-YYYY)
			String currentDate=""; 
			
			for(int i=0; i < individualDateElements.length; i++) { // Going through each element in the individual date elements
				// Checking for the length of the DAYS string, if its two, that means its double digits, if its one, that means its a single digit, therefore i can add a 0, to exactly match the sample data in the question.
				if((i == 0) && (individualDateElements[0].length() == 1)) {
					currentDate += "0"+date.getDayOfMonth();
				} else if((i == 0) && individualDateElements[0].length() == 2) {
					currentDate += date.getDayOfMonth();
				}
			
				// Checking for the length of the MONTHS string, so that the code can add a 0 before the units place of month
				if((i == 1) && (individualDateElements[1].length() == 1)) {
					currentDate += "-0" + date.getMonthValue();
				} else if((i == 1) && individualDateElements[1].length() == 2) {
					currentDate += date.getMonthValue();
				}
				
				//Checking if its the YEAR string
				if((i == 2)) {
					currentDate += "-" + date.getYear(); // finally, the currentdate is concatenated with the year
					break; // breaks out of loop
				}
			
			}
		
			this.imageDate = currentDate; // Finally, after formatting, we just give the value of the currentDate to the imageDate variable
			
		} else {
			
			String formattedDate = dateParameter.getDayOfMonth() + "-" + dateParameter.getMonthValue() + "-" + dateParameter.getYear();

			// Splitting the days,months, and year to check the length of each string
			String[] individualDateElements = formattedDate.split("-"); // making an array of individual date elements (day, month, year)

			// Creating another String variable to hold the formatted Date (DD-MM-YYYY)
			String currentDate = "";

			for (int i = 0; i < individualDateElements.length; i++) { // Going through each element in the individual date elements
				// Checking for the length of the DAYS string, if its two, that means its double digits, if its one, that means its a single digit, therefore i can add a 0, to exactly match the sample data in the question.
				if ((i == 0) && (individualDateElements[0].length() == 1)) {
					currentDate += "0" + dateParameter.getDayOfMonth();
				} else if((i == 0) && individualDateElements[0].length() == 2) {
					currentDate += dateParameter.getDayOfMonth();
				}

				// Checking for the length of the MONTHS string, so that the code can add a 0
				// before the units place of month
				if ((i == 1) && (individualDateElements[1].length() == 1)) {
					currentDate += "-0" + dateParameter.getMonthValue();
				} else if((i == 1) && individualDateElements[1].length() == 2) {
					currentDate += dateParameter.getMonthValue();
				}

				// Checking if its the YEAR string
				if ((i == 2)) {
					currentDate += "-" + dateParameter.getYear(); // finally, the currentdate is concatenated with the year
					break; // breaks out of loop
				}

			}

			this.imageDate = currentDate; // Finally, after formatting, we just give the value of the currentDate to the imageDate variable
		}
		
	}
	
	/**
	 * This method checks for various vulnerabilities that could be encountered when naming thumbnail files:
	 * 
	 * - Checking if the string is a null value
	 * - Checking if its a empty string 
	 * - Checking if its a blank string
	 * 
	 * - Checking for an extenstion and its validity (must be '.png')
	 * - Checking for any whitespace characters, as there must be none when naming a thumbnail file.
	 * 
	 * @param thumbnail: a thumbnail file name appropriate for image record
	 */
	public void setImageThumbnail(String thumbnail) {
		if(thumbnail == null) { // Checking if the user inputted thumbnail is null or not
			this.imageThumbnail = "Untitled.png"; // if true, then the thumbnail is set to "untitled.png" by default
			throw new IllegalArgumentException("Thumbnail string can NOT be null"); // throws exception if thumbnail is null
		}
		
		String[] lengthChecker = thumbnail.split(" "); // Initializing one array for checking the number of whitespaces (shouldnt be any)
		String[] extenstionChecker = thumbnail.split("\\."); // Initializing another array for checking if a file extenstion exists, and if so, to check if its '.png'
	
		if (!thumbnail.isEmpty()) { // Checking if the thumbnail string is just whitespaces
			if(!thumbnail.isBlank()) { // Checking if the thumbnail string is an empty string

			
				// Checking if theres just one element in the String (no whitespaces)
				if ((lengthChecker.length == 1)) {

					// Checking if a file extenstion exists, and if so, to check if its a ".png" file
					if ((extenstionChecker.length > 1) && (extenstionChecker[1].contains("png"))) {
						this.imageThumbnail = thumbnail; // Setting image thumbnail to user inputted thumbnail
					} else {
						this.imageThumbnail = "Untitled.png"; // Some default value for the thumbnail String
						throw new IllegalArgumentException("Extenstion is invalid, must be '.png' "); // exception is thrown if wrong extention used
					}

				} else {
					this.imageThumbnail = "Untitled.png"; // Some default value for the thumbnail String
					throw new IllegalArgumentException("Whitespace characters are not allowed when naming Thumbnail files."); // exception is thrown if inputted string is just whitespace characters
				}
				

			} else {
				this.imageThumbnail = "Untitled.png"; // default value for invalid input.
				throw new IllegalArgumentException("Thumbnail string can NOT be just whitespace characters"); // throws exception if the thumbnail is just whitespace characters.
			}
			
		} else {
			this.imageThumbnail = "Untitled.png"; // default value for invalid input.
			throw new IllegalArgumentException("Thumbnail string can NOT be an empty string"); // exception is thrown if the thumbnail string is an empty string
		}
		
		}
	
		
	// toString() method: (no line breaks as mentioned in the question)
	public String toString() {
		return "["+this.imageID +", "+ this.imageTitle+ ", "+this.imageDesc+", "+this.imageGenre+", "+this.imageDate+", "+this.imageThumbnail+"]"; // returns am image record
		
	}
}

