import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerAI
{
  String action = "";
  public PlayerAI()
  {
  }
  public String judgeAction (Hand p, Hand d)
  {
    
    
    //BLACKJACK CONDITIONS
    if(((p.getCard(0) == 10 && p.getCard (1) == 11) || (p.getCard(0) == 11 && p.getCard (1) == 10))
         && p.getSplitStatus() == false) 
    {
      action = "blackJack";
    }
    else
    {
      //SPLIT CONDITIONS
      if (p.getSplitStatus() == false)
      {
        //SPLIT 2s v 2 to 7
        if ((p.getCard(0) == 2 && p.getCard(1) == 2) && (d.getCard(1) > 1 && d.getCard(1) < 8))
          action = "split";
        //SPLIT 3s v 2 to 7
        else if ((p.getCard(0) == 3 && p.getCard(1) == 3) && (d.getCard(1) > 1 && d.getCard(1) < 8))
          action = "split";
        //SPLIT 4s v 5 or 6
        else if ((p.getCard(0) == 4 && p.getCard(1) == 4) && (d.getCard(1) > 4 && d.getCard(1) < 7))
          action = "split";
        //SPLIT 6s v 2 to 6
        else if ((p.getCard(0) == 6 && p.getCard(1) == 6) && (d.getCard(1) > 1 && d.getCard(1) < 7))
          action = "split";
        //SPLIT 7s v 2 to 7
        else if ((p.getCard(0) == 7 && p.getCard(1) == 7) && (d.getCard(1) > 1 && d.getCard(1) < 8))
          action = "split";
        //SPLIT 7s v 2 to 7
        else if ((p.getCard(0) == 7 && p.getCard(1) == 7) && (d.getCard(1) > 1 && d.getCard(1) < 8))
          action = "split";
        //SPLIT 8s v 2 to A
        else if (p.getCard(0) == 8 && p.getCard(1) == 8)
          action = "split";
        //SPLIT 9s v 2 to 6 and 8 and 9
        else if ((p.getCard(0) == 9 && p.getCard(1) == 9) && ((d.getCard(1) > 1 && d.getCard(1) < 7) ||
                 d.getCard(1) == 8 || d.getCard(1) == 9))
          action = "split";
        //SPLIT As v 2 to A
        else if (p.getCard(0) == 11 && p.getCard(1) == 1) 
          action = "split";
        if (action == "split")
          return action;
      }
      //DOUBLE CONDITIONS
      if (p.getCard (2) == 0)
      {
        //DOUBLE 9 V 3 TO 6
        if (p.getHandValue() == 9 && (d.getCard(1) > 2 && d.getCard(1) < 7))
          action = "double";
        //DOUBLE 10 V 2 TO 9
        else if (p.getHandValue() == 10 && (d.getCard(1) > 1 && d.getCard(1) < 10))
          action = "double";
        //DOUBLE 11 V 2 TO 10
        else if (p.getHandValue() == 11 && (d.getCard(1) > 1 && d.getCard(1) < 11))
          action = "double";
        //DOUBLE SOFT 13 V 5 OR 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 13 && (d.getCard(1) > 4 && d.getCard(1) < 7)))
          action = "double";
        //DOUBLE SOFT 14 V 5 OR 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 14 && (d.getCard(1) > 4 && d.getCard(1) < 7)))
          action = "double";
        //DOUBLE SOFT 15 V 4 TO 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 15 && (d.getCard(1) > 3 && d.getCard(1) < 7)))
          action = "double";
        //DOUBLE SOFT 16 V 4 TO 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 16 && (d.getCard(1) > 3 && d.getCard(1) < 7)))
          action = "double";
        //DOUBLE SOFT 17 V 3 TO 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 17 && (d.getCard(1) > 2 && d.getCard(1) < 7)))
          action = "double";
        //DOUBLE SOFT 18 V 3 TO 6
        else if (p.isHandSoft() == true && (p.getHandValue() == 18 && (d.getCard(1) > 2 && d.getCard(1) < 7)))
          action = "double";
        if (action == "double")
          return action;
      }
      //HIT CONDITIONS
      //Hit 12 v 2 or 3
      if (p.getHandValue() == 12 && (d.getCard(1) == 2 || d.getCard(1) == 3))
        action = "hit";
      //Stand on hard 3+ card 16 v 10
      else if(p.getHandValue() == 16 && d.getCard(1) == 10 && p.getCard (2) > 0 && p.isHandSoft() == false)
        action = "stand";
      //Hit soft 17 v bust card
      else if (p.isHandSoft() == true && p.getHandValue() < 18 && d.getCard(1) < 7)
        action = "hit";
      //Hit soft 18 v 9 or higher
      else if (p.isHandSoft() == true && p.getHandValue() < 19 && d.getCard(1) > 8)
        action = "hit";
      //Hit less than 12
      else if (p.getHandValue() < 12)
        action = "hit";
      //Hit less than 17 v 7 or higher
      else if (p.getHandValue()<17 && (d.getCard(1) >=7 || d.getCard(1) == 1))
        action = "hit";
      else if (p.getHandValue() > 21)
        action = "bust";
      else
        action = "stand";
    }
    return action;
  }
}