import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class TypesTableTest extends BaseIOValidationTest {
  @Test
  public void testSolution1() {
    List<String> patterns = Arrays.asList(
      "^Byte\\s+-128\\s+127\\s+1$",
      "^Double\\s+4.9E-324\\s+1.7976931348623157E308\\s+8$",
      "^Long\\s+-9223372036854775808\\s+9223372036854775807\\s+8$",
      "^Integer\\s+-2147483648\\s+2147483647\\s+4$",
      "^Short\\s+-32768\\s+32767\\s+2$",
      "^Float\\s+1.4E-45\\s+3.4028235E38\\s+4$",
      ""
    );

    TypesTable.main(new String[] {});
    for (String pattern : patterns) {
      boolean found = Pattern.compile(pattern, Pattern.MULTILINE).matcher(output.toString()).find();
      assertTrue(TestMsg.of(1, "", String.format("Pattern was not found in output: %s", pattern)).toString(), found);
    }
  }
}