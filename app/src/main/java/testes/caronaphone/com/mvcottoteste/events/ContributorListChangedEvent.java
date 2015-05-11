package testes.caronaphone.com.mvcottoteste.events;

/**
 * Created by -Bernardo on 2015-05-11.
 */
public class ContributorListChangedEvent {

    public static enum ListChange {
        ADD, ADDALL, REMOVE, UPDATE
    }

    private ListChange change;
    private int[] changedIndexes;

    public ContributorListChangedEvent(ListChange change, int[] changedIndexes) {
        this.change = change;
        this.changedIndexes = changedIndexes;
    }

    public ListChange getChange() {
        return change;
    }

    public int[] getChangedIndexes() {
        return changedIndexes;
    }

}
