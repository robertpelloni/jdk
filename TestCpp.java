import com.sun.tools.javac.parser.JavaTokenizer;
import com.sun.tools.javac.parser.ScannerFactory;
import com.sun.tools.javac.parser.Tokens;
import com.sun.tools.javac.util.Context;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCpp {
    public static void main(String[] args) throws Exception {
        Context context = new Context();
        ScannerFactory factory = ScannerFactory.instance(context);
        String content = Files.readString(Paths.get("sample.cpp"));
        CharBuffer buf = CharBuffer.wrap(content);

        // Reflection to access protected constructor or use factory if possible?
        // JavaTokenizer is protected. We need to use ScannerFactory.newScanner?
        // ScannerFactory.newScanner returns a Scanner (interface) or Lexer.

        // Actually, JavaTokenizer is public but constructor is protected?
        // Let's check JavaTokenizer.java.
        // Constructors are protected.
        // ScannerFactory.newScanner(CharSequence, boolean) returns Scanner.

        var scanner = factory.newScanner(buf, false);

        while (true) {
            scanner.nextToken();
            Tokens.Token token = scanner.token();
            System.out.println(token.kind + " : " + (token.name() != null ? token.name() : ""));
            if (token.kind == Tokens.TokenKind.EOF) break;
        }
    }
}
