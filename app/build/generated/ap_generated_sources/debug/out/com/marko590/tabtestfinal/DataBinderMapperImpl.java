package com.marko590.tabtestfinal;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.marko590.tabtestfinal.databinding.DailyFragmentBindingImpl;
import com.marko590.tabtestfinal.databinding.PopupViewLayoutBindingImpl;
import com.marko590.tabtestfinal.databinding.StatsFragmentBindingImpl;
import com.marko590.tabtestfinal.databinding.WaterFragmentBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_DAILYFRAGMENT = 1;

  private static final int LAYOUT_POPUPVIEWLAYOUT = 2;

  private static final int LAYOUT_STATSFRAGMENT = 3;

  private static final int LAYOUT_WATERFRAGMENT = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.marko590.tabtestfinal.R.layout.daily_fragment, LAYOUT_DAILYFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.marko590.tabtestfinal.R.layout.popup_view_layout, LAYOUT_POPUPVIEWLAYOUT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.marko590.tabtestfinal.R.layout.stats_fragment, LAYOUT_STATSFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.marko590.tabtestfinal.R.layout.water_fragment, LAYOUT_WATERFRAGMENT);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_DAILYFRAGMENT: {
          if ("layout/daily_fragment_0".equals(tag)) {
            return new DailyFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for daily_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_POPUPVIEWLAYOUT: {
          if ("layout/popup_view_layout_0".equals(tag)) {
            return new PopupViewLayoutBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for popup_view_layout is invalid. Received: " + tag);
        }
        case  LAYOUT_STATSFRAGMENT: {
          if ("layout/stats_fragment_0".equals(tag)) {
            return new StatsFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for stats_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_WATERFRAGMENT: {
          if ("layout/water_fragment_0".equals(tag)) {
            return new WaterFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for water_fragment is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/daily_fragment_0", com.marko590.tabtestfinal.R.layout.daily_fragment);
      sKeys.put("layout/popup_view_layout_0", com.marko590.tabtestfinal.R.layout.popup_view_layout);
      sKeys.put("layout/stats_fragment_0", com.marko590.tabtestfinal.R.layout.stats_fragment);
      sKeys.put("layout/water_fragment_0", com.marko590.tabtestfinal.R.layout.water_fragment);
    }
  }
}