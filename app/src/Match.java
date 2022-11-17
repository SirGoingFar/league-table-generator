import java.util.Objects;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private final int homeScore;
    private final int awayScore;

    public Match(final String homeTeam, final String awayTeam, final int homeScore, final int awayScore) {

        if (homeTeam == null || homeTeam.isEmpty() || awayTeam == null || awayTeam.isEmpty()
                || homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Invalid match argument supplied.");
        }

        if(homeTeam.equalsIgnoreCase(awayTeam)){
            throw new IllegalArgumentException("Same team can't play against itself.");
        }

        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return homeScore == match.homeScore && awayScore == match.awayScore && Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore);
    }
}
