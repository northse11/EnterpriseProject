package com.ufcfightertracker.enterprise;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.User;
import com.ufcfightertracker.enterprise.dto.WeightClass;
import com.ufcfightertracker.enterprise.service.IFighterService;
import com.ufcfightertracker.enterprise.service.IUserService;
import com.ufcfightertracker.enterprise.service.IWeightClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
public class UfcTrackerController {

    @Autowired
    IFighterService fighterService;
    @Autowired
    IWeightClassService weightClassService;
    @Autowired
    IUserService userService;

    User currentUser = null;

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "start";
    }

    @RequestMapping("/home")
    public String index(Model model) {
        List<Fighter> fighters = fighterService.fetchAll();
        fighters.removeIf(fighter -> fighter.getRank() != 1);
        fighters.sort(Comparator.comparing(Fighter::getWeightClassId));
        model.addAttribute("fighters", fighters);
        return "home";
    }

    @RequestMapping("/addFighter")
    public String addFighter(Model model) {
        Fighter fighter = new Fighter();
        model.addAttribute(fighter);
        return "addFighter";
    }

    @GetMapping("/fighter")
    @ResponseBody
    public List<Fighter> fetchAllFighters() {
        return fighterService.fetchAll();
    }

    @RequestMapping("/saveFighter")
    public String saveFighter(Fighter fighter) {
        try {
            fighterService.save(fighter);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "start";
        }
        return "start";
    }

    @GetMapping("/fighter/{id}")
    public ResponseEntity fetchFighterById(@PathVariable("id") int id)
    {
        Fighter foundFIghter = fighterService.fetchById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundFIghter, headers, HttpStatus.OK);
    }

    @PostMapping(value="/saveFighter")
    public String createFighter(@ModelAttribute Fighter fighter, Model model)
    {
        try{
            double weight = fighter.getWeight();
            if(116.0 <= weight  && weight <= 125.0){
                fighter.setWeightClassId(1);
            }
            if(126.0 <= weight  && weight <= 135.0){
                fighter.setWeightClassId(2);
            }
            if(136.0 <= weight  && weight <= 145.0){
                fighter.setWeightClassId(3);
            }
            if(146.0 <= weight  && weight <= 155.0){
                fighter.setWeightClassId(4);
            }
            if(156.0 <= weight  && weight < 170.0){
                fighter.setWeightClassId(5);
            }
            if(171.0 <= weight  && weight <= 185.0){
                fighter.setWeightClassId(6);
            }
            if(186.0 <= weight  && weight <= 205.0){
                fighter.setWeightClassId(7);
            }
            if(206.0 <= weight  && weight <= 265.0){
                fighter.setWeightClassId(8);
            }
            fighterService.save(fighter);
            model.addAttribute("fighter", new Fighter());
            model.addAttribute("successMessage", "Fighter added successfully!");
        }
        catch(Exception e){
            e.printStackTrace();
            return "start";
        }
        return "addFighter";
    }

    @RequestMapping("/signup")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping(value="/signup")
    public String createUser(@ModelAttribute User user, Model model)
    {
        List<User> users = userService.findAll();
        for(User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                model.addAttribute("statusMessage", "User already exists");
                return "signup";
            }
        }
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("statusMessage", "Passwords do not match");
            return "signup";
        }
        else{
        userService.save(user);
        currentUser = user;
        List<Fighter> fighters = fighterService.fetchAll();
        fighters.removeIf(fighter -> fighter.getRank() != 1);
        fighters.sort(Comparator.comparing(Fighter::getWeightClassId));
        model.addAttribute("fighters", fighters);
        return "home";
}
    }

    @PostMapping(value="/login")
    public String login(@ModelAttribute User user, Model model) {
        List<User> users = userService.findAll();
        for(User u : users) {
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                currentUser = u;
                List<Fighter> fighters = fighterService.fetchAll();
                fighters.removeIf(fighter -> fighter.getRank() != 1);
                fighters.sort(Comparator.comparing(Fighter::getWeightClassId));
                model.addAttribute("fighters", fighters);
                return "home";
            }
        }
        model.addAttribute("statusMessage", "Incorrect username or password");
        return "login";
    }

    @DeleteMapping("/fighter/{id}")
    public ResponseEntity deleteFighter(@PathVariable("id") int id)
    {
        try {
            fighterService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/fighters", consumes = "application/json", produces = "application/json")
    public ResponseEntity searchFighters(@RequestParam(value="searchTerm", required=false, defaultValue="None") String searchTerm){
        try{
            List<Fighter> fighters = fighterService.fetchAll();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(fighters, headers, HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fighters")
    public String searchFighters(@RequestParam(value="searchTerm", required=false, defaultValue="None") String searchTerm, Model model){
        try{
            List<Fighter> fighters = fighterService.fetchByName(searchTerm);
            if(searchTerm.equals("None")){
                fighters = fighterService.fetchAll();
                model.addAttribute("fighters", fighters);
                return "fighters";
            }
            else if(fighterService.fetchByName(searchTerm) == null){
                return "error";
            }
            else{
                model.addAttribute("fighters", fighters);
                return "fighters";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/fightersByWeight/{weightClassId}")
    public String getFightersByWeightClass(@PathVariable int weightClassId, Model model) {
        List<Fighter> fighters = fighterService.fetchAll();
        fighters.removeIf(fighter -> fighter.getWeightClassId() != weightClassId);
        model.addAttribute("fighters", fighters);
        return "fightersByWeight";
    }

    @GetMapping("/favoriteFighters")
    public String getFavoriteFighters(Model model) {
        String favorites = currentUser.getFavoriteFighters();
        List<Fighter> fighters = new ArrayList<Fighter>();
        if(favorites == null || favorites.isEmpty()){
            model.addAttribute("statusMessage", "You have no favorite fighters yet!");
        }
        else {
            List<Integer> fighterIds = Arrays.stream(favorites.split(",")).map(String::trim).map(Integer::parseInt).toList();
            for (Integer fighterId : fighterIds) {
                fighters.add(fighterService.fetchById(fighterId));
            }
        }
        model.addAttribute("fighters", fighters);
        return "favoriteFighters";
    }

    @RequestMapping("/weightClasses")
    public String weightClasses(Model model) {
        List<WeightClass> weightClasses = weightClassService.fetchAll();
        model.addAttribute("weightClasses", weightClasses);
        return "weightClasses";
    }

    @GetMapping("/fighters/{id}")
    public String getFighterDetails(@PathVariable int id, Model model) {
        Fighter fighter = fighterService.fetchById(id);
        model.addAttribute("fighter", fighter);
        return "fighterDetails";
    }

    @PostMapping("/fighters/{id}/toggle-favorite")
    public String toggleFavorite(@PathVariable int id, Model model) {
        Fighter fighter = fighterService.fetchById(id);
        String favorites = currentUser.getFavoriteFighters();
        List<String> fighterIds = new ArrayList<>(Arrays.asList(favorites.split(",")));
        if(favorites != null && !favorites.isEmpty()) {
            if (!favorites.contains(Integer.toString(id))) {
                fighterIds.add(Integer.toString(id));
            }
            else{
                fighterIds.remove(Integer.toString(id));
            }
        }
        else{
            favorites = "" + id;
            currentUser.setFavoriteFighters(favorites);
            userService.save(currentUser);
            model.addAttribute("fighter", fighter);
            return "fighterDetails";
        }
        favorites = String.join(",", fighterIds);
        currentUser.setFavoriteFighters(favorites);
        userService.save(currentUser);
        model.addAttribute("fighter", fighter);
        return "fighterDetails";
    }

    @RequestMapping("/signout")
    public String SignOut() {
        currentUser = null;
        return "start";
    }
}
