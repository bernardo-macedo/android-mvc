package testes.caronaphone.com.mvcottoteste.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import testes.caronaphone.com.mvcottoteste.R;
import testes.caronaphone.com.mvcottoteste.models.Contributor;
import testes.caronaphone.com.mvcottoteste.models.ContributorList;
import testes.caronaphone.com.mvcottoteste.shared.events.ContributorSelectedEvent;
import testes.caronaphone.com.mvcottoteste.shared.events.SingletonBus;

/**
 * Created by -Bernardo on 2015-05-11.
 * Adapter para a RecyclerView implementada na MainView.
 * <p/>
 * Faz a adaptacao de uma lista de Contributor para uma lista de pares de TextViews
 * inicializados na classe interna ViewHolder.
 */
public class ContributorsRecyclerAdapter extends RecyclerView.Adapter<ContributorsRecyclerAdapter.ViewHolder> {

    private ContributorList model;

    public ContributorsRecyclerAdapter(ContributorList model) {
        this.model = model;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        SingletonBus.getInstance().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        SingletonBus.getInstance().unregister(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_contributor, viewGroup, false);
        return new ViewHolder(v);
    }

    /**
     * Este metodo configura cada viewHolder do apresentada na RecyclerView.
     * <p/>
     * O clique em uma viewHolder propaga um novo evento no EventBus que deve ser tratado pelo
     * layout em que a RecyclerView esta sendo apresentada ou pela activity controladora.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        final Contributor contributor = model.get(position);
        viewHolder.textViewLogin.setText(contributor.getLogin());
        viewHolder.textViewContributions.setText(String.valueOf(contributor.getContributions()));
        viewHolder.textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingletonBus.getInstance().post(new ContributorSelectedEvent(contributor));
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLogin;
        TextView textViewContributions;

        public ViewHolder(View v) {
            super(v);
            this.textViewLogin = (TextView) v.findViewById(R.id.textView_login);
            this.textViewContributions = (TextView) v.findViewById(R.id.textView_contributions);
        }
    }
}
