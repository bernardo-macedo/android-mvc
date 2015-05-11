package testes.caronaphone.com.mvcottoteste.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import testes.caronaphone.com.mvcottoteste.R;
import testes.caronaphone.com.mvcottoteste.events.ContributorSelectedEvent;
import testes.caronaphone.com.mvcottoteste.events.SingletonBus;
import testes.caronaphone.com.mvcottoteste.models.Contributor;
import testes.caronaphone.com.mvcottoteste.models.ContributorList;

/**
 * Created by -Bernardo on 2015-05-11.
 */
public class ContributorsRecyclerAdapter extends RecyclerView.Adapter<ContributorsRecyclerAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewLogin;
        TextView textViewContributions;

        public ViewHolder(View v) {
            super(v);
            this.textViewLogin = (TextView) v.findViewById(R.id.textView_login);
            this.textViewContributions = (TextView) v.findViewById(R.id.textView_contributions);
        }
    }

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
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

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
}
