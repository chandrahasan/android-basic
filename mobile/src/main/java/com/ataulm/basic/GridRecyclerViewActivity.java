package com.ataulm.basic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GridRecyclerViewActivity extends Activity {

    private static final int SPAN_COUNT = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler_view);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        RecyclerView.Adapter adapter = new Examples(getLayoutInflater());
        adapter.setHasStableIds(true);
        list.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        list.setAdapter(adapter);
    }

    private static class Examples extends RecyclerView.Adapter<Examples.LetterViewHolder> {

        private final LayoutInflater layoutInflater;
        private Toast toast;

        Examples(LayoutInflater layoutInflater) {
            this.layoutInflater = layoutInflater;
        }

        @Override
        public LetterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout.view_letter_item, parent, false);
            return new LetterViewHolder(view);
        }

        @Override
        public void onBindViewHolder(LetterViewHolder holder, int position) {
            final Character item = getItem(position);
            ((TextView) holder.itemView).setText(item.getTelephony());
            // Trying to be clever here didn't help - goog's done work trying to read naturally; not so great when we interfere with phonicPronunciation
            holder.itemView.setContentDescription(item.getPhonicPronunciation());
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = Toast.makeText(v.getContext(), "clicked: " + item.name(), Toast.LENGTH_SHORT);
                    toast.show();
                }

            });
        }

        @Override
        public int getItemCount() {
            return Character.values().length;
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).ordinal();
        }

        private Character getItem(int position) {
            return Character.values()[position];
        }

        static class LetterViewHolder extends RecyclerView.ViewHolder {

            public LetterViewHolder(View itemView) {
                super(itemView);
            }

        }

    }

}
