package study.enumsetter.code1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TableStatusTest {
    @Test
    public void testTableStatus() {
        TableStatus origin = TableStatus.Y;

        String table1Value = origin.getTable1Value();
        boolean table2Value = origin.isTable2Value();

        assertThat(origin).isSameAs(TableStatus.Y);
        assertThat(table1Value).isSameAs("1");
        assertThat(table2Value).isSameAs(true);
    }
}