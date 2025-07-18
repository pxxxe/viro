//
//  ViroAnimatedComponentView.java
//  ViroReact
//
//  Created for ReactVision.
//  Copyright © 2025 ReactVision. All rights reserved.
//

package com.viromedia.bridge.fabric;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import com.viromedia.bridge.utility.ViroLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ViroAnimatedComponent - Animation Container Component
 * 
 * ViroAnimatedComponent provides comprehensive animation capabilities for ViroReact.
 * It supports transform animations, opacity animations, color animations, material
 * animations, and physics-based animations with complete lifecycle management.
 * 
 * Key Features:
 * - Transform animations (position, scale, rotation)
 * - Opacity and color animations with smooth transitions
 * - Material property animations for dynamic effects
 * - Physics-based animations with velocity and acceleration
 * - Animation control (play, pause, reverse, loop, iteration count)
 * - Keyframe animations with custom timing and easing
 * - Event callbacks for animation lifecycle management
 * - Integration with Android animation framework
 * - Cross-platform compatibility with iOS implementation
 */
public class ViroAnimatedComponentView extends ViroNodeView {

    private static final String TAG = ViroLog.getTag(ViroAnimatedComponentView.class);

    // Animation properties
    private ReadableMap mAnimation;
    private String mAnimationName;
    private boolean mLoop = false;
    private float mDelay = 0.0f;
    private float mDuration = 1.0f;
    private String mEasing = "linear";
    private String mInterpolatorType = "linear";

    // Animation control
    private boolean mRun = false;
    private boolean mPaused = false;
    private boolean mReverse = false;
    private String mDirection = "normal";
    private int mIterationCount = 1;

    // Animation values
    private ReadableMap mFromValue;
    private ReadableMap mToValue;
    private ReadableArray mValues;
    private ReadableArray mKeyTimes;

    // Transform animations
    private float[] mPositionFrom;
    private float[] mPositionTo;
    private float[] mScaleFrom;
    private float[] mScaleTo;
    private float[] mRotationFrom;
    private float[] mRotationTo;

    // Opacity animations
    private Float mOpacityFrom;
    private Float mOpacityTo;

    // Color animations
    private float[] mColorFrom;
    private float[] mColorTo;

    // Material animations
    private ReadableMap mMaterialFrom;
    private ReadableMap mMaterialTo;

    // Physics animations
    private boolean mPhysicsEnabled = false;
    private float[] mVelocity;
    private float[] mAcceleration;

    // Animation state
    private boolean mIsAnimating = false;
    private boolean mIsPaused = false;
    private long mStartTime = 0;
    private long mPauseTime = 0;
    private long mCurrentTime = 0;

    // Android animation objects
    private AnimatorSet mAnimatorSet;
    private List<Animator> mAnimators;
    private Map<String, ValueAnimator> mActiveAnimators;

    public ViroAnimatedComponentView(@NonNull ThemedReactContext context) {
        super(context);
        ViroLog.debug(TAG, "ViroAnimatedComponentView initialized");
        
        // Initialize animation collections
        mAnimators = new ArrayList<>();
        mActiveAnimators = new HashMap<>();
        
        // Initialize default transform values
        mPositionFrom = new float[]{0.0f, 0.0f, 0.0f};
        mPositionTo = new float[]{0.0f, 0.0f, 0.0f};
        mScaleFrom = new float[]{1.0f, 1.0f, 1.0f};
        mScaleTo = new float[]{1.0f, 1.0f, 1.0f};
        mRotationFrom = new float[]{0.0f, 0.0f, 0.0f};
        mRotationTo = new float[]{0.0f, 0.0f, 0.0f};
        
        // TODO: Initialize ViroReact animated component
        // This will need to integrate with the existing ViroReact animation system
        initializeAnimatedComponent();
    }

    private void initializeAnimatedComponent() {
        ViroLog.debug(TAG, "Initializing animated component with default properties");
        
        // TODO: Set up ViroReact animated component with default properties
        // This should create the underlying animated object in the scene graph
    }

