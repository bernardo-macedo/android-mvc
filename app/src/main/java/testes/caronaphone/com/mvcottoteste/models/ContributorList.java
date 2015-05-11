package testes.caronaphone.com.mvcottoteste.models;

import java.util.ArrayList;

import testes.caronaphone.com.mvcottoteste.shared.events.ContributorListChangedEvent;
import testes.caronaphone.com.mvcottoteste.shared.events.SingletonBus;

/**
 * Created by -Bernardo on 2015-05-10.
 * Classe modelo da resposta da requisicao de uma lista de Contributors
 */
public class ContributorList extends ArrayList<Contributor> {

    private static ContributorList instance;

    private ContributorList() {
        super();
    }

    public static ContributorList getInstance() {
        if (instance == null) {
            instance = new ContributorList();
        }
        return instance;
    }

    @Override
    public boolean add(Contributor object) {
        boolean result = super.add(object);
        if (result) {
            notifyChange(ContributorListChangedEvent.ListChange.ADD, super.size() - 1);
        }
        return result;
    }

    @Override
    public void add(int index, Contributor object) {
        super.add(index, object);
        notifyChange(ContributorListChangedEvent.ListChange.ADD, index);
    }

    @Override
    public boolean remove(Object object) {
        int position = super.indexOf(object);
        if (position != -1) {
            super.remove(position);
            notifyChange(ContributorListChangedEvent.ListChange.REMOVE, position);
            return true;
        }
        return false;
    }

    public void setContributors(ArrayList<Contributor> contributors) {
        super.clear();
        super.addAll(contributors);
        notifyChange(ContributorListChangedEvent.ListChange.ADDALL, null);
    }

    private void notifyChange(ContributorListChangedEvent.ListChange type, int changedIndex) {
        notifyChange(type, new int[] {changedIndex});
    }

    private void notifyChange(ContributorListChangedEvent.ListChange type, int[] changedIndexes) {
        SingletonBus.getInstance().post(new ContributorListChangedEvent(type, changedIndexes));
    }
}
