package bootcamptask.utils.converters;

import org.junit.jupiter.api.BeforeEach;

class CategoryColumnCsvConverterTest {

    private CategoryColumnCsvConverter categoryColumnCsvConverter;

    @BeforeEach
    void init(){
        this.categoryColumnCsvConverter=new CategoryColumnCsvConverter();
    }

    /*@Test
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

    }*/



}