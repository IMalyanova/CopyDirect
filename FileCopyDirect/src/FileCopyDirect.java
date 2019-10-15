import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyDirect {

    public static void main(String[] args) throws IOException {

        System.out.println("Введите адрес директории, которую необходимо скопировать: ");
        String adressCopyInput = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        File fileAdressCopy = new File(adressCopyInput);

        System.out.println("Введите адрес директории, в которую необходимо скопированый файл/папку вставить: ");
        String adressNewDirectInput = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        File fileadressNewDirectInput = new File(adressNewDirectInput);

        Files.copy(fileAdressCopy.toPath(), fileadressNewDirectInput.toPath(), StandardCopyOption.REPLACE_EXISTING);

        copyDirectFile(fileAdressCopy, fileadressNewDirectInput);
    }

    private static void copyDirectFile(File fileAdressCopy, File fileadressNewDirectInput) throws IOException {

        File[] listOfFiles = fileAdressCopy.listFiles();
        Path destDir = Paths.get(String.valueOf(fileadressNewDirectInput));

        for (File file : listOfFiles){
            Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);

            if (file.isDirectory()) {
                String pathInNewDirect = fileadressNewDirectInput + "\\"+ file.getName();
                File newFile = new File(pathInNewDirect);

                copyDirectFile(file, newFile);
            }

        }

    }
}

