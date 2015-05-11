package testes.caronaphone.com.mvcottoteste.shared.events;

import testes.caronaphone.com.mvcottoteste.models.Contributor;

/**
 * Created by -Bernardo on 2015-05-11.
 * Evento que eh criado quando um Contributor eh selecionado na view
 */
public class ContributorSelectedEvent {

    private Contributor contributor;

    public ContributorSelectedEvent(Contributor contributor) {
        this.contributor = contributor;
    }

    public Contributor getContributor() {
        return contributor;
    }
}
