class WordNotFoundException extends RuntimeException {
    WordNotFoundException() {
        super("Word is not in the dictionary");
    }
}