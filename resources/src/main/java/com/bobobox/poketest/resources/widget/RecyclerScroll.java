package com.bobobox.poketest.resources.widget;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class RecyclerScroll extends RecyclerView.OnScrollListener {
    private static final float HIDE_THRESHOLD = 100.0F;
    private static final float SHOW_THRESHOLD = 50.0F;
    int scrollDist = 0;
    private boolean isVisible = true;
    int pastVisiblesItems;
    int visibleItemCount;
    int totalItemCount;
    LinearLayoutManager mLayoutManager;

    public RecyclerScroll(LinearLayoutManager mLayoutManager) {
        this.mLayoutManager = mLayoutManager;
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
//        Timber.d("hi Item Bro " + dx+ " " +dy);
        if (this.isVisible && (float)this.scrollDist > 100.0F) {
            this.hide();
            this.scrollDist = 0;
            this.isVisible = false;
        } else if (!this.isVisible && (float)this.scrollDist < -50.0F) {
            this.show();
            this.scrollDist = 0;
            this.isVisible = true;
        }

        if (this.isVisible && dy > 0 || !this.isVisible && dy < 0) {
            this.scrollDist += dy;
        }

        if (dy > 0) {
            this.visibleItemCount = this.mLayoutManager.getChildCount();
            this.totalItemCount = this.mLayoutManager.getItemCount();
            this.pastVisiblesItems = this.mLayoutManager.findFirstVisibleItemPosition();
            if (this.visibleItemCount + this.pastVisiblesItems >= this.totalItemCount) {
//                Timber.d("Last Item Bro " + this.totalItemCount);
                this.loadMore();
            }
        }

    }

    public abstract void show();

    public abstract void hide();

    public abstract void loadMore();
}
