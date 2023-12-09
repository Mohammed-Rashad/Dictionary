class WordAlreadyExistsException extends RuntimeException {
    WordAlreadyExistsException() {
        super("Word already exist in the dictionary");
    }
}