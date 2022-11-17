import java.util.Objects;

public class LeagueTableEntry {
    /**
     * name of this team
     */
    private final String teamName;
    /**
     * total games played
     */
    private final int played;
    /**
     * total games won
     */
    private final int won;
    /**
     * total games drawn
     */
    private final int drawn;
    /**
     * total games lost
     */
    private final int lost;
    /**
     * total goals scored by this team
     */
    private final int goalsFor;
    /**
     * total goals against this team
     */
    private final int goalsAgainst;
    /**
     * Average absolute score difference.
     */
    private final int goalDifference;
    /**
     * total points (3 for wins, and 1 for draws, 0 for loss)
     */
    private final int points;

    public LeagueTableEntry(String teamName, int played, int won, int drawn, int lost, int goalsFor,
                            int goalsAgainst, int goalDifference, int points) {
        if (teamName == null || teamName.isEmpty() || played < 0 || won < 0 || drawn < 0 || lost < 0 || goalsFor < 0
                || goalsAgainst < 0 || points < 0) {
            throw new IllegalArgumentException("Invalid league table entry argument supplied.");
        }

        if(won + lost + drawn != played){
            throw new IllegalArgumentException("Number of played match mismatch.");
        }

        if(goalsFor - goalsAgainst != goalDifference){
            throw new IllegalArgumentException("Goal record mismatch.");
        }

        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.drawn = drawn;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getLost() {
        return lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueTableEntry that = (LeagueTableEntry) o;
        return played == that.played && won == that.won && drawn == that.drawn && lost == that.lost && goalsFor == that.goalsFor && goalsAgainst == that.goalsAgainst && goalDifference == that.goalDifference && points == that.points && Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, played, won, drawn, lost, goalsFor, goalsAgainst, goalDifference, points);
    }

    @Override
    public String toString() {
        return "{" +
                "teamName = '" + teamName + '\'' +
                ", played = " + played +
                ", won = " + won +
                ", drawn = " + drawn +
                ", lost = " + lost +
                ", goalsFor = " + goalsFor +
                ", goalsAgainst = " + goalsAgainst +
                ", goalDifference = " + goalDifference +
                ", points = " + points +
                '}';
    }
}
