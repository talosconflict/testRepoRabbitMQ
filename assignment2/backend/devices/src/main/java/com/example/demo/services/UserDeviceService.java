package com.example.demo.services;

import com.example.demo.entities.UserDevice;
import com.example.demo.repositories.UserDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDeviceService {

    @Autowired
    private UserDeviceRepository userDeviceRepository;

    public List<UserDevice> getAllUserDevices() {
        return userDeviceRepository.findAll();
    }
}

