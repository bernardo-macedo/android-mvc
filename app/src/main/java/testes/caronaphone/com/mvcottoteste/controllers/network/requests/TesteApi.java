package testes.caronaphone.com.mvcottoteste.controllers.network.requests;

import retrofit.http.GET;
import retrofit.http.Path;
import testes.caronaphone.com.mvcottoteste.models.Contributor;

/**
 * Created by -Bernardo on 2015-05-07.
 * Interface do Retrofit que encapsula as chamadas REST do aplicativo.
 * Os metodos desta interface sao implementados pela propria biblioteca Retrofit e
 * sao utilizados no metodo loadFromNetwork dos requests.
 */
public interface TesteApi {

    @GET("/repos/{owner}/{repo}/contributors")
    Contributor[] contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
