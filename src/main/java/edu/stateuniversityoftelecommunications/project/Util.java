package edu.stateuniversityoftelecommunications.project;


import edu.diploma.warehouse.schema.audit.Product;
import edu.diploma.warehouse.schema.audit.Carton;
import edu.diploma.warehouse.schema.audit.ShipmentEnded;
import edu.stateuniversityoftelecommunications.project.domain.CartonEntity;
import edu.stateuniversityoftelecommunications.project.domain.ProductEntity;
import edu.stateuniversityoftelecommunications.project.domain.ShipmentEndedEntity;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public static ProductEntity productEntity(Product product) {
        return ProductEntity.builder()
                .productName(valueOf(product.getProductName()))
                .productSku(valueOf(product.getProductSku()))
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static ShipmentEndedEntity getShipmentEndedEntity(ShipmentEnded shipmentEnded) {
        return ShipmentEndedEntity.builder()
                .orderNumber(valueOf(shipmentEnded.getOrderNumber()))
                .build();
    }

    public static CartonEntity getCartonEntity(Carton carton) {
        return CartonEntity.builder()
                .orderNumber(valueOf(carton.getOrderNumber()))
                .productSku(valueOf(carton.getProductSku()))
                .products(getProductEntities(carton.getProducts()))
                .build();
    }

    public static List<ProductEntity> getProductEntities(List<Product> products) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (Product product : products) {
            productEntities.add(getProductEntity(product));
        }
        return productEntities;
    }

    public static ProductEntity getProductEntity(Product product) {
        return ProductEntity.builder()
                .productName(valueOf(product.getProductName()))
                .productSku(valueOf(product.getProductSku()))
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static String valueOf(CharSequence sequence) {
        return sequence == null ?  null : String.valueOf(sequence);
    }

}
