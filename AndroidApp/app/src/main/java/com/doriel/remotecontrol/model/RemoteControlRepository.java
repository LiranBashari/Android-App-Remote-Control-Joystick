package com.doriel.remotecontrol.model;

public class RemoteControlRepository implements Repository {
    private final NetworkClient networkClient;

    public RemoteControlRepository() {
        this.networkClient = new NetworkClient();
    }

    @Override
    public void connect(String ipAddress, int port) {
        networkClient.connect(ipAddress, port);
    }

    @Override
    public void onAileronChanged(float value) {
        sendCommand("set /controls[0]/flight[0]/aileron[0] " + value);
    }

    @Override
    public void onElevatorChanged(float value) {
        sendCommand("set /controls[0]/flight[0]/elevator[0] " + value);
    }

    @Override
    public void onThrottleChanged(float value) {
        sendCommand("set /controls[0]/engines[0]/current-engine[0]/throttle " + value);
    }

    @Override
    public void onRudderChanged(float value) {
        sendCommand("set /controls[0]/flight[0]/rudder[0] " + value);
    }

    private void sendCommand(String command) {
        networkClient.sendCommand(command);
    }
}
