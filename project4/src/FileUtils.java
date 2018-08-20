import java.io.*;
import java.util.List;

/**
 * Created by hexu1 on 4/6/2017.
 */

public class FileUtils {
    public FileUtils() {
    }

    public boolean saveWordTable(List<Word> wordTable, String filePath) {
        if (filePath == null || wordTable == null || filePath.equals(""))
            return false;

        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(wordTable);
            if (oos != null)
                return true;

            fos.close();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean savePageTable(List<Page> pageTable, String filePath) {
        if (filePath == null || pageTable == null || filePath.equals(""))
            return false;

        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(pageTable);
            if (oos != null)
                return true;

            fos.close();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public List<Word> getWordList(String filePath){
        if (filePath == null || filePath.equals(""))
            return null;
        try {
                FileInputStream fis = new FileInputStream(new File(filePath));
                ObjectInputStream ois = new ObjectInputStream(fis);

                List<Word> word = (List<Word>) ois.readObject();
                ois.close();
                fis.close();

                return word;
        } catch(Exception e){e.printStackTrace();}

        return null;
    }

    public List<Page> getPageList(String filePath) throws Exception {
        if (filePath == null || filePath.equals(""))
            return null;
        else {
            try {
                FileInputStream fis = new FileInputStream(new File(filePath));
                ObjectInputStream ois = new ObjectInputStream(fis);

                List<Page> page = (List<Page>) ois.readObject();
                ois.close();
                fis.close();

                return page;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
