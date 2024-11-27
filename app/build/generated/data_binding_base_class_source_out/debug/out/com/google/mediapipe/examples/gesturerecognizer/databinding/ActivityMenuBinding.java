// Generated by view binder compiler. Do not edit!
package com.google.mediapipe.examples.gesturerecognizer.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.mediapipe.examples.gesturerecognizer.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMenuBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnMP;

  @NonNull
  public final Button btnStt;

  @NonNull
  public final Button btnTts;

  @NonNull
  public final EditText etResult;

  @NonNull
  public final SeekBar seekBarPitch;

  @NonNull
  public final SeekBar seekBarSpeed;

  @NonNull
  public final TextView tvInstruction;

  private ActivityMenuBinding(@NonNull LinearLayout rootView, @NonNull Button btnMP,
      @NonNull Button btnStt, @NonNull Button btnTts, @NonNull EditText etResult,
      @NonNull SeekBar seekBarPitch, @NonNull SeekBar seekBarSpeed,
      @NonNull TextView tvInstruction) {
    this.rootView = rootView;
    this.btnMP = btnMP;
    this.btnStt = btnStt;
    this.btnTts = btnTts;
    this.etResult = etResult;
    this.seekBarPitch = seekBarPitch;
    this.seekBarSpeed = seekBarSpeed;
    this.tvInstruction = tvInstruction;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMenuBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMenuBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_menu, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMenuBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btn_MP;
      Button btnMP = ViewBindings.findChildViewById(rootView, id);
      if (btnMP == null) {
        break missingId;
      }

      id = R.id.btn_stt;
      Button btnStt = ViewBindings.findChildViewById(rootView, id);
      if (btnStt == null) {
        break missingId;
      }

      id = R.id.btn_tts;
      Button btnTts = ViewBindings.findChildViewById(rootView, id);
      if (btnTts == null) {
        break missingId;
      }

      id = R.id.etResult;
      EditText etResult = ViewBindings.findChildViewById(rootView, id);
      if (etResult == null) {
        break missingId;
      }

      id = R.id.seek_bar_pitch;
      SeekBar seekBarPitch = ViewBindings.findChildViewById(rootView, id);
      if (seekBarPitch == null) {
        break missingId;
      }

      id = R.id.seek_bar_speed;
      SeekBar seekBarSpeed = ViewBindings.findChildViewById(rootView, id);
      if (seekBarSpeed == null) {
        break missingId;
      }

      id = R.id.tvInstruction;
      TextView tvInstruction = ViewBindings.findChildViewById(rootView, id);
      if (tvInstruction == null) {
        break missingId;
      }

      return new ActivityMenuBinding((LinearLayout) rootView, btnMP, btnStt, btnTts, etResult,
          seekBarPitch, seekBarSpeed, tvInstruction);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
