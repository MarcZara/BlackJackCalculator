import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BetAI
{
  public static boolean checked = false;
  public static int maxBetRecoup = 0, maxLoss = 0, win = 0, loss = 0, wBet = 0, lBet = 0;
  PlayerAI pAI = new PlayerAI();
  public BetAI()
  {
  }
  public int makeEndBet (Hand p, Hand d)
  {
    int bet = 0;
    if (pAI.judgeAction(p, d) == "double")
    {
      bet = p.getFirstBet() * 2;
    }
    return bet;
  }
  public int makeFirstBet (Hand p, Record r)
  {
    int bet = 0, twoBet = 0, winCheck = 0;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    if (temp.isEmpty() != true)
    {
      String lastHand = r.getWLP(temp.size()-1);
      if (r.getLossSet() >= 8)
      {
        bet = r.getFirstBet(temp.size() - 1) * 2;
        if (bet == 1000)
          bet = p.getFirstBet();
        if (bet > 500)
          bet = 500;
        return bet;
      }
      
    }
    bet = p.getFirstBet ();
    return bet;
    /*
    //INTERNET STRATEGY
    int bet = 0, twoBet = 0, winCheck = 0;
    boolean winBet = false;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    if (temp.isEmpty() != true)
    {
      String lastHand = r.getWLP(temp.size()-1);
      if (r.getFirstBet(temp.size() - 1) >= 5 && r.getWinSet() < 4 && (lastHand == "win" || lastHand == "blackJack"))
      {
        bet = r.getFirstBet(temp.size() - 1);
        return bet;
      }
      if (r.getFirstBet(temp.size() - 1) == 5 && r.getWinSet() < 4 && lastHand == "loss")
      {
        bet = p.getFirstBet ();
        return bet;
      }
       if (r.getFirstBet(temp.size() - 1) > 2 && r.getWinSet() >= 4 && lastHand == "loss")
      {
        bet = r.getFirstBet(temp.size() - 1) * 2;
        if (bet == 1000)
          bet = p.getFirstBet();
        if (bet > 500)
          bet = 500;
        return bet;
      }
      
      if (r.getLossSet() >= 5)
        bet = r.getFirstBet(temp.size() - 1) * 2;
      else if (r.getWinSet() >= 4 && (lastHand == "win" || lastHand == "blackJack"))
      {
        bet = 5;
      }
      else
        bet = p.getFirstBet();
      if (bet == 500)
        bet = p.getFirstBet ();
      if (bet > 500)
        bet = 500;
      return bet;
    }
    bet = p.getFirstBet ();
    return bet;
    */
    /*
    int bet = 0, twoBet = 0, halfBet = 0, win = 0, loss = 0, bBet = 0, lBBet = 0;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    bet = p.getFirstBet();
    if (temp.isEmpty() != true)
    {
      String lastHand = r.getWLP(temp.size()-1);
      if (temp.size() > 11)
      {
        for (int i = 1; i < 11; i++)
        {
          String lHand = r.getWLP(temp.size() - i);
          if (lHand == "loss")
            twoBet++;
        }
        if (twoBet == 10)
        {
          bet = r.getFirstBet(temp.size() - 1) * 2;
          if (bet > 500)
            bet = 500;
          return bet;
        }
      }
      if (temp.size() > 65)
      {
        for (int i = 1; i < 65; i++)
        {
          String lHand = r.getWLP(temp.size()-i);
          if(lHand == "blackJack")
            bBet++;
        }
        if (bBet == 0)
        {
          bet++;
        }
      }
      if (temp.size() > 129)
      {
        for (int i = 1; i < 129; i++)
        {
          String lHand = r.getWLP(temp.size()-i);
          if(lHand == "blackJack")
            lBBet++;
        }
        if (lBBet < 4)
        {
          bet++;
        }
      }*/
        /*
      if (lastHand == "loss")
      {
        bet = r.getFirstBet(temp.size() - 1) * 2;
        if (r.getFirstBet(temp.size() - 1) == 256)
          bet = p.getFirstBet();
        return bet;
      }
      else if (lastHand == "win" || lastHand == "blackJack")
      {
        bet = p.getFirstBet();
        return bet;
      }
      else if (lastHand == "push")
      {
        bet = r.getFirstBet(temp.size() - 1);
        return bet; 
      }
    }
    return bet;*/
    /* BY CHECKING 100 HANDS
    double lossWin = 0.00;
    int bet = 0, twoBet = 0, halfBet = 0;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    if (temp.isEmpty() != true)
    {
      String lastHand = r.getWLP(temp.size()-1);
      if (temp.size() > 100 && checked == false)
      {
        for (int i = 1; i < 101; i++)
        {
          lastHand = r.getWLP(temp.size()-i);
          if(lastHand == "win" || lastHand == "blackJack")
            win++;
          if(lastHand == "loss")
            loss++;
        }
        if (temp.size() == 101 || temp.size() == 102)
          System.out.println("Change\nwin: " + win + "loss: " + loss);
        lossWin = loss / win;
        checked = true;
      }
      if (lossWin > 1.00)
      {
        bet = p.getFirstBet();
        return bet;
      }
      else if (lossWin > 1.2)
      {
        lastHand = r.getWLP(temp.size() - 1);
        if (lastHand == "win" || lastHand == "blackJack")
        {
          bet = 4;
          return bet;
        }
        else if (lastHand == "loss")
        {
          bet = r.getFirstBet(temp.size() - 1) * 2;
          if (bet > 500)
            bet = 500;
          return bet; 
        }
      }
      else if (lastHand == "loss")
      {
        bet = r.getFirstBet(temp.size() - 1) * 2;
        if (bet > 500)
          bet = 500;
        return bet; 
      }
      else if (lastHand == "win" || lastHand == "blackJack")
      {
        bet = p.getFirstBet();
        return bet;
      }
      else
      {
        bet = r.getFirstBet(temp.size() - 1);
        return bet; 
      }
    }
    bet = p.getFirstBet();
    return bet;
    */
    /*
    int minBet = 1, twoBet = 0,bet = 0;
    double blackJack;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    if (temp.isEmpty() != true)
    {
      if (r.getFirstBet(temp.size()- 1) > 2)
      {
        if (r.getBlackJack() == 0)
        {
          if (r.getWLP(temp.size() - 1) == "loss")
        {
          bet = r.getFirstBet(temp.size() - 1) * 2;
          if (bet > 479)
            bet = 480;
          return bet;
        }
          if (r.getWLP(temp.size() - 1) == "push")
          {
            bet = r.getFirstBet(temp.size() - 1);
            return bet;
          }
        }
      }
      if (temp.size() > 32)
      {
       blackJack = r.getBlackJack();
       if (blackJack == 0)
         return 15;
      }
    }
    bet = p.getFirstBet();
    return bet;*/
    /*//INCREASE BET AFTER 3 LOSSES, THEN DOUBLE AFTER LOSS, MAX BET RECOUP BASED ON LOSS PERCENT
    int minBet = 1, twoBet = 0,bet = 0;
    double win, loss;
    ArrayList<Hand> temp = new ArrayList<Hand>();
    temp = r.getRecord();
    if (temp.isEmpty() != true)
    {
      if (temp.size() > 8)
      {
        for (int i = 1; i < 8; i++)
        {
          String lastHand = r.getWLP(temp.size()-i);
          if(lastHand == "loss")
            twoBet++;
        }
        if (r.getWLP(temp.size()-1) == "loss")
        {
          if (r.getFirstBet(temp.size() -1) == 480)
          {
         //   maxLoss = maxLoss + 945;
        //    maxBetRecoup = maxBetRecoup + 1;
            bet = 30;
            return bet;
          }
          /*else if (r.getFirstBet(temp.size() -1) == 448)
          {
            maxLoss = maxLoss + 896;
            maxBetRecoup = maxBetRecoup + 1;
            bet = 15;
            return bet;
          }
        }
        /*
        if (r.getWLP(temp.size()-1) == "win")
        {
          if (r.getFirstBet(temp.size() -1) > 1)
          {
            if (maxBetRecoup != 0)
            {
            maxBetRecoup = maxBetRecoup - 1;
            maxLoss = maxLoss - r.getFirstBet(temp.size() - 1);
            }
          }
        }
        /*if(r.getWLP(temp.size()-1) == "win")
        {
          if (maxBetRecoup != 0)
          {
            bet = 480;
            return bet;
          }
        }
        if (r.getWLP(temp.size()-1) == "push")
        {
          if (r.getFirstBet(temp.size() -1) > 1)
          {
            bet = r.getFirstBet(temp.size() - 1);
            return bet;
          }
        }
        if (r.getWLP(temp.size()-1) == "loss")
        {
          if (r.getFirstBet(temp.size() -1) > 1)
          {
            bet = r.getFirstBet(temp.size() - 1) * 2;
            return bet;
          }
        }
        if (twoBet == 7)
        {
        /*  if (maxLoss > 0)
          {
            maxBetRecoup = maxBetRecoup + 1;
            bet = 15;
            return bet;
          } 
          bet = 30;
          return bet;
        }
      }
    }
    bet = p.getFirstBet();
    return bet;
    */
    /*INCREASING BET BASED ON LOSS PERCENTAGE
     int minBet = 1, bet = 0;
     ArrayList<Hand> temp = new ArrayList<Hand>();
     temp = r.getRecord();
     double win, loss;
     if (temp.isEmpty() != true) 
     {
     if (temp.size() > 100)
     {
     win = r.getWin();
     loss = r.getLoss();
     double lossPercent;
     lossPercent = loss / win;
     if (lossPercent > 1.6)
     {
     bet = 100;
     return bet;
     }
     else if (r.getFirstBet(temp.size() - 1) == 100)
     {
     if (lossPercent > 1.5)
     {
     bet = 100;
     return bet;
     }
     else
     return minBet;
     }
     else
     return minBet;
     }
     else
     return minBet;
     }
     bet = p.getFirstBet();
     return bet;*/
    
    /* DOUBLING AFTER WIN STRATEGY, SET TO 3 TIMES
     int minBet = 1, bet = 0;
     ArrayList<Hand> temp = new ArrayList<Hand>();
     temp = r.getRecord();
     if (temp.isEmpty() != true) 
     {
     String lastHand = r.getWLP(temp.size()-1);
     if (lastHand == "win" && r.getFirstBet(temp.size() - 1) != 4)
     {
     bet = r.getFirstBet(temp.size() - 1) * 2;
     return bet;
     }
     else
     return minBet;
     }
     bet = p.getFirstBet();
     return bet;*/
    
    /* FIRST BETTING STRATEGY, DOUBLE AFTER LOSS, FINITE HANDS WITH INDIVIDUAL MONEY LIMIT
     int bet = 0;
     ArrayList<Hand> temp = new ArrayList<Hand>();
     temp = r.getRecord();
     if (temp.isEmpty() != true)
     {
     String lastHand = r.getWLP(temp.size()-1);
     if (lastHand == "loss")
     {
     bet = r.getFirstBet(temp.size() - 1) * 2;
     if (bet > 128)
     bet = 128;
     if (bet > r.getMoney())
     return r.getMoney();
     return bet; 
     }
     else
     {
     bet = r.getFirstBet(temp.size() - 1);
     if (r.getMoney() <= 128)
     return r.getMoney();
     if (bet > 128)
     bet = 128;
     return bet; 
     }
     }
     bet = p.getFirstBet();
     return bet;*/
  }
}