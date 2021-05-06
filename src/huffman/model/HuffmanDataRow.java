package huffman.model;

public class HuffmanDataRow {
    private final String character;
    private final int frequency;
    private final String huffCode;

    public HuffmanDataRow(String character, int frequency, String huffCode) {
        this.character = character;
        this.frequency = frequency;
        this.huffCode = huffCode;
    }

    public String getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getHuffCode() {
        return huffCode;
    }
}
