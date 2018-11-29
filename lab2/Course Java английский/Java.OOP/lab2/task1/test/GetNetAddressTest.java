import org.junit.Test;

import java.security.Permission;

import static org.junit.Assert.assertEquals;

public class GetNetAddressTest extends BaseIOValidationTest {

  private static final String MSG_ARGUMENTS_NUM_INCORRECT = "If incorrect number number of arguments usage info should be printed";
  private static final String WRONG_IP_ADDRESS = "Wrong ip address";
  private static final String WRONG_MASK = "Wrong mask";
  private static final String WRONG_OUTPUT = "Wrong output";

  private static final String USAGE_INFO = "GetNetAddress <IP address> <Subnet mask>\r\n";

  @Test
  public void testNoArguments() {
    GetNetAddress.main(new String[]{});
    assertEquals(TestMsg.of(1, "", MSG_ARGUMENTS_NUM_INCORRECT).toString(), USAGE_INFO, output.toString());
  }

  @Test
  public void testOneArgument() {
    String[] args = {"192.168.1.2"};
    GetNetAddress.main(args);
    assertEquals(TestMsg.of(2, args, MSG_ARGUMENTS_NUM_INCORRECT).toString(), USAGE_INFO, output.toString());
  }

  @Test
  public void testThreeArgument() {
    String[] args = {"192.168.1.2", "255.255.254.0", "192.168.0.0"};
    GetNetAddress.main(args);
    assertEquals(TestMsg.of(3, args, MSG_ARGUMENTS_NUM_INCORRECT).toString(), USAGE_INFO, output.toString());
  }

  @Test
  public void testWrongIpArgument() {
    String[][] argsSet = {
            {"256.168.1.2", "255.255.254.0"},
            {"999999999999999999999.168.1.2", "255.255.254.0"},
            {"255.-1.1.2", "255.255.254.0"},
            {"111.168.1", "255.255.254.0"},
            {"111.168.1..", "255.255.254.0"},
            {"111.168.1.abc.", "255.255.254.0"},
            {"111.-168.1.2.", "255.255.254.0"}
    };

    for (String[] args : argsSet) {
      GetNetAddress.main(args);
      assertEquals(TestMsg.of(4, args, WRONG_IP_ADDRESS).toString(), WRONG_IP_ADDRESS + "\r\n" , output.toString());
      resetOutput();
    }
  }

  @Test
  public void testWrongMaskArgument() {
    String[][] argsSet = {
            {"255.255.254.0", "256.255.255.255"},
            {"255.255.254.0", "999999999999999999999.255.255.255"},
            {"255.255.254.0", "255.-0.0.0"},
            {"255.255.254.0", "255.255.255"},
            {"255.255.254.0", "255.255.255.." },
            {"255.255.254.0", "255.255.255.abc"},
            {"255.255.254.0", "255.255.255.255."},
            {"255.255.254.0", "127.0.0.0"},
            {"255.255.254.0", "255.255.255.253"},
            {"255.255.254.0", "255.255.190.000"}
    };

    for (String[] args : argsSet) {
      GetNetAddress.main(args);
      assertEquals(TestMsg.of(5, args, WRONG_MASK).toString(), WRONG_MASK + "\r\n", output.toString());
      resetOutput();
    }
  }

  @Test
  public void testSolution() {
    String[][] argsAndResultSet = {
            {"192.168.1.2", "255.255.254.0", "192.168.0.0"},
            {"192.0.2.130", "255.255.255.0", "192.0.2.0"},
            {"192.0.2.130", "255.255.255.192", "192.0.2.128"},
            {"192.168.0.1", "255.255.255.0", "192.168.0.0"},
            {"200.200.200.200", "128.0.0.0", "128.0.0.0"},
            {"0.0.0.0", "255.255.255.128", "0.0.0.0"},
            {"91.174.148.74", "255.255.192.0", "91.174.128.0"}
    };

    for (String[] argsRes : argsAndResultSet) {
      String[] args = {argsRes[0], argsRes[1]};
      GetNetAddress.main(args);
      assertEquals(TestMsg.of(6, args, WRONG_OUTPUT).toString(), argsRes[2] + "\r\n", output.toString());
      resetOutput();
    }
  }
}