package com.pms.patientservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.pms.patientservice.dto.PatientResponseDTO;
import com.pms.patientservice.mapper.PatientMapper;
import com.pms.patientservice.model.Patient;
import com.pms.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    //dependency injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients =  patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toPatientResponseDTO).toList();

        return patientResponseDTOs;
    }
}
