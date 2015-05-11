package testes.caronaphone.com.mvcottoteste.events;

import testes.caronaphone.com.mvcottoteste.models.Contributor;

/**
 * Created by -Bernardo on 2015-05-11.
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
