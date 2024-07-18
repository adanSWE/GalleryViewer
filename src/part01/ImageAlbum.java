package part01;

public class ImageAlbum {
	// Instance variables:
	private ImageRecord[] myRecords; // Using an array to deal with all the records for Image Album class
	private int currentArrayIndex = -1; // Using a currentArrayIndex variable as a pointer to see where the index has reached. (Initial value of the pointer: -1, because 0...n are all the indices of an array.)
	
	// Constructor method:
	public ImageAlbum(ImageRecord[] myRecords) { // Constructor method takes and array (myRecords) of type ImageRecord
		this.myRecords = myRecords; // Initalizing the myRecords array with an array of type ImageRecord
		sortbyDate(); // Sorting all records
	}
	
	// Object behaviour methods:
	/**
	 * This method organizes all Image records in date order. (using bubble sort)
	 */
	protected void sortbyDate() { // making it private since it doesnt make sense to make it accessible by other classes.
		for (int i = 0; i < myRecords.length-1; i++) { // Since the myRecords array is an array holding ImageRecord objects, first we go through each record
			for (int j = 0; j < myRecords.length-i-1; j++) { // In this for loop we go through each attribute of an Image record (each attribute of the image record object)
                
				// If the date of a particular image record is greater than the date of the consecutive image Record. (lexographic String comparision using compareTo(), returns a positive number if string1 > string2)
				if (myRecords[j].getImageDate().compareTo(myRecords[j + 1].getImageDate()) < 0) { 
					// then swap both the records (bubble sort algorithim)
                    ImageRecord temp = myRecords[j]; 
                    myRecords[j] = myRecords[j+1];
                    myRecords[j+1] = temp;
                }
            }
        }
	}
	
	/**
	 * This method returns the first record in the array (myRecords) which contains all image records.
	 * @return returns the first record from the array of all records, and if it doesnt exist, then returns null.
	 */
	public ImageRecord getFirst() {
		if(this.myRecords != null && this.myRecords.length > 0) { // Checking if the array of all records is null or not AND if the array is not an empty array.
			this.currentArrayIndex = 0; // setting the currentArrayIndex to 0 (because we get the first record from the array of all records)
			return this.myRecords[this.currentArrayIndex];
		} else {
			return null; // method returns null if the array is either null / contains no elements
		}
	}
	
	/**
	 * This method returns the consecutive record in the array (myRecords) which contains all image records.
	 * @return returns the next consecutive record, and if it doesnt exist, it returns null
	 */
	public ImageRecord getNext() {
		if((this.currentArrayIndex >= 0 && this.currentArrayIndex < this.myRecords.length-1)) { // Checking if the currentIndex is between 0 and the last index of the array
			this.currentArrayIndex++; // If the case is true, then the arrayIndex is incremented
			return this.myRecords[this.currentArrayIndex]; // the next record is returned
		}  else {
			return null; // If the getFirst() function has not been passed, then null will be returned. (because, initial index is -1, thus the getFirst() method has to be executed first).
		}
	}
	
	/**
	 * This method returns the consecutive record in the array (myRecords) which contains all image records.
	 * @return returns the previous consecutive record, and if it doesnt exist, then returns null.
	 */
	public ImageRecord getPrevious() {
		if((this.currentArrayIndex > 0 && this.currentArrayIndex < this.myRecords.length)) { // Checking if the currentIndex is between 0 and the last index of the array
			this.currentArrayIndex--; // If the case is true, then the arrayIndex is decremented
			return this.myRecords[this.currentArrayIndex]; // the previous record is returned
		}  else {
			return null; // If the getFirst() function has not been passed, then null will be returned. (because, initial index is -1, thus the getFirst() method has to be executed first).
		}
	}
	
	// Overriding the toString() method in order to change the output for the search menu: (to properly format)
	@Override
	public String toString() {
		String data="Found Records: "; // Initializing a string variable (data)
		for(int i =0; i < myRecords.length; i++) { // Using a for loop to go through each record in the array.
			if(i == myRecords.length-1) { // If the index of the record in the array is the last index
				data += myRecords[i]; // No seperation comma (for formatting purposes only)
			} else {
				data += myRecords[i]+", "; // Seperate records with a comma
			}
		}
		return data; // return the whole data string
	}
}
