// Generated by view binder compiler. Do not edit!
package com.example.EpicDoodles.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.EpicDoodles.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class EmployeeItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView agetext;

  @NonNull
  public final Button btnMDelete;

  @NonNull
  public final Button btnMEdit;

  @NonNull
  public final TextView contacttext;

  @NonNull
  public final TextView enametext;

  @NonNull
  public final TextView positiontext;

  private EmployeeItemBinding(@NonNull CardView rootView, @NonNull TextView agetext,
      @NonNull Button btnMDelete, @NonNull Button btnMEdit, @NonNull TextView contacttext,
      @NonNull TextView enametext, @NonNull TextView positiontext) {
    this.rootView = rootView;
    this.agetext = agetext;
    this.btnMDelete = btnMDelete;
    this.btnMEdit = btnMEdit;
    this.contacttext = contacttext;
    this.enametext = enametext;
    this.positiontext = positiontext;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static EmployeeItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static EmployeeItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.employee_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static EmployeeItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.agetext;
      TextView agetext = ViewBindings.findChildViewById(rootView, id);
      if (agetext == null) {
        break missingId;
      }

      id = R.id.btnMDelete;
      Button btnMDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnMDelete == null) {
        break missingId;
      }

      id = R.id.btnMEdit;
      Button btnMEdit = ViewBindings.findChildViewById(rootView, id);
      if (btnMEdit == null) {
        break missingId;
      }

      id = R.id.contacttext;
      TextView contacttext = ViewBindings.findChildViewById(rootView, id);
      if (contacttext == null) {
        break missingId;
      }

      id = R.id.enametext;
      TextView enametext = ViewBindings.findChildViewById(rootView, id);
      if (enametext == null) {
        break missingId;
      }

      id = R.id.positiontext;
      TextView positiontext = ViewBindings.findChildViewById(rootView, id);
      if (positiontext == null) {
        break missingId;
      }

      return new EmployeeItemBinding((CardView) rootView, agetext, btnMDelete, btnMEdit,
          contacttext, enametext, positiontext);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
