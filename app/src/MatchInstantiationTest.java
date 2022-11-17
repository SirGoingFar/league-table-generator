import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatchInstantiationTest {

    @Test
    public void testMatchInstantiation_invalidHomeTeam_throwsIllegalArgumentException() {
        final IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match(null, "Away Team", 0, 0));
        Assertions.assertEquals("Invalid match argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());

        final IllegalArgumentException emptyInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("", "Away Team", 0, 0));
        Assertions.assertEquals("Invalid match argument supplied.", emptyInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testMatchInstantiation_invalidAwayTeam_throwsIllegalArgumentException() {
        final IllegalArgumentException nullInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("Home Team", null, 5, 6));
        Assertions.assertEquals("Invalid match argument supplied.", nullInputIllegalArgumentException.getLocalizedMessage());

        final IllegalArgumentException emptyInputIllegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("Home Team", "", 2, 1));
        Assertions.assertEquals("Invalid match argument supplied.", emptyInputIllegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testMatchInstantiation_invalidHomeScore_throwsIllegalArgumentException() {
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("Home Team", "Away Team", -5, 6));
        Assertions.assertEquals("Invalid match argument supplied.", illegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testMatchInstantiation_invalidAwayScore_throwsIllegalArgumentException() {
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("Home Team", "Away Team", 5, -6));
        Assertions.assertEquals("Invalid match argument supplied.", illegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testMatchInstantiation_homeAndAwayTeamsAreSame_throwsIllegalArgumentException() {
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Match("Same Team", "Same Team", 5, 6));
        Assertions.assertEquals("Same team can't play against itself.", illegalArgumentException.getLocalizedMessage());
    }

    @Test
    public void testMatchInstantiation_validInput_instantiationIsSuccessful() {
        final Match match = new Match("Home Team", "Away Team", 5, 6);
        Assertions.assertEquals("Home Team", match.getHomeTeam());
        Assertions.assertEquals("Away Team", match.getAwayTeam());
        Assertions.assertEquals(5, match.getHomeScore());
        Assertions.assertEquals(6, match.getAwayScore());
    }

}