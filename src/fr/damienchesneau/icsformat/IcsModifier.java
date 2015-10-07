package fr.damienchesneau.icsformat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Damien Chesneau contact@damienchesneau.fr
 */
class IcsModifier {

    private static final String LOCATION = "Université Paris-Est Marne-la-Vallée, Boulevard Descartes, Champs-sur-Marne, France";
    private static final String LOCATION_LABEL = "LOCATION:";
    private static int i = 0;
    private final String inputFile;
    private final String outputFile;

    public IcsModifier(String inputFile, String outputFile) {
        this.inputFile = Objects.requireNonNull(inputFile);
        this.outputFile = Objects.requireNonNull(outputFile);
    }

    private List<String> getLines() throws IOException {
        Path file = Paths.get(inputFile);
        List<String> lines = Files.readAllLines(file);
        return lines;
    }

    private void writeInNewFile(String fileName, List<String> lines) throws IOException {
        Path p = Paths.get(fileName);
        Files.write(p, lines);
    }

    public void update() throws IOException {
        List<String> lines = getLines();
        List<String> locations = lines.stream()
                .filter((line) -> line.contains("LOCATION"))
                .collect(Collectors.<String>toList()).stream().map(line -> line.substring(LOCATION_LABEL.length()))
                .collect(Collectors.<String>toList());
        lines = lines.stream().map((line) -> {
            if (line.contains("LOCATION:")) {
                return "LOCATION:" + IcsModifier.LOCATION;
            }
            if (line.contains("DESCRIPTION:")) {
                String toret = line + ((locations.get(i).equals("")) ? "Pas de salle." : "\\nSalle: " + locations.get(i));
                i++;
                return toret;
            }
            return line;
        }).collect(Collectors.<String>toList());
        writeInNewFile(outputFile, lines);
    }
}
