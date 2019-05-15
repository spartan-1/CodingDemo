package au.com.pnr.codingdemo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The type Rest service test helper.
 */
public class RestServiceTestHelper {
    /**
     * Convert stream to string string.
     *
     * @param inputStream the inputStream
     * @return the string
     * @throws Exception the exception
     */
    public static String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        reader.close();
        return stringBuilder.toString();
    }

    /**
     * Gets string from file.
     *
     * @param context  the context
     * @param filePath the file path
     * @return the string from file
     * @throws Exception the exception
     */
    public static String getStringFromFile(Context context, String filePath) throws Exception {
        final InputStream stream = context.getResources().getAssets().open(filePath);

        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}
