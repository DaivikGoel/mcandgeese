package ece.example.mcandgeese;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ece.example.mcandgeese.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Item> items;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<Item> items) {
        this.context = context;
        this.items = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView name = (TextView) view.findViewById(R.id.textView);
        TextView quantity = (TextView) view.findViewById(R.id.quantity);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        Item item = items.get(i);
        name.setText(item.getName());
        quantity.setText("x" + item.getQuantity());
        icon.setImageResource(item.getImageId());
        return view;
    }
}
