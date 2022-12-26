package edu.stateuniversityoftelecommunications.project.service;

import edu.diploma.warehouse.schema.audit.Carton;
import edu.diploma.warehouse.schema.audit.ShipmentEnded;
import edu.stateuniversityoftelecommunications.project.Util;
import edu.stateuniversityoftelecommunications.project.domain.CartonEntity;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;
import edu.stateuniversityoftelecommunications.project.repository.CartonRepository;
import edu.stateuniversityoftelecommunications.project.repository.ProductRepository;
import edu.stateuniversityoftelecommunications.project.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.stateuniversityoftelecommunications.project.Util.getCartonEntity;
import static edu.stateuniversityoftelecommunications.project.Util.getShipmentEndedEntity;


@Service
@Slf4j
@RequiredArgsConstructor
public class DiplomaConsumer {

    public final ProductRepository productRepository;
    public final CartonRepository cartonRepository;

    public final ShipmentRepository shipmentRepository;

    @KafkaListener(topics = {"${application.topics.first}"}, groupId = "diploma-group-id")
    public void consume(ConsumerRecord<String, SpecificRecordBase > record, Acknowledgment acknowledgment) {

        if (record.value() instanceof Carton carton) {
            CartonEntity toSave = getCartonEntity(carton);
            List<ProductEntity> products = toSave.getProducts();
            for (ProductEntity product : products) {
                product.setCarton(toSave);
            }

                cartonRepository.save(toSave);
        } else if (record.value() instanceof ShipmentEnded shipmentEnded) {
            shipmentRepository.save(getShipmentEndedEntity(shipmentEnded));
        }
    }
}
