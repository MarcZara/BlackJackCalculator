import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DealerAI
{
  String action = "";
  public DealerAI ()
  {
  }
  public String judgeAction (Hand h)
  {
    //BLACKJACK CONDITIONS
    if((h.getCard(0) == 10 && h.getCard (1) == 11) || (h.getCard(0) == 11 && h.getCard (1) == 10))
    {
      action = "blackJack";
    }
    else 
    {
      if (h.getHandValue()<17)
        action = "hit";
      else if (h.getHandValue() > 21)
        action = "bust";
      else
        action = "stand";
    } 
    return action;
  }
}