import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LeagueTableEntryInstantiationTest {

    @Test
    public void testInstantiation_invalidTeamName_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry(null,  0, 1, 2, 3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());

        IllegalArgumentException emptyInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("",  0, 1, 2, 3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", emptyInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidPlayedValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  -1, 1, 2, 3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidWonValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, -10, 2, 3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidDrawnValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, -1, 3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidLostValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, 1, -3, 5,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidGoalsForValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, 1, 3, -1,7,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidGoalsAgainstValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, 1, 3, 1,-1,-2, 2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_invalidPointValue_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, 1, 3, 1,1,-2, -2));
        Assertions.assertEquals("Invalid league table entry argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_AdditionOfMatchWonAndLostAndDrawnMoreThanMatchPlayed_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  1, 10, 1, 3, 1,1,-2, 2));
        Assertions.assertEquals("Number of played match mismatch.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_AdditionOfMatchWonAndLostAndDrawnLessThanMatchPlayed_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  20, 10, 1, 3, 1,1,-2, 2));
        Assertions.assertEquals("Number of played match mismatch.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_AdditionOfGoalsForAndGoalsAgainstLessThanGoalDifference_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  9, 5, 1, 3, 1,1,3, 2));
        Assertions.assertEquals("Goal record mismatch.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_AdditionOfGoalsForAndGoalsAgainstMoreThanGoalDifference_throwsIllegalArgumentException(){
        IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new LeagueTableEntry("Team",  9, 5, 1, 3, 1,1,-1, 2));
        Assertions.assertEquals("Goal record mismatch.", nullInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testInstantiation_validInput_instantiationIsSuccessful(){
        LeagueTableEntry leagueTableEntry = new LeagueTableEntry("Team", 9, 5, 1, 3, 1, 1, 0, 2);
        Assertions.assertEquals("Team", leagueTableEntry.getTeamName());
        Assertions.assertEquals(9, leagueTableEntry.getPlayed());
        Assertions.assertEquals(5, leagueTableEntry.getWon());
        Assertions.assertEquals(1, leagueTableEntry.getDrawn());
        Assertions.assertEquals(3, leagueTableEntry.getLost());
        Assertions.assertEquals(1, leagueTableEntry.getGoalsFor());
        Assertions.assertEquals(1, leagueTableEntry.getGoalsAgainst());
        Assertions.assertEquals(0, leagueTableEntry.getGoalDifference());
        Assertions.assertEquals(2, leagueTableEntry.getPoints());

        leagueTableEntry = new LeagueTableEntry("Team", 9, 5, 1, 3, 1, 2, -1, 2);
        Assertions.assertEquals(-1, leagueTableEntry.getGoalDifference());

        leagueTableEntry = new LeagueTableEntry("Team", 9, 5, 1, 3, 5, 2, 3, 2);
        Assertions.assertEquals(3, leagueTableEntry.getGoalDifference());
    }


}