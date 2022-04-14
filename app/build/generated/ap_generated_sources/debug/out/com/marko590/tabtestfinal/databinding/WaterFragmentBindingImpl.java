package com.marko590.tabtestfinal.databinding;
import com.marko590.tabtestfinal.R;
import com.marko590.tabtestfinal.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class WaterFragmentBindingImpl extends WaterFragmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.pbWaterIntake, 1);
        sViewsWithIds.put(R.id.sGlasses, 2);
        sViewsWithIds.put(R.id.tvWater, 3);
        sViewsWithIds.put(R.id.tvWaterIntake, 4);
        sViewsWithIds.put(R.id.tvWaterProgress, 5);
        sViewsWithIds.put(R.id.tvWaterRemain, 6);
        sViewsWithIds.put(R.id.btnReminder, 7);
        sViewsWithIds.put(R.id.tvSteps, 8);
        sViewsWithIds.put(R.id.tvStepsProgress, 9);
        sViewsWithIds.put(R.id.tvSteps2, 10);
        sViewsWithIds.put(R.id.ivFootsteps, 11);
        sViewsWithIds.put(R.id.ivFootsteps2, 12);
        sViewsWithIds.put(R.id.btnPlus, 13);
        sViewsWithIds.put(R.id.otfStepGoals, 14);
        sViewsWithIds.put(R.id.pbStepsTrackerBg, 15);
        sViewsWithIds.put(R.id.pbStepsTracker, 16);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public WaterFragmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds));
    }
    private WaterFragmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[7]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.ImageView) bindings[12]
            , (com.google.android.material.textfield.TextInputLayout) bindings[14]
            , (android.widget.ProgressBar) bindings[16]
            , (android.widget.ProgressBar) bindings[15]
            , (android.widget.ProgressBar) bindings[1]
            , (android.widget.Spinner) bindings[2]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[10]
            , (android.widget.TextView) bindings[9]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
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