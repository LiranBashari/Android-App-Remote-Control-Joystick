package com.doriel.remotecontrol.viewmodel;

import com.doriel.remotecontrol.model.Repository;

public class RemoteControlViewModel implements ViewModel {
    private final Repository repository;

    private CharSequence ipAddress = "";
    private CharSequence port = "";

    public RemoteControlViewModel(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void onIpChanged(CharSequence ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public void onPortChanged(CharSequence port) {
        this.port = port;
    }

    @Override
    public void connect() {
        repository.connect(ipAddress.toString(), Integer.parseInt(port.toString()));
    }

    @Override
    public void onThrottleChanged(float value) {
        repository.onThrottleChanged(value);
    }

    @Override
    public void onRudderChanged(float value) {
        repository.onRudderChanged(value);
    }

    @Override
    public void onJoystickMove(float x, float y) {
        repository.onAileronChanged(x);
        repository.onElevatorChanged(y);
    }
}
