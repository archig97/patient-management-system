package com.pms.patientservice.mapper;

import com.pms.patientservice.dto.PatientResponseDTO;
import com.pms.patientservice.model.Patient;

public class PatientMapper {

    //mapper class helps by keeping all this logic out of the service layer
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setBirthDate(patient.getBirthDate().toString());

        return patientResponseDTO;
    }
}
