package testes.caronaphone.com.mvcottoteste.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Subscribe;

import testes.caronaphone.com.mvcottoteste.R;
import testes.caronaphone.com.mvcottoteste.adapters.ContributorsRecyclerAdapter;
import testes.caronaphone.com.mvcottoteste.events.ContributorListChangedEvent;
import testes.caronaphone.com.mvcottoteste.events.ContributorSelectedEvent;
import testes.caronaphone.com.mvcottoteste.exceptions.ViewException;
import testes.caronaphone.com.mvcottoteste.models.Contributor;
import testes.caronaphone.com.mvcottoteste.models.ContributorList;

/**
 * Created by -Bernardo on 2015-05-10.
 */
public class MainView extends BaseView {

    /**
     * The interface to send events from the view to the controller
     */
    public static interface ViewListener {
        public void onListUpdateRequested();
        public void onContributorSelected(Contributor contributor);
    }

    public static enum State {
        IDLE, UPDATING
    }

    private Button mButtonAtualizar;
    private ProgressDialog mPDialog;

    private RecyclerView mRecyclerView;
    private ContributorsRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private ArrayAdapter mListAdapter;
    //private ListView mListViewDados;

    private ContributorList model;
    private ViewListener viewListener;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        model = ContributorList.getInstance();
    }

    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

    /**
     * Exposed method so the controller can set the view state.
     */
    public void setState(State state) {
        if (state.equals(State.UPDATING)) {
            mPDialog = ProgressDialog.show(getContext(), "Aguarde", "Recuperando dados do servidor...");
        } else {
            if (mPDialog != null) {
                mPDialog.dismiss();
            }
        }
    }

    /**
     * Find our references to the objects in the xml layout
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mButtonAtualizar = (Button) findViewById(R.id.button);
        //mListViewDados = (ListView) findViewById(R.id.listView);
        mRecyclerView = (RecyclerView) findViewById(R.id.listView);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mButtonAtualizar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                viewListener.onListUpdateRequested();
            }
        });

        mAdapter = new ContributorsRecyclerAdapter(model);
        mRecyclerView.setAdapter(mAdapter);

        bind();
    }

    /**
     * Does the work to update the view when the model changes.
     */
    private void bind() {
        if (model.size() > 0) {
            // Torna a listView visivel
            mRecyclerView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void handleError(ViewException e) {
        Toast.makeText(getContext(), "Ocorreu um erro. Verifique sua conexao e tente novamente", Toast.LENGTH_SHORT).show();
    }

    /**
     * The listener for when the model has changed -> Change the adapter view.
     */
    @Subscribe
    public void onContributorListChanged(ContributorListChangedEvent e) {
        if (e != null) {
            if (e.getChange().equals(ContributorListChangedEvent.ListChange.ADD)) {
                for (int i = 0; i < e.getChangedIndexes().length; i++) {
                    mAdapter.notifyItemInserted(e.getChangedIndexes()[i]);
                }
            } else if (e.getChange().equals(ContributorListChangedEvent.ListChange.REMOVE)) {
                for (int i = 0; i < e.getChangedIndexes().length; i++) {
                    mAdapter.notifyItemRemoved(e.getChangedIndexes()[i]);
                }
            } else if (e.getChange().equals(ContributorListChangedEvent.ListChange.ADDALL)) {
                    mAdapter.notifyDataSetChanged();
            } else if (e.getChange().equals(ContributorListChangedEvent.ListChange.UPDATE)){
                for (int i = 0; i < e.getChangedIndexes().length; i++) {
                    mAdapter.notifyItemChanged(e.getChangedIndexes()[i]);
                }
            }
        }
        bind();
    }

    /**
     * The listener for when the adapter view is clicked -> Delegate action to controller.
     */
    @Subscribe
    public void onContributorSelected(ContributorSelectedEvent e) {
        viewListener.onContributorSelected(e.getContributor());
    }

}
