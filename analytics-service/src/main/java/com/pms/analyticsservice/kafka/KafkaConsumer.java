package com.pms.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import patients.events.PatientEvent;

public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="patient", groupId="analytics-service")
    public void consumeEvent(byte[] event){
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //any business logic regarding analytics to be performed here
            log.info("Received Patient Event: [PatientId={}. PatientName={}, "+ "PatientEmail={} ]",patientEvent.getPatientId(),patientEvent.getName(),patientEvent.getEmail());
        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());

        }
    }
}
