package com.example.myalertdialog;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;

public class USelect {
  private Activity activity;

  private CharSequence title;
  private CharSequence[] options;

  private int selectedIndex = -1;
  private int defaultIndex = -1;
  private CharSequence defaultValue;

  private boolean isCancelable = true;
  private boolean isImmediate = true;

  private CharSequence okLabel = "OK";
  private CharSequence cancelLabel = "Cancel";

  private OnItemSelectedListener listener;

  public interface OnItemSelectedListener {
    void onItemSelected(int selectedIndex, CharSequence selectedValue);
  }

  public USelect(Activity activity) {
    this.activity = activity;
  }

  public USelect setTitle(CharSequence title) {
    this.title = title;
    return this;
  }

  public USelect setOkLabel(CharSequence label) {
    this.okLabel = label;
    return this;
  }

  public USelect setOkLabel(@StringRes int label) {
    this.okLabel = activity.getString(label);
    return this;
  }

  public USelect setCancelLabel(CharSequence label) {
    this.cancelLabel = label;
    return this;
  }

  public USelect setCancelLabel(@StringRes int label) {
    this.cancelLabel = activity.getString(label);
    return this;
  }

  public USelect setOptions(CharSequence... options) {
    this.options = options;
    return this;
  }

  public USelect setIsCancelable(boolean isCancelable) {
    this.isCancelable = isCancelable;
    return this;
  }

  public USelect setDefaultIndex(int index) {
    this.defaultIndex = index;
    return this;
  }

  public USelect setDefaultValue(CharSequence value) {
    this.defaultValue = value;
    return this;
  }

  public USelect setIsImmediate(boolean isImmediate) {
    this.isImmediate = isImmediate;
    return this;
  }

  public USelect setOnItemSelectedListener(OnItemSelectedListener listener) {
    this.listener = listener;
    return this;
  }

  public void show() {
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setTitle(title);
    builder.setCancelable(isCancelable);

    if (defaultValue != null) {
      for (int i = 0; i < options.length; i++) {
        if (options[i].equals(defaultValue)) {
          defaultIndex = i;
          break;
        }
      }
    }

    builder.setSingleChoiceItems(options, defaultIndex, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        selectedIndex = which;

        if (isImmediate) {
          if (listener != null) {
            listener.onItemSelected(selectedIndex, options[selectedIndex]);
          }

          dialog.dismiss();
        }
      }
    });

    if (!isImmediate) {
      builder.setPositiveButton(okLabel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          if (listener != null) {
            listener.onItemSelected(selectedIndex, options[selectedIndex]);
          }
        }
      });
    }

    if (isCancelable) {
      builder.setNegativeButton(cancelLabel, null);
    }

    AlertDialog dialog = builder.create();
    dialog.show();
  }
}
