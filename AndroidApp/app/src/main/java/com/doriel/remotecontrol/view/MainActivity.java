package com.doriel.remotecontrol.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.doriel.remotecontrol.R;
import com.doriel.remotecontrol.databinding.ActivityMainBinding;
import com.doriel.remotecontrol.model.RemoteControlRepository;
import com.doriel.remotecontrol.viewmodel.RemoteControlViewModel;

public class MainActivity extends AppCompatActivity {
    RemoteControlViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        RemoteControlRepository repository = new RemoteControlRepository();
        this.viewModel = new RemoteControlViewModel(repository);
        binding.setViewModel(viewModel);
    }
}