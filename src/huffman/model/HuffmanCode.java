package huffman.model;


import java.util.*;

public class HuffmanCode {
    private final String textToCode;
    private final Map<Character, String> lettersWithCode;
    private String encodedText;
    private HuffmanNode root;

    public HuffmanCode(String textToCode) {
        this.textToCode = textToCode;
        this.lettersWithCode = new HashMap<>();
        this.encodedText = "";
        this.root = null;
        huffmanCoding();
    }

    public String getTextToCode() {
        return textToCode;
    }

    public Map<Character, String> getLettersWithCode() {
        return lettersWithCode;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public HuffmanNode getRoot() {
        return root;
    }


    private void mapLetters(HuffmanNode root, String s) {
        if (root.getLeftNode() == null && root.getRightNode() == null ) {
            lettersWithCode.put(root.getCharacter(), s);
            encodedText += s;
            return;
        }
        mapLetters(root.getLeftNode(), s + "0");
        mapLetters(root.getRightNode(), s + "1");
    }

    private HashMap<Character, Integer> countLetterFrequency(String text) {
        HashMap<Character, Integer> letterFrequency = new HashMap<>();
        char[] letters = text.toCharArray();
        for (char letter : letters) {
            if (letterFrequency.containsKey(letter)) {
                letterFrequency.put(letter, letterFrequency.get(letter) + 1);
            } else {
                letterFrequency.put(letter, 1);
            }
        }
        return letterFrequency;
    }

    private void huffmanCoding() {
        Map<Character, Integer> letterFrequency = countLetterFrequency(textToCode);
        PriorityQueue<HuffmanNode> tree = new PriorityQueue<>(letterFrequency.size(),
                Comparator.comparingInt(HuffmanNode::getFrequency));

        for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
            HuffmanNode huffmanNode = new HuffmanNode(entry.getKey(), entry.getValue());
            tree.add(huffmanNode);
        }

        if (tree.size() == 1) {
            root = new HuffmanNode(tree.peek().getCharacter(), tree.peek().getFrequency());
        }

        while (tree.size() > 1) {
            HuffmanNode firstChild = tree.poll();
            HuffmanNode secondChild = tree.poll();


            HuffmanNode parentNode = new HuffmanNode('-',
                    firstChild.getFrequency() + secondChild.getFrequency());
            parentNode.setLeftNode(firstChild);
            parentNode.setRightNode(secondChild);

            root = parentNode;

            tree.add(parentNode);
        }
        mapLetters(root, "");
    }

    private double log2(double x) {
        return (Math.log(x) / Math.log(2));
    }

    public double calculateEntropy() {
        Map<Character, Integer> letterFrequency = countLetterFrequency(textToCode);
        int size = textToCode.length();
        double entropy = 0;
        for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
            double p = (double) entry.getValue() / size;
            entropy += p * log2( 1 / p);
        }
        return entropy;
    }

    public double calculateAverageWordLength() {
        Map<Character, Integer> letterFrequency = countLetterFrequency(textToCode);
        int size = textToCode.length();
        double averageWordLength = 0;
        for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
            double p = (double) entry.getValue() / size;
            averageWordLength += p * lettersWithCode.get(entry.getKey()).length();
        }
        return averageWordLength;
    }
}
