import java.util.*;

public class NumberGame{
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int chances = 10;
        int finals = 0;
        boolean playAgain = true;
        
        System.out.println("Welcome player!");
        System.out.println("Hey player, you have about " + chances + " chances to win the game.");

        while (playAgain) {
            int rand = getRandN(1, 100);
            boolean guess = false;

            for (int i = 0; i < chances; i++) {
                System.out.println("Chance " + (i + 1) + ": Enter your guess:");
                int user = sc.nextInt();

                if (user == rand) {
                    guess = true;
                    finals++;
                    System.out.println("You won!");
                    break;
                } else if (user > rand) {
                    System.out.println("Too high");
                } else {
                    System.out.println("Too low");
                }
            }

            if (!guess) {
                System.out.println("Sorry player, you lost all chances. The number was " + rand);
            }

            System.out.println("Do you want to play again? (y/n)");
            String playAgainResponse = sc.next();
            playAgain = playAgainResponse.equalsIgnoreCase("y");
        }

        System.out.println("That's it player, hope you enjoyed it.");
        System.out.println("Here is your score: " + finals);
    }

    public static int getRandN(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
