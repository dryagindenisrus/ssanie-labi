import custom.input.FormattedInput;
import custom.input.EncodingConverter;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Object[] vals = FormattedInput.scanf("%d %s %c");
        for (Object val : vals) {
            System.out.print(val.getClass().getName() + " > ");
            System.out.println(val);
        }
        EncodingConverter.changeCharset(
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l6\\input.txt",
            "C:\\Users\\dryag\\Documents\\ssanie-labi\\java\\l6\\output.txt",
            "UTF-8",
            "US-ASCII"
        );
    }
}