
/**
 * 
 * @author: sami
 * created: 19/3/18
 * 
 */

public class GuessingGame {
	
	public static void main(String[] args){
		
		gameControl controller = new gameControl();
		controller.start();
		
		while(true){
			controller.guessNumber();
		}
	}
}
