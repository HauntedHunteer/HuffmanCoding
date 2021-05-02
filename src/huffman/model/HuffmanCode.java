package huffman.model;


import java.util.*;
// todo describe code here and in other files
public class HuffmanCode {
    private final String textToCode;
    private final Map<Character, String> lettersWithCode; // todo letters -> characters
    private final Map<Character, Integer> characterWithFrequency;
    private String encodedText;
    private HuffmanNode root;

    public HuffmanCode(String textToCode) {
        this.textToCode = textToCode;
        this.lettersWithCode = new HashMap<>();
        this.characterWithFrequency = countLetterFrequency(textToCode);
        this.encodedText = "";
        this.root = null;
        huffmanCoding();
    }

    public String getTextToCode() {
        return textToCode;
    }

    public Map<Character, String> getLettersWithCode() {
        return lettersWithCode;
    } // todo refactor letter name -> inaccurate

    public Map<Character, Integer> getCharacterWithFrequency() {
        return characterWithFrequency;
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
        PriorityQueue<HuffmanNode> tree = new PriorityQueue<>(characterWithFrequency.size(),
                Comparator.comparingInt(HuffmanNode::getFrequency));

        for (Map.Entry<Character, Integer> entry : characterWithFrequency.entrySet()) {
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
        System.out.println("Huffman code calculated");
    }

    private double log2(double x) {
        return (Math.log(x) / Math.log(2));
    }

    public double calculateEntropy() {
        int size = textToCode.length();
        double entropy = 0;
        for (Map.Entry<Character, Integer> entry : characterWithFrequency.entrySet()) {
            double p = (double) entry.getValue() / size;
            entropy += p * log2( 1 / p);
        }
        System.out.println("Entropy calculated");
        return entropy;
    }

    public double calculateAverageWordLength() {
        int size = textToCode.length();
        double averageWordLength = 0;
        for (Map.Entry<Character, Integer> entry : characterWithFrequency.entrySet()) {
            double p = (double) entry.getValue() / size;
            averageWordLength += p * lettersWithCode.get(entry.getKey()).length();
        }
        System.out.println("Average Word Length calculated");
        return averageWordLength;
    }
}
