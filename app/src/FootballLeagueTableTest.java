import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FootballLeagueTableTest {

    @Test
    public void testInstantiation_matchesInputIsNull_throwsIllegalArgumentException() {
        final IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new FootballLeagueTable(null));
        Assertions.assertEquals("Valid list of match(es) is required.", nullInputIllegalArgumentException.getLocalizedMessage());

    }

    @Test
    public void testInstantiation_validMatchesInput_instantiationIsSuccessful() {
        final List<Match> matches = new ArrayList<>();

        //When match list is empty
        final LeagueTable emptyLeagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(emptyLeagueTable);

        //When match list is not empty
        matches.add(new Match("Home Team", "Away Team", 2, 1));
        final LeagueTable filledLeagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(filledLeagueTable);
        Assertions.assertEquals(2, filledLeagueTable.getTableEntries().size());
    }

    @Test
    public void testGetTableEntries_aTeamWonTheMatch_teamTableEntryValuesAreCorrect() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 1));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        final List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(2, tableEntryList.size());

        final LeagueTableEntry chelseaTableEntry = tableEntryList.get(0);
        final LeagueTableEntry arsenalTableEntry = tableEntryList.get(1);

        //Confirm Chelsea's table entry is correct
        Assertions.assertEquals("Chelsea", chelseaTableEntry.getTeamName());
        Assertions.assertEquals(1, chelseaTableEntry.getPlayed());
        Assertions.assertEquals(1, chelseaTableEntry.getWon());
        Assertions.assertEquals(0, chelseaTableEntry.getDrawn());
        Assertions.assertEquals(0, chelseaTableEntry.getLost());
        Assertions.assertEquals(2, chelseaTableEntry.getGoalsFor());
        Assertions.assertEquals(1, chelseaTableEntry.getGoalsAgainst());
        Assertions.assertEquals(1, chelseaTableEntry.getGoalDifference());
        Assertions.assertEquals(3, chelseaTableEntry.getPoints());

        //Confirm Arsenal's table entry is correct
        Assertions.assertEquals("Arsenal", arsenalTableEntry.getTeamName());
        Assertions.assertEquals(1, arsenalTableEntry.getPlayed());
        Assertions.assertEquals(0, arsenalTableEntry.getWon());
        Assertions.assertEquals(0, arsenalTableEntry.getDrawn());
        Assertions.assertEquals(1, arsenalTableEntry.getLost());
        Assertions.assertEquals(1, arsenalTableEntry.getGoalsFor());
        Assertions.assertEquals(2, arsenalTableEntry.getGoalsAgainst());
        Assertions.assertEquals(-1, arsenalTableEntry.getGoalDifference());
        Assertions.assertEquals(0, arsenalTableEntry.getPoints());
    }

    @Test
    public void testGetTableEntries_matchEndedADraw_teamTableEntryValuesAreCorrect() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 2));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        final List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(2, tableEntryList.size());

        final LeagueTableEntry arsenalTableEntry = tableEntryList.get(0);
        final LeagueTableEntry chelseaTableEntry = tableEntryList.get(1);

        //Confirm Arsenal's table entry is correct
        Assertions.assertEquals("Arsenal", arsenalTableEntry.getTeamName());
        Assertions.assertEquals(1, arsenalTableEntry.getPlayed());
        Assertions.assertEquals(0, arsenalTableEntry.getWon());
        Assertions.assertEquals(1, arsenalTableEntry.getDrawn());
        Assertions.assertEquals(0, arsenalTableEntry.getLost());
        Assertions.assertEquals(2, arsenalTableEntry.getGoalsFor());
        Assertions.assertEquals(2, arsenalTableEntry.getGoalsAgainst());
        Assertions.assertEquals(0, arsenalTableEntry.getGoalDifference());
        Assertions.assertEquals(1, arsenalTableEntry.getPoints());

        //Confirm Chelsea's table entry is correct
        Assertions.assertEquals("Chelsea", chelseaTableEntry.getTeamName());
        Assertions.assertEquals(1, chelseaTableEntry.getPlayed());
        Assertions.assertEquals(0, chelseaTableEntry.getWon());
        Assertions.assertEquals(1, chelseaTableEntry.getDrawn());
        Assertions.assertEquals(0, chelseaTableEntry.getLost());
        Assertions.assertEquals(2, chelseaTableEntry.getGoalsFor());
        Assertions.assertEquals(2, chelseaTableEntry.getGoalsAgainst());
        Assertions.assertEquals(0, chelseaTableEntry.getGoalDifference());
        Assertions.assertEquals(1, chelseaTableEntry.getPoints());
    }

    @Test
    public void testGetTableEntries_teamNameCaseDifference_teamNameIsMappedCorrectly() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 2));
        matches.add(new Match("arsenal", "Chelsea", 1, 2));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        final List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(2, tableEntryList.size());

        final LeagueTableEntry chelseaTableEntry = tableEntryList.get(0);
        final LeagueTableEntry arsenalTableEntry = tableEntryList.get(1);

        //Confirm Chelsea's table entry is correct
        Assertions.assertEquals("Chelsea", chelseaTableEntry.getTeamName());
        Assertions.assertEquals(2, chelseaTableEntry.getPlayed());
        Assertions.assertEquals(1, chelseaTableEntry.getWon());
        Assertions.assertEquals(1, chelseaTableEntry.getDrawn());
        Assertions.assertEquals(0, chelseaTableEntry.getLost());
        Assertions.assertEquals(4, chelseaTableEntry.getGoalsFor());
        Assertions.assertEquals(3, chelseaTableEntry.getGoalsAgainst());
        Assertions.assertEquals(1, chelseaTableEntry.getGoalDifference());
        Assertions.assertEquals(4, chelseaTableEntry.getPoints());

        //Confirm Arsenal's table entry is correct
        Assertions.assertEquals("Arsenal", arsenalTableEntry.getTeamName());
        Assertions.assertEquals(2, arsenalTableEntry.getPlayed());
        Assertions.assertEquals(0, arsenalTableEntry.getWon());
        Assertions.assertEquals(1, arsenalTableEntry.getDrawn());
        Assertions.assertEquals(1, arsenalTableEntry.getLost());
        Assertions.assertEquals(3, arsenalTableEntry.getGoalsFor());
        Assertions.assertEquals(4, arsenalTableEntry.getGoalsAgainst());
        Assertions.assertEquals(-1, arsenalTableEntry.getGoalDifference());
        Assertions.assertEquals(1, arsenalTableEntry.getPoints());
    }

    @Test
    public void testGetTableEntries_teamNameCaseDifference_firstTeamOccurrenceNameIsUsedOnTable() {
        //Team name with Sentence Case comes first
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 2));
        matches.add(new Match("arsenal", "Chelsea", 1, 2));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        final LeagueTableEntry arsenalTableEntry = tableEntryList.get(1);

        Assertions.assertEquals("Arsenal", arsenalTableEntry.getTeamName());

        //Team name with lower Case comes first
        final List<Match> _matches = new ArrayList<>();
        _matches.add(new Match("arsenal", "Chelsea", 1, 2));
        _matches.add(new Match("Chelsea", "Arsenal", 2, 2));

        final LeagueTable _leagueTable = new FootballLeagueTable(_matches);
        Assertions.assertNotNull(_leagueTable);

        List<LeagueTableEntry> _tableEntryList = _leagueTable.getTableEntries();
        final LeagueTableEntry _arsenalTableEntry = _tableEntryList.get(1);

        Assertions.assertEquals("arsenal", _arsenalTableEntry.getTeamName());
    }

    @Test
    public void testGetTableEntries_multipleMatchesPlayed_teamTableEntryPointsAreCorrect() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 3));
        matches.add(new Match("Chelsea", "Sunderland", 2, 2));
        matches.add(new Match("Tottenham", "Sunderland", 0, 0));
        matches.add(new Match("Arsenal", "Sunderland", 0, 0));
        matches.add(new Match("Arsenal", "Tottenham", 5, 1));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(leagueTable);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(4, tableEntryList.size());

        LeagueTableEntry arsenalTableEntry = tableEntryList.get(0);
        LeagueTableEntry sunderLandTableEntry = tableEntryList.get(1);
        LeagueTableEntry chelseaTableEntry = tableEntryList.get(2);
        LeagueTableEntry tottenhamTableEntry = tableEntryList.get(3);

        Assertions.assertEquals("Chelsea", chelseaTableEntry.getTeamName());
        Assertions.assertEquals(1, chelseaTableEntry.getPoints());

        Assertions.assertEquals("Arsenal", arsenalTableEntry.getTeamName());
        Assertions.assertEquals(7, arsenalTableEntry.getPoints());

        Assertions.assertEquals("Sunderland", sunderLandTableEntry.getTeamName());
        Assertions.assertEquals(3, sunderLandTableEntry.getPoints());

        Assertions.assertEquals("Tottenham", tottenhamTableEntry.getTeamName());
        Assertions.assertEquals(1, tottenhamTableEntry.getPoints());
    }

    /*
     * Test sorting rules in this order:
     * - Sort by total points (descending)
     * - Then by goal difference (descending)
     * - Then by goals scored (descending)
     * - Then by team name (in alphabetical order)
     * */
    @Test
    public void testGetTableEntries_teamHasDifferentPoints_teamWithHigherPointsAreAtTheTopOfTheTable() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 1));
        matches.add(new Match("Chelsea", "Sunderland", 2, 2));
        matches.add(new Match("Arsenal", "Sunderland", 5, 1));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(leagueTable);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(3, tableEntryList.size());

        Assertions.assertEquals("Chelsea", tableEntryList.get(0).getTeamName()); //Point = 4
        Assertions.assertEquals("Arsenal", tableEntryList.get(1).getTeamName()); //Point = 3
        Assertions.assertEquals("Sunderland", tableEntryList.get(2).getTeamName()); //Point = 1
    }

    @Test
    public void testGetTableEntries_twoTeamsHaveSamePoints_teamWithHigherGoalDifferenceAreAtTheTopOfTheTable() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 2, 1));
        matches.add(new Match("Chelsea", "Sunderland", 2, 2));
        matches.add(new Match("Arsenal", "Sunderland", 2, 1));
        matches.add(new Match("Arsenal", "Middlesbrough", 1, 1));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(leagueTable);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(4, tableEntryList.size());

        final LeagueTableEntry firstTableEntry = tableEntryList.get(0);
        final LeagueTableEntry secondTableEntry = tableEntryList.get(1);

        Assertions.assertEquals(4, firstTableEntry.getPoints());
        Assertions.assertEquals(4, secondTableEntry.getPoints());

        Assertions.assertEquals("Chelsea", firstTableEntry.getTeamName()); //GoalDifference = 1
        Assertions.assertEquals("Arsenal", secondTableEntry.getTeamName()); //GoalDifference = 0
    }

    @Test
    public void testGetTableEntries_twoTeamsHaveSamePointsAndGoalDifference_teamThatScoredMoreGoalsIsAtTheTopOfTheTable() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Arsenal", 5, 2));
        matches.add(new Match("Chelsea", "Sunderland", 2, 2));
        matches.add(new Match("Arsenal", "Sunderland", 3, 1));
        matches.add(new Match("Arsenal", "Middlesbrough", 1, 1));
        matches.add(new Match("Arsenal", "Middlesbrough", 1, 4));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(leagueTable);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(4, tableEntryList.size());

        final LeagueTableEntry firstTableEntry = tableEntryList.get(0);
        final LeagueTableEntry secondTableEntry = tableEntryList.get(1);

        Assertions.assertEquals(4, firstTableEntry.getPoints());
        Assertions.assertEquals(4, secondTableEntry.getPoints());

        Assertions.assertEquals(3, firstTableEntry.getGoalDifference());
        Assertions.assertEquals(3, secondTableEntry.getGoalDifference());

        Assertions.assertEquals("Chelsea", firstTableEntry.getTeamName()); //GoalsFor = 7
        Assertions.assertEquals("Middlesbrough", secondTableEntry.getTeamName()); //GoalsFor = 5
    }

    @Test
    public void testGetTableEntries_twoTeamsHaveSamePointsAndGoalDifferenceAndGoalsFor_suchTeamsAreSortedWithTheirNamesInAlphabeticalOrderFromTheTopOfTheTable() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Manchester United", "Arsenal", 5, 2));
        matches.add(new Match("Manchester United", "Sunderland", 2, 2));
        matches.add(new Match("Arsenal", "Sunderland", 3, 1));
        matches.add(new Match("Arsenal", "Manchester City", 3, 3));
        matches.add(new Match("Arsenal", "Manchester City", 1, 4));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        Assertions.assertNotNull(leagueTable);

        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(4, tableEntryList.size());

        final LeagueTableEntry firstTableEntry = tableEntryList.get(0);
        final LeagueTableEntry secondTableEntry = tableEntryList.get(1);

        Assertions.assertEquals(4, firstTableEntry.getPoints());
        Assertions.assertEquals(4, secondTableEntry.getPoints());

        Assertions.assertEquals(3, firstTableEntry.getGoalDifference());
        Assertions.assertEquals(3, secondTableEntry.getGoalDifference());

        Assertions.assertEquals(7, firstTableEntry.getGoalsFor());
        Assertions.assertEquals(7, secondTableEntry.getGoalsFor());

        Assertions.assertEquals("Manchester City", firstTableEntry.getTeamName()); //First Letter in City is 'C'
        Assertions.assertEquals("Manchester United", secondTableEntry.getTeamName()); //First Letter in United is 'U'
    }

    @Test
    public void testGetTableEntries_validMatchesListInput_teamAreSortedAccordingToRules() {
        final String chelsea = "Chelsea";
        final String arsenal = "Arsenal";
        final String sunderland = "Sunderland";
        final String brighton = "Brighton";
        final String manUtd = "Manchester United";
        final String westhamUtd = "Westham United";
        final String middlesbrough = "Middlesbrough";
        final String liverpool = "Liverpool";

        List<Match> matches = new ArrayList<>();
        matches.add(new Match(chelsea, liverpool, 2, 1));
        matches.add(new Match(liverpool, chelsea, 0, 5));
        matches.add(new Match(arsenal, sunderland, 3, 3));
        matches.add(new Match(westhamUtd, sunderland, 3, 3));
        matches.add(new Match(manUtd, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, arsenal, 0, 0));
        matches.add(new Match(brighton, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, brighton, 0, 0));
        matches.add(new Match(sunderland, chelsea, 3, 6));
        matches.add(new Match(brighton, chelsea, 1, 6));
        matches.add(new Match(arsenal, middlesbrough, 1, 0));
        matches.add(new Match(middlesbrough, arsenal, 1, 1));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();
        Assertions.assertEquals(8, tableEntryList.size());

        final LeagueTableEntry firstPosition = tableEntryList.get(0);
        final LeagueTableEntry secondPosition = tableEntryList.get(1);
        final LeagueTableEntry thirdPosition = tableEntryList.get(2);
        final LeagueTableEntry fourthPosition = tableEntryList.get(3);
        final LeagueTableEntry fifthPosition = tableEntryList.get(4);
        final LeagueTableEntry sixthPosition = tableEntryList.get(5);
        final LeagueTableEntry seventhPosition = tableEntryList.get(6);
        final LeagueTableEntry eighthPosition = tableEntryList.get(7);

        //Validate first position entry data points
        Assertions.assertEquals(chelsea, firstPosition.getTeamName());
        Assertions.assertEquals(6, firstPosition.getPlayed());
        Assertions.assertEquals(6, firstPosition.getWon());
        Assertions.assertEquals(0, firstPosition.getDrawn());
        Assertions.assertEquals(0, firstPosition.getLost());
        Assertions.assertEquals(25, firstPosition.getGoalsFor());
        Assertions.assertEquals(5, firstPosition.getGoalsAgainst());
        Assertions.assertEquals(20, firstPosition.getGoalDifference());
        Assertions.assertEquals(18, firstPosition.getPoints());

        //Validate second position entry data points
        Assertions.assertEquals(arsenal, secondPosition.getTeamName());
        Assertions.assertEquals(4, secondPosition.getPlayed());
        Assertions.assertEquals(1, secondPosition.getWon());
        Assertions.assertEquals(3, secondPosition.getDrawn());
        Assertions.assertEquals(0, secondPosition.getLost());
        Assertions.assertEquals(5, secondPosition.getGoalsFor());
        Assertions.assertEquals(4, secondPosition.getGoalsAgainst());
        Assertions.assertEquals(1, secondPosition.getGoalDifference());
        Assertions.assertEquals(6, secondPosition.getPoints());

        //Validate third position entry data points
        Assertions.assertEquals(sunderland, thirdPosition.getTeamName());
        Assertions.assertEquals(9, thirdPosition.getPlayed());
        Assertions.assertEquals(0, thirdPosition.getWon());
        Assertions.assertEquals(6, thirdPosition.getDrawn());
        Assertions.assertEquals(3, thirdPosition.getLost());
        Assertions.assertEquals(15, thirdPosition.getGoalsFor());
        Assertions.assertEquals(24, thirdPosition.getGoalsAgainst());
        Assertions.assertEquals(-9, thirdPosition.getGoalDifference());
        Assertions.assertEquals(6, thirdPosition.getPoints());

        //Validate fourth position entry data points
        Assertions.assertEquals(brighton, fourthPosition.getTeamName());
        Assertions.assertEquals(3, fourthPosition.getPlayed());
        Assertions.assertEquals(0, fourthPosition.getWon());
        Assertions.assertEquals(2, fourthPosition.getDrawn());
        Assertions.assertEquals(1, fourthPosition.getLost());
        Assertions.assertEquals(4, fourthPosition.getGoalsFor());
        Assertions.assertEquals(9, fourthPosition.getGoalsAgainst());
        Assertions.assertEquals(-5, fourthPosition.getGoalDifference());
        Assertions.assertEquals(2, fourthPosition.getPoints());

        //Validate fifth position entry data points
        Assertions.assertEquals(manUtd, fifthPosition.getTeamName());
        Assertions.assertEquals(1, fifthPosition.getPlayed());
        Assertions.assertEquals(0, fifthPosition.getWon());
        Assertions.assertEquals(1, fifthPosition.getDrawn());
        Assertions.assertEquals(0, fifthPosition.getLost());
        Assertions.assertEquals(3, fifthPosition.getGoalsFor());
        Assertions.assertEquals(3, fifthPosition.getGoalsAgainst());
        Assertions.assertEquals(0, fifthPosition.getGoalDifference());
        Assertions.assertEquals(1, fifthPosition.getPoints());

        //Validate sixth position entry data points
        Assertions.assertEquals(westhamUtd, sixthPosition.getTeamName());
        Assertions.assertEquals(1, sixthPosition.getPlayed());
        Assertions.assertEquals(0, sixthPosition.getWon());
        Assertions.assertEquals(1, sixthPosition.getDrawn());
        Assertions.assertEquals(0, sixthPosition.getLost());
        Assertions.assertEquals(3, sixthPosition.getGoalsFor());
        Assertions.assertEquals(3, sixthPosition.getGoalsAgainst());
        Assertions.assertEquals(0, sixthPosition.getGoalDifference());
        Assertions.assertEquals(1, sixthPosition.getPoints());

        //Validate seventh position entry data points
        Assertions.assertEquals(middlesbrough, seventhPosition.getTeamName());
        Assertions.assertEquals(2, seventhPosition.getPlayed());
        Assertions.assertEquals(0, seventhPosition.getWon());
        Assertions.assertEquals(1, seventhPosition.getDrawn());
        Assertions.assertEquals(1, seventhPosition.getLost());
        Assertions.assertEquals(1, seventhPosition.getGoalsFor());
        Assertions.assertEquals(2, seventhPosition.getGoalsAgainst());
        Assertions.assertEquals(-1, seventhPosition.getGoalDifference());
        Assertions.assertEquals(1, seventhPosition.getPoints());

        //Validate eighth position entry data points
        Assertions.assertEquals(liverpool, eighthPosition.getTeamName());
        Assertions.assertEquals(2, eighthPosition.getPlayed());
        Assertions.assertEquals(0, eighthPosition.getWon());
        Assertions.assertEquals(0, eighthPosition.getDrawn());
        Assertions.assertEquals(2, eighthPosition.getLost());
        Assertions.assertEquals(1, eighthPosition.getGoalsFor());
        Assertions.assertEquals(7, eighthPosition.getGoalsAgainst());
        Assertions.assertEquals(-6, eighthPosition.getGoalDifference());
        Assertions.assertEquals(0, eighthPosition.getPoints());
    }

    @Test
    public void testGetTableEntries_confirmTableEntryListCaching_secondCallToGetTableEntriesIsFaster() {
        final String chelsea = "Chelsea";
        final String arsenal = "Arsenal";
        final String sunderland = "Sunderland";
        final String brighton = "Brighton";
        final String manUtd = "Manchester United";
        final String westhamUtd = "Westham United";
        final String middlesbrough = "Middlesbrough";
        final String liverpool = "Liverpool";

        List<Match> matches = new ArrayList<>();
        matches.add(new Match(chelsea, liverpool, 2, 1));
        matches.add(new Match(liverpool, chelsea, 0, 5));
        matches.add(new Match(arsenal, sunderland, 3, 3));
        matches.add(new Match(westhamUtd, sunderland, 3, 3));
        matches.add(new Match(manUtd, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, arsenal, 0, 0));
        matches.add(new Match(brighton, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, brighton, 0, 0));
        matches.add(new Match(sunderland, chelsea, 3, 6));
        matches.add(new Match(brighton, chelsea, 1, 6));
        matches.add(new Match(arsenal, middlesbrough, 1, 0));
        matches.add(new Match(middlesbrough, arsenal, 1, 1));
        matches.add(new Match(chelsea, liverpool, 2, 1));
        matches.add(new Match(liverpool, chelsea, 0, 5));
        matches.add(new Match(arsenal, sunderland, 3, 3));
        matches.add(new Match(westhamUtd, sunderland, 3, 3));
        matches.add(new Match(manUtd, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, arsenal, 0, 0));
        matches.add(new Match(brighton, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, brighton, 0, 0));
        matches.add(new Match(sunderland, chelsea, 3, 6));
        matches.add(new Match(brighton, chelsea, 1, 6));
        matches.add(new Match(arsenal, middlesbrough, 1, 0));
        matches.add(new Match(middlesbrough, arsenal, 1, 1));
        matches.add(new Match(chelsea, liverpool, 2, 1));
        matches.add(new Match(liverpool, chelsea, 0, 5));
        matches.add(new Match(arsenal, sunderland, 3, 3));
        matches.add(new Match(westhamUtd, sunderland, 3, 3));
        matches.add(new Match(manUtd, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, arsenal, 0, 0));
        matches.add(new Match(brighton, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, brighton, 0, 0));
        matches.add(new Match(sunderland, chelsea, 3, 6));
        matches.add(new Match(brighton, chelsea, 1, 6));
        matches.add(new Match(arsenal, middlesbrough, 1, 0));
        matches.add(new Match(middlesbrough, arsenal, 1, 1));
        matches.add(new Match(chelsea, liverpool, 2, 1));
        matches.add(new Match(liverpool, chelsea, 0, 5));
        matches.add(new Match(arsenal, sunderland, 3, 3));
        matches.add(new Match(westhamUtd, sunderland, 3, 3));
        matches.add(new Match(manUtd, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, arsenal, 0, 0));
        matches.add(new Match(brighton, sunderland, 3, 3));
        matches.add(new Match(sunderland, chelsea, 0, 3));
        matches.add(new Match(sunderland, brighton, 0, 0));
        matches.add(new Match(sunderland, chelsea, 3, 6));
        matches.add(new Match(brighton, chelsea, 1, 6));
        matches.add(new Match(arsenal, middlesbrough, 1, 0));
        matches.add(new Match(middlesbrough, arsenal, 1, 1));

        final long firstCallStartTime = System.nanoTime();


        final LeagueTable leagueTable = new FootballLeagueTable(matches);
        List<LeagueTableEntry> firstCallTableEntryList = leagueTable.getTableEntries();
        final long firstCallEndTime = System.nanoTime();

        final long secondCallStartTime = System.nanoTime();
        List<LeagueTableEntry> secondCallTableEntryList = leagueTable.getTableEntries();
        final long secondCallEndTime = System.nanoTime();

        final long timeTakenForFirstCall = firstCallEndTime - firstCallStartTime;
        final long timeTakenForSecondCall = secondCallEndTime - secondCallStartTime;

        Assertions.assertTrue(timeTakenForFirstCall > timeTakenForSecondCall);
    }

    @Test
    public void testGetTableEntries_tableEntryListResultIsModified_cachedTableEntryListIsUnaffected() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match("Manchester United", "Arsenal", 5, 2));
        matches.add(new Match("Manchester United", "Sunderland", 2, 2));
        matches.add(new Match("Arsenal", "Sunderland", 3, 1));
        matches.add(new Match("Arsenal", "Manchester City", 3, 3));
        matches.add(new Match("Arsenal", "Manchester City", 1, 4));

        final LeagueTable leagueTable = new FootballLeagueTable(matches);

        //Get table entry list
        List<LeagueTableEntry> tableEntryList = leagueTable.getTableEntries();

        //Determine the size of the original copy of table entry list received
        int initialTableEntrySize = tableEntryList.size();

        //Remove an entry from the copy of table entry list received
        tableEntryList.remove(0);

        //Determine the size of the modified copy of table entry list
        int finalTableEntrySize = tableEntryList.size();

        //Assert that the size of the table entry list is not the same after modification
        Assertions.assertTrue(initialTableEntrySize != finalTableEntrySize);

        //Confirm that the initial size of the table entry received is same as the freshly received table entry list
        Assertions.assertEquals(leagueTable.getTableEntries().size(), initialTableEntrySize);

    }
}