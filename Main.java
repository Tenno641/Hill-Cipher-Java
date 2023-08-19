public class Main {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final int MATRIX_SIZE = 4;

    private int[][] keyMatrix;
    private int[] messageMatrix;
    private String plaintext;

    public Main(String key) {
        this.keyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        this.messageMatrix = new int[MATRIX_SIZE];
        this.plaintext = "";

        if (key.length() != MATRIX_SIZE) {
            throw new IllegalArgumentException("The key must be 4 characters long.");
        }

        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                this.keyMatrix[i][j] = ALPHABET.indexOf(key.charAt(i * MATRIX_SIZE + j));
            }
        }
    }

    public void encrypt(String message) {
        if (message.length() % MATRIX_SIZE != 0) {
            throw new IllegalArgumentException("The message must be a multiple of 4 characters.");
        }

        for (int i = 0; i < message.length(); i++) {
            this.messageMatrix[i % MATRIX_SIZE] = ALPHABET.indexOf(message.charAt(i));
        }

        int[] ciphertextMatrix = new int[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                ciphertextMatrix[i] += this.keyMatrix[i][j] * this.messageMatrix[j];
            }
            ciphertextMatrix[i] %= 26;
        }

        this.plaintext = "";
        for (int i = 0; i < ciphertextMatrix.length; i++) {
            this.plaintext += ALPHABET.charAt(ciphertextMatrix[i]);
        }
    }

    public void decrypt() {
        int[][] inverseKeyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int determinant = this.keyMatrix[0][0] * this.keyMatrix[1][1] - this.keyMatrix[0][1] * this.keyMatrix[1][0];
        if (determinant == 0) {
            throw new ArithmeticException("The key matrix is not invertible.");
        }

        inverseKeyMatrix[0][0] = this.keyMatrix[1][1] % 26;
        inverseKeyMatrix[1][1] = this.keyMatrix[0][0] % 26;
        inverseKeyMatrix[0][1] = -this.keyMatrix[0][1] % 26;
        inverseKeyMatrix[1][0] = -this.keyMatrix[1][0] % 26;

        int[] plaintextMatrix = new int[MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                plaintextMatrix[i] += inverseKeyMatrix[i][j] * this.messageMatrix[j];
            }
            plaintextMatrix[i] %= 26;
        }

        this.plaintext = "";
        for (int i = 0; i < plaintextMatrix.length; i++) {
            this.plaintext += ALPHABET.charAt(plaintextMatrix[i]);
        }
    }

    public String getPlaintext() {
        return this.plaintext;
    }

    public static void main(String[] args) {

        Main main = new Main("test");
        main.encrypt("Ahmed");

    }

}
