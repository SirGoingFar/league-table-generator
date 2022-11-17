import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to trigger the generator with a sample match dataset
 * */
public class FootballLeagueTableGeneratorRunner {

    public static void main(String[] args) {
        List<Match> matches = new ArrayList<>();
        matches.add(new Match("Chelsea", "Liverpool", 2, 1));
        matches.add(new Match("Liverpool", "Chelsea", 0, 5));
        matches.add(new Match("Arsenal", "Sunderland", 3, 3));
        matches.add(new Match("WestHam United", "Sunderland", 3, 3));
        matches.add(new Match("Manchester United", "Sunderland", 3, 3));
        matches.add(new Match("Sunderland", "Chelsea", 0, 3));
        matches.add(new Match("Sunderland", "Arsenal", 0, 0));
        matches.add(new Match("Brighton", "Sunderland", 3, 3));
        matches.add(new Match("Sunderland", "Chelsea", 0, 3));
        matches.add(new Match("Sunderland", "Brighton", 0, 0));
        matches.add(new Match("Sunderland", "Chelsea", 3, 6));
        matches.add(new Match("Brighton", "Chelsea", 1, 6));
        matches.add(new Match("Arsenal", "Middlesbrough", 1, 0));
        matches.add(new Match("Middlesbrough", "Arsenal", 1, 1));

        //Instantiate FootballLeagueTable as the exercise is about football.
        //This logic needn't change if we are interested in a cricket league. All needed is to create a CricketLeagueTable
        //that extends LeagueTable and implements required abstract functions. Then instantiate CricketLeagueTable class below instead.
        final LeagueTable leagueTable = new FootballLeagueTable(matches);

        for (LeagueTableEntry e : leagueTable.getTableEntries()) {
            System.out.println(e);
        }

    }
}