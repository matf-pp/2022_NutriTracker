package com.marko590.tabtestfinal.databinding;
import com.marko590.tabtestfinal.R;
import com.marko590.tabtestfinal.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DailyFragmentBindingImpl extends DailyFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pbCaloriesTrackerBg, 1);
        sViewsWithIds.put(R.id.pbCaloriesTracker, 2);
        sViewsWithIds.put(R.id.tvCaloriesIntake, 3);
        sViewsWithIds.put(R.id.tvPercent, 4);
        sViewsWithIds.put(R.id.tvCalories, 5);
        sViewsWithIds.put(R.id.tvCaloriesCounter, 6);
        sViewsWithIds.put(R.id.otfFoodName, 7);
        sViewsWithIds.put(R.id.btnAdd, 8);
        sViewsWithIds.put(R.id.tvProtein, 9);
        sViewsWithIds.put(R.id.pbProteinIntake, 10);
        sViewsWithIds.put(R.id.tvProteinIntake, 11);
        sViewsWithIds.put(R.id.tvFat, 12);
        sViewsWithIds.put(R.id.pbFatIntake, 13);
        sViewsWithIds.put(R.id.tvFatIntake, 14);
        sViewsWithIds.put(R.id.tvCarbs, 15);
        sViewsWithIds.put(R.id.pbCarbsIntake, 16);
        sViewsWithIds.put(R.id.tvCarbsIntake, 17);
        sViewsWithIds.put(R.id.fabHistory, 18);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DailyFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private DailyFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[8]
            , (com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton) bindings[18]
            , (com.google.android.material.textfield.TextInputLayout) bindings[7]
            , (android.widget.ProgressBar) bindings[2]
            , (android.widget.ProgressBar) bindings[1]
            , (android.widget.ProgressBar) bindings[16]
            , (android.widget.ProgressBar) bindings[13]
            , (android.widget.ProgressBar) bindings[10]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TextView) bindings[14]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[11]
            );
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}