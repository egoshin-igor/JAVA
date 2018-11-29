import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PasswordGeneratorTest extends BaseIOValidationTest {

  private static final String WRONG_ARGUMENTS = "Wrong arguments";
  private static final String WRONG_OUTPUT = "Wrong output";

  @Test
  public void testWrongArguments() {
    String[] argsSet = {
      "",
      "1 2 3",
      "10",
      "-5 abdefg123456_",
      "999999999999999999999 afdsfsafd23421412"
    };

    for (String args : argsSet) {
      PasswordGenerator.main(args.split(" "));
      assertEquals(TestMsg.of(1, args, WRONG_ARGUMENTS).toString(), WRONG_ARGUMENTS + "\r\n", output.toString());
      resetOutput();
    }
  }

  @Test
  public void testSolution1() {
    Set<String> uniquePasswords = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      PasswordGenerator.main(new String[]{"100", "afdsfsafd2342141asdfcdhasbgghgxty23u4f 1rcx 32671xxy2319crweaecfljsgyc4[]{}[d]sa2"});
      uniquePasswords.add(output.toString());
      resetOutput();
    }
    assertEquals("Test #2. Weak randomization", 10, uniquePasswords.size());
  }

  @Test
  public void testSolution2() {
    String[][] argsResSet = {
      {"5 a", "aaaaa"},
      {"5 aaa", "aaaaa"}
    };

    for (String[] argsRes : argsResSet) {
      PasswordGenerator.main(argsRes[0].split(" "));
      assertEquals(TestMsg.of(2, argsRes, WRONG_OUTPUT).toString(), argsRes[1] + "\r\n", output.toString());
      resetOutput();
    }
  }

  @Test
  public void testSolution3() {
    String[] args = {"12", "abc"};
    PasswordGenerator.main(args);
    assertEquals(TestMsg.of(3, args, "Password length mismatch").toString(), 12, output.toString().trim().length());
  }

  @Test
  public void testSolution4() {
    String[] args = {"1", "abc"};
    PasswordGenerator.main(args);
    assertEquals(TestMsg.of(4, args, "Password length mismatch").toString(), 1, output.toString().trim().length());
  }
}