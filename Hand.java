import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Hand
{
  boolean handOver = false, splitStatus = false;
  String winLossPush;
  int firstBet = 1, cardCount = 0, endBet = 0;
  int hand [] = new int[44];
  
  public Hand()
  {
  }
  public void changeCard (int i, int j)
  {
    hand[i] = j;
  }
  public void setCard (int i)
  {
    hand[cardCount] = i;
    cardCount++;
  }
  public void removeCard (int i)
  {
    hand[i] = 0;
    cardCount--;
  }
  public int getHandValue()
  {
    int value = 0;
    for (int i = 0; i <11; i++)
    {
        value = value + hand[i];
    }
    return value;
  }
  public int getCardCount ()
  {
    return cardCount;
  }
  public void showHand ()
  {
    System.out.println(Arrays.toString(hand));
  }
  public void setHandOver (boolean b)
  {
    handOver = b;
  }
  public boolean getHandOver ()
  {
    return handOver;
  }
  public void addHand (Hand h)
  {
    for (int i = 0; i < 11; i ++)
    {
      hand [i] =  h.getCard(i);
    }
    for (int i = 11; i < 22; i ++)
    {
      hand [i] =  h.getCard(i);
    }
    for (int i = 22; i < 33; i ++)
    {
      hand [i] =  h.getCard(i);
    }
    for (int i = 33; i < 44; i ++)
    {
      hand [i] =  h.getCard(i);
    }
  }
  public int[] getHandArray ()
  {
    return hand;
  }
  public int getCard (int i)
  {
    return hand[i];
  }
  public int getFirstBet ()
  {
    return firstBet;
  }
  public int getEndBet ()
  {
    return endBet;
  }
  public boolean isHandSoft ()
  {
    boolean soft = false;
    for (int i = 0; i < 11; i++)
    {
      if (hand[i] == 11)
        soft = true;
    }
    return soft;
  }
  public void setSplitStatus (boolean b)
  {
    splitStatus = b;
  }
  public boolean getSplitStatus ()
  {
    return splitStatus;
  }
  public void setFirstBet (int b)
  {
    firstBet = b;
  }
  public void setEndBet (int b)
  {
    endBet = b;
  }
  public String getWLP()
  {
    return winLossPush;
  }
  public void setWLP(String s)
  {
    winLossPush = s;
  }
  public String toString ()
  {
    String cards = "";
    for (int i = 0; i <hand.length; i++)
      cards = hand[i] + " ";
    return cards;
  }
}