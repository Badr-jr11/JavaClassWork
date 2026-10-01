import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

    public static ArrayList<RecIntegral> loadText(File file)
            throws IOException, InvalidRangeException {
        ArrayList<RecIntegral> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                if (temp.trim().isEmpty()) {
                    continue;
                }
                String[] part = temp.trim().split(" ");
                list.add(new RecIntegral(
                        Double.parseDouble(part[0]),
                        Double.parseDouble(part[1]),
                        Double.parseDouble(part[2]),
                        Double.parseDouble(part[3])));
            }
        }
        return list;
    }

    public static ArrayList<RecIntegral> loadJson(File file)
            throws IOException, InvalidRangeException {
        // 1) n9raw l-fichier kamel f String wa7da
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                sb.append(temp);
            }
        }

        // 2) kol { ... } howa objet wa7ed
        ArrayList<RecIntegral> list = new ArrayList<>();
        Matcher objects = Pattern.compile("\\{[^}]*\\}").matcher(sb.toString());
        while (objects.find()) {
            String obj = objects.group();
            list.add(new RecIntegral(
                    getValue(obj, "lowLim"),
                    getValue(obj, "upperLim"),
                    getValue(obj, "step"),
                    getValue(obj, "result")));
        }
        return list;
    }

    // kayjbed l-valeur dyal "key": 12.5 mn objet JSON
    private static double getValue(String obj, String key) {
        Matcher m = Pattern.compile("\"" + key + "\"\\s*:\\s*([-+0-9.eE]+)").matcher(obj);
        if (!m.find()) {
            throw new NumberFormatException("Missing field: " + key);
        }
        return Double.parseDouble(m.group(1));
    }
}