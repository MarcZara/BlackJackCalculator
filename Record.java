import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Record 
{
  int startMoney = 30000, money = 30000;
  double winSet = 0.0, lossSet = 0.0, lossStreakSet = 0.0;
  ArrayList<Hand> blackJack = new ArrayList<Hand>();
  ArrayList<Hand> win = new ArrayList<Hand>();
  ArrayList<Hand> loss = new ArrayList<Hand>();
  ArrayList<Hand> push = new ArrayList<Hand>();
  ArrayList<Hand> record = new ArrayList <Hand>();
  public Record()
  {
  }
  public void addHand (Hand hand)
  {
    record.add(hand);
    if (hand.getWLP() == "push")
      record.remove(hand);
    if (record.size() > 3 && hand.getSplitStatus() == true)
    {
      if (hand.getSplitStatus() == true && record.get(record.size() - 2).getSplitStatus() == true)
      {
        int handTotal = 0, lastTotal = 0, bothTotal = 0;
        handTotal = hand.getEndBet() + hand.getFirstBet();
        lastTotal = record.get(record.size() - 2).getEndBet() + record.get(record.size() - 2).getFirstBet();
        if (hand.getWLP() == "win")
          bothTotal += handTotal;
        if (hand.getWLP() == "loss")
          bothTotal -= handTotal;
        if (record.get(record.size() - 2).getWLP() == "win")
          bothTotal += lastTotal;
        if (record.get(record.size() - 2).getWLP() == "loss")
          bothTotal -= lastTotal;
        if (bothTotal > hand.getFirstBet())
          lossSet = 0.0;
        else if (bothTotal < 0)
          lossSet++;
      }
    }
    if (record.size() > 3)
    {
      if (hand.getWLP() == "win" || hand.getWLP() == "blackJack")
      {
        lossSet = 0.0;
        winSet += 0.5;
      }
      if (hand.getWLP() == "loss")
      {
        if (record.get(record.size() - 2).getWLP() == "win" || record.get(record.size() - 2).getWLP() == "blackJack")
        {
          winSet += 0.5;
        }
        lossSet++;
      }
      if ((hand.getWLP() == "win" || hand.getWLP() == "blackJack") && (record.get(record.size() - 2).getWLP() == "win" ||
           record.get(record.size() - 2).getWLP() == "blackJack"))
        winSet = 0.0;
    }
    if (hand.getEndBet() > 0)
    {
      if (hand.getWLP()== "win")
      {
        win.add(hand);
        money = money + hand.getEndBet();
      }
      else if (hand.getWLP() == "blackJack")
      {
        blackJack.add(hand);
        money = money + (hand.getEndBet() + (hand.getEndBet() / 2));
      }
      else if (hand.getWLP() == "loss")
      {
        loss.add(hand);
        money = money - hand.getEndBet();
      }
      else if (hand.getWLP() == "push")
        push.add(hand);
    }
    else
    {
      if (hand.getWLP()== "win")
      {
        win.add(hand);
        money = money + hand.getFirstBet();
      }
      else if (hand.getWLP() == "blackJack")
      {
        blackJack.add(hand);
        money = money + (hand.getFirstBet() + (hand.getFirstBet() / 2));
      }
      else if (hand.getWLP() == "loss")
      {
        loss.add(hand);
        money = money - hand.getFirstBet();
      }
      else if (hand.getWLP() == "push")
        push.add(hand);
    }
  }
  //This will get the bet made on the hand specified 
  public int getEndBet (int i)
  {
    return record.get(i).getEndBet();
  }
  public int getFirstBet (int i)
  {
    return record.get(i).getFirstBet();
  }
  //This will get the win loss or push status of the hand specified
  public String getWLP (int i)
  {
    return record.get(i).getWLP();
  }
  public void setStartMoney (int i)
  {
    startMoney = i;
  }
  public double getLossSet ()
  {
    return lossSet;
  }
  public double getWinSet ()
  {
    return winSet;
  }
  public int getMoney ()
  {
    return money;
  }
  public int getWin ()
  {
    return win.size();
  }
  public int getLoss()
  {
    return loss.size();
  }
  public int getBlackJack()
  {
    return blackJack.size();
  }
  public void showRecord()
  {
    System.out.println("Overall results : Loss: " + loss.size() + " Win: " + win.size() + " Push: " + push.size()
                         + " BlackJack: " + blackJack.size());
    System.out.println("Money at start: " + startMoney + " Money now: " + money );
  }
  public Hand getHand (int i)
  {
    return record.get(i);
  }
  public ArrayList<Hand> getRecord ()
  {
    return record;
  }
}