import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Search_FunctionTest {

    @Test
    void search() {
        String all = "i will search it.";
        String s1 = "search";
        String s2 = "find";
        assertEquals("got it! ", Search_Function.search(all, s1));
        assertEquals("Cannot find it! ", Search_Function.search(all, s2));
    }
}