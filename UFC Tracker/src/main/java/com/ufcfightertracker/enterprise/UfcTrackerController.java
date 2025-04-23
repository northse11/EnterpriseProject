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
     * Handles the default endpoint
     * @return directs users to the start page
     */
    @RequestMapping("/")
    public String index() {
        return "start";
    }

    /**
     * Handles the signup endpoint
     * @param model is the model that passes user information to the page
     * @return directs users to the signup page
     */
    @RequestMapping("/signup")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    /**
     * Handless the login endpoint
     * @param model is the model that passes user information to the page
     * @return directs users to the login page
     */
    @RequestMapping("/login")
    public String login(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    /**
     * Handles the action of signing up
     * @param user the user details passed in from the form
     * @param model the model that passes user information to the page
     * @return directs the user to the home page after successful signup
     */
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

    /**
     * Handles the action of logging in as a returning user
     * @param user the user details passed in from the form
     * @param model the model that passes user information to the page
     * @return directs the user to the home page after successful login
     */
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

    /**
     * Handles the signout endpoint
     * @return clears the current user and directs to the start page
     */
    @RequestMapping("/signout")
    public String SignOut() {
        currentUser = null;
        return "start";
    }

    /**
     * Handles the home endpoint
     * @param model is the model that passes fighter data to the page
     * @return directs the user to the home page
     */
    @RequestMapping("/home")
    public String index(Model model) {
        List<Fighter> fighters = fighterService.fetchAll();
        fighters.removeIf(fighter -> fighter.getRank() != 1);
        fighters.sort(Comparator.comparing(Fighter::getWeightClassId));
        model.addAttribute("fighters", fighters);
        return "home";
    }

    /**
     * Handles the add fighter endpoint
     * @param model is the model that passes fighter information to the page
     * @return directs the user to the add fighter page
     */
    @RequestMapping("/addFighter")
    public String addFighter(Model model) {
        Fighter fighter = new Fighter();
        model.addAttribute(fighter);
        return "addFighter";
    }

    /**
     * Handles the fighter endpoint
     * @return a list of every fighter
     */
    @GetMapping("/fighter")
    @ResponseBody
    public List<Fighter> fetchAllFighters() {
        return fighterService.fetchAll();
    }

    /**
     * Handles the save fighter endpoint
     * @param fighter is the fighter being created by the form
     * @return directs the user back to the add fighter page
     */
    @RequestMapping("/saveFighter")
    public String saveFighter(Fighter fighter) {
        try {
            fighterService.save(fighter);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "addFighter";
    }

    /**
     * Handles the get by id endpoint for fighters
     * @param id the fighter's id
     * @return response entity that shows the fighter and http messages
     */
    @GetMapping("/fighter/{id}")
    public ResponseEntity fetchFighterById(@PathVariable("id") int id)
    {
        Fighter foundFIghter = fighterService.fetchById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundFIghter, headers, HttpStatus.OK);
    }

    /**
     * Handles the action for creating a new fighter
     * @param fighter the fighter being created by the form
     * @param model the fighter details and details for status messages
     * @return clears the fields and notifies the user of success/failure
     */
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
            model.addAttribute("fighter", new Fighter());
            model.addAttribute("successMessage", "Fighter could not be added!");
            return "addFighter";
        }
        return "addFighter";
    }

    /**
     * Handles the delete mapping for fighters
     * @param id the fighter's id
     * @return http ok response
     */
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

    /**
     * Handles the search fighters endpoint for json
     * @param searchTerm the term or fighter's name the user searches for
     * @return http status ok or error depending on success of search
     */
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

    /**
     * Handles the endpoint for the search box in the nav bar
     * @param searchTerm what the user enters to be searched (a fighter's name)
     * @return sends the user to the page of the exact fighter if found, or sends them to a list of the other fighters
     */
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
            else if(fighters.stream().count() == 1){
                model.addAttribute("fighter", fighters.get(0));
                return "fighterDetails";
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

    /**
     * Handles the endpoint to get the fighters in a specific weight class
     * @param weightClassId the id of the weight class being searched
     * @param model the model that passes weight class and fighter data to the page
     * @return directs the user to the fighters by weight page, loaded with a list of fighters from the specified weight class
     */
    @GetMapping("/fightersByWeight/{weightClassId}")
    public String getFightersByWeightClass(@PathVariable int weightClassId, Model model) {
        List<Fighter> fighters = fighterService.fetchAll();
        fighters.removeIf(fighter -> fighter.getWeightClassId() != weightClassId);
        model.addAttribute("fighters", fighters);
        return "fightersByWeight";
    }

    /**
     * Handles the favorite fighters endpoint
     * @param model the model that passes user and fighter data to the page
     * @return directs the user to the favoriteFighters page and loads it with the list of their favorite fighters
     */
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

    /**
     * Handles the weight classes endpoint
     * @param model the model that passes weight class data to the page
     * @return directs the user to the weight classes page
     */
    @RequestMapping("/weightClasses")
    public String weightClasses(Model model) {
        List<WeightClass> weightClasses = weightClassService.fetchAll();
        model.addAttribute("weightClasses", weightClasses);
        return "weightClasses";
    }

    /**
     * Handles the get fighter by id endpoint
     * @param id the id of the fighter
     * @param model the model that passes fighter data to the page
     * @return directs the user to the details page for the specified fighter
     */
    @GetMapping("/fighters/{id}")
    public String getFighterDetails(@PathVariable int id, Model model) {
        Fighter fighter = fighterService.fetchById(id);
        model.addAttribute("fighter", fighter);
        return "fighterDetails";
    }

    /**
     * Handles the action of toggling the favorite status on a fighter
     * @param id the id of the fighter
     * @param model the model that passes user and fighter data to the page
     * @return adds the fighter to the current user's favorite fighter list and refreshes the page
     */
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

    /**
     * Handles the fighter names endpoint for the autofill function on the navbar search
     * @param query the text in the search box
     * @return a list of fighter names that contain the text from the box
     */
    @GetMapping("/fighterNames")
    @ResponseBody
    public List<String> getFighterNames(@RequestParam("query") String query) {
        List<Fighter> fighters = fighterService.fetchAll();
        List<String> fighterNames = fighters.stream()
                .filter(fighter -> fighter.getName().toLowerCase().contains(query.toLowerCase()))
                .map(Fighter::getName)
                .toList();

        return fighterNames;
    }
}