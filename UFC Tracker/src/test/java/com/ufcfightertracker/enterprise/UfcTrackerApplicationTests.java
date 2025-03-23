package com.ufcfightertracker.enterprise;

import com.ufcfightertracker.enterprise.dao.iFighterDAO;
import com.ufcfightertracker.enterprise.dto.Fighter;
import com.ufcfightertracker.enterprise.service.FighterServiceStub;
import com.ufcfightertracker.enterprise.service.IFighterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class UfcTrackerApplicationTests {

    private IFighterService fighterService;
    private Fighter fighter = new Fighter();
    private Fighter createdFighter();

    @MockBean
    private iFighterDAO fighterDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void fetchFighterById_returnsMichaelPageForId1() throws Exception {
        givenFighterDataIsAvailable();
        whenFighter1AddedIsMichaelPage();
        whenSearchFighterWithId1();
        thenReturnMichaelPageFighterForId1();
    }

    private void whenFighter1AddedIsMichaelPage() {
        Fighter mvp = new Fighter();
        mvp.setId(1);
        mvp.setName("Michael Page");
        mvp.setAge(37);
        mvp.setWeight(185.00);
        mvp.setStyle("Switch");
        mvp.setWins(23);
        mvp.setLosses(3);
        mvp.setTies(0);
        mvp.setRank(15);
        Mockito.when(fighterDAO.fetch(1)).thenReturn(mvp);
    }

    private void givenFighterDataIsAvailable() throws Exception {
        Mockito.when(fighterDAO.save(fighter)).thenReturn(fighter);
        fighterService = new FighterServiceStub(fighterDAO);
    }

    private void whenSearchFighterWithId1() throws Exception {
        fighter = fighterService.fetchById(1);
    }

    private void thenReturnMichaelPageFighterForId1() throws Exception {
        String name = fighter.getName();
        assertEquals("Michael Page", name);
    }

    @Test
    void fetchFighterByName_returnsMaxHollowayForMaxHolloway() throws Exception {
        givenFighterDataIsAvailable();
        whenUserSearchesForFighterByNameMaxHolloway();
        thenReturnFighterMaxHollowayForNameMaxHolloway();
    }

    private void whenUserSearchesForFighterByNameMaxHolloway() {
        fighter = fighterService.fetchByName("Max Holloway");
    }

    private void thenReturnFighterMaxHollowayForNameMaxHolloway() {
        String name = fighter.getName();
        assertEquals("Max Holloway", name);
    }
    
    @Test
    void saveFighter_validateReturnFighterWithAllAttributes() throws Exception {
        givenFighterDataIsAvailable();
        whenUserCreatesANewFighterAndSaves();
        thenSavedFigherIsReturnedIdentical();
    }

    private void whenUserCreatesANewFighterAndSaves() {
        fighter = new fighter();
        fighter.setName("Michel Pereira");
        fighter.setAge(31);
        fighter.setWeight(185.00);
        fighter.setStyle("Orthodox");
        fighter.setWins(31);
        fighter.setLosses(12);
        fighter.setTies(0);
        fighter.setRank(13);
        createdFighter = fighterService.save(fighter);
    }

    private void thenSavedFigherIsReturnedIdentical() throws Exception {
        assertEquals(fighter, createdFighter);
        verify(fighterDAO, atLeastOnce()).save(fighter);
    }

}
