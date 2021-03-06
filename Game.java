import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game
{
  public static int money = 0, countOfSets = 5, sLoss = 0;
  public static double sWin = 0.0;
  public static String recoup = "inactive";
  public static Deck deck = new Deck();
  public static DealerAI dAI = new DealerAI();
  public static PlayerAI pAI = new PlayerAI();
  public static void main (String [] args)
  { 
    deck.shuffle();
    deck.shuffle();
    deck.shuffle();
    while (countOfSets > 0)
    {
      Record pRecord = new Record();
      int countOfHands  = 1000000;
      while (countOfHands > 0)
      {
        BetAI bAI = new BetAI();
        deck.shuffle();
        deck.shuffle();
        deck.shuffle();
        Hand pHand = new Hand();
        Hand sPHand = new Hand();
        Hand dHand = new Hand();
        pHand.setFirstBet(bAI.makeFirstBet(pHand, pRecord));
     //   System.out.print(bAI.makeFirstBet(pHand, pRecord));
        dealHands(pHand, dHand);
        if (dAI.judgeAction(dHand) == "blackJack")
        {
          pHand.setHandOver(true);
          dHand.setHandOver(true);
        }
        else if (pAI.judgeAction(pHand, dHand) == "split")
        {
    //      System.out.println("SPLIT");
          pHand.setSplitStatus(true);
          sPHand.setSplitStatus(true);
          sPHand.setCard(pHand.getCard(0));
          pHand.removeCard(1);
          pHand.setCard(deck.dealCard(pHand));
          sPHand.setCard(deck.dealCard(sPHand));
          sPHand.setFirstBet(bAI.makeFirstBet(sPHand, pRecord));
     //     System.out.print(bAI.makeFirstBet(sPHand, pRecord));
          while (sPHand.getHandOver() != true)
          {
            sPHand.setEndBet(bAI.makeEndBet(sPHand, dHand));
    //        System.out.println(bAI.makeEndBet(sPHand, dHand));
            takePAction(sPHand, dHand);
          }
  //          sPHand.showHand();
        }
        while (pHand.getHandOver() != true)
        {
          pHand.setEndBet(bAI.makeEndBet(pHand, dHand));
  //        System.out.println(bAI.makeEndBet(pHand, dHand));
          takePAction(pHand, dHand);
        }
        while (dHand.getHandOver() != true)
        {
          takeDAction(pHand, dHand);
        }
//        pHand.showHand();
 //       dHand.showHand();
        if (pHand.getSplitStatus() == true)
        {
           sPHand.setWLP(judgeWLP(sPHand, dHand));
 //          System.out.println(judgeWLP(sPHand, dHand));
           pRecord.addHand(sPHand);
        }
        pHand.setWLP(judgeWLP(pHand, dHand));
    //    System.out.println(judgeWLP(pHand, dHand));
        pRecord.addHand(pHand);
        if (pRecord.getMoney() <= 0)
          countOfHands = 1;
         countOfHands--;
      }
      pRecord.showRecord();
      money = pRecord.getMoney() + money;
      countOfSets--;
    }
    System.out.println("Money: " + money);
  }
  public static void dealHands (Hand p, Hand d)
  {
    p.setCard(deck.dealCard(p));
    d.setCard(deck.dealCard(d));
    p.setCard(deck.dealCard(p));
    d.setCard(deck.dealCard(d));
  }
  public static String judgeWLP (Hand p, Hand d)
  {
    
    String wLP = "";
    if (dAI.judgeAction(d) == "blackJack" && pAI.judgeAction(p, d) == "blackJack")
    {
      wLP = "push";
    }
    else if (pAI.judgeAction(p, d) == "blackJack")
    {
      wLP = "blackJack";
    }   
    else if (dAI.judgeAction(d) == "blackJack")
    {
      wLP = "loss";
    }
    else if (pAI.judgeAction(p, d) == "bust")
    {
      wLP = "loss";
    }
    else if (dAI.judgeAction(d) == "bust")
    {
      wLP = "win";
    }
    else if (p.getHandValue() == d.getHandValue())
    {
      wLP = "push";
    }
    else if (p.getHandValue() > d.getHandValue())
    {
      wLP = "win";
    }
    else if (p.getHandValue() < d.getHandValue())
    {
      wLP = "loss";
    }
    return wLP;
  }
  public static void takeDAction (Hand p, Hand d)
  {
    if (dAI.judgeAction(d) == "stand" || dAI.judgeAction(d) == "bust" || pAI.judgeAction(p, d) == "blackJack") 
    {
      d.setHandOver(true);
    }
    else if (dAI.judgeAction(d) == "hit")
    {
      d.setCard(deck.dealCard(d));
    }
  }
  public static void takePAction (Hand p, Hand d)
  {
    if (pAI.judgeAction(p, d) == "stand" || pAI.judgeAction(p, d) == "bust" || pAI.judgeAction(p, d) == "blackJack")
    {
      p.setHandOver(true);
    }
    else if (pAI.judgeAction(p, d) == "double")
    {
      p.setCard(deck.dealCard(p));
      p.setHandOver(true);
    }
    else if (pAI.judgeAction(p, d) == "hit")
    {
      p.setCard(deck.dealCard(p));
    }
  }
}