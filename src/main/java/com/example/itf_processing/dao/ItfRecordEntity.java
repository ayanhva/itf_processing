package com.example.itf_processing.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itf_records")
public class ItfRecordEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TRN_CODE")
    private String TRN_CODE;    //operation_number

    @Column(name = "TRN_CODE_QUALIFIER")
    private String TRN_CODE_QUALIFIER;             //operation_type

    @Column(name = "TRN_COMPONENT_SEQ_NUM")
    private String TRN_COMPONENT_SEQ_NUM;             //operation_date

    @Column(name = "DESTINATION_BIN")
    private String DESTINATION_BIN;             //operation_amount1

    @Column(name = "SOURCE_BIN")
    private String SOURCE_BIN;               //operation_d_c (debit/credit)

    @Column(name = "REPORT_IDENTIFIER")
    private String REPORT_IDENTIFIER;         //operation_currency1

    @Column(name = "REPORT_LINE_SEQ_NUM")
    private String REPORT_LINE_SEQ_NUM;    //operation_account

    @Column(name = "ACQUIRER_BIN")
    private String ACQUIRER_BIN;     //operation_approval_code

    @Column(name = "RECORD_IDENTIFIER")
    private String RECORD_IDENTIFIER;         //operation_post_date

    @Column(name = "TRN_CODE_CLEARED")
    private String TRN_CODE_CLEARED;       //operation_card_number

    @Column(name = "TRN_CODE_QUALIFIER_CLEARED")
    private String TRN_CODE_QUALIFIER_CLEARED;        //operation_currency2

    @Column(name = "ACCOUNT_NUMBER")
    private String ACCOUNT_NUMBER;          //operation_amount2

    @Column(name = "ACCOUNT_NUMBER_EXTENSION")
    private String ACCOUNT_NUMBER_EXTENSION;    //operation_111_index_value

    @Column(name = "POSITIVE_CARDH_AUTH_SERVC_IND")
    private String POSITIVE_CARDH_AUTH_SERVC_IND;       //operation_merchant_id

    @Column(name = "ACQUIRER_REFERENCE_NUMBER")
    private String ACQUIRER_REFERENCE_NUMBER;      //operation_description

    @Column(name = "ACQUIRERS_BUSINESS_ID")
    private String ACQUIRERS_BUSINESS_ID;          //operation_amount3

    @Column(name = "CARD_ACCEPTOR_NAME")
    private String CARD_ACCEPTOR_NAME;        //operation_currency3

    @Column(name = "PURCHASE_DATE")
    private String PURCHASE_DATE;          //operation_cnv_date

    @Column(name = "ACQUIRER_IRF_DESCRIPTOR")
    private String ACQUIRER_IRF_DESCRIPTOR;          //operation_country

    @Column(name = "RESERVED")
    private String RESERVED;             //operation_time

    @Column(name = "REIMBURSEMENT_ATTRIBUTE")
    private String REIMBURSEMENT_ATTRIBUTE;          //operation_mcc_code

    @Column(name = "TRN_CODE_1")
    private String TRN_CODE_1;              // operation_rrn

    @Column(name = "TRN_CODE_QUALIFIER_1")
    private String TRN_CODE_QUALIFIER_1;        //operation_currency3

    @Column(name = "TRN_COMPONENT_SEQ_NUM_1")
    private String TRN_COMPONENT_SEQ_NUM_1;          //operation_cnv_date

    @Column(name = "DESTINATION_BIN_1")
    private String DESTINATION_BIN_1;          //operation_country

    @Column(name = "SOURCE_BIN_1")
    private String SOURCE_BIN_1;             //operation_time

    @Column(name = "REPORT_IDENTIFIER_1")
    private String REPORT_IDENTIFIER_1;          //operation_mcc_code

    @Column(name = "REPORT_LINE_SEQ_NUM_1")
    private String REPORT_LINE_SEQ_NUM_1;              // operation_rrn

    @Column(name = "ACQUIRER_BIN_1")
    private String ACQUIRER_BIN_1;        //operation_currency3

    @Column(name = "RECORD_IDENTIFIER_1")
    private String RECORD_IDENTIFIER_1;          //operation_cnv_date

    @Column(name = "DESTINATION_AMOUNT")
    private Double DESTINATION_AMOUNT;          //operation_country

    @Column(name = "DESTINATION_CCY_CODE")
    private String DESTINATION_CCY_CODE;             //operation_time

    @Column(name = "SOURCE_AMOUNT")
    private Double SOURCE_AMOUNT;          //operation_mcc_code

    @Column(name = "SOURCE_CCY_CODE")
    private String SOURCE_CCY_CODE;              // operation_rrn

    @Column(name = "CARD_ACCEPTOR_IDENTIF_CODE")
    private String CARD_ACCEPTOR_IDENTIF_CODE;              // operation_rrn

    @Column(name = "MERCHANT_CATEGORY_CODE")
    private String MERCHANT_CATEGORY_CODE;        //operation_currency3

    @Column(name = "USAGE_CODE")
    private String USAGE_CODE;          //operation_cnv_date

    @Column(name = "SETTLEMENT_FLAG")
    private String SETTLEMENT_FLAG;          //operation_country

    @Column(name = "AUTH_CHARACTERISTICS_IND")
    private String AUTH_CHARACTERISTICS_IND;             //operation_time

    @Column(name = "AUTHORIZATION_CODE")
    private String AUTHORIZATION_CODE;          //operation_mcc_code

    @Column(name = "POS_TERMINAL_CAPABILITY")
    private String POS_TERMINAL_CAPABILITY;              // operation_rrn

    @Column(name = "CARDHOLDER_ID_METHOD")
    private String CARDHOLDER_ID_METHOD;              // operation_rrn

    @Column(name = "POS_ENTRY_MODE")
    private String POS_ENTRY_MODE;        //operation_currency3

    @Column(name = "CENTRAL_PROCESSING_DATE")
    private String CENTRAL_PROCESSING_DATE;          //operation_cnv_date

    @Column(name = "REIMBURSEMENT_ATTRB_CLEARED")
    private String REIMBURSEMENT_ATTRB_CLEARED;          //operation_country

    @Column(name = "PAYMENT_INDICATOR")
    private String PAYMENT_INDICATOR;             //operation_time

    @Column(name = "CASHBACK_AMOUNT")
    private Double CASHBACK_AMOUNT;          //operation_mcc_code

    @Column(name = "FEE_PROGRAM_INDICATOR")
    private String FEE_PROGRAM_INDICATOR;              // operation_rrn

    @Column(name = "AUTHORIZED_AMOUNT")
    private Double AUTHORIZED_AMOUNT;          //operation_cnv_date

    @Column(name = "INTERCHANGE_FEE_AMOUNT")
    private Double INTERCHANGE_FEE_AMOUNT;          //operation_country

    @Column(name = "INTERCHANGE_FEE_SIGN")
    private String INTERCHANGE_FEE_SIGN;             //operation_time

    @Column(name = "ISSUER_COUNTRY_CODE")
    private String ISSUER_COUNTRY_CODE;          //operation_mcc_code

    @Column(name = "RESERVED_1")
    private String RESERVED_1;              // operation_rrn

    @Column(name = "REIMBURSEMENT_ATTRIBUTE_1")
    private String REIMBURSEMENT_ATTRIBUTE_1;          //operation_cnv_date

    @Column(name = "FILE_NAME")
    private String FILE_NAME;          //operation_cnv_date

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
