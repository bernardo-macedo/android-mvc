package testes.caronaphone.com.mvcottoteste.requests;

import retrofit.http.GET;
import retrofit.http.Path;
import testes.caronaphone.com.mvcottoteste.models.Contributor;

/**
 * Created by -Bernardo on 2015-05-07.
 */
public interface TesteApi {

    @GET("/repos/{owner}/{repo}/contributors")
    Contributor[] contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );
}
