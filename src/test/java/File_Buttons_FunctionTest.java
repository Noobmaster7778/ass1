import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class File_Buttons_FunctionTest {

    @Test
    void openfile() {
        java.io.File file = new File("src\\main\\resources\\Test.txt");
        assertEquals("First,I will open the file.",File_Buttons_Function.openfile(file));
    }

    @Test
    void savefile() {
        String s1 = "to test save";
        Frame frame = null;
        java.io.File file = new File("src\\main\\resources\\Test1.txt");
        char[] chars = new char [(int)file.length()];
        String fileName = "src\\main\\resources\\Test1.txt";
        File_Buttons_Function.savefile(s1, frame, fileName);
        try {
            BufferedReader BR = new BufferedReader(new FileReader(file));
            BR.read(chars);
            BR.close();
        }
        catch(FileNotFoundException FE){
            System.out.println("file not found");
            System.exit(0);
        }
        catch(IOException IE){
            System.out.println("IO error");
            System.exit(0);
        }
        String s2 = new String (chars);
        assertEquals(s1,s2);
    }
}