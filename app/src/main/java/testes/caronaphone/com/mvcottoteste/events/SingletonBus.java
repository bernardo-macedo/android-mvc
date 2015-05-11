package testes.caronaphone.com.mvcottoteste.events;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by -Bernardo on 2015-05-10.
 */
public class SingletonBus {
    private static Bus bus;

    private SingletonBus() {}

    public static Bus getInstance() {
        if(bus == null) {
            bus = new Bus(ThreadEnforcer.ANY);
        }
        return bus;
    }

    public Bus getBus() {
        return bus;
    }
}
