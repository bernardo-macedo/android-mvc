package testes.caronaphone.com.mvcottoteste.models;


import testes.caronaphone.com.mvcottoteste.events.ContributorChangedEvent;
import testes.caronaphone.com.mvcottoteste.events.SingletonBus;

/**
 * Created by -Bernardo on 2015-05-07.
 *
 * Classe modelo da resposta da requisicao
 */
public class Contributor {

    private String login;
    private int contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyChange(ContributorChangedEvent.ChangedProperty.LOGIN);
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
        notifyChange(ContributorChangedEvent.ChangedProperty.CONTRIBUTIONS);
    }

    private void notifyChange(ContributorChangedEvent.ChangedProperty type) {
        SingletonBus.getInstance().post(new ContributorChangedEvent(type));
    }
}
