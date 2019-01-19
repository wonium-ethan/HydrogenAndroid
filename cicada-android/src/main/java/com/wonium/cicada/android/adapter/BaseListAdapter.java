package com.wonium.cicada.android.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class BaseListAdapter<T, VH extends BaseListViewHolder> extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    public abstract VH createView(int position, ViewGroup parent);

    public abstract void bindView(int position, VH holder, T data);

    private List<T> datas;

   public BaseListAdapter(List<T> datas) {
        this.datas = datas;
    }


    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH holder;
        if (convertView == null) {
            holder = createView(position, parent);
            convertView = holder.getItemView();
        } else {
            holder = (VH) convertView.getTag();
        }

        bindView(position, holder, datas.get(position));

        return convertView;
    }

    /**
     * 添加一条数据，并更新适配器
     *
     * @param data 数据
     */
    public void addData(T data) {
        this.datas.add(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一个数据集合到原有数据集合里面，并更新适配器
     *
     * @param datas 新数据集合
     */
    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 根据索引删除对应的数据，并更新适配器
     *
     * @param position 集合索引
     */
    public void removeData(int position) {
        this.datas.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除指定的数据，并更新适配器
     *
     * @param data 被删除的数据
     */
    public void remoteData(T data) {
        this.datas.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 数据集合清空，并更新适配器
     */
    public void clear() {
        this.datas.clear();
        notifyDataSetChanged();
    }


    private OnChildItemClickListener onChildItemClickListener;

    public void setOnChildItemClickListener(OnChildItemClickListener onChildItemClickListener) {
        if (onChildItemClickListener == null) {
            Log.e(TAG, "setOnChildItemClickListener: " + "onChildItemClickListener is not null");
            return;
        }
        this.onChildItemClickListener = onChildItemClickListener;

    }

    public interface OnChildItemClickListener {
        void onClickChildItem(int position, View view);
    }
}