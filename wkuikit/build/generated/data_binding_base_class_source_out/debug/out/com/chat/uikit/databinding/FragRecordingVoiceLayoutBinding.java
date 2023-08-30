// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.uikit.R;
import com.chat.uikit.view.voice.LineWaveVoiceView;
import com.chat.uikit.view.voice.RecordAudioView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragRecordingVoiceLayoutBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final RecordAudioView ivRecording;

  @NonNull
  public final LinearLayout layoutRecordAudio;

  @NonNull
  public final LinearLayout ppLayoutCancel;

  @NonNull
  public final RelativeLayout recordContent;

  @NonNull
  public final TextView recordTips;

  @NonNull
  public final LineWaveVoiceView waveVoiceView;

  private FragRecordingVoiceLayoutBinding(@NonNull RelativeLayout rootView,
      @NonNull RecordAudioView ivRecording, @NonNull LinearLayout layoutRecordAudio,
      @NonNull LinearLayout ppLayoutCancel, @NonNull RelativeLayout recordContent,
      @NonNull TextView recordTips, @NonNull LineWaveVoiceView waveVoiceView) {
    this.rootView = rootView;
    this.ivRecording = ivRecording;
    this.layoutRecordAudio = layoutRecordAudio;
    this.ppLayoutCancel = ppLayoutCancel;
    this.recordContent = recordContent;
    this.recordTips = recordTips;
    this.waveVoiceView = waveVoiceView;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragRecordingVoiceLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragRecordingVoiceLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.frag_recording_voice_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragRecordingVoiceLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ivRecording;
      RecordAudioView ivRecording = ViewBindings.findChildViewById(rootView, id);
      if (ivRecording == null) {
        break missingId;
      }

      id = R.id.layout_record_audio;
      LinearLayout layoutRecordAudio = ViewBindings.findChildViewById(rootView, id);
      if (layoutRecordAudio == null) {
        break missingId;
      }

      id = R.id.pp_layout_cancel;
      LinearLayout ppLayoutCancel = ViewBindings.findChildViewById(rootView, id);
      if (ppLayoutCancel == null) {
        break missingId;
      }

      RelativeLayout recordContent = (RelativeLayout) rootView;

      id = R.id.record_tips;
      TextView recordTips = ViewBindings.findChildViewById(rootView, id);
      if (recordTips == null) {
        break missingId;
      }

      id = R.id.waveVoiceView;
      LineWaveVoiceView waveVoiceView = ViewBindings.findChildViewById(rootView, id);
      if (waveVoiceView == null) {
        break missingId;
      }

      return new FragRecordingVoiceLayoutBinding((RelativeLayout) rootView, ivRecording,
          layoutRecordAudio, ppLayoutCancel, recordContent, recordTips, waveVoiceView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}