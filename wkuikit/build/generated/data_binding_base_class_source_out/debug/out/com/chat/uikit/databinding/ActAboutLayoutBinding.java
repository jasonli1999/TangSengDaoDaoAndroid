// Generated by view binder compiler. Do not edit!
package com.chat.uikit.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.chat.base.ui.components.AvatarView;
import com.chat.uikit.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActAboutLayoutBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView appNameTv;

  @NonNull
  public final AvatarView avatarView;

  @NonNull
  public final LinearLayout checkNewVersionLayout;

  @NonNull
  public final AppCompatImageView newVersionIv;

  @NonNull
  public final LinearLayout privacyPolicyLayout;

  @NonNull
  public final LinearLayout userAgreementLayout;

  @NonNull
  public final TextView versionTv;

  private ActAboutLayoutBinding(@NonNull LinearLayout rootView, @NonNull TextView appNameTv,
      @NonNull AvatarView avatarView, @NonNull LinearLayout checkNewVersionLayout,
      @NonNull AppCompatImageView newVersionIv, @NonNull LinearLayout privacyPolicyLayout,
      @NonNull LinearLayout userAgreementLayout, @NonNull TextView versionTv) {
    this.rootView = rootView;
    this.appNameTv = appNameTv;
    this.avatarView = avatarView;
    this.checkNewVersionLayout = checkNewVersionLayout;
    this.newVersionIv = newVersionIv;
    this.privacyPolicyLayout = privacyPolicyLayout;
    this.userAgreementLayout = userAgreementLayout;
    this.versionTv = versionTv;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActAboutLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActAboutLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.act_about_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActAboutLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.appNameTv;
      TextView appNameTv = ViewBindings.findChildViewById(rootView, id);
      if (appNameTv == null) {
        break missingId;
      }

      id = R.id.avatarView;
      AvatarView avatarView = ViewBindings.findChildViewById(rootView, id);
      if (avatarView == null) {
        break missingId;
      }

      id = R.id.checkNewVersionLayout;
      LinearLayout checkNewVersionLayout = ViewBindings.findChildViewById(rootView, id);
      if (checkNewVersionLayout == null) {
        break missingId;
      }

      id = R.id.newVersionIv;
      AppCompatImageView newVersionIv = ViewBindings.findChildViewById(rootView, id);
      if (newVersionIv == null) {
        break missingId;
      }

      id = R.id.privacyPolicyLayout;
      LinearLayout privacyPolicyLayout = ViewBindings.findChildViewById(rootView, id);
      if (privacyPolicyLayout == null) {
        break missingId;
      }

      id = R.id.userAgreementLayout;
      LinearLayout userAgreementLayout = ViewBindings.findChildViewById(rootView, id);
      if (userAgreementLayout == null) {
        break missingId;
      }

      id = R.id.versionTv;
      TextView versionTv = ViewBindings.findChildViewById(rootView, id);
      if (versionTv == null) {
        break missingId;
      }

      return new ActAboutLayoutBinding((LinearLayout) rootView, appNameTv, avatarView,
          checkNewVersionLayout, newVersionIv, privacyPolicyLayout, userAgreementLayout, versionTv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}