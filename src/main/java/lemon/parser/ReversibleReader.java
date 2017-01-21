package lemon.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.Stack;

public class ReversibleReader extends Reader {
    private final Reader reader;
    private Stack<Character> unreadCharacters;

    public ReversibleReader(Reader reader) {
        this.reader = reader;
        this.unreadCharacters = new Stack<>();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if(!unreadCharacters.isEmpty()) {
            if(len < 1) {
                throw new RuntimeException("len must be at least 1");
            }

            cbuf[off] = unreadCharacters.pop();
            return 1;
        }

        return reader.read(cbuf, off, len);
    }

    public void unread(char ch) {
        unreadCharacters.add(ch);
    }

    public Character readChar() {
        try {
            int ch = this.read();

            if(ch == -1) {
                return null;
            }

            return (char) ch;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
