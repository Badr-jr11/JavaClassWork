import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {

    // Text: kol objet f ster -> "lowLim upperLim step result"
    public static void saveText(File file, ArrayList<RecIntegral> list) throws IOException {
        try (FileWriter writer = new FileWriter(file, false)) {
            for (RecIntegral o : list) {
                writer.write(o.getLowLim() + " " +
                             o.getUpperLim() + " " +
                             o.getStep() + " " +
                             o.getResult() + "\n");
            }
            writer.flush();
        }
    }

    // JSON: tableau dyal les objets
    public static void saveJson(File file, ArrayList<RecIntegral> list) throws IOException {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("[\n");
            for (int i = 0; i < list.size(); i++) {
                RecIntegral o = list.get(i);
                writer.write("  {\"lowLim\": " + o.getLowLim() +
                             ", \"upperLim\": " + o.getUpperLim() +
                             ", \"step\": " + o.getStep() +
                             ", \"result\": " + o.getResult() + "}");
                if (i < list.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]\n");
            writer.flush();
        }
    }
}