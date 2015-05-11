package testes.caronaphone.com.mvcottoteste.requests;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;
import testes.caronaphone.com.mvcottoteste.models.Contributor;

/**
 * Created by -Bernardo on 2015-05-08.
 */
public class ListContributorsRequest extends RetrofitSpiceRequest<Contributor[], TesteApi> {

    public static final String CACHE_KEY = "github_contributors";
    public static final long CACHE_TIME = 5 * DurationInMillis.ONE_MINUTE;

    private String owner;
    private String repo;

    public ListContributorsRequest(String owner, String repo) {
        super(Contributor[].class, TesteApi.class);
        this.owner = owner;
        this.repo = repo;
    }

    @Override
    public Contributor[] loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().contributors(owner, repo);
    }
}
