# BlackJackCalculator
 Calculate games and analyze results

The purpose of this program was to create a very light weight blacjack game which could read and store the results of all the past hands, analyze them, and make bets based on a statistical analysis or probability that develops over the course of the games.

The game is able to split and double down and is able to manage bets and record them in these instances.

The cards are basic integers but the game will still be able to manage the Ace as 1 or 11 properly. 

The hardcoded values of minimum bet 1 and maximum bet 500 follow the constraints of some of the common betting websites.

Right now the program can simulate 1 million hands in ~30 seconds, however it is recommended to use it in sets of 1 million as going over a million will slow the program down as the hands are stored and read from array lists.

I thusfar have found some interesting results on how to gain an edge for the player but ultimately I have not yet found a way to "beat" the game.

I hope that someone can continue my work and using probabilites someday beat blackjack.
