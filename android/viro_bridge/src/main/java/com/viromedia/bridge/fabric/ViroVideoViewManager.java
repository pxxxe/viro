//
//  ViroVideoViewManager.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.ViroVideoManagerInterface;
import com.facebook.react.viewmanagers.ViroVideoManagerDelegate;

import com.viromedia.bridge.utility.ViroLog;

import java.util.Map;

/**
 * ViewManager for ViroVideo - Video playback in ViroReact
 * 
 * ViroVideo provides comprehensive video playback capabilities in 3D space,
 * supporting various video formats, streaming sources, and immersive experiences.
 * It can display standard videos, 360° videos, and serves as video textures
 * for 3D geometry and materials.
 */
public class ViroVideoViewManager extends SimpleViewManager<ViroVideoView>
        implements ViroVideoManagerInterface<ViroVideoView> {

    public static final String REACT_CLASS = "ViroVideo";
    private final ViewManagerDelegate<ViroVideoView> mDelegate;

    public ViroVideoViewManager() {
        mDelegate = new ViroVideoManagerDelegate<>(this);
    }

    @Override
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    @NonNull
    public ViroVideoView createViewInstance(@NonNull ThemedReactContext context) {
        ViroLog.debug(REACT_CLASS, "Creating ViroVideo instance");
        return new ViroVideoView(context);
    }

    @Override
    public ViewManagerDelegate<ViroVideoView> getDelegate() {
        return mDelegate;
    }

    // Video Source Properties
    @Override
    @ReactProp(name = "source")
    public void setSource(ViroVideoView view, @Nullable ReadableMap source) {
        ViroLog.debug(REACT_CLASS, "Setting source: " + source);
        view.setSource(source);
    }

    @Override
    @ReactProp(name = "uri")
    public void setUri(ViroVideoView view, @Nullable String uri) {
        ViroLog.debug(REACT_CLASS, "Setting URI: " + uri);
        view.setUri(uri);
    }

    // Video Playback Control
    @Override
    @ReactProp(name = "loop", defaultBoolean = false)
    public void setLoop(ViroVideoView view, boolean loop) {
        ViroLog.debug(REACT_CLASS, "Setting loop: " + loop);
        view.setLoop(loop);
    }

    @Override
    @ReactProp(name = "muted", defaultBoolean = false)
    public void setMuted(ViroVideoView view, boolean muted) {
        ViroLog.debug(REACT_CLASS, "Setting muted: " + muted);
        view.setMuted(muted);
    }

    @Override
    @ReactProp(name = "volume", defaultFloat = 1.0f)
    public void setVolume(ViroVideoView view, float volume) {
        ViroLog.debug(REACT_CLASS, "Setting volume: " + volume);
        view.setVolume(volume);
    }

    @Override
    @ReactProp(name = "paused", defaultBoolean = false)
    public void setPaused(ViroVideoView view, boolean paused) {
        ViroLog.debug(REACT_CLASS, "Setting paused: " + paused);
        view.setPaused(paused);
    }

    // Video Display Properties
    @Override
    @ReactProp(name = "width", defaultFloat = 1.0f)
    public void setWidth(ViroVideoView view, float width) {
        ViroLog.debug(REACT_CLASS, "Setting width: " + width);
        view.setVideoWidth(width);
    }

    @Override
    @ReactProp(name = "height", defaultFloat = 1.0f)
    public void setHeight(ViroVideoView view, float height) {
        ViroLog.debug(REACT_CLASS, "Setting height: " + height);
        view.setVideoHeight(height);
    }

    @Override
    @ReactProp(name = "resizeMode")
    public void setResizeMode(ViroVideoView view, @Nullable String resizeMode) {
        ViroLog.debug(REACT_CLASS, "Setting resize mode: " + resizeMode);
        view.setResizeMode(resizeMode != null ? resizeMode : "scaleAspectFit");
    }

    @Override
    @ReactProp(name = "rotation")
    public void setRotation(ViroVideoView view, @Nullable ReadableArray rotation) {
        ViroLog.debug(REACT_CLASS, "Setting rotation: " + rotation);
        view.setRotation(rotation);
    }

    // Video Material Properties
    @Override
    @ReactProp(name = "materials")
    public void setMaterials(ViroVideoView view, @Nullable ReadableArray materials) {
        ViroLog.debug(REACT_CLASS, "Setting materials: " + materials);
        view.setMaterials(materials);
    }

    // Video Quality and Performance
    @Override
    @ReactProp(name = "playbackRate", defaultFloat = 1.0f)
    public void setPlaybackRate(ViroVideoView view, float playbackRate) {
        ViroLog.debug(REACT_CLASS, "Setting playback rate: " + playbackRate);
        view.setPlaybackRate(playbackRate);
    }

    @Override
    @ReactProp(name = "stereoMode")
    public void setStereoMode(ViroVideoView view, @Nullable String stereoMode) {
        ViroLog.debug(REACT_CLASS, "Setting stereo mode: " + stereoMode);
        view.setStereoMode(stereoMode != null ? stereoMode : "none");
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onVideoLoadStart", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoLoadStart")))
                .put("onVideoLoad", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoLoad")))
                .put("onVideoProgress", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoProgress")))
                .put("onVideoEnd", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoEnd")))
                .put("onVideoError", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoError")))
                .put("onVideoBuffer", MapBuilder.of("phasedRegistrationNames",
                        MapBuilder.of("bubbled", "onVideoBuffer")))
                .build();
    }

    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onVideoSeekComplete", MapBuilder.of("registrationName", "onVideoSeekComplete"))
                .build();
    }

    @Override
    public void receiveCommand(ViroVideoView view, String commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case "seekToTime":
                if (args != null && args.size() > 0) {
                    float seconds = (float) args.getDouble(0);
                    view.seekToTime(seconds);
                }
                break;
            case "play":
                view.play();
                break;
            case "pause":
                view.pause();
                break;
            case "stop":
                view.stop();
                break;
            default:
                super.receiveCommand(view, commandId, args);
                break;
        }
    }
}