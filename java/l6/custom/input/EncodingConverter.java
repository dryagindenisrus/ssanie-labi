package custom.input;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingConverter {
    public static void changeCharset(String inputFile, String outputName, String charset1, String charset2) throws IOException {

        File fileInput = new File(inputFile);
        File fileOutput = new File(outputName);

        FileInputStream inputStream= new FileInputStream(fileInput);
        FileOutputStream outStream = new FileOutputStream(fileOutput);


        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();
        Charset fromCharset = Charset.forName(charset1);
        Charset toCharset = Charset.forName(charset2);
//        Charset toCharset = StandardCharsets.US_ASCII;

        String string = new String (buffer, fromCharset);

        outStream.write(string.getBytes(toCharset));
        outStream.close();
    }
}
