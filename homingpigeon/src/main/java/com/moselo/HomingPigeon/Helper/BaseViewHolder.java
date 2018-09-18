package com.moselo.HomingPigeon.Helper;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fadhlan on 8/2/16.
 *
 * @see {https://gist.github.com/aurae/ebf8ec212e4296aebb24}
 */
public abstract class BaseViewHolder<T>
        extends RecyclerView.ViewHolder
        /*implements View.OnClickListener, View.OnLongClickListener*/ {
    private T item;
    protected final int marginBottom;
    protected final int marginTop;

    public BaseViewHolder(View itemView) {
        super(itemView);
        marginTop = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).topMargin;
        marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
//        setupItemView();
    }

    protected BaseViewHolder(ViewGroup parent, @LayoutRes int itemLayoutId) {
        super(LayoutInflater.from(parent.getContext())
                .inflate(itemLayoutId, parent, false));
        marginTop = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).topMargin;
        marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
//        setupItemView();
    }

//    private void setupItemView() {
//        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(this);
//        itemView.setOnLongClickListener(this);
//    }

    public final void performBind(T item, int position) {
        this.item = item;
        onBind(item, position);
    }

    protected T getItem() {
        return item;
    }

    protected abstract void onBind(T item, int position);

//    protected abstract void onClick(View view, T item);
//
//    protected abstract boolean onLongClick(View view, T item);
//
//    @Override
//    final public void onClick(View view) {
//        onClick(view, item);
//    }
//
//    @Override
//    final public boolean onLongClick(View view) {
//        return onLongClick(view, item);
//    }

    public int getMarginBottom() {
        return marginBottom;
    }
}