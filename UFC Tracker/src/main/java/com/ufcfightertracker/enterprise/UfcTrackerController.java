package com.ufcfightertracker.enterprise;

import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.service.IFighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UfcTrackerController {

    @Autowired
    IFighterService fighterService;

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

    @GetMapping("/fighters")
    public ResponseEntity searchFighters(@RequestParam Map<String,String> requestParams){
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping("/sustainability")
    public String sustainability() {
        return "sustainability";
    }
}