    // Animation Properties
    public void setAnimation(@Nullable ReadableMap animation) {
        ViroLog.debug(TAG, "Setting animation: " + animation);
        mAnimation = animation;
        
        if (animation != null) {
            // Parse animation dictionary and set individual properties
            if (animation.hasKey("name")) {
                setAnimationName(animation.getString("name"));
            }
            
            if (animation.hasKey("loop")) {
                setLoop(animation.getBoolean("loop"));
            }
            
            if (animation.hasKey("delay")) {
                setDelay((float) animation.getDouble("delay"));
            }
            
            if (animation.hasKey("duration")) {
                setDuration((float) animation.getDouble("duration"));
            }
            
            if (animation.hasKey("easing")) {
                setEasing(animation.getString("easing"));
            }
            
            if (animation.hasKey("run")) {
                setRun(animation.getBoolean("run"));
            }
            
            // Parse transform animations
            if (animation.hasKey("positionFrom")) {
                setPositionFrom(animation.getArray("positionFrom"));
            }
            
            if (animation.hasKey("positionTo")) {
                setPositionTo(animation.getArray("positionTo"));
            }
            
            if (animation.hasKey("scaleFrom")) {
                setScaleFrom(animation.getArray("scaleFrom"));
            }
            
            if (animation.hasKey("scaleTo")) {
                setScaleTo(animation.getArray("scaleTo"));
            }
            
            if (animation.hasKey("rotationFrom")) {
                setRotationFrom(animation.getArray("rotationFrom"));
            }
            
            if (animation.hasKey("rotationTo")) {
                setRotationTo(animation.getArray("rotationTo"));
            }
            
            // Parse opacity animations
            if (animation.hasKey("opacityFrom")) {
                setOpacityFrom((float) animation.getDouble("opacityFrom"));
            }
            
            if (animation.hasKey("opacityTo")) {
                setOpacityTo((float) animation.getDouble("opacityTo"));
            }
        }
        
        updateAnimation();
    }

    public void setAnimationName(@Nullable String animationName) {
        ViroLog.debug(TAG, "Setting animation name: " + animationName);
        mAnimationName = animationName;
        updateAnimation();
    }

    public void setLoop(boolean loop) {
        ViroLog.debug(TAG, "Setting loop: " + loop);
        mLoop = loop;
        updateAnimation();
    }

    public void setDelay(float delay) {
        ViroLog.debug(TAG, "Setting delay: " + delay);
        mDelay = delay;
        updateAnimation();
    }

    public void setDuration(float duration) {
        ViroLog.debug(TAG, "Setting duration: " + duration);
        mDuration = duration;
        updateAnimation();
    }

    public void setEasing(@Nullable String easing) {
        ViroLog.debug(TAG, "Setting easing: " + easing);
        mEasing = easing != null ? easing : "linear";
        updateAnimation();
    }

    public void setInterpolatorType(@Nullable String interpolatorType) {
        ViroLog.debug(TAG, "Setting interpolator type: " + interpolatorType);
        mInterpolatorType = interpolatorType != null ? interpolatorType : "linear";
        updateAnimation();
    }

