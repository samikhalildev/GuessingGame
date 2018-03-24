import javax.swing.JOptionPane;

public class gameControl {

	int level = 1;
	int max = 5;
	int min = 1;
	
	int generatedNum;
	int userGuessedNum;
	
	int numOfAttempts = 1;
	int points = 0;
	int counter = 5;
	
	String userInput;

	public void start(){
		JOptionPane.showMessageDialog(null, "Guessing Game");
	}
			
	public void guessNumber(){
		JOptionPane.showMessageDialog(null, "Level " + level +": (" + min + "-" + max +")", "GuessingGame", JOptionPane.PLAIN_MESSAGE);

		//Generates a new random number
		generatedNum = global.RANDOM.nextInt((max - min) +1) + min;
		
		 //This will loop until the user guesses the number
		while(userGuessedNum != generatedNum){
						
			userInput = JOptionPane.showInputDialog("Guess the number: ");
			userGuessedNum = Integer.parseInt(userInput);
			
			outOfBound();
			reset();
			
			//If it is LESS than the generated number, do this
			
			if (userGuessedNum < generatedNum){
				JOptionPane.showMessageDialog (null, userGuessedNum + " is too small\n" + 
				"You have " + --counter + " attempts left", "GuessingGame", JOptionPane.PLAIN_MESSAGE);
				
				numOfAttempts++;

			//If it is GREATER than the generated number, do this
				
			} else if (userGuessedNum > generatedNum){
				JOptionPane.showMessageDialog (null, userGuessedNum + " is too large\n" +
				"You have " + --counter + " attempts left", "GuessingGame", JOptionPane.PLAIN_MESSAGE);
				
				numOfAttempts++;
			
				//If it matches, display the number of tries, give points and increase the level
				
			} else if(userGuessedNum == generatedNum){

				checkNum_OfAttempts();
				
				max += 5;
				numOfAttempts = 1;
				counter = 5;
			}
		}
		
		finish();
	}
	
	public void outOfBound(){
		
		while(userGuessedNum < min || userGuessedNum > max){
			JOptionPane.showMessageDialog(null, "The number must be between (" + min + "-" + max +")", "GuessingGame", JOptionPane.PLAIN_MESSAGE);
			userInput = JOptionPane.showInputDialog("Guess the number: ");
			userGuessedNum = Integer.parseInt(userInput);
		}
	}
	
	public void reset(){
		
		if (counter == 0){
			level = 1;
			max = 5;
			points = 0;
			numOfAttempts = 1;
			counter = 5;
			JOptionPane.showMessageDialog(null, "You have used all your attempts! Click 'OK' to restart the game.", "GuessingGame", JOptionPane.PLAIN_MESSAGE);

			JOptionPane.showMessageDialog(null, "Level " + level +": (" + min + "-" + max +")", "GuessingGame", JOptionPane.PLAIN_MESSAGE);
			generatedNum = global.RANDOM.nextInt((max - min) +1) + min;
			
			userInput = JOptionPane.showInputDialog("Guess the number: ");
			userGuessedNum = Integer.parseInt(userInput);
		}
	}
	
	public void checkNum_OfAttempts(){
		
		String[] someStrings = {"Today is your lucky day!", "try", "tries"," bounce", ""};
		final int[] ADDPOINTS = {20, 10, 5, 3};

		switch(numOfAttempts) {
		
		   case 1 :
			   showTries_Points(ADDPOINTS[0], someStrings[0], someStrings[1], someStrings[3]);
			   break;
		   
		   case 2 :
			   showTries_Points(ADDPOINTS[1], someStrings[4], someStrings[2], someStrings[3]);
			   break;
			   
		   case 3 :
			   showTries_Points(ADDPOINTS[2], someStrings[4], someStrings[2], someStrings[3]);
			   break;
			   
		   default :
			   showTries_Points(ADDPOINTS[3], someStrings[4], someStrings[2], someStrings[4]);
			   break;
		}
	}
	
	public void showTries_Points(int pointsNum, String firstTryOnly, String tri, String forBounceTries){
		
		points += pointsNum;
		JOptionPane.showMessageDialog(null, firstTryOnly + "\nIt took you " + numOfAttempts + " " + tri + " to guess it!\n" + 
				   "You have recevied " + pointsNum + forBounceTries +" points\n\n" +
				   "You now have " + points + " points in total\n" + "Click 'OK' to proceed to level "+ ++level + "\n", "GuessingGame", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void finish(){
		
		if(points >= 50 && level == 10){
			JOptionPane.showMessageDialog(null, "Congrats, you scored " + points + " points in total and reached level 10!\n");		
			
		} 
	}
}
