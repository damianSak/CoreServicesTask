package bootcamptask.utils;

import com.opencsv.bean.BeanField;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
 * Custom mapping strategy used during process of saving data to CSV format - combination of ordering strategy with
 * proper names of headers.
 *
 * @param "T" class type which needs to be mapped
 */
public class CustomBeanToCSVMappingStrategy<T> extends ColumnPositionMappingStrategy<T> {

    @Override
    public String[] generateHeader(T bean) throws CsvRequiredFieldEmptyException {

        // header name based on field name
        String[] headersAsPerFieldName = getFieldMap().generateHeader(bean);

        String[] header = new String[headersAsPerFieldName.length];

        for (int i = 0; i <= headersAsPerFieldName.length - 1; i++) {

            BeanField beanField = findField(i);

            // header name based on @CsvBindByName annotation
            String columnHeaderName = extractHeaderName(beanField);

            // No @CsvBindByName is present
            if (columnHeaderName.isEmpty())
                // defaults to header name based on field name
                columnHeaderName = headersAsPerFieldName[i];
            header[i] = columnHeaderName;
        }

        headerIndex.initializeHeaderIndex(header);
        return header;
    }

    private String extractHeaderName(final BeanField beanField) {
        if (beanField == null || beanField.getField() == null || beanField.getField()
                .getDeclaredAnnotationsByType(CsvBindByName.class).length == 0) {
            return "";
        }

        final CsvBindByName bindByNameAnnotation = beanField.getField()
                .getDeclaredAnnotationsByType(CsvBindByName.class)[0];
        return bindByNameAnnotation.column();
    }
}
