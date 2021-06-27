package com.doriel.remotecontrol.model;

public interface Repository {
    void connect(String ipAddress, int port);
    void onAileronChanged(float value);
    void onElevatorChanged(float value);
    void onThrottleChanged(float value);
    void onRudderChanged(float value);
}
