
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

/**
 * FootballLeagueTable handles football-specific behaviour customization. E.g. how the table entry data points are generated,
 * the sorting rules, etc.
 */
public class FootballLeagueTable extends LeagueTable {

    public FootballLeagueTable(final List<Match> matches) {
        super(matches);
    }

    /**
     * Build table entries according to football rules:
     * <ul>
     *     <li>Winning team gets 3 points</li>
     *     <li>A match draw attracts a point each to both teams</li>
     * </ul>
     */
    @Override
    protected Collection<LeagueTableEntry> generateLeagueTableEntry(@NotNull final Collection<Match> matches) {

        if (matches == null || matches.isEmpty()) {
            return Collections.emptyList();
        }

        final Map<String, LeagueTableEntry> teamToTableEntryMap = new HashMap<>();

        for (Match match : matches) {

            LeagueTableEntry homeTeamTableEntry = getTeamTableEntryOrCreate(teamToTableEntryMap, match.getHomeTeam());
            LeagueTableEntry awayTeamTableEntry = getTeamTableEntryOrCreate(teamToTableEntryMap, match.getAwayTeam());

            if (match.getHomeScore() == match.getAwayScore()) {

                //Case 1: Match ended as draw

                homeTeamTableEntry = new LeagueTableEntry(
                        homeTeamTableEntry.getTeamName(),
                        homeTeamTableEntry.getPlayed() + 1,
                        homeTeamTableEntry.getWon(),
                        homeTeamTableEntry.getDrawn() + 1,
                        homeTeamTableEntry.getLost(),
                        homeTeamTableEntry.getGoalsFor() + match.getHomeScore(),
                        homeTeamTableEntry.getGoalsAgainst() + match.getAwayScore(),
                        homeTeamTableEntry.getGoalDifference() + (match.getHomeScore() - match.getAwayScore()),
                        homeTeamTableEntry.getPoints() + 1
                );

                awayTeamTableEntry = new LeagueTableEntry(
                        awayTeamTableEntry.getTeamName(),
                        awayTeamTableEntry.getPlayed() + 1,
                        awayTeamTableEntry.getWon(),
                        awayTeamTableEntry.getDrawn() + 1,
                        awayTeamTableEntry.getLost(),
                        awayTeamTableEntry.getGoalsFor() + match.getAwayScore(),
                        awayTeamTableEntry.getGoalsAgainst() + match.getHomeScore(),
                        awayTeamTableEntry.getGoalDifference() + (match.getAwayScore() - match.getHomeScore()),
                        awayTeamTableEntry.getPoints() + 1
                );

            } else if (match.getHomeScore() > match.getAwayScore()) {

                //Case 2: Home team won

                homeTeamTableEntry = new LeagueTableEntry(
                        homeTeamTableEntry.getTeamName(),
                        homeTeamTableEntry.getPlayed() + 1,
                        homeTeamTableEntry.getWon() + 1,
                        homeTeamTableEntry.getDrawn(),
                        homeTeamTableEntry.getLost(),
                        homeTeamTableEntry.getGoalsFor() + match.getHomeScore(),
                        homeTeamTableEntry.getGoalsAgainst() + match.getAwayScore(),
                        homeTeamTableEntry.getGoalDifference() + (match.getHomeScore() - match.getAwayScore()),
                        homeTeamTableEntry.getPoints() + 3
                );

                awayTeamTableEntry = new LeagueTableEntry(
                        awayTeamTableEntry.getTeamName(),
                        awayTeamTableEntry.getPlayed() + 1,
                        awayTeamTableEntry.getWon(),
                        awayTeamTableEntry.getDrawn(),
                        awayTeamTableEntry.getLost() + 1,
                        awayTeamTableEntry.getGoalsFor() + match.getAwayScore(),
                        awayTeamTableEntry.getGoalsAgainst() + match.getHomeScore(),
                        awayTeamTableEntry.getGoalDifference() + (match.getAwayScore() - match.getHomeScore()),
                        awayTeamTableEntry.getPoints()
                );

            } else{

                //Case 3: Away team won

                homeTeamTableEntry = new LeagueTableEntry(
                        homeTeamTableEntry.getTeamName(),
                        homeTeamTableEntry.getPlayed() + 1,
                        homeTeamTableEntry.getWon(),
                        homeTeamTableEntry.getDrawn(),
                        homeTeamTableEntry.getLost() + 1,
                        homeTeamTableEntry.getGoalsFor() + match.getHomeScore(),
                        homeTeamTableEntry.getGoalsAgainst() + match.getAwayScore(),
                        homeTeamTableEntry.getGoalDifference() + (match.getHomeScore() - match.getAwayScore()),
                        homeTeamTableEntry.getPoints()
                );

                awayTeamTableEntry = new LeagueTableEntry(
                        awayTeamTableEntry.getTeamName(),
                        awayTeamTableEntry.getPlayed() + 1,
                        awayTeamTableEntry.getWon() + 1,
                        awayTeamTableEntry.getDrawn(),
                        awayTeamTableEntry.getLost(),
                        awayTeamTableEntry.getGoalsFor() + match.getAwayScore(),
                        awayTeamTableEntry.getGoalsAgainst() + match.getHomeScore(),
                        awayTeamTableEntry.getGoalDifference() + (match.getAwayScore() - match.getHomeScore()),
                        awayTeamTableEntry.getPoints() + 3
                );

            }

            teamToTableEntryMap.put(homeTeamTableEntry.getTeamName().toLowerCase(Locale.getDefault()), homeTeamTableEntry);
            teamToTableEntryMap.put(awayTeamTableEntry.getTeamName().toLowerCase(Locale.getDefault()), awayTeamTableEntry);
        }

        return teamToTableEntryMap.values();
    }

    @Override
    public Optional<Comparator<LeagueTableEntry>> getSortingRule() {
        return Optional.of(Comparator.comparing(LeagueTableEntry::getPoints).reversed()
                .thenComparing(Comparator.comparing(LeagueTableEntry::getGoalDifference).reversed())
                .thenComparing(Comparator.comparing(LeagueTableEntry::getGoalsFor).reversed())
                .thenComparing(LeagueTableEntry::getTeamName));
    }

    @Nullable
    private LeagueTableEntry getTeamTableEntryOrCreate(@NotNull final Map<String, LeagueTableEntry> teamToTableEntryMap, @NotNull final String teamName) {
        if (teamToTableEntryMap == null || teamName == null || teamName.isEmpty()) {
            return null;
        }

        return teamToTableEntryMap.getOrDefault(teamName.toLowerCase(Locale.getDefault()), new LeagueTableEntry(teamName, 0, 0, 0, 0, 0, 0, 0, 0));
    }
}
