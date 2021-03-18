package bootcamp_task.utils.converters;

import bootcamp_task.model.Genre;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static bootcamp_task.model.Genre.*;
import static org.assertj.core.api.Assertions.assertThat;

class CategoryColumnCsvConverterTest {

    private CategoryColumnCsvConverter categoryColumnCsvConverter;

    @BeforeEach
    void init(){
        this.categoryColumnCsvConverter=new CategoryColumnCsvConverter();
    }

    @Test
    void convert_should_returnCorrectEnum_when_properEnumValueGiven() {
        //given
        String value = "R&B";
        Genre result=Alternative;

        //when
        try {
            result = categoryColumnCsvConverter.convert(value);
        } catch (CsvDataTypeMismatchException | CsvConstraintViolationException e) {
            e.printStackTrace();
        }

        //then
        assertThat(R_AND_B).isEqualTo(result);

    }

    @Test
    void convert_shouldNot_returnCorrectEnum_when_differentEnumValueGiven() {
        //given
        String value = "R&B";
        Genre result=Alternative;

        //when
        try {
            result = categoryColumnCsvConverter.convert(value);
        } catch (CsvDataTypeMismatchException | CsvConstraintViolationException e) {
            e.printStackTrace();
        }
        //then
        assertThat(Rock).isNotEqualByComparingTo(result);

    }

    @Test
    void convert_should_CastException_when_wrongEnumValueGiven() {
        //given
        String value = "Pop";
        Genre expected = Rock;
        Genre result=Alternative;

        //when
        try {
            result = categoryColumnCsvConverter.convert(value);
        } catch (CsvDataTypeMismatchException | CsvConstraintViolationException e) {
            e.printStackTrace();
        }
        assertThat(result).isNull();

    }



}