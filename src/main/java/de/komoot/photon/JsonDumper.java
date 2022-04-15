package de.komoot.photon;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.Strings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * useful to create json files that can be used for fast re imports
 *
 * @author christoph
 */
@Slf4j
public class JsonDumper implements Importer {
    private PrintWriter writer;
    private final String[] languages;
    private final String[] extraTags;

    public JsonDumper(String filename, String[] languages, String[] extraTags) throws FileNotFoundException {
        this.writer = new PrintWriter(filename);
        this.languages = languages;
        this.extraTags = extraTags;
    }

    @Override
    public void add(PhotonDoc doc) {
        try {
            writer.println("{\"index\": {}}");
            writer.println(Strings.toString(Utils.convert(doc, languages, extraTags)));
        } catch (IOException e) {
            log.error("error writing json file", e);
        }
    }

    @Override
    public void finish() {
        if (writer != null) {
            writer.close();
        }
    }
}
