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
    private Date orderDate; // මෙතන String තිබුණොත් අයින් කරලා Date (java.sql.Date) දාන්න
    private String custID;
}