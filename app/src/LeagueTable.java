import com.sun.istack.internal.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeagueTable is made abstract for extensibility as there could be different leagues for different sports. Examples could be FootballLeagueTable,
 * CricketLeagueTable, BaseballLeagueTable, etc.
 * <p/>
 * All of them have table entry generation and sorting in common. Those common behaviors are implemented here.
 */
public abstract class LeagueTable {
    private final List<Match> matches;
    private List<LeagueTableEntry> tableEntryList;

    public LeagueTable(final List<Match> matches) {
        if (matches == null) {
            throw new IllegalArgumentException("Valid list of match(es) is required.");
        }
        this.matches = matches;
        this.tableEntryList = Collections.emptyList();
    }

    public List<LeagueTableEntry> getTableEntries() {
        if (this.matches.isEmpty()) {
            return this.tableEntryList;
        }

        if (!tableEntryList.isEmpty()) {
            return new ArrayList<>(this.tableEntryList);
        }

        final Collection<LeagueTableEntry> availableLeagueTableEntries = generateLeagueTableEntry(this.matches);

        //ASSUMPTION: Since the list of played matches is passed in the constructor, this somewhat means that the list of matches
        //from which the LeagueTable is generated cannot change (this assumes that a setter method is not exposed).
        //The generator logic becomes more performant if the first generated table entries are cached and returned subsequently.
        this.tableEntryList = new ArrayList<>(sortTableEntries(availableLeagueTableEntries));

        //A new copy of the table entry list is returned so that if the returned result is modified by the caller, it won't
        //affect the copy in this class. [Java's "pass by reference" concept]
        return new ArrayList<>(this.tableEntryList);
    }

    private Collection<LeagueTableEntry> sortTableEntries(final Collection<LeagueTableEntry> tableEntries) {

        if (!getSortingRule().isPresent() || tableEntries == null || tableEntries.isEmpty()) {
            return tableEntries;
        }

        return tableEntries.stream().sorted(getSortingRule().get()).collect(Collectors.toList());
    }

    protected abstract Collection<LeagueTableEntry> generateLeagueTableEntry(@NotNull final Collection<Match> matches);

    protected abstract Optional<Comparator<LeagueTableEntry>> getSortingRule();

}
