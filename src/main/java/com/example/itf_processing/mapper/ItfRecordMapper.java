package com.example.itf_processing.mapper;

import com.example.itf_processing.dao.ItfRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class ItfRecordMapper {

    public static final ItfRecordMapper INSTANCE = Mappers.getMapper(ItfRecordMapper.class);

    public ItfRecordEntity toEntity(String row_part_1, String row_part_1_1, String row_part_2, String row_part_2_1, String fileName, String folderName, String filePath) {
        // Part 1
        String TRN_CODE = row_part_1.replaceAll("^\\s+", "").substring(0, 2);
        String TRN_CODE_QUALIFIER = row_part_1.replaceAll("^\\s+", "").substring(2, 3);
        String TRN_COMPONENT_SEQ_NUM = row_part_1.replaceAll("^\\s+", "").substring(3, 4);
        String DESTINATION_BIN = row_part_1.replaceAll("^\\s+", "").substring(4, 10);
        String SOURCE_BIN = row_part_1.replaceAll("^\\s+", "").substring(10, 16);
        String REPORT_IDENTIFIER = row_part_1.replaceAll("^\\s+", "").substring(16, 26);
        String REPORT_LINE_SEQ_NUM = row_part_1.replaceAll("^\\s+", "").substring(26, 34);
        String ACQUIRER_BIN = row_part_1.replaceAll("^\\s+", "").substring(34, 40);
        String RECORD_IDENTIFIER = row_part_1.replaceAll("^\\s+", "").substring(40, 45);
        String TRN_CODE_CLEARED = row_part_1.replaceAll("^\\s+", "").substring(45, 47);
        String TRN_CODE_QUALIFIER_CLEARED = row_part_1.replaceAll("^\\s+", "").substring(47, 48);
        String ACCOUNT_NUMBER = row_part_1.replaceAll("^\\s+", "").substring(48, 64);
        String ACCOUNT_NUMBER_EXTENSION = row_part_1.replaceAll("^\\s+", "").substring(64, 67);
        String POSITIVE_CARDH_AUTH_SERVC_IND = row_part_1.replaceAll("^\\s+", "").substring(67, 68);
        String ACQUIRER_REFERENCE_NUMBER = row_part_1.replaceAll("^\\s+", "").substring(68, 91);
        String ACQUIRERS_BUSINESS_ID = row_part_1.replaceAll("^\\s+", "").substring(91, 99);

        String CARD_ACCEPTOR_NAME = row_part_1.replaceAll("^\\s+", "").substring(99, 100) + row_part_1_1.substring(0, 24).trim();
        String PURCHASE_DATE = row_part_1_1.substring(24, 28);
        String ACQUIRER_IRF_DESCRIPTOR = row_part_1_1.substring(28, 44).trim();
        String RESERVED = row_part_1_1.substring(44, 67);
        String REIMBURSEMENT_ATTRIBUTE = row_part_1_1.substring(67, 68);

        // Part 2
        String TRN_CODE_1 = row_part_2.substring(0, 2);
        String TRN_CODE_QUALIFIER_1 = row_part_2.substring(2, 3);
        String TRN_COMPONENT_SEQ_NUM_1 = row_part_2.substring(3, 4);
        String DESTINATION_BIN_1 = row_part_2.substring(4, 10);
        String SOURCE_BIN_1 = row_part_2.substring(10, 16);
        String REPORT_IDENTIFIER_1 = row_part_2.substring(16, 26);
        String REPORT_LINE_SEQ_NUM_1 = row_part_2.substring(26, 34);
        String ACQUIRER_BIN_1 = row_part_2.substring(34, 40);
        String RECORD_IDENTIFIER_1 = row_part_2.substring(40, 45);
        Double DESTINATION_AMOUNT = Double.parseDouble(row_part_2.substring(45, 57));
        String DESTINATION_CCY_CODE = row_part_2.substring(57, 60);
        Double SOURCE_AMOUNT = Double.parseDouble(row_part_2.substring(60, 72));
        String SOURCE_CCY_CODE = row_part_2.substring(72, 75);
        String CARD_ACCEPTOR_IDENTIF_CODE = row_part_2.substring(75, 90);
        String MERCHANT_CATEGORY_CODE = row_part_2.substring(90, 94);
        String USAGE_CODE = row_part_2.substring(94, 95);
        String SETTLEMENT_FLAG = row_part_2.substring(95, 96);
        String AUTH_CHARACTERISTICS_IND = row_part_2.substring(96, 97);

        // Part 2 || Part 2.1
        String AUTHORIZATION_CODE = row_part_2.substring(97, 100) + row_part_2_1.substring(0, 3);

        // Part 2.1
        String POS_TERMINAL_CAPABILITY = row_part_2_1.substring(3, 4);
        String CARDHOLDER_ID_METHOD = row_part_2_1.substring(4, 5);
        String POS_ENTRY_MODE = row_part_2_1.substring(5, 7);
        String CENTRAL_PROCESSING_DATE = row_part_2_1.substring(7, 11);
        String REIMBURSEMENT_ATTRB_CLEARED = row_part_2_1.substring(11, 12);
        String PAYMENT_INDICATOR = row_part_2_1.substring(12, 13);
        Double CASHBACK_AMOUNT = Double.parseDouble(row_part_2_1.substring(13, 22));
        String FEE_PROGRAM_INDICATOR = row_part_2_1.substring(22, 25);
        Double AUTHORIZED_AMOUNT = Double.parseDouble(row_part_2_1.substring(25, 37));

        String extractedSubstring = row_part_2_1.substring(37, 52);

        Double INTERCHANGE_FEE_AMOUNT;

        if (extractedSubstring.contains("-")) {
            extractedSubstring = extractedSubstring.replace("-", "0");
            INTERCHANGE_FEE_AMOUNT = Double.parseDouble(extractedSubstring) * -1;
        } else {
            INTERCHANGE_FEE_AMOUNT = Double.parseDouble(extractedSubstring);
        }

        String INTERCHANGE_FEE_SIGN = row_part_2_1.substring(52, 53);
        String ISSUER_COUNTRY_CODE = row_part_2_1.substring(53, 56);
        String RESERVED_1 = row_part_2_1.substring(56, 67);
        String REIMBURSEMENT_ATTRIBUTE_1 = row_part_2_1.substring(67, 68);

        return ItfRecordEntity.builder()
                .TRN_CODE(TRN_CODE)
                .TRN_CODE_QUALIFIER(TRN_CODE_QUALIFIER)
                .TRN_COMPONENT_SEQ_NUM(TRN_COMPONENT_SEQ_NUM)
                .DESTINATION_BIN(DESTINATION_BIN)
                .SOURCE_BIN(SOURCE_BIN)
                .REPORT_IDENTIFIER(REPORT_IDENTIFIER)
                .REPORT_LINE_SEQ_NUM(REPORT_LINE_SEQ_NUM)
                .ACQUIRER_BIN(ACQUIRER_BIN)
                .RECORD_IDENTIFIER(RECORD_IDENTIFIER)
                .TRN_CODE_CLEARED(TRN_CODE_CLEARED)
                .TRN_CODE_QUALIFIER_CLEARED(TRN_CODE_QUALIFIER_CLEARED)
                .ACCOUNT_NUMBER(ACCOUNT_NUMBER)
                .ACCOUNT_NUMBER_EXTENSION(ACCOUNT_NUMBER_EXTENSION)
                .POSITIVE_CARDH_AUTH_SERVC_IND(POSITIVE_CARDH_AUTH_SERVC_IND)
                .ACQUIRER_REFERENCE_NUMBER(ACQUIRER_REFERENCE_NUMBER)
                .ACQUIRERS_BUSINESS_ID(ACQUIRERS_BUSINESS_ID)
                .CARD_ACCEPTOR_NAME(CARD_ACCEPTOR_NAME)
                .PURCHASE_DATE(PURCHASE_DATE)
                .ACQUIRER_IRF_DESCRIPTOR(ACQUIRER_IRF_DESCRIPTOR)
                .RESERVED(RESERVED)
                .REIMBURSEMENT_ATTRIBUTE(REIMBURSEMENT_ATTRIBUTE)
                .TRN_CODE_1(TRN_CODE_1)
                .TRN_CODE_QUALIFIER_1(TRN_CODE_QUALIFIER_1)
                .TRN_COMPONENT_SEQ_NUM_1(TRN_COMPONENT_SEQ_NUM_1)
                .DESTINATION_BIN_1(DESTINATION_BIN_1)
                .SOURCE_BIN_1(SOURCE_BIN_1)
                .REPORT_IDENTIFIER_1(REPORT_IDENTIFIER_1)
                .REPORT_LINE_SEQ_NUM_1(REPORT_LINE_SEQ_NUM_1)
                .ACQUIRER_BIN_1(ACQUIRER_BIN_1)
                .RECORD_IDENTIFIER_1(RECORD_IDENTIFIER_1)
                .DESTINATION_AMOUNT(DESTINATION_AMOUNT)
                .DESTINATION_CCY_CODE(DESTINATION_CCY_CODE)
                .SOURCE_AMOUNT(SOURCE_AMOUNT)
                .SOURCE_CCY_CODE(SOURCE_CCY_CODE)
                .CARD_ACCEPTOR_IDENTIF_CODE(CARD_ACCEPTOR_IDENTIF_CODE)
                .MERCHANT_CATEGORY_CODE(MERCHANT_CATEGORY_CODE)
                .USAGE_CODE(USAGE_CODE)
                .SETTLEMENT_FLAG(SETTLEMENT_FLAG)
                .AUTH_CHARACTERISTICS_IND(AUTH_CHARACTERISTICS_IND)
                .AUTHORIZATION_CODE(AUTHORIZATION_CODE)
                .POS_TERMINAL_CAPABILITY(POS_TERMINAL_CAPABILITY)
                .CARDHOLDER_ID_METHOD(CARDHOLDER_ID_METHOD)
                .POS_ENTRY_MODE(POS_ENTRY_MODE)
                .CENTRAL_PROCESSING_DATE(CENTRAL_PROCESSING_DATE)
                .REIMBURSEMENT_ATTRB_CLEARED(REIMBURSEMENT_ATTRB_CLEARED)
                .PAYMENT_INDICATOR(PAYMENT_INDICATOR)
                .CASHBACK_AMOUNT(CASHBACK_AMOUNT)
                .FEE_PROGRAM_INDICATOR(FEE_PROGRAM_INDICATOR)
                .AUTHORIZED_AMOUNT(AUTHORIZED_AMOUNT)
                .INTERCHANGE_FEE_AMOUNT(INTERCHANGE_FEE_AMOUNT)
                .INTERCHANGE_FEE_SIGN(INTERCHANGE_FEE_SIGN)
                .ISSUER_COUNTRY_CODE(ISSUER_COUNTRY_CODE)
                .RESERVED_1(RESERVED_1)
                .REIMBURSEMENT_ATTRIBUTE_1(REIMBURSEMENT_ATTRIBUTE_1)
                .FILE_NAME(fileName)
                .FOLDER_NAME(folderName)
                .FILE_PATH(filePath)
                .build();
    }
}
