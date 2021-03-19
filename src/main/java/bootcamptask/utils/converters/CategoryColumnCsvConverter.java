package bootcamptask.utils.converters;

import bootcamptask.utils.parsers.GenreUtils;
import com.opencsv.bean.AbstractBeanField;

public class CategoryColumnCsvConverter<T, I> extends AbstractBeanField<T, I> {

    public CategoryColumnCsvConverter() {}

    @Override
    protected Object convert(String valueFromCsv) {
        return GenreUtils.findByNameCaseInsensitive(valueFromCsv);
    }
}
