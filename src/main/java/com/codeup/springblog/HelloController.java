package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//entire class will be a controller
public class HelloController {

    @GetMapping("/hello/{name}")
    // {name} establishes the path variable in the mapping definition
    @ResponseBody
    //these two annotations @GetMapping and @ResponseBody are specifically for this method
    //use annotation @PathVariable to get the value of the path variable
    public String hello(@PathVariable String name) {
        return "Hello, " + name + " !";
    }

    //Request Mapping
    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String increment(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }
}
