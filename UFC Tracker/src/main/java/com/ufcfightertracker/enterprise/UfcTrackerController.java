package com.ufcfightertracker.enterprise;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.dto.WeightClass;
import com.ufcfightertracker.enterprise.service.IFighterService;
import com.ufcfightertracker.enterprise.service.IWeightClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UfcTrackerController {

    @Autowired
    IFighterService fighterService;
    @Autowired
    IWeightClassService weightClassService;

    /**
     * Handle the root (/) endpoint and return a start page.
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        Fighter fighter = new Fighter();
        fighter.setId(2);
        fighter.setName("Max Holloway");
        fighter.setAge(33);
        fighter.setWeight(135.00);
        fighter.setStyle("Striker");
        fighter.setWins(26);
        fighter.setLosses(8);
        fighter.setTies(0);
        fighter.setRank(2);
        model.addAttribute(fighter);
        return "start";
    }

    @GetMapping("/fighter")
    @ResponseBody
    public List<Fighter> fetchAllFighters() {
        return fighterService.fetchAll();
    }

    @GetMapping("/weightClass")
    @ResponseBody
    public List<WeightClass> fetchAllWeightClasses() {
        return weightClassService.fetchAll();
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

    @RequestMapping("/saveWeightClass")
    public String saveWeightClass(WeightClass weightClass) {
        try{
            weightClassService.save(weightClass);
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

    @GetMapping("/weightClass/{id}")
    public ResponseEntity getWeightClassById(@PathVariable("id") int id){
        WeightClass foundWeightClass = weightClassService.getWeightClassById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(foundWeightClass, headers, HttpStatus.OK);
    }

    @PostMapping(value="/fighter", consumes="application/json", produces="application/json")
    @ResponseBody
    public Fighter createFighter(@RequestBody Fighter fighter)
    {
        Fighter newFighter = null;
        try{
            newFighter = fighterService.save(fighter);
        }
        catch(Exception e){
            //TODO add logging
        }
        return newFighter;
    }

    @PostMapping(value = "/weightClass", consumes = "application/json", produces="application/json")
    @ResponseBody
    public WeightClass createWeightClass(@RequestBody WeightClass weightClass)
    {
        WeightClass newWeightClass = null;
        try{
            newWeightClass = weightClassService.save(weightClass);
        }
        catch(Exception e){
            //TODO add logging
    }
        return newWeightClass;
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

    @DeleteMapping("/weightClass/{id}")
    public ResponseEntity deleteWeightClass(@PathVariable("id") int id){
        try {
            weightClassService.delete(id);
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
            if(searchTerm.equals("None")){
                List<WeightClass> weightClasses = weightClassService.fetchAll();
                model.addAttribute("weightClasses", weightClasses);
                return "weightClasses";
            }
            else if(fighterService.fetchByName(searchTerm) == null){
                return "error";
            }
            else {
                List<Fighter> fighters = new ArrayList<>();
                fighters.add(fighterService.fetchByName(searchTerm));
                model.addAttribute("fighters", fighters);
                return "fighters";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/weightClasses")
    public String weightClasses(Model model) {
        List<WeightClass> weightClasses = weightClassService.fetchAll();
        model.addAttribute("weightClasses", weightClasses);
        return "weightClasses";
    }
}
