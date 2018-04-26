package com.martin.codestar.repos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.martin.codestar.API.models.Repository;
import com.martin.codestar.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposAdapter extends BaseAdapter {

    private List<Repository> mRepositories;
    private LayoutInflater mInflater;

    public ReposAdapter(Activity activity, List<Repository> list) {
        this.mInflater = activity.getLayoutInflater();
        this.mRepositories = list;
    }

    @Override
    public int getCount() {
        return this.mRepositories.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mRepositories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder item;
        Repository repository = this.mRepositories.get(i);

        if (view == null) {
            view = this.mInflater.inflate(R.layout.item_repository, viewGroup, false);

            item = new ViewHolder(view);
            view.setTag(item);
        } else {
            item = (ViewHolder) view.getTag();
        }

        item.labelRepo.setText(repository.getName());
        item.labelStars.setText(repository.getStargazers_count());

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.label_item_repo)
        TextView labelRepo;
        @BindView(R.id.label_item_stars)
        TextView labelStars;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
    
    public void updateRepos(List<Repository> repos) {
        this.mRepositories.clear();
        this.notifyDataSetChanged();
        this.mRepositories = repos;
        this.notifyDataSetChanged();
    }
}
