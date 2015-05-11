package testes.caronaphone.com.mvcottoteste.activities;

import android.support.v4.app.FragmentActivity;

import com.octo.android.robospice.SpiceManager;

import testes.caronaphone.com.mvcottoteste.events.SingletonBus;
import testes.caronaphone.com.mvcottoteste.services.ExemploSpiceService;

/**
 * Created by -Bernardo on 2015-05-08.
 */
public abstract class BaseActivity extends FragmentActivity {

    private SpiceManager spiceManager = new SpiceManager(ExemploSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        SingletonBus.getInstance().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        SingletonBus.getInstance().unregister(this);
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }
}
