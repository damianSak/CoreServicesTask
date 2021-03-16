package bootcamp_task.utils;

import bootcamp_task.model.Genre;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CategoryColumnCsvConverter extends AbstractBeanField<String, Genre> {

    @Override
    protected Genre convert(String valueFromCsv) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return Genre.findByName(valueFromCsv);
    }
}
