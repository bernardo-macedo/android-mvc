package testes.caronaphone.com.mvcottoteste.controllers.activities;

import android.support.v4.app.FragmentActivity;

import com.octo.android.robospice.SpiceManager;

import testes.caronaphone.com.mvcottoteste.controllers.network.services.ExemploSpiceService;
import testes.caronaphone.com.mvcottoteste.shared.events.SingletonBus;

/**
 * Created by -Bernardo on 2015-05-08.
 * Classe da qual todas as activities devem herdar.
 * <p/>
 * Ela possui uma referencia para o servico da biclioteca Robospice que
 * permite lidar com requisicoes em background de forma eficaz e permite tambem
 * armazenar as requisicoes realizadas em cache.
 * <p/>
 * Esta classe tambem gerencia o registro da activity como um possivel listener dos
 * eventos do EventBus.
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
