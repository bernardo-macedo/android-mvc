package testes.caronaphone.com.mvcottoteste.events;

/**
 * Created by -Bernardo on 2015-05-11.
 */
public class ContributorChangedEvent {

    public static enum ChangedProperty {
        LOGIN, CONTRIBUTIONS
    }

    private ChangedProperty changedProperty;

    public ContributorChangedEvent(ChangedProperty changedProperty) {
        this.changedProperty = changedProperty;
    }

    public ChangedProperty getChangedProperty() {
        return changedProperty;
    }
}
