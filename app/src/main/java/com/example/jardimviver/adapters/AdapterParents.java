package com.example.jardimviver.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jardimviver.R;
import com.example.jardimviver.dto.ParentDTO;

import java.util.List;

public class AdapterParents extends BaseAdapter {

  private final List<ParentDTO> parents;

  private final Activity activity;

  public AdapterParents(List<ParentDTO> parents, Activity activity) {
    super();
    this.parents = parents;
    this.activity = activity;
  }

  @Override
  public int getCount() {
    return parents.size();
  }

  @Override
  public Object getItem(int position) {
    return parents.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      View view = activity.getLayoutInflater().inflate(R.layout.row_item, parent, false);
      ParentDTO parentDTO = parents.get(position);
      TextView full_name = view.findViewById(R.id.full_name);
      TextView sigla = view.findViewById(R.id.sigla);
      TextView email = view.findViewById(R.id.email);

      full_name.setText(parentDTO.toString());
      sigla.setText(parentDTO.getSigla());
      email.setText(parentDTO.getEmail());

      return view;
    }
    return convertView;
  }
}
