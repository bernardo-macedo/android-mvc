package testes.caronaphone.com.mvcottoteste.activities;

import android.os.Bundle;
import android.view.View;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.Arrays;

import testes.caronaphone.com.mvcottoteste.R;
import testes.caronaphone.com.mvcottoteste.exceptions.ViewException;
import testes.caronaphone.com.mvcottoteste.models.Contributor;
import testes.caronaphone.com.mvcottoteste.models.ContributorList;
import testes.caronaphone.com.mvcottoteste.requests.ListContributorsRequest;
import testes.caronaphone.com.mvcottoteste.views.MainView;


public class MainActivity extends BaseActivity {

    private ContributorList model;
    private MainView view;

    private ListContributorsRequest contributorsRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = ContributorList.getInstance();
        view = (MainView) View.inflate(this, R.layout.activity_main, null);
        view.setViewListener(viewListener);
        setContentView(view);

        contributorsRequest = new ListContributorsRequest("square", "retrofit");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        view.destroy();
    }

    private void executeRequest() {
        getSpiceManager().execute(contributorsRequest,
                ListContributorsRequest.CACHE_KEY,
                ListContributorsRequest.CACHE_TIME, new ListContributorsListener());
    }

    /**
     * This is how we receive events from the view.
     * The view takes user actions
     * The controller/activity responds to user actions
     */
    private MainView.ViewListener viewListener = new MainView.ViewListener() {
        @Override
        public void onListUpdateRequested() {
            // Controller can call method directly on the view
            view.setState(MainView.State.UPDATING);

            executeRequest();
        }

        @Override
        public void onContributorSelected(Contributor contributor) {
            model.remove(contributor);
        }
    };

    private class ListContributorsListener implements RequestListener<Contributor[]> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            view.handleError(new ViewException(spiceException.getMessage(), ViewException.Severity.ERROR));
        }

        @Override
        public void onRequestSuccess(Contributor[] contributors) {
            // Controller is responsible for updating the model
            model.setContributors(new ArrayList<Contributor>(Arrays.asList(contributors)));
            // Controller can call method directly on the view
            view.setState(MainView.State.IDLE);
        }
    }
}
