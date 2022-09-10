package test.project.mornhouse.ui.interesting_fact.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import test.project.mornhouse.R;
import test.project.mornhouse.ui.interesting_fact.repository.room_db.item.Fact;

public class InterestingFactAdapter extends RecyclerView.Adapter<InterestingFactAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private List<Fact> facts;
    private IInterestingFactListener listener;

    public InterestingFactAdapter(Context context, IInterestingFactListener listener, List<Fact> facts) {
        this.inflater = LayoutInflater.from(context);
        this.facts = facts;
        this.listener = listener;
    }

    @Override
    public InterestingFactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_interesting_fact, parent, false);
        return new InterestingFactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InterestingFactAdapter.ViewHolder holder, int position) {
        Fact fact = facts.get(position);

        holder.txtInfo.setText(fact.getText());
        holder.clMain.setOnClickListener(v -> {
            listener.itemSelected(fact);
        });


    }

    @Override
    public int getItemCount() {
        return facts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtInfo;
        ConstraintLayout clMain;

        ViewHolder(View view){
            super(view);
            txtInfo = view.findViewById(R.id.txtInfo);
            clMain = view.findViewById(R.id.clMain);
        }
    }
}
