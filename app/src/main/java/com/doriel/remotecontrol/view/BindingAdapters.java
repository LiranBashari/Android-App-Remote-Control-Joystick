package com.doriel.remotecontrol.view;

import android.util.Log;

import com.google.android.material.slider.Slider;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    // https://stackoverflow.com/questions/59924065/databind-slider-in-xml
    // https://developer.android.com/topic/libraries/data-binding/binding-adapters
    @BindingAdapter("onValueChangeListener")
    public static void setOnValueChangeListener(Slider slider, OnValueChangeListener listener) {
        slider.addOnChangeListener((slider1, value, fromUser) -> listener.onValueChanged(value));
    }

    public interface OnValueChangeListener {
        void onValueChanged(float value);
    }

    @BindingAdapter("onJoystickChangeListener")
    public static void setOnJoystickChangeListener(Joystick joystick, OnJoystickChangeListener listener) {
        Joystick.JoystickListener l = new Joystick.JoystickListener() {
            @Override
            public void onMove(float x, float y) {
                Log.d("HEY", "got x=" + x + ", y=" + y);
                listener.onValueChanged(x, y);
            }
        };

        joystick.setOnMoveListener(l);
    }

    public interface OnJoystickChangeListener {
        void onValueChanged(float x, float y);
    }
}