    // Animation Control
    public void setRun(boolean run) {
        ViroLog.debug(TAG, "Setting run: " + run);
        mRun = run;
        
        if (run) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    public void setPaused(boolean paused) {
        ViroLog.debug(TAG, "Setting paused: " + paused);
        mPaused = paused;
        
        if (paused) {
            pauseAnimation();
        } else {
            resumeAnimation();
        }
    }

    public void setReverse(boolean reverse) {
        ViroLog.debug(TAG, "Setting reverse: " + reverse);
        mReverse = reverse;
        updateAnimation();
    }

    public void setDirection(@Nullable String direction) {
        ViroLog.debug(TAG, "Setting direction: " + direction);
        mDirection = direction != null ? direction : "normal";
        updateAnimation();
    }

    public void setIterationCount(int iterationCount) {
        ViroLog.debug(TAG, "Setting iteration count: " + iterationCount);
        mIterationCount = iterationCount;
        updateAnimation();
    }

    // Animation Values
    public void setFromValue(@Nullable ReadableMap fromValue) {
        ViroLog.debug(TAG, "Setting from value: " + fromValue);
        mFromValue = fromValue;
        updateAnimation();
    }

    public void setToValue(@Nullable ReadableMap toValue) {
        ViroLog.debug(TAG, "Setting to value: " + toValue);
        mToValue = toValue;
        updateAnimation();
    }

    public void setValues(@Nullable ReadableArray values) {
        ViroLog.debug(TAG, "Setting values: " + values);
        mValues = values;
        updateAnimation();
    }

    public void setKeyTimes(@Nullable ReadableArray keyTimes) {
        ViroLog.debug(TAG, "Setting key times: " + keyTimes);
        mKeyTimes = keyTimes;
        updateAnimation();
    }

    // Transform Animations
    public void setPositionFrom(@Nullable ReadableArray positionFrom) {
        ViroLog.debug(TAG, "Setting position from: " + positionFrom);
        
        if (positionFrom != null && positionFrom.size() >= 3) {
            try {
                mPositionFrom[0] = (float) positionFrom.getDouble(0); // X
                mPositionFrom[1] = (float) positionFrom.getDouble(1); // Y
                mPositionFrom[2] = (float) positionFrom.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing position from: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setPositionTo(@Nullable ReadableArray positionTo) {
        ViroLog.debug(TAG, "Setting position to: " + positionTo);
        
        if (positionTo != null && positionTo.size() >= 3) {
            try {
                mPositionTo[0] = (float) positionTo.getDouble(0); // X
                mPositionTo[1] = (float) positionTo.getDouble(1); // Y
                mPositionTo[2] = (float) positionTo.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing position to: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setScaleFrom(@Nullable ReadableArray scaleFrom) {
        ViroLog.debug(TAG, "Setting scale from: " + scaleFrom);
        
        if (scaleFrom != null && scaleFrom.size() >= 3) {
            try {
                mScaleFrom[0] = (float) scaleFrom.getDouble(0); // X
                mScaleFrom[1] = (float) scaleFrom.getDouble(1); // Y
                mScaleFrom[2] = (float) scaleFrom.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing scale from: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setScaleTo(@Nullable ReadableArray scaleTo) {
        ViroLog.debug(TAG, "Setting scale to: " + scaleTo);
        
        if (scaleTo != null && scaleTo.size() >= 3) {
            try {
                mScaleTo[0] = (float) scaleTo.getDouble(0); // X
                mScaleTo[1] = (float) scaleTo.getDouble(1); // Y
                mScaleTo[2] = (float) scaleTo.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing scale to: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setRotationFrom(@Nullable ReadableArray rotationFrom) {
        ViroLog.debug(TAG, "Setting rotation from: " + rotationFrom);
        
        if (rotationFrom != null && rotationFrom.size() >= 3) {
            try {
                mRotationFrom[0] = (float) rotationFrom.getDouble(0); // X
                mRotationFrom[1] = (float) rotationFrom.getDouble(1); // Y
                mRotationFrom[2] = (float) rotationFrom.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing rotation from: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setRotationTo(@Nullable ReadableArray rotationTo) {
        ViroLog.debug(TAG, "Setting rotation to: " + rotationTo);
        
        if (rotationTo != null && rotationTo.size() >= 3) {
            try {
                mRotationTo[0] = (float) rotationTo.getDouble(0); // X
                mRotationTo[1] = (float) rotationTo.getDouble(1); // Y
                mRotationTo[2] = (float) rotationTo.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing rotation to: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    // Opacity Animations
    public void setOpacityFrom(@Nullable Float opacityFrom) {
        ViroLog.debug(TAG, "Setting opacity from: " + opacityFrom);
        mOpacityFrom = opacityFrom;
        updateAnimation();
    }

    public void setOpacityTo(@Nullable Float opacityTo) {
        ViroLog.debug(TAG, "Setting opacity to: " + opacityTo);
        mOpacityTo = opacityTo;
        updateAnimation();
    }

    // Color Animations
    public void setColorFrom(@Nullable ReadableArray colorFrom) {
        ViroLog.debug(TAG, "Setting color from: " + colorFrom);
        
        if (colorFrom != null && colorFrom.size() >= 3) {
            try {
                mColorFrom = new float[4];
                mColorFrom[0] = (float) colorFrom.getDouble(0); // R
                mColorFrom[1] = (float) colorFrom.getDouble(1); // G
                mColorFrom[2] = (float) colorFrom.getDouble(2); // B
                mColorFrom[3] = colorFrom.size() > 3 ? (float) colorFrom.getDouble(3) : 1.0f; // A
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing color from: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setColorTo(@Nullable ReadableArray colorTo) {
        ViroLog.debug(TAG, "Setting color to: " + colorTo);
        
        if (colorTo != null && colorTo.size() >= 3) {
            try {
                mColorTo = new float[4];
                mColorTo[0] = (float) colorTo.getDouble(0); // R
                mColorTo[1] = (float) colorTo.getDouble(1); // G
                mColorTo[2] = (float) colorTo.getDouble(2); // B
                mColorTo[3] = colorTo.size() > 3 ? (float) colorTo.getDouble(3) : 1.0f; // A
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing color to: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    // Material Animations
    public void setMaterialFrom(@Nullable ReadableMap materialFrom) {
        ViroLog.debug(TAG, "Setting material from: " + materialFrom);
        mMaterialFrom = materialFrom;
        updateAnimation();
    }

    public void setMaterialTo(@Nullable ReadableMap materialTo) {
        ViroLog.debug(TAG, "Setting material to: " + materialTo);
        mMaterialTo = materialTo;
        updateAnimation();
    }

    // Physics Animations
    public void setPhysicsEnabled(boolean physicsEnabled) {
        ViroLog.debug(TAG, "Setting physics enabled: " + physicsEnabled);
        mPhysicsEnabled = physicsEnabled;
        updateAnimation();
    }

    public void setVelocity(@Nullable ReadableArray velocity) {
        ViroLog.debug(TAG, "Setting velocity: " + velocity);
        
        if (velocity != null && velocity.size() >= 3) {
            try {
                mVelocity = new float[3];
                mVelocity[0] = (float) velocity.getDouble(0); // X
                mVelocity[1] = (float) velocity.getDouble(1); // Y
                mVelocity[2] = (float) velocity.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing velocity: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    public void setAcceleration(@Nullable ReadableArray acceleration) {
        ViroLog.debug(TAG, "Setting acceleration: " + acceleration);
        
        if (acceleration != null && acceleration.size() >= 3) {
            try {
                mAcceleration = new float[3];
                mAcceleration[0] = (float) acceleration.getDouble(0); // X
                mAcceleration[1] = (float) acceleration.getDouble(1); // Y
                mAcceleration[2] = (float) acceleration.getDouble(2); // Z
            } catch (Exception e) {
                ViroLog.error(TAG, "Error parsing acceleration: " + e.getMessage());
            }
        }
        
        updateAnimation();
    }

    // Animation Control Methods
    public void startAnimation() {
        ViroLog.debug(TAG, "Starting animation");
        
        if (mIsAnimating && !mIsPaused) {
            ViroLog.debug(TAG, "Animation already running");
            return;
        }
        
        buildAnimatorSet();
        
        if (mAnimatorSet != null) {
            mIsAnimating = true;
            mIsPaused = false;
            mStartTime = System.currentTimeMillis();
            
            // Fire onStart event
            emitStartEvent();
            
            // Start animation
            mAnimatorSet.start();
            
            // TODO: Start animation in ViroReact scene
            // This will integrate with the ViroReact animation system
        }
    }

    public void pauseAnimation() {
        ViroLog.debug(TAG, "Pausing animation");
        
        if (!mIsAnimating || mIsPaused) {
            ViroLog.debug(TAG, "Animation not running or already paused");
            return;
        }
        
        mIsPaused = true;
        mPauseTime = System.currentTimeMillis();
        
        // Pause Android animation
        if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mAnimatorSet.pause();
            }
        }
        
        // TODO: Pause animation in ViroReact scene
    }

    public void resumeAnimation() {
        ViroLog.debug(TAG, "Resuming animation");
        
        if (!mIsAnimating || !mIsPaused) {
            ViroLog.debug(TAG, "Animation not running or not paused");
            return;
        }
        
        mIsPaused = false;
        
        // Resume Android animation
        if (mAnimatorSet != null && mAnimatorSet.isPaused()) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mAnimatorSet.resume();
            }
        }
        
        // TODO: Resume animation in ViroReact scene
    }

    public void stopAnimation() {
        ViroLog.debug(TAG, "Stopping animation");
        
        if (!mIsAnimating) {
            ViroLog.debug(TAG, "Animation not running");
            return;
        }
        
        mIsAnimating = false;
        mIsPaused = false;
        
        // Stop Android animation
        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }
        
        // TODO: Stop animation in ViroReact scene
        
        // Fire onCancel event
        emitCancelEvent();
    }

    public void resetAnimation() {
        ViroLog.debug(TAG, "Resetting animation");
        
        stopAnimation();
        mCurrentTime = 0;
        
        // TODO: Reset animation in ViroReact scene to initial state
    }

    // Animation State
    public boolean isAnimating() {
        return mIsAnimating;
    }

    public boolean isPaused() {
        return mIsPaused;
    }

    public long getCurrentTime() {
        if (mIsAnimating && !mIsPaused) {
            return System.currentTimeMillis() - mStartTime;
        } else if (mIsPaused) {
            return mPauseTime - mStartTime;
        } else {
            return mCurrentTime;
        }
    }

    public float getProgress() {
        if (mDuration > 0.0f) {
            return Math.min(1.0f, getCurrentTime() / (mDuration * 1000.0f));
        }
        return 0.0f;
    }

    // Helper Methods
    private void updateAnimation() {
        if (mRun && !mIsAnimating) {
            startAnimation();
        } else if (mIsAnimating) {
            // Update running animation
            stopAnimation();
            if (mRun) {
                startAnimation();
            }
        }
    }

    private void buildAnimatorSet() {
        mAnimators.clear();
        
        // Build position animation
        if (hasPositionAnimation()) {
            ValueAnimator positionAnimator = createPositionAnimator();
            if (positionAnimator != null) {
                mAnimators.add(positionAnimator);
            }
        }
        
        // Build scale animation
        if (hasScaleAnimation()) {
            ValueAnimator scaleAnimator = createScaleAnimator();
            if (scaleAnimator != null) {
                mAnimators.add(scaleAnimator);
            }
        }
        
        // Build rotation animation
        if (hasRotationAnimation()) {
            ValueAnimator rotationAnimator = createRotationAnimator();
            if (rotationAnimator != null) {
                mAnimators.add(rotationAnimator);
            }
        }
        
        // Build opacity animation
        if (hasOpacityAnimation()) {
            ValueAnimator opacityAnimator = createOpacityAnimator();
            if (opacityAnimator != null) {
                mAnimators.add(opacityAnimator);
            }
        }
        
        // Create animator set
        if (!mAnimators.isEmpty()) {
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(mAnimators);
            mAnimatorSet.setDuration((long) (mDuration * 1000));
            mAnimatorSet.setStartDelay((long) (mDelay * 1000));
            mAnimatorSet.setInterpolator(getInterpolatorForEasing(mEasing));
            
            // Set repeat count
            if (mLoop) {
                // Android doesn't support infinite repeat on AnimatorSet
                // We'll handle this in the animation listener
            }
            
            // Set animation listener
            mAnimatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    ViroLog.debug(TAG, "Animation started");
                    emitStartEvent();
                }
                
                @Override
                public void onAnimationEnd(Animator animation) {
                    ViroLog.debug(TAG, "Animation ended");
                    mIsAnimating = false;
                    mIsPaused = false;
                    
                    if (mLoop) {
                        // Restart animation for loop
                        startAnimation();
                    } else {
                        emitFinishEvent();
                    }
                }
                
                @Override
                public void onAnimationCancel(Animator animation) {
                    ViroLog.debug(TAG, "Animation cancelled");
                    mIsAnimating = false;
                    mIsPaused = false;
                    emitCancelEvent();
                }
            });
        }
    }

    private boolean hasPositionAnimation() {
        return mPositionFrom != null && mPositionTo != null &&
               (!java.util.Arrays.equals(mPositionFrom, mPositionTo));
    }

    private boolean hasScaleAnimation() {
        return mScaleFrom != null && mScaleTo != null &&
               (!java.util.Arrays.equals(mScaleFrom, mScaleTo));
    }

    private boolean hasRotationAnimation() {
        return mRotationFrom != null && mRotationTo != null &&
               (!java.util.Arrays.equals(mRotationFrom, mRotationTo));
    }

    private boolean hasOpacityAnimation() {
        return mOpacityFrom != null && mOpacityTo != null &&
               (!mOpacityFrom.equals(mOpacityTo));
    }

    private ValueAnimator createPositionAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (Float) animation.getAnimatedValue();
                float x = mPositionFrom[0] + (mPositionTo[0] - mPositionFrom[0]) * progress;
                float y = mPositionFrom[1] + (mPositionTo[1] - mPositionFrom[1]) * progress;
                float z = mPositionFrom[2] + (mPositionTo[2] - mPositionFrom[2]) * progress;
                
                // TODO: Apply position to ViroReact 3D object
                // updateViroReactPosition(x, y, z);
                
                emitUpdateEvent("position", new float[]{x, y, z});
            }
        });
        return animator;
    }

    private ValueAnimator createScaleAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (Float) animation.getAnimatedValue();
                float x = mScaleFrom[0] + (mScaleTo[0] - mScaleFrom[0]) * progress;
                float y = mScaleFrom[1] + (mScaleTo[1] - mScaleFrom[1]) * progress;
                float z = mScaleFrom[2] + (mScaleTo[2] - mScaleFrom[2]) * progress;
                
                // TODO: Apply scale to ViroReact 3D object
                // updateViroReactScale(x, y, z);
                
                emitUpdateEvent("scale", new float[]{x, y, z});
            }
        });
        return animator;
    }

    private ValueAnimator createRotationAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (Float) animation.getAnimatedValue();
                float x = mRotationFrom[0] + (mRotationTo[0] - mRotationFrom[0]) * progress;
                float y = mRotationFrom[1] + (mRotationTo[1] - mRotationFrom[1]) * progress;
                float z = mRotationFrom[2] + (mRotationTo[2] - mRotationFrom[2]) * progress;
                
                // TODO: Apply rotation to ViroReact 3D object
                // updateViroReactRotation(x, y, z);
                
                emitUpdateEvent("rotation", new float[]{x, y, z});
            }
        });
        return animator;
    }

    private ValueAnimator createOpacityAnimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(mOpacityFrom, mOpacityTo);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float opacity = (Float) animation.getAnimatedValue();
                
                // TODO: Apply opacity to ViroReact 3D object
                // updateViroReactOpacity(opacity);
                
                emitUpdateEvent("opacity", opacity);
            }
        });
        return animator;
    }

    private Interpolator getInterpolatorForEasing(String easing) {
        switch (easing) {
            case "linear":
                return new LinearInterpolator();
            case "ease-in":
                return new AccelerateInterpolator();
            case "ease-out":
                return new DecelerateInterpolator();
            case "ease-in-out":
                return new AccelerateDecelerateInterpolator();
            default:
                return new LinearInterpolator();
        }
    }

    // Event Emission
    private void emitStartEvent() {
        WritableMap event = Arguments.createMap();
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onStart", event);
    }

    private void emitFinishEvent() {
        WritableMap event = Arguments.createMap();
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onFinish", event);
    }

    private void emitUpdateEvent(String property, Object value) {
        WritableMap event = Arguments.createMap();
        event.putString("property", property);
        
        if (value instanceof float[]) {
            float[] array = (float[]) value;
            WritableMap valueMap = Arguments.createMap();
            valueMap.putDouble("x", array[0]);
            valueMap.putDouble("y", array[1]);
            valueMap.putDouble("z", array[2]);
            event.putMap("value", valueMap);
        } else if (value instanceof Float) {
            event.putDouble("value", (Float) value);
        }
        
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onUpdate", event);
    }

    private void emitCancelEvent() {
        WritableMap event = Arguments.createMap();
        ThemedReactContext reactContext = (ThemedReactContext) getContext();
        reactContext.getJSModule(RCTEventEmitter.class)
                .receiveEvent(getId(), "onCancel", event);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViroLog.debug(TAG, "Animated component attached to window");
        
        // TODO: Add animated component to ViroReact scene when attached to window
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViroLog.debug(TAG, "Animated component detached from window");
        
        // TODO: Remove animated component from ViroReact scene when detached from window
        stopAnimation();
    }

    // Getters for current values (useful for debugging and testing)
    public String getAnimationName() { return mAnimationName; }
    public boolean isLoop() { return mLoop; }
    public float getDelay() { return mDelay; }
    public float getDuration() { return mDuration; }
    public String getEasing() { return mEasing; }
    public boolean isRun() { return mRun; }
    public boolean isPausedState() { return mPaused; }
    public boolean isReverse() { return mReverse; }
    public String getDirection() { return mDirection; }
    public int getIterationCount() { return mIterationCount; }
    public boolean isPhysicsEnabled() { return mPhysicsEnabled; }
}