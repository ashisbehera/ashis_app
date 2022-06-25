package com.example.ashis_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.NewViewHolder> {

    private Context context;
    private ArrayList<Height> item_list;
    public static final int VIEW_TYPE_PADDING = 1;
    public static final int VIEW_TYPE_ITEM = 2;
    private int paddingWidth = 0;
    private  OnClickListener onClickListener;
    private int center_item = -1;

    /**
     * constructor
     * @param context
     * @param clickListener
     * @param barCodeGetList
     * @param paddingWidth
     */
    public CustomAdapter(Context context, OnClickListener clickListener , ArrayList<Height> barCodeGetList ,
                         int paddingWidth) {
        this.context = context;
        this.item_list = barCodeGetList;
        this.paddingWidth = paddingWidth;
        this.onClickListener = clickListener;
    }


    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list, parent, false);

            return new NewViewHolder(itemView);
        }else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list, parent, false);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            layoutParams.width = paddingWidth;
            view.setLayoutParams(layoutParams);
            return new NewViewHolder(view);
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, @SuppressLint("RecyclerView") int position) {


        if(position<=290) {

            if (getItemViewType(position) == VIEW_TYPE_ITEM) {

                /**
                 * if pos is equals to center item
                 */
                if (position == center_item) {
                    /**
                     * large bars
                     */
                    if (position==1 || position % 10 == 0) {
                        holder.short_view.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.GONE);
                        holder.long_View.setVisibility(View.VISIBLE);
                        holder.long_View.setBackgroundResource(R.color.red);
                        holder.num_text_v.setVisibility(View.VISIBLE);
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.red));
                        holder.num_text_v.setText(item_list.get(position).getCm());
                    }
                    /**
                     * middle bars
                     */
                    else if((position % 5==0) && (((position/5)%2)!=0)){
                        holder.short_view.setVisibility(View.GONE);
                        holder.long_View.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.VISIBLE);
                        holder.num_text_v.setVisibility(View.VISIBLE);
                        holder.num_text_v.setText(item_list.get(position).getCm());
                        holder.middle_view.setBackgroundResource(R.color.red);
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.red));
                    }

                    else {/**small bars */
                        holder.short_view.setVisibility(View.VISIBLE);
                        holder.long_View.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.GONE);
                        holder.num_text_v.setVisibility(View.VISIBLE);
                        holder.num_text_v.setText(item_list.get(position).getCm());
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.red));
                        holder.short_view.setBackgroundResource(R.color.red);

                    }
                } else {
                    if (position==1 || position % 10 == 0) {
                        holder.short_view.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.GONE);
                        holder.long_View.setVisibility(View.VISIBLE);
                        holder.num_text_v.setVisibility(View.VISIBLE);
                        holder.long_View.setBackgroundResource(R.color.white);
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.white));
                        holder.num_text_v.setText(item_list.get(position).getCm());
                    }
                    else if((position % 5==0) && (((position/5)%2)!=0)){
                        holder.short_view.setVisibility(View.GONE);
                        holder.long_View.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.VISIBLE);
                        holder.num_text_v.setVisibility(View.VISIBLE);
                        holder.middle_view.setBackgroundResource(R.color.white);
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.white));
                        holder.num_text_v.setText(item_list.get(position).getCm());
                    }
                    else {
                        holder.short_view.setVisibility(View.VISIBLE);
                        holder.long_View.setVisibility(View.GONE);
                        holder.middle_view.setVisibility(View.GONE);
                        holder.num_text_v.setVisibility(View.GONE);
                        holder.short_view.setBackgroundResource(R.color.white);
                        holder.num_text_v.setTextColor(ContextCompat.getColor(context, R.color.white));
                    }
                }
            } else {
                holder.long_View.setVisibility(View.GONE);
                holder.num_text_v.setVisibility(View.GONE);
                holder.short_view.setVisibility(View.GONE);
                holder.middle_view.setVisibility(View.GONE);
            }

            /**
             * add clickListener on all views
             */
            holder.short_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onclick(position, center_item);
                }
            });

            holder.long_View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onclick(position, center_item);
                }
            });
            holder.num_text_v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onclick(position, center_item);
                }
            });
            holder.list_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onclick(position, center_item);
                }
            });

        }else{
            holder.long_View.setVisibility(View.GONE);
            holder.num_text_v.setVisibility(View.GONE);
            holder.short_view.setVisibility(View.GONE);
            holder.middle_view.setVisibility(View.GONE);
        }

    }

    /**
     * set centerItem pos
     * @param center_item
     */
    public void setCenter_item(int center_item) {
        this.center_item = center_item;
        notifyDataSetChanged();
    }

    /**
     * item count
     * @return
     */
    @Override
    public int getItemCount() {
        return item_list.size();
    }

    /**
     * get itemType
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Height labelerDate = item_list.get(position);
        if (labelerDate.getType() == VIEW_TYPE_PADDING) {
            return VIEW_TYPE_PADDING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }


    /**
     * Custom ViewHolder class for adapter
     */
    class NewViewHolder extends RecyclerView.ViewHolder {
        TextView num_text_v;
        View long_View , short_view , middle_view;
        LinearLayout list_layout;

        NewViewHolder(View view) {
            super(view);
            num_text_v = view.findViewById(R.id.num_txtV);
            long_View = view.findViewById(R.id.large_view);
            middle_view = view.findViewById(R.id.middle_view);
            short_view = view.findViewById(R.id.short_view);
            list_layout = view.findViewById(R.id.list_ll);
        }
    }
}
