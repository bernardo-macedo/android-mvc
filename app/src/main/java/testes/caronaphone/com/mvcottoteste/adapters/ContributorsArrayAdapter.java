package testes.caronaphone.com.mvcottoteste.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import testes.caronaphone.com.mvcottoteste.R;
import testes.caronaphone.com.mvcottoteste.models.Contributor;


/**
 * Created by -Bernardo on 2015-05-07.
 *
 * Adapter que eh usado para mostrar os dados no ListView de Contributors
 */
public class ContributorsArrayAdapter extends ArrayAdapter<Contributor> {

    int mResource;

    public ContributorsArrayAdapter(Context context, int resource, List<Contributor> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Contributor contributor = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }

        TextView textViewLogin = (TextView) convertView.findViewById(R.id.textView_login);
        TextView textViewContributions = (TextView) convertView.findViewById(R.id.textView_contributions);

        textViewLogin.setText(contributor.getLogin());
        textViewContributions.setText(String.valueOf(contributor.getContributions()));

        // Return the completed view to render on screen
        return convertView;
    }
}
