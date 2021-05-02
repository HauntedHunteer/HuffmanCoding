package huffman.model;

public class HuffmanDataRow {
    private final String character;
    private final String frequency;
    private final String huffCode;

    public HuffmanDataRow(String character, String frequency, String huffCode) {
        this.character = character;
        this.frequency = frequency;
        this.huffCode = huffCode;
    }

    public String getCharacter() {
        return character;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getHuffCode() {
        return huffCode;
    }
}
