package com.semakin.lections.lection5_1SpringMVC;

import com.semakin.lections.lection5_1SpringMVC.models.Car;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Семакин Виктор
 */
@Controller
public class HomeController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping(value= "/home", method = RequestMethod.GET)
    public String showBasePage(){
        return "home";
    }

    @RequestMapping(value="/text", method = RequestMethod.GET)
    public String showtextPage(Model model){
        model.addAttribute("car", new Car(100000, "Kia cerrato",
                "E777RF"));
        model.addAttribute("myText", "My super text");
        return "text";
    }

    @RequestMapping(value="/text", method = RequestMethod.POST)
    public String showText(Model model,
                                  @RequestParam(name="param1", defaultValue = "1") String param,
                                  @ModelAttribute(name="car") Car car ){

        model.addAttribute("myText", param + car.getModel());

        return "text";
    }

    @RequestMapping(value="/model", method = RequestMethod.POST)
    public ModelAndView showModelPage(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("text");
        modelAndView.addObject("car", new Car(100000, "carr",
                "E777RF"));
        return modelAndView;
    }

    @RequestMapping(value = "/text/showfullcar", method = RequestMethod.POST)
    public ModelAndView showFullCar(@ModelAttribute("car") Car car) {

        ModelAndView modelAndView = new ModelAndView("fullCar");

        modelAndView.addObject("car", car);

        return modelAndView;
    }
}
