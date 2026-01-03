package com.example.resapp;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.resapp.model.MenuItem;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private List<MenuItem> menuList;
    private OnMenuChangedListener listener;

    public interface OnMenuChangedListener {
        void onMenuDeleted(int position);
        void onMenuChanged(int position, String name, double price);
    }

    public MenuAdapter(List<MenuItem> menuList, OnMenuChangedListener listener) {
        this.menuList = menuList;
        this.listener = listener;
    }
    public List<MenuItem> getMenuList() {
        return menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuList.get(position);


        if (holder.nameWatcher != null)
            holder.etMenuName.removeTextChangedListener(holder.nameWatcher);
        if (holder.priceWatcher != null)
            holder.etMenuPrice.removeTextChangedListener(holder.priceWatcher);

        holder.etMenuName.setText(item.name == null ? "" : item.name);
        holder.etMenuPrice.setText(item.price > 0 ? String.valueOf(item.price) : "");

        holder.nameWatcher = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                item.name = editable.toString();
                listener.onMenuChanged(holder.getAdapterPosition(), item.name, item.price);
            }
        };
        holder.priceWatcher = new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    item.price = Double.parseDouble(editable.toString());
                } catch (NumberFormatException e) {
                    item.price = 0.0;
                }
                listener.onMenuChanged(holder.getAdapterPosition(), item.name, item.price);
            }
        };

        holder.etMenuName.addTextChangedListener(holder.nameWatcher);
        holder.etMenuPrice.addTextChangedListener(holder.priceWatcher);

        holder.btnDelete.setOnClickListener(v -> {
            listener.onMenuDeleted(holder.getAdapterPosition());
        });
    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        EditText etMenuName, etMenuPrice;
        ImageButton btnDelete;

        TextWatcher nameWatcher;
        TextWatcher priceWatcher;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            etMenuName = itemView.findViewById(R.id.etMenuName);
            etMenuPrice = itemView.findViewById(R.id.etMenuPrice);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }



    public static abstract class SimpleTextWatcher implements TextWatcher {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
    }
}
