import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "cli",
        description = "Vendure command line interface",
        subcommands = {CLI_List.class}
)
public class CLI implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Use a subcommand, e.g.: list --format table");
    }
}