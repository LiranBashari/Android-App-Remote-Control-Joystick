package com.doriel.remotecontrol.viewmodel;

public interface ViewModel {
    void onIpChanged(CharSequence ipAddress);
    void onPortChanged(CharSequence port);
    void connect();

    void onThrottleChanged(float value);
    void onRudderChanged(float value);

    void onJoystickMove(float x, float y);
}
