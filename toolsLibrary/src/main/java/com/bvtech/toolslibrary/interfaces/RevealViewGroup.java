package com.bvtech.toolslibrary.interfaces;

import android.view.ViewGroup;

import com.bvtech.toolslibrary.animation.ViewRevealManager;

/**
 * Indicator for internal API that {@link ViewGroup} support
 * Circular Reveal animation
 */
public interface RevealViewGroup {

  /**
   * @return Bridge between view and circular reveal animation
   */
  ViewRevealManager getViewRevealManager();

  /**
   *
   * @param manager
   */
  void setViewRevealManager(ViewRevealManager manager);
}