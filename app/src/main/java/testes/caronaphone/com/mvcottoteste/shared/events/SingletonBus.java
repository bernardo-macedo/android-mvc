package testes.caronaphone.com.mvcottoteste.shared.events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by -Bernardo on 2015-05-10.
 * Classe que encapsula o EventBus da biblioteca Otto em um Singletons
 */
public class SingletonBus {
    private static Bus bus;

    private SingletonBus() {
    }

    public static Bus getInstance() {
        if (bus == null) {
            bus = new Bus(ThreadEnforcer.ANY);
        }
        return bus;
    }

}
