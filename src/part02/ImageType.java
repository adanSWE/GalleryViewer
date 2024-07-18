package part02;

public enum ImageType {
	// Initializing all the Image Genres (along with a wee string name)
	ASTRONOMY("ASTRONOMY"), ARCHITECTURE("ARCHITECTURE"), SPORT("SPORT"), LANDSCAPE("LANDSCAPE"), PORTRAIT("PORTRAIT"),
	NATURE("NATURE"), AERIAL("AERIAL"), FOOD("FOOD"), OTHER("OTHER");

	// Instance variable:
	private String genreName; // Using this instance variable to assign genres their respective names.

	// Constructor method:
	private ImageType(String name) {
		this.genreName = name; // assigning the name of the enum type to the genre
	}

	// Object methods:
	public static ImageType genreFinder(String genre) {
		if (genre.equals("ASTRONOMY")) { // If the user inputted genre is equal to the ASTRONOMY string
			return ImageType.ASTRONOMY; // return astronomy enum type

		} else if (genre.equals("ARCHITECTURE")) { // If the user inputted genre is equal to the ARCHITECTURE string
			return ImageType.ARCHITECTURE; // return ARCHITECTURE enum type

		} else if (genre.equals("SPORT")) { // If the user inputted genre is equal to the SPORT string
			return ImageType.SPORT; // return astronomy SPORT type

		} else if (genre.equals("LANDSCAPE")) { // If the user inputted genre is equal to the LANDSCAPE string
			return ImageType.LANDSCAPE; // return LANDSCAPE enum type

		} else if (genre.equals("PORTRAIT")) { // If the user inputted genre is equal to the POTRAIT string
			return ImageType.PORTRAIT; // return POTRAIT enum type

		} else if (genre.equals("NATURE")) { // If the user inputted genre is equal to the NATURE string
			return ImageType.NATURE; // return NATURE enum type

		} else if (genre.equals("AERIAL")) { // If the user inputted genre is equal to the AERIAL string
			return ImageType.AERIAL; // return AERIAL enum type

		} else if (genre.equals("FOOD")) { // If the user inputted genre is equal to the FOOD string
			return ImageType.FOOD; // return FOOD enum type

		} else if (genre.equals("OTHER")) { // If the user inputted genre is equal to the OTHER string
			return ImageType.OTHER; // return OTHER enum type

		} else {
			return ImageType.OTHER; // return OTHER enum type if the user inputted type is not found in the pre-existing types
		}

	}

	// toString() method:
	public String toString() {
		String string = ""; // Initializing a string variable 
		string += this.genreName; // concatenating the string variable with the genreName
		return string; // returning a string which holds the genre name
	}

}
