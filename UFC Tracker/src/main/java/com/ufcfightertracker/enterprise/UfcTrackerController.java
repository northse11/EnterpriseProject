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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@Controller
public class UfcTrackerController {

    private static final Logger logger = LoggerFactory.getLogger(UfcTrackerController.class);

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
        WeightClass weightClass = new WeightClass();
        weightClass.setWeightClassId(1);
        weightClass.setWeightClassName("Bantamweight");
        weightClass.setMinWeight(140);
        weightClass.setMaxWeight(145);
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
            logger.error("Error saving fighter: " + fighter, e);
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
            logger.error("Error saving weight class: " + weightClass, e);
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
        try {
            newFighter = fighterService.save(fighter);
            logger.info("Fighter created successfully: {}", newFighter);
        } catch (Exception e) {
            logger.error("Error creating fighter: " + fighter, e);
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
            logger.info("WeightClass created successfully: {}", newWeightClass);
        }
        catch(Exception e){
            logger.error("Error creating weightClass: " + weightClass, e);
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

    @GetMapping("/fighters")
    public ResponseEntity searchFighters(@RequestParam Map<String,String> requestParams){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/weightClasses")
    public ResponseEntity searchWeightClasses(@RequestParam Map<String,String> requestParams){
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/sustainability")
    public String sustainability() {
        return "sustainability";
    }
}
