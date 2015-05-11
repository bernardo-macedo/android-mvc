package testes.caronaphone.com.mvcottoteste.services;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by -Bernardo on 2015-05-08.
 *
 * Classe principal do RoboSpice. Este eh o servico android que controla os requests.
 * A maior parte do codigo eh encapsulado dentro da classe pai SpiceService. A unica exigencia
 * eh que temos que implementar o metodo createCacheManager, que retorna um CacheManager configurado.
 */
public class ExemploSpiceService extends RetrofitGsonSpiceService {

    private static final String BASE_URL = "https://api.github.com";

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
