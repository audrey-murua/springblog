package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {

    //main Dice website showing links, navbar, footer etc
    @GetMapping("/roll-dice")
    public String showDice() {
        return "Dice";
    }

    //website that uses a path variable for user guess {n}, model is used to input results into main website
    @GetMapping("/roll-dice/{n}")
//    @ResponseBody not needed, using model
    public String linkGuess(@PathVariable int n, Model model) {
      //setting the min and max values for random number generator
      int min = 1;
      int max = 6;

     //strings used for correct/incorrect message
      String correct = " You are correct!";
      String incorrect = " I'm sorry, that's incorrect.";

      //Generating random int value from 1 to 6
        int random = (int) Math.floor(Math.random()*(max-min+1)+min);

      //Shows if user guess is equal to random number, it's correct. Otherwise, it's incorrect.
        if(n == random) {
            model.addAttribute("result", "Dice rolled: " + random + " Your guess was: " + n + correct);
        } else {
            model.addAttribute("result", "Dice rolled: " + random + " Your guess was: " + n + incorrect);
        }
        return "dice";

    }

}
