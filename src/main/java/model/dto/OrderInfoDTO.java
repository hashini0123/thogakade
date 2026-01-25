package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderInfoDTO {
    private String orderID;
    private Date orderDate;
    private String custID;

    public OrderInfoDTO(String text, String text1, String text2) {
    }
}