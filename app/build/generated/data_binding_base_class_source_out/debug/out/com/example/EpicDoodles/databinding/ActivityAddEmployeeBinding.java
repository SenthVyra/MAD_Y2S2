// Generated by view binder compiler. Do not edit!
package com.example.EpicDoodles.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.EpicDoodles.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddEmployeeBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnMAdd;

  @NonNull
  public final Button btnMBack;

  @NonNull
  public final EditText txtMDescription;

  @NonNull
  public final EditText txtMName;

  @NonNull
  public final EditText txtMPrice;

  @NonNull
  public final EditText txtMType;

  private ActivityAddEmployeeBinding(@NonNull LinearLayout rootView, @NonNull Button btnMAdd,
      @NonNull Button btnMBack, @NonNull EditText txtMDescription, @NonNull EditText txtMName,
      @NonNull EditText txtMPrice, @NonNull EditText txtMType) {
    this.rootView = rootView;
    this.btnMAdd = btnMAdd;
    this.btnMBack = btnMBack;
    this.txtMDescription = txtMDescription;
    this.txtMName = txtMName;
    this.txtMPrice = txtMPrice;
    this.txtMType = txtMType;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddEmployeeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddEmployeeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add_employee, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddEmployeeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnMAdd;
      Button btnMAdd = ViewBindings.findChildViewById(rootView, id);
      if (btnMAdd == null) {
        break missingId;
      }

      id = R.id.btnMBack;
      Button btnMBack = ViewBindings.findChildViewById(rootView, id);
      if (btnMBack == null) {
        break missingId;
      }

      id = R.id.txtMDescription;
      EditText txtMDescription = ViewBindings.findChildViewById(rootView, id);
      if (txtMDescription == null) {
        break missingId;
      }

      id = R.id.txtMName;
      EditText txtMName = ViewBindings.findChildViewById(rootView, id);
      if (txtMName == null) {
        break missingId;
      }

      id = R.id.txtMPrice;
      EditText txtMPrice = ViewBindings.findChildViewById(rootView, id);
      if (txtMPrice == null) {
        break missingId;
      }

      id = R.id.txtMType;
      EditText txtMType = ViewBindings.findChildViewById(rootView, id);
      if (txtMType == null) {
        break missingId;
      }

      return new ActivityAddEmployeeBinding((LinearLayout) rootView, btnMAdd, btnMBack,
          txtMDescription, txtMName, txtMPrice, txtMType);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
