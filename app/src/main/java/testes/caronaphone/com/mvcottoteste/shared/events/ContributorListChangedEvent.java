package testes.caronaphone.com.mvcottoteste.shared.events;

/**
 * Created by -Bernardo on 2015-05-11.
 * Evento que eh criado quando se adiciona, remove ou altera um elemento de uma lista de Contributors
 */
public class ContributorListChangedEvent {

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

    public static enum ListChange {
        ADD, ADDALL, REMOVE, UPDATE
    }

}
