package testes.caronaphone.com.mvcottoteste.shared.events;

/**
 * Created by -Bernardo on 2015-05-11.
 * Evento que eh criado quando um atributo de um Contributor eh alterado
 */
public class ContributorChangedEvent {

    private ChangedProperty changedProperty;

    public ContributorChangedEvent(ChangedProperty changedProperty) {
        this.changedProperty = changedProperty;
    }

    public ChangedProperty getChangedProperty() {
        return changedProperty;
    }

    public static enum ChangedProperty {
        LOGIN, CONTRIBUTIONS
    }
}
