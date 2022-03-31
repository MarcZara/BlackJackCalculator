import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Deck 
{
  int deck [] = new int[312];
  int counter = 0;
  public Deck() 
  {
    //Fill Deck
    //2-9
    for (int i = 0; i <24 ; i++)
    {
      deck [i] = 2;
    }
    for (int i = 24; i <48 ; i++)
    {
      deck [i] = 3;
    }
    for (int i = 48; i <72 ; i++)
    {
      deck [i] = 4;
    }
    for (int i = 72; i <96 ; i++)
    {
      deck [i] = 5;
    }
    for (int i = 96; i <120 ; i++)
    {
      deck [i] = 6;
    }
    for (int i = 120; i <144 ; i++)
    {
      deck [i] = 7;
    }
    for (int i = 144; i <168 ; i++)
    {
      deck [i] = 8;
    }
    for (int i = 168; i <192 ; i++)
    {
      deck [i] = 9;
    }
    //Four 10 values
    for (int i = 192; i< 288; i++)
    {
      deck[i]= 10;
    }
    //Ace
    for (int i = 288; i< 312; i++)
      deck[i] = 11;
  }
  public void showDeck ()
  {
    System.out.println(Arrays.toString(deck));
  }
  public void shuffle()
  {
    Random rnd = ThreadLocalRandom.current();
    for (int i = deck.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      int a = deck[index];
      deck[index] = deck[i];
      deck[i] = a;
    }
    counter = 0;
  }
  public int dealCard (Hand h)
  {
    if (h.getHandValue() > 10 && deck[counter] == 11)
    {
       counter ++;
       return 1;
    }
    else if (h.isHandSoft() == true && ((h.getHandValue() + deck[counter]) > 21))
    {
      for (int i = 0; i < 11; i++)
      {
        if (h.getCard(i) == 11)
          h.changeCard(i, 1);
      }
      counter ++;
      return deck[counter - 1];
    }
    else
    {
     counter ++;
     return deck[counter - 1];
    }
  }
  public int getCounter ()
  {
    return counter;
  }
}